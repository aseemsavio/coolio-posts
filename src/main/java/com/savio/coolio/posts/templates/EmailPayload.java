package com.savio.coolio.posts.templates;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Aseem Savio
 * POC for Rabbit MQ introduction into the ecosystem
 */

public class EmailPayload {

    private String name;
    private String from;
    private String to;
    private String operation;
    private String yetAnotherPayload;
    private String subject;

    public EmailPayload() {
    }

    public EmailPayload(@JsonProperty("name") String name,
                        @JsonProperty("from") String from,
                        @JsonProperty("to") String to,
                        @JsonProperty("operation") String operation,
                        @JsonProperty("yetAnotherPayload") String yetAnotherPayload,
                        @JsonProperty("subject") String subject) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.operation = operation;
        this.yetAnotherPayload = yetAnotherPayload;
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getYetAnotherPayload() {
        return yetAnotherPayload;
    }

    public void setYetAnotherPayload(String yetAnotherPayload) {
        this.yetAnotherPayload = yetAnotherPayload;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "EmailPayload{" +
                "name='" + name + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", operation='" + operation + '\'' +
                ", yetAnotherPayload='" + yetAnotherPayload + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
