package com.mirahtec.apisiraparents.dao.presence;

import com.mirahtec.apisiraparents.model.Presence;
import org.springframework.stereotype.Component;

@Component
public interface IPresenceDao {
    //TODO: Add methods to get presence of student by matricule
    public Presence getPresenceByMatricule(String matricule);
}
