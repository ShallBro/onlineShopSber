package com.example.onlineShop.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Phone {
  private String modelPhone;
  private List<String> availableStores;
  private Integer cost;
}
