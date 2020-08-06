package com.move.scheduled;

import com.move.entities.Request;
import com.move.repositories.RequestRepository;
import com.move.services.CallbackManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * this thread manage first (0 - 12 hrs) request in priority queue
 */
public class ScheduledTask12 implements Runnable {
    Logger log = LoggerFactory.getLogger(ScheduledTask12.class);
    private CallbackManager callbackManager;
    private RequestRepository requestRepository;

    public ScheduledTask12(CallbackManager callbackManager, RequestRepository requestRepository) {
        this.callbackManager = callbackManager;
        this.requestRepository = requestRepository;
    }

    /**
     * check for data in queue and time and pull the data from queue and does callback.
     * not implemented httpclient in this POC
     */
    @Override
    public void run() {
        log.info("Started thread for queue 12");
        PriorityBlockingQueue<Request> pq = callbackManager.getPq0_12();
        LocalDateTime current = LocalDateTime.now();

        while(!pq.isEmpty() &&
                (pq.peek().getCallbackTime().isBefore(current) ||
                pq.peek().getCallbackTime().isEqual(current))) {
            Request request = pq.poll();
            request.setStatus("COMPLETED");
            log.info("Sending message: " + request.getCallbackUrl());
            requestRepository.update(request);
        }
    }
}
