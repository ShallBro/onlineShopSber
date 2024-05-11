package com.example.onlineShop.service;

import com.example.onlineShop.dao.PhoneDAO;
import com.example.onlineShop.entity.PhoneEntity;
import com.example.onlineShop.model.Phone;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис для управления телефонами в маркетплейсе.
 */
@Service
public class OnlineShopService {

  private final PhoneDAO phoneDAO;

  private final LoggerService loggerService;

  /**
   * Конструктор сервиса.
   * @param phoneDAO Объект для доступа к данным телефонов
   * @param loggerService Сервис для логирования действий
   */
  @Autowired
  public OnlineShopService(PhoneDAO phoneDAO, LoggerService loggerService) {
    this.phoneDAO = phoneDAO;
    this.loggerService = loggerService;
  }

  /**
   * Получить список всех доступных телефонов.
   * @return Список объектов типа Phone, представляющих доступные телефоны
   */
  public List<Phone> allAvailablePhones() {
    List<PhoneEntity> phoneEntities = phoneDAO.get();
    loggerService.getLoggerReadPhoneEntity();
    List<Phone> phoneList = new ArrayList<>();
    phoneEntities.forEach(phoneEntity -> {
      List<String> availableStores = new ArrayList<>();
      phoneEntity.getAvailableStoreEntities().forEach(availableStoreEntity -> availableStores.add(availableStoreEntity.getName()));
      phoneList.add(new Phone(phoneEntity.getId(), phoneEntity.getModel(), availableStores, phoneEntity.getCost()));
    });
    return phoneList;
  }

  /**
   * Добавить новый телефон.
   * @param phone Объект типа Phone, представляющий новый телефон
   */
  public void addPhone(Phone phone) {
    phoneDAO.create(phone);
    loggerService.getLoggerAddPhoneEntity();
    loggerService.getLoggerAddAvailableStoreEntity();
  }

  /**
   * Обновить информацию о телефоне.
   * @param phone Объект типа Phone, представляющий информацию для обновления
   * @return Объект типа PhoneEntity, обновленный телефон или null, если телефон не найден
   */
  public PhoneEntity updateInformationPhone(Phone phone) {
    PhoneEntity phoneEntityFoundById = findPhoneEntityById(phoneDAO.get(), phone.getId());
    loggerService.getLoggerReadPhoneEntity();
    if (phoneEntityFoundById != null) {
      phoneDAO.update(phone);
      loggerService.getLoggerUpdatePhoneEntity();
    }
    return phoneEntityFoundById;
  }

  /**
   * Удалить телефон по его идентификатору.
   * @param idPhone Идентификатор удаляемого телефона
   * @return Объект типа PhoneEntity, удаленный телефон или null, если телефон не найден
   */
  public PhoneEntity deletePhone(int idPhone) {
    PhoneEntity phoneEntityFoundById = findPhoneEntityById(phoneDAO.get(), idPhone);
    loggerService.getLoggerReadPhoneEntity();
    if (phoneEntityFoundById != null) {
      phoneDAO.delete(idPhone);
      loggerService.getLoggerDeletePhoneEntity();
      loggerService.getLoggerDeleteAvailableStoreEntity();
    }
    return phoneEntityFoundById;
  }

  /**
   * Найти телефон по его идентификатору.
   * @param phoneEntities Список объектов типа PhoneEntity
   * @param idPhone Идентификатор телефона
   * @return Объект типа PhoneEntity, найденный телефон или null, если телефон не найден
   */
  private PhoneEntity findPhoneEntityById(List<PhoneEntity> phoneEntities, long idPhone) {
    return phoneEntities.stream()
      .filter(phoneEntity -> phoneEntity.getId().equals(idPhone))
      .findFirst()
      .orElse(null);
  }
}