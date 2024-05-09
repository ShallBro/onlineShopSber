package com.example.onlineShop.dao;

import com.example.onlineShop.entity.AvailableStoreEntity;
import com.example.onlineShop.entity.PhoneEntity;
import com.example.onlineShop.model.Phone;
import java.util.List;

public interface AvailableStoreDAO {
  void create(Phone phone, PhoneEntity phoneEntity);
  void update(Phone phone);
  List<AvailableStoreEntity> get(PhoneEntity phoneEntity);
}
