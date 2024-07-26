package com.mirahtec.apisiraparents.service;

import com.mirahtec.apisiraparents.dao.impl.TimeTableJDBCDao;
import com.mirahtec.apisiraparents.dto.TimeTableReponse;
import com.mirahtec.apisiraparents.model.TimeTable;
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
            log.info("TimeTableService called with day: {}", day);
            TimeTableReponse timeTableReponse = timeTableJDBCDao.getSubjectTimeTable(classId, day);
            log.info("TimeTableService called with day: {}", timeTableReponse);
            return timeTableReponse;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<TimeTableReponse> getTimetableByWeek(String classId) {
        try {
            log.info("TimeTableService called with week: {}");
            List<TimeTableReponse> timeTableReponse = List.of(
                    timeTableJDBCDao.getSubjectTimeTable(classId, "LUNDI"),
                    timeTableJDBCDao.getSubjectTimeTable(classId, "MARDI"),
                    timeTableJDBCDao.getSubjectTimeTable(classId, "MERCREDI"),
                    timeTableJDBCDao.getSubjectTimeTable(classId, "JEUDI"),
                    timeTableJDBCDao.getSubjectTimeTable(classId, "VENDREDI"),
                    timeTableJDBCDao.getSubjectTimeTable(classId, "SAMEDI"),
                    timeTableJDBCDao.getSubjectTimeTable(classId, "DIMANCHE")

            );

            log.info("TimeTableService called with day: {}", timeTableReponse);
            return timeTableReponse;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
