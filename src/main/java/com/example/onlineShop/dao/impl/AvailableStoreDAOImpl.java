package com.example.onlineShop.dao.impl;

import com.example.onlineShop.dao.AvailableStoreDAO;
import com.example.onlineShop.entity.AvailableStoreEntity;
import com.example.onlineShop.entity.PhoneEntity;
import com.example.onlineShop.model.Phone;
import com.example.onlineShop.service.LoggerService;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Реализация интерфейса AvailableStoreDAO.
 */
@Component
public class AvailableStoreDAOImpl implements AvailableStoreDAO {

  private final SessionFactory sessionFactory;

  private final LoggerService loggerService;

  /**
   * Конструктор класса.
   *
   * @param sessionFactory Фабрика сессий Hibernate
   * @param loggerService  Сервис логирования
   */
  @Autowired
  public AvailableStoreDAOImpl(SessionFactory sessionFactory, LoggerService loggerService) {
    this.sessionFactory = sessionFactory;
    this.loggerService = loggerService;
  }

  @Transactional
  @Override
  public void create(Phone phone, PhoneEntity phoneEntity) {
    Session session = sessionFactory.getCurrentSession();
    phone.getAvailableStores().forEach(store -> {
      AvailableStoreEntity availableStoreEntity = new AvailableStoreEntity(phoneEntity, store);
      session.persist(availableStoreEntity);
    });
  }

  @Override
  public void update(List<AvailableStoreEntity> updatedAvailableStores, PhoneEntity phoneEntity, Session session) {
    phoneEntity.getAvailableStoreEntities().stream()
      .filter(availableStore -> !updatedAvailableStores.contains(availableStore))
      .forEach(session::remove);
    phoneEntity.setAvailableStoreEntities(updatedAvailableStores);
  }

  @Override
  public List<AvailableStoreEntity> get(PhoneEntity phoneEntity) {
    Hibernate.initialize(phoneEntity.getAvailableStoreEntities());
    loggerService.getLoggerReadAvailableStoreEntity();
    return phoneEntity.getAvailableStoreEntities();
  }

}
