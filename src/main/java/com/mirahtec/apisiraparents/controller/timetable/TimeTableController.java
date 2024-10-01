package com.mirahtec.apisiraparents.controller;

import com.mirahtec.apisiraparents.dto.DayRequest;
import com.mirahtec.apisiraparents.dto.TimeTableReponse;
import com.mirahtec.apisiraparents.model.TimeTable;
import com.mirahtec.apisiraparents.service.TimeTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/timetable")
@Slf4j
public class TimeTableController {

    @Autowired
    private TimeTableService timeTableService;

    @PostMapping("/day")
    public ResponseEntity<?> getTimetableByDay(@RequestBody DayRequest dayRequest) {
        if (dayRequest.getDay() == null) {
            return ResponseEntity.badRequest().build();
        }
        String day = dayRequest.getDay().toUpperCase();
        TimeTableReponse timetableEntries = timeTableService.getTimetable(dayRequest.getClassId(), day)  ;
        if (timetableEntries == null) {
            return ResponseEntity.ok(timetableEntries);
        }
        if (timetableEntries.getMatieres().isEmpty()) {
            HashMap<String, String> response = new HashMap<>();
            response.put("message", "No timetable found for classId: " + dayRequest.getClassId() + " and day: " + day);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    response
            );
        }
        timetableEntries = timetableEntries;
        return ResponseEntity.ok(timetableEntries);
    }
    @PostMapping("/week")
    public ResponseEntity<?> getTimetableByWeek(@RequestBody DayRequest dayRequest) {
        List<TimeTableReponse> timetableEntries = timeTableService.getTimetableByWeek(dayRequest.getClassId())  ;
        if (timetableEntries == null) {
            return ResponseEntity.ok(timetableEntries);
        }
        if (timetableEntries.isEmpty()) {
            HashMap<String, String> response = new HashMap<>();
            response.put("message", "No timetable found for classId: " + dayRequest.getClassId() + " and day: " + null);
            //No content
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    response
            );
        }
        return ResponseEntity.ok(timetableEntries);
    }
}