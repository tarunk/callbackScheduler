package com.move.entities;

import com.move.models.CallBackRequest;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * this Entity is used to store request on H2 in-memory database
 */
@Entity
@Table(name = "request")
public class Request implements Comparable<Request>{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name="client_id")
    private String clientId;

    @Column(name="callback_url")
    private String callbackUrl;

    private String status;

    @Column(name="callback_time")
    private LocalDateTime callbackTime;

    @Column(name="create_time")
    @CreationTimestamp
    private LocalDateTime createTime;

    public Request() {

    }
    public Request(int id, String clientId, String callback_url, LocalDateTime callbackTime) {
        super();
        this.id = id;
        this.clientId = clientId;
        this.status = "PENDING";
        this.callbackUrl = callback_url;
        this.callbackTime = callbackTime;
    }
    public Request(String clientId, String callback_url, LocalDateTime callbackTime) {
        super();
        this.clientId = clientId;
        this.status = "PENDING";
        this.callbackUrl = callback_url;
        this.callbackTime = callbackTime;
    }

    public Request(CallBackRequest callBackRequest) {
        this.clientId = callBackRequest.getClientId();
        this.status = "PENDING";
        this.callbackUrl = callBackRequest.getCallbackUrl();
        this.callbackTime = callBackRequest.getCallbackTime();
    }
    public int getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getCallbackTime() {
        return callbackTime;
    }

    public void setCallbackTime(LocalDateTime callbackTime) {
        this.callbackTime = callbackTime;
    }

    @Override
    public int compareTo(Request request) {
        if (callbackTime.isBefore(request.getCallbackTime())) {
            return 1;
        } else if (callbackTime.isEqual(request.getCallbackTime())) {
            return 0;
        }
        return -1;
    }
}
