package com.move.repositories;

import com.move.entities.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

/**
 * Encapsulate EntityManager which helps to save/update data to Request table
 */
@Repository
public class RequestRepository {
    Logger logger = LoggerFactory.getLogger(RequestRepository.class);
    @Autowired
    EntityManager em;

    @Transactional
    public Request save(Request request) {
        try {
            em.persist(request);
            logger.info("Added Request : " + request.getId());
        } catch (Exception e) {
            logger.debug(e.toString());
        }
        return request;
    }

    @Transactional
    public void update(Request request) {
        em.merge(request);
        logger.info("Update Request : " + request.getId());
    }
}
