package com.example.onlineShop.dao.impl;

import com.example.onlineShop.dao.AvailableStoreDAO;
import com.example.onlineShop.dao.PhoneDAO;
import com.example.onlineShop.entity.PhoneEntity;
import com.example.onlineShop.model.Phone;
import com.example.onlineShop.service.AvailableStoreService;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Реализация интерфейса PhoneDAO.
 */
@Component
public class PhoneDAOImpl implements PhoneDAO {

  private final SessionFactory sessionFactory;

  private final AvailableStoreDAO availableStoreDAO;

  private final AvailableStoreService availableStoreService;

  /**
   * Конструктор класса.
   *
   * @param sessionFactory        Фабрика сессий Hibernate
   * @param availableStoreDAO     Объект доступа к данным о доступных магазинах
   * @param availableStoreService Сервис для работы с доступными магазинами
   */
  @Autowired
  public PhoneDAOImpl(SessionFactory sessionFactory, AvailableStoreDAO availableStoreDAO, AvailableStoreService availableStoreService) {
    this.sessionFactory = sessionFactory;
    this.availableStoreDAO = availableStoreDAO;
    this.availableStoreService = availableStoreService;
  }

  @Transactional
  @Override
  public void create(Phone phone) {
    Session session = sessionFactory.getCurrentSession();
    PhoneEntity phoneEntity = new PhoneEntity(phone.getModelPhone(), phone.getCost(), phone.getId());
    session.persist(phoneEntity);
    availableStoreDAO.create(phone, phoneEntity);
  }

  @Transactional
  @Override
  public void update(Phone phone) {
    Session session = sessionFactory.getCurrentSession();
    PhoneEntity phoneEntity = session.get(PhoneEntity.class, phone.getId());
    phoneEntity.setModel(phone.getModelPhone());
    phoneEntity.setCost(phone.getCost());
    availableStoreService.updateAvailableStores(session, phoneEntity, phone.getAvailableStores());
    session.merge(phoneEntity);
  }

  @Transactional
  @Override
  public List<PhoneEntity> get() {
    Session session = sessionFactory.getCurrentSession();
    List<PhoneEntity> phoneEntityList = session.createQuery("FROM PhoneEntity").getResultList();
    phoneEntityList.forEach(phoneEntity -> phoneEntity.setAvailableStoreEntities(availableStoreDAO.get(phoneEntity)));
    return phoneEntityList;
  }

  @Transactional
  @Override
  public void delete(int idPhone) {
    Session session = sessionFactory.getCurrentSession();
    PhoneEntity phoneEntity = session.get(PhoneEntity.class, idPhone);
    session.remove(phoneEntity);
  }
}
