package com.example.onlineShop.entity;

import com.example.onlineShop.model.Phone;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "phone")
@Getter
@Setter
public class PhoneEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String model;
  private Integer cost;

  @OneToMany(mappedBy = "phoneEntityId", cascade = CascadeType.ALL)
  private List<AvailableStoreEntity> availableStoreEntities;

  public PhoneEntity(String model, Integer cost) {
    this.model = model;
    this.cost = cost;
  }

  public PhoneEntity() {

  }
}
