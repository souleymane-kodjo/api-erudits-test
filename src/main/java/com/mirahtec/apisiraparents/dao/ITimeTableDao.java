package com.mirahtec.apisiraparents.dao;

import com.mirahtec.apisiraparents.model.TimeTable;

import java.util.List;

public interface ITimeTableDao {
    public List<TimeTable> getTimeTable(String className, String day);
}
