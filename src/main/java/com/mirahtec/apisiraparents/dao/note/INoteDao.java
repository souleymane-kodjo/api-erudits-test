package com.mirahtec.apisiraparents.dao.note;

import com.mirahtec.apisiraparents.model.Note;

import java.util.List;

public interface INoteDao {
    public List<Note> getNotesByStudentMatricule(String matricule);
}
