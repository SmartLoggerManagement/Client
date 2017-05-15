package com.smartlogger.smartclient.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "log")
public class LogEntity {
    // ATTRIBUTES
    /** Identification string of the given log */
    @Id
    @Column(name = "id")
    private String id;

    /**
     * The log's label : An indicator of how critical the situation is
     * for the app's state described by the log's content
     */
    @Column(name = "label")
    @NotNull
    private double label;

    /** The log's text content */
    @Column(name = "content", columnDefinition = "TEXT")
    @NotNull
    private String content;


    // CONSTRUCTORS
    public LogEntity() {}

    public LogEntity(String id, double label, String content) {
        this.id = id;
        this.label = label;
        this.content = content;
    }


    // REQUESTS
    /**
     * Return the log's id.
     */
    public String getId() {
        return id;
    }

    /**
     * Return the log's label.
     */
    public double getLabel() {
        return label;
    }

    /**
     * Return the log's content.
     */
    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "LogEntity{" +
                "id=" + getId() +
                ", label='" + getLabel() + '\'' +
                ", content='" + getContent() + '\'' +
                '}';
    }


    // COMMANDS
    /**
     * Sets a new value as the id.
     * @param id The new id to set at this log
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets a new label for this log.
     * @param label The new label's value
     */
    public void setLabel(double label) {
        this.label = label;
    }

    /**
     * Sets the log's content.
     * @param content The new content to set
     */
    public void setContent(String content) {
        this.content = content;
    }
}
