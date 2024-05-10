package com.example.onlineShop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Сервис для логирования действий в приложении.
 */
@Service
public class LoggerService {
  private static final Logger LOGGER = LoggerFactory.getLogger(LoggerService.class);

  /**
   * Логирует обновление записи о телефоне.
   */
  public void getLoggerUpdatePhoneEntity() {
    LOGGER.info("Entity Phone updated");
  }

  /**
   * Логирует обновление записи о доступном магазине.
   */
  public void getLoggerUpdateAvailableStoreEntity() {
    LOGGER.info("Entity AvailableStore updated");
  }

  /**
   * Логирует добавление новой записи о телефоне.
   */
  public void getLoggerAddPhoneEntity() {
    LOGGER.info("Entity Phone add new record");
  }

  /**
   * Логирует добавление новой записи о доступном магазине.
   */
  public void getLoggerAddAvailableStoreEntity() {
    LOGGER.info("Entity AvailableStoreEntity add new record");
  }

  /**
   * Логирует удаление записи о телефоне.
   */
  public void getLoggerDeletePhoneEntity() {
    LOGGER.info("Entity Phone deleted record");
  }

  /**
   * Логирует удаление записи о доступном магазине.
   */
  public void getLoggerDeleteAvailableStoreEntity() {
    LOGGER.info("Entity AvailableStoreEntity deleted record");
  }

  /**
   * Логирует чтение записи о телефоне.
   */
  public void getLoggerReadPhoneEntity() {
    LOGGER.info("Entity Phone was read");
  }

  /**
   * Логирует чтение записи о доступном магазине.
   */
  public void getLoggerReadAvailableStoreEntity() {
    LOGGER.info("Entity AvailableStoreEntity was read");
  }
}
