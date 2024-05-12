package com.example.onlineShop.dao;

import com.example.onlineShop.entity.AvailableStoreEntity;
import com.example.onlineShop.entity.PhoneEntity;
import com.example.onlineShop.model.Phone;
import java.util.List;
import org.hibernate.Session;

/**
 * Интерфейс для доступа к данным о доступных магазинах телефонов.
 */
public interface AvailableStoreDAO {

  /**
   * Создать записи о доступных магазинах для указанного телефона.
   *
   * @param phone       Объект типа Phone, представляющий телефон
   * @param phoneEntity Объект типа PhoneEntity, представляющий сущность телефона
   */
  void create(Phone phone, PhoneEntity phoneEntity);

  /**
   * Обновить записи о доступных магазинах для указанного телефона.
   *
   * @param updatedAvailableStores Список объектов типа AvailableStoreEntity, представляющих обновленные данные о магазинах
   * @param phoneEntity            Объект типа PhoneEntity, представляющий сущность телефона
   * @param session                Сессия Hibernate для выполнения операций с базой данных
   */
  void update(List<AvailableStoreEntity> updatedAvailableStores, PhoneEntity phoneEntity, Session session);

  /**
   * Получить список доступных магазинов для указанного телефона.
   *
   * @param phoneEntity Объект типа PhoneEntity, представляющий сущность телефона
   * @return Список объектов типа AvailableStoreEntity, представляющих доступные магазины для указанного телефона
   */
  List<AvailableStoreEntity> get(PhoneEntity phoneEntity);
}
