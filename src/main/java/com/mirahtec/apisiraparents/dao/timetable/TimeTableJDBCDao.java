package com.mirahtec.apisiraparents.dao.timetable;

import com.mirahtec.apisiraparents.dto.TimeTableReponse;
import com.mirahtec.apisiraparents.model.Subject;
import com.mirahtec.apisiraparents.model.TimeTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
public class TimeTableJDBCDao implements ITimeTableDao {
    @Autowired
    private JdbcTemplate beanJDBCTemplate;

    @Override
    public List<TimeTable> getTimeTable(String className, String day) {
        if (className == null || day == null) {
            return null;
        }
        String sql = "SELECT * FROM classes inner join edts ON edts.id_classe=classes.idClasse INNER JOIN cours ON cours.id_cours=edts.id_cours inner join profs ON profs.matriculeProf=edts.matricule_prof WHERE edts.id_classe=? and edts.jour=? ";

        List<TimeTable> timeTables = beanJDBCTemplate.query(sql, new Object[]{className, day}, new BeanPropertyRowMapper<>(TimeTable.class));

        return timeTables;
    }


    public TimeTableReponse getSubjectTimeTable(String className, String day) {
        try {
            if (className == null || day == null) {
                return null;
            }
            String sql = "SELECT * FROM classes inner join edts ON edts.id_classe=classes.idClasse INNER JOIN cours ON cours.id_cours=edts.id_cours inner join profs ON profs.matriculeProf=edts.matricule_prof WHERE edts.id_classe=? and edts.jour=? ";
            //List<TimeTable> timeTables = beanJDBCTemplate.query(sql, new Object[]{className, day}, new BeanPropertyRowMapper<>(TimeTable.class));
            List<Subject> timeTables = beanJDBCTemplate.query(sql, new Object[]{className, day}, (rs, rowNum) -> {
                Subject subject = new Subject();
                subject.setId(rs.getLong("id_cours"));
                subject.setMatiere(rs.getString("nom_cours"));
                subject.setProf(rs.getString("prenom")+ " " + rs.getString("nom"));
                subject.setBgColor(rs.getString("bgColor"));
                subject.setTextColor(rs.getString("textColor"));
                subject.setIcone(rs.getString("nom_cours").toLowerCase(Locale.ROOT));
                subject.setHoraire(rs.getString("debut_cours") + "-" + rs.getString("fin_cours"));
                return subject;
            });
            TimeTableReponse timeTableReponse = new TimeTableReponse();
            timeTableReponse.setId(day.toLowerCase());
            timeTableReponse.setDayDate(0);
            timeTableReponse.setJour(day);
            timeTableReponse.setMatieres(timeTables);
            return timeTableReponse;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}