package com.example.onlineShop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

/**
 * Сущность, представляющая доступный магазин для телефона.
 */
@Entity
@Table(name = "available_store")
@Getter
public class AvailableStoreEntity {

  /**
   * Уникальный идентификатор доступного магазина.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Телефон, который доступен в этом магазине.
   */
  @ManyToOne
  @JoinColumn(name = "phone_id", referencedColumnName = "id")
  private PhoneEntity phoneEntityId;

  /**
   * Название магазина.
   */
  private String name;

  /**
   * Конструктор с параметрами.
   *
   * @param phoneEntityId Телефон, который доступен в этом магазине
   * @param name          Название магазина
   */
  public AvailableStoreEntity(PhoneEntity phoneEntityId, String name) {
    this.phoneEntityId = phoneEntityId;
    this.name = name;
  }

  public AvailableStoreEntity() {

  }
}
