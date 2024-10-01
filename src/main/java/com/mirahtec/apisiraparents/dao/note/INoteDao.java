package com.mirahtec.apisiraparents.dao;

import com.mirahtec.apisiraparents.model.Note;

import java.util.List;

public interface INoteDao {
    public List<Note> getNotesByStudentMatricule(String matricule);
}
