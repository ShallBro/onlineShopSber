package com.example.onlineShop.dao;

import com.example.onlineShop.entity.PhoneEntity;
import com.example.onlineShop.model.Phone;
import java.util.List;

public interface PhoneDAO {
  void create(Phone phone);
  void update(Phone phone);
  List<PhoneEntity> get();
  void delete(int idPhone);
}
