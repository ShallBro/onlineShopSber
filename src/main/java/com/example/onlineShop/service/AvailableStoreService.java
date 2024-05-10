package com.example.onlineShop.service;

import com.example.onlineShop.dao.AvailableStoreDAO;
import com.example.onlineShop.entity.AvailableStoreEntity;
import com.example.onlineShop.entity.PhoneEntity;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с доступными магазинами телефонов.
 */
@Service
public class AvailableStoreService {

  private final AvailableStoreDAO availableStoreDAO;

  private final LoggerService loggerService;

  /**
   * Конструктор класса.
   * @param availableStoreDAO Объект доступа к данным о доступных магазинах
   * @param loggerService Сервис логирования
   */
  @Autowired
  public AvailableStoreService(AvailableStoreDAO availableStoreDAO, LoggerService loggerService) {
    this.availableStoreDAO = availableStoreDAO;
    this.loggerService = loggerService;
  }

  /**
   * Обновить доступные магазины для указанного телефона.
   * @param session Сессия Hibernate для выполнения операций с базой данных
   * @param phoneEntity Объект типа PhoneEntity, представляющий сущность телефона
   * @param newAvailableStores Новый список доступных магазинов для телефона
   */
  public void updateAvailableStores(Session session, PhoneEntity phoneEntity, List<String> newAvailableStores) {
    List<AvailableStoreEntity> updatedAvailableStores = new ArrayList<>();
    newAvailableStores.forEach(store -> {
      AvailableStoreEntity existServer = findStore(phoneEntity.getAvailableStoreEntities(), store);
      if (existServer != null) {
        updatedAvailableStores.add(existServer);
      } else {
        AvailableStoreEntity newServer = new AvailableStoreEntity(phoneEntity, store);
        updatedAvailableStores.add(newServer);
      }
    });
    availableStoreDAO.update(updatedAvailableStores, phoneEntity, session);
    loggerService.getLoggerUpdateAvailableStoreEntity();
  }

  /**
   * Поиск доступного магазина в списке по его имени.
   * @param availableServers Список доступных магазинов
   * @param store Имя магазина для поиска
   * @return Объект типа AvailableStoreEntity, представляющий найденный магазин, или null, если магазин не найден
   */
  private AvailableStoreEntity findStore(List<AvailableStoreEntity> availableServers, String store) {
    return availableServers.stream()
      .filter(availableServer -> availableServer.getName().equals(store))
      .findFirst()
      .orElse(null);
  }


}
