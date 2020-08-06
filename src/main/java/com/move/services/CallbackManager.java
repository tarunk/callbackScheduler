package com.move.services;

import com.move.entities.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.PriorityBlockingQueue;

@Service
public class CallbackManager {
    Logger log = LoggerFactory.getLogger(CallbackManager.class);
    /**
     * Divided queue in two section (12 hours basis)
     */
    private PriorityBlockingQueue<Request> pq0_12;
    private PriorityBlockingQueue<Request> pq12_24;

    /**
     * this service is for adding request to priority queue
     */
    public CallbackManager() {
        log.info("CTO'R of Callback Manager");
        pq0_12 = new PriorityBlockingQueue<>();
        pq12_24 = new PriorityBlockingQueue<>();
    }
    /**
     * Adds Request based on hours of Local Date Time
     */
    public void add(Request request) {
        if (request.getCallbackTime().getHour() >= 0 &&
                request.getCallbackTime().getHour() < 12) {
            log.info("adding request to 12, " + request.getId());
            pq0_12.add(request);
        } else {
            log.info("adding request to 24, " + request.getId());
            pq12_24.add(request);
        }
    }

    public PriorityBlockingQueue<Request> getPq0_12() {
        log.info("calling get of pq12");
        return pq0_12;
    }

    public PriorityBlockingQueue<Request> getPq12_24() {
        log.info("calling get of pq24");
        return pq12_24;
    }
}
