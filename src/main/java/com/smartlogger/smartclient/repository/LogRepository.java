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
public interface LogRepository extends JpaRepository<LogEntity, String> {
    /**
     * Retrieve a log by searching a similar content.
     *
     * @param content
     *  The content to search
     * @return
     *  An instance of LogEntity.
     */
    LogEntity findByContent(String content);
}
