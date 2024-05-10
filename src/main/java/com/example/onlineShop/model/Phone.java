package com.example.onlineShop.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс, представляющий телефон.
 */
@Getter
@Setter
public class Phone {
  /**
   * Уникальный идентификатор телефона.
   */
  private Long id;

  /**
   * Модель телефона.
   */
  private String modelPhone;

  /**
   * Список магазинов, в которых доступен телефон.
   */
  private List<String> availableStores;

  /**
   * Стоимость телефона.
   */
  private Integer cost;
}
