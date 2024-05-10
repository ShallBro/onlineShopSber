package com.example.onlineShop.dao;

import com.example.onlineShop.entity.PhoneEntity;
import com.example.onlineShop.model.Phone;
import java.util.List;

/**
 * Интерфейс для доступа к данным о телефонах.
 */
public interface PhoneDAO {

  /**
   * Создать новую запись о телефоне.
   * @param phone Объект типа Phone, представляющий новый телефон
   */
  void create(Phone phone);

  /**
   * Обновить существующую запись о телефоне.
   * @param phone Объект типа Phone, представляющий информацию для обновления
   */
  void update(Phone phone);

  /**
   * Получить список всех телефонов.
   * @return Список объектов типа PhoneEntity, представляющих все телефоны
   */
  List<PhoneEntity> get();

  /**
   * Удалить телефон по его идентификатору.
   * @param idPhone Идентификатор удаляемого телефона
   */
  void delete(int idPhone);
}
