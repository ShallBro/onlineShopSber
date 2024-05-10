package com.example.onlineShop.controller;

import com.example.onlineShop.entity.PhoneEntity;
import com.example.onlineShop.exception.PhoneNotFoundException;
import com.example.onlineShop.model.Phone;
import com.example.onlineShop.service.OnlineShopService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST-контроллер для управления телефонами в маркетплейсе.
 */
@RestController
@RequestMapping("/restApi")
public class OnlineShopController {

  private final OnlineShopService onlineShopService;

  /**
   * Конструктор контроллера.
   * @param onlineShopService Сервис для работы с телефонами в маркетплейсе
   */
  @Autowired
  public OnlineShopController(OnlineShopService onlineShopService) {
    this.onlineShopService = onlineShopService;
  }

  /**
   * Получить список всех доступных телефонов.
   * @return Список объектов типа Phone, представляющих доступные телефоны
   */
  @GetMapping("/allPhones")
  public List<Phone> getPhones() {
    return onlineShopService.allAvailablePhones();
  }

  /**
   * Добавить новый телефон.
   * @param phone Объект типа Phone, представляющий новый телефон
   */
  @PostMapping("/addPhone")
  public void postPhone(@RequestBody Phone phone) {
    onlineShopService.addPhone(phone);
  }

  /**
   * Обновить информацию о телефоне.
   * @param phone Объект типа Phone, представляющий информацию для обновления
   * @throws PhoneNotFoundException Если телефон не найден
   */
  @PutMapping("/updatePhone")
  public void putPhone(@RequestBody Phone phone) {
    PhoneEntity phoneEntity = onlineShopService.updateInformationPhone(phone);
    if (phoneEntity == null) {
      throw new PhoneNotFoundException();
    }
  }

  /**
   * Удалить телефон по его идентификатору.
   * @param idPhone Идентификатор удаляемого телефона
   * @throws PhoneNotFoundException Если телефон не найден
   */
  @DeleteMapping("/deletePhone")
  public void deletePhone(int idPhone) {
    PhoneEntity phoneEntity = onlineShopService.deletePhone(idPhone);
    if (phoneEntity == null) {
      throw new PhoneNotFoundException();
    }
  }
}
