package com.example.onlineShop.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Сущность, представляющая телефон.
 */
@Entity
@Table(name = "phone")
@Getter
@Setter
public class PhoneEntity {

  /**
   * Уникальный идентификатор телефона.
   */
  @Id
  private Long id;

  /**
   * Модель телефона.
   */
  private String model;

  /**
   * Стоимость телефона.
   */
  private Integer cost;

  /**
   * Список магазинов, в которых доступен телефон.
   */
  @OneToMany(mappedBy = "phoneEntityId", cascade = CascadeType.ALL)
  private List<AvailableStoreEntity> availableStoreEntities;

  /**
   * Конструктор с параметрами.
   *
   * @param model Модель телефона
   * @param cost  Стоимость телефона
   * @param id    Уникальный идентификатор телефона
   */
  public PhoneEntity(String model, Integer cost, Long id) {
    this.id = id;
    this.model = model;
    this.cost = cost;
  }

  public PhoneEntity() {

  }
}
