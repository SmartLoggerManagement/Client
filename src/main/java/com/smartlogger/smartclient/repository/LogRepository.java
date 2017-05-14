package com.smartlogger.smartclient.repository;

import com.smartlogger.smartclient.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * @author Nicolas GILLE
 * @since SmartClient 1.0
 * @version 1.0
 */
@Transactional
public interface LogRepository extends JpaRepository<LogEntity, Long> {

    /**
     * Retrieve log by log content.
     *
     * @param log
     *  Log at search.
     * @return
     *  An instance of LogEntity.
     */
    LogEntity findByLog(String log);
}
