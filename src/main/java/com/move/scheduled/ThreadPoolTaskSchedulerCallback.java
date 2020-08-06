package com.move.scheduled;

import com.move.repositories.RequestRepository;
import com.move.services.CallbackManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * here 5 threads managing two different Priority queue
 */
@Component
public class ThreadPoolTaskSchedulerCallback {
    Logger log = LoggerFactory.getLogger(ThreadPoolTaskSchedulerCallback.class);
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    @Autowired
    private CallbackManager callbackManager;
    @Autowired
    private RequestRepository requestRepository;

    @PostConstruct
    public void scheduleCallbackThread() {
        threadPoolTaskScheduler.scheduleAtFixedRate(new ScheduledTask12(callbackManager, requestRepository), 5000);
        threadPoolTaskScheduler.scheduleAtFixedRate(new ScheduledTask24(callbackManager, requestRepository), 5000);
    }
}
