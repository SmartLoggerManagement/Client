package com.smartlogger.smartclient.entity;

import javax.validation.constraints.NotNull;

@Entity
@Table(name = "logs")
public class LogEntity {

    /**
     * Identifier of the table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    /**
     * Log content.
     */
    @Column(name = "log")
    @NotNull
    private String log;

    /**
     * Empty constructor.
     */
    public LogEntity() {}

    /**
     * Constructor used by client when he send log on Database by user interface.
     *
     * @param log
     */
    public LogEntity(String log) {
        this.log = log;
    }

    /**
     * Constructor used to build object retrieve from Database.
     *
     * @param id
     * @param log
     */
    public LogEntity(long id, String log) {
        this.id = id;
        this.log = log;
    }

    /**
     * Get id.
     *
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     * Set id.
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get log content.
     *
     * @return
     */
    public String getLog() {
        return log;
    }

    /**
     * Set log content.
     *
     * @param log
     */
    public void setLog(String log) {
        this.log = log;
    }

    @Override
    public String toString() {
        return "LogEntity{" +
                "id=" + id +
                ", log='" + log + '\'' +
                '}';
    }
}
