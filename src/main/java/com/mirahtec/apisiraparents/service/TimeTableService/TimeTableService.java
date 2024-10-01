package com.mirahtec.apisiraparents.service.TimeTableService;

import com.mirahtec.apisiraparents.dao.timetable.TimeTableJDBCDao;
import com.mirahtec.apisiraparents.dto.TimeTableReponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class  TimeTableService {
    @Autowired
    private TimeTableJDBCDao timeTableJDBCDao;
    public TimeTableReponse getTimetable(String  classId, String day) {
        try {
            return timeTableJDBCDao.getSubjectTimeTable(classId, day);
        }catch (Exception e){
            log.error("Erreur de récupération des emplois du temps", e);
            return null;
        }
    }

    public List<TimeTableReponse> getTimetableByWeek(String classId) {
        try {
            return List.of(
                    timeTableJDBCDao.getSubjectTimeTable(classId, "LUNDI"),
                    timeTableJDBCDao.getSubjectTimeTable(classId, "MARDI"),
                    timeTableJDBCDao.getSubjectTimeTable(classId, "MERCREDI"),
                    timeTableJDBCDao.getSubjectTimeTable(classId, "JEUDI"),
                    timeTableJDBCDao.getSubjectTimeTable(classId, "VENDREDI"),
                    timeTableJDBCDao.getSubjectTimeTable(classId, "SAMEDI"),
                    timeTableJDBCDao.getSubjectTimeTable(classId, "DIMANCHE")

            );
        }catch (Exception e){
            log.error("Erreur de récupération d'emploi du temps", e);
            return null;
        }
    }
}
