package com.project.automationplatform.models;

public class ExceptionResponse {

    private String summary;
    private String message;
    private long timestamp;

    // Constructors
    public ExceptionResponse() {
        // Default constructor
    }

    public ExceptionResponse(String summary, String message, long timestamp) {
        this.summary = summary;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
