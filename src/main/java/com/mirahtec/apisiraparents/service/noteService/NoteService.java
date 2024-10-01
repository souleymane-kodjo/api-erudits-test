package com.mirahtec.apisiraparents.service;

import com.mirahtec.apisiraparents.dao.note.NoteJDBCDaoImpl;
import com.mirahtec.apisiraparents.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    @Autowired
    private NoteJDBCDaoImpl noteJDBCDao;
    public List<Note> getNotesByStudentMatricule(String matricule) {
        return noteJDBCDao.getNotesByStudentMatricule(matricule);
    }
}
