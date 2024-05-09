package com.example.onlineShop.service;

import com.example.onlineShop.dao.PhoneDAO;
import com.example.onlineShop.dao.impl.PhoneDAOImpl;
import com.example.onlineShop.entity.PhoneEntity;
import com.example.onlineShop.model.Phone;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OnlineShopService {

  private final PhoneDAO phoneDAO;

  @Autowired
  public OnlineShopService(PhoneDAO phoneDAO) {
    this.phoneDAO = phoneDAO;
  }

  public List<Phone> allAvailablePhones(){
    List<PhoneEntity> phoneEntities = phoneDAO.get();
    List<Phone> phoneList = new ArrayList<>();
    phoneEntities.forEach(phoneEntity -> {
      Phone phone = new Phone();
      phone.setModelPhone(phoneEntity.getModel());
      List<String> availableStores = new ArrayList<>();
      phoneEntity.getAvailableStoreEntities().forEach(availableStoreEntity -> availableStores.add(availableStoreEntity.getName()));
      phone.setAvailableStores(availableStores);
      phone.setCost(phoneEntity.getCost());
      phoneList.add(phone);
    });
    return phoneList;
  }

  public void addPhone(Phone phone) {
    phoneDAO.create(phone);
  }

  public void updateInformationPhone(Phone phone) {
  }

  public void deletePhone(int idPhone) {
    phoneDAO.delete(idPhone);
  }

}