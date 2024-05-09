package com.example.onlineShop.dao.impl;

import static org.hibernate.Hibernate.initialize;

import com.example.onlineShop.dao.AvailableStoreDAO;
import com.example.onlineShop.dao.PhoneDAO;
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
public class PhoneDAOImpl implements PhoneDAO {

  private final SessionFactory sessionFactory;

  private final AvailableStoreDAO availableStoreDAO;

  @Autowired
  public PhoneDAOImpl(SessionFactory sessionFactory, AvailableStoreDAO availableStoreDAO) {
    this.sessionFactory = sessionFactory;
    this.availableStoreDAO = availableStoreDAO;
  }

  @Transactional
  @Override
  public void create(Phone phone) {
    Session session = sessionFactory.getCurrentSession();
    PhoneEntity phoneEntity = new PhoneEntity(phone.getModelPhone(), phone.getCost());
    session.persist(phoneEntity);
    availableStoreDAO.create(phone, phoneEntity);
  }

  @Override
  public void update(Phone phone) {

  }

  @Transactional
  @Override
  public List<PhoneEntity> get() {
    Session session = sessionFactory.getCurrentSession();
    List<PhoneEntity> phoneEntityList = session.createQuery("FROM PhoneEntity").getResultList();
    phoneEntityList.forEach(phoneEntity -> {
      phoneEntity.setAvailableStoreEntities(availableStoreDAO.get(phoneEntity));
    });
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
