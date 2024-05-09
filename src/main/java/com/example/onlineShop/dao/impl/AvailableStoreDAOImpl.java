package com.example.onlineShop.dao.impl;

import com.example.onlineShop.dao.AvailableStoreDAO;
import com.example.onlineShop.entity.AvailableStoreEntity;
import com.example.onlineShop.entity.PhoneEntity;
import com.example.onlineShop.model.Phone;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AvailableStoreDAOImpl implements AvailableStoreDAO {

  private final SessionFactory sessionFactory;

  @Autowired
  public AvailableStoreDAOImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
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
  public void update(Phone phone) {

  }

  @Override
  public List<AvailableStoreEntity> get(PhoneEntity phoneEntity) {
    Hibernate.initialize(phoneEntity.getAvailableStoreEntities());
    return phoneEntity.getAvailableStoreEntities();
  }

}
