package com.mirahtec.apisiraparents.service.parent;

import com.mirahtec.apisiraparents.dao.parent.ParentJDBCDaoImpl;
import com.mirahtec.apisiraparents.model.Parent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ParentService implements IParentService {
    @Autowired
    private ParentJDBCDaoImpl parentRepository;

    public Parent getParentByUsername(String parentUsername) {
        return parentRepository.findByUsername(parentUsername);
    }

    public String getMatriculeParentByParentUsername(String parentUsername) {
        try {
        String matricule = parentRepository.findMatriculeParentByUsername(parentUsername);
        log.info("Matricule parent: {}", matricule);
        return matricule;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error while fetching matricule for parent with username: {}", parentUsername);
            return null;
        }
    }
}
