package com.move.endpoints;

import com.move.entities.Request;
import com.move.models.CallBackRequest;
import com.move.repositories.RequestRepository;
import com.move.services.CallbackManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *  - post call can be done with
 *  curl --location --request POST 'http://127.0.0.1:8080/move/request' \
 * --header 'Content-Type: application/json' \
 * --data-raw '{
 * 	"clientId":"tarun",
 * 	"callbackUrl":"https://google.com",
 * 	"callbackTime":"2020-08-07T00:46:20"
 * }'
 */
@RestController
@RequestMapping(path = "/move")
public class ApplicationController {
    @Autowired
    RequestRepository requestRepository;

    @Autowired
    CallbackManager callbackManager;

    @PostMapping(path = "/request", produces={"application/json", "text/xml"})
    ResponseEntity<?> setRequest(@RequestBody CallBackRequest callBackRequest) {
        try {
            Request request = requestRepository.save(new Request(callBackRequest));
            callbackManager.add(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(request);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
