package com.example.sharath.designtest;

/**
 * Created by sharath on 14/3/18.
 */

public class Message {
    private String message, timeStamp, msgType;

    Message(String message, String timeStamp, String msgType) {
        this.message = message;
        this.timeStamp = timeStamp;
        this.msgType = msgType;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getMsgType() {
        return msgType;
    }
}
