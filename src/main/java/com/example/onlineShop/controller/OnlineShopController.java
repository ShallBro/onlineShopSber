package com.example.onlineShop.controller;

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

@RestController
@RequestMapping("/restApi")
public class OnlineShopController {

  private final OnlineShopService onlineShopService;

  @Autowired
  public OnlineShopController(OnlineShopService onlineShopService) {
    this.onlineShopService = onlineShopService;
  }

  @GetMapping("/allPhones")
  public List<Phone> getPhones() {
    return onlineShopService.allAvailablePhones();
  }

  @PostMapping("/addPhone")
  public void postPhone(@RequestBody Phone phone){
    onlineShopService.addPhone(phone);
  }

  @PutMapping("/updatePhone")
  public void putPhone(@RequestBody Phone phone){
    onlineShopService.updateInformationPhone(phone);
  }

  @DeleteMapping("/deletePhone")
  public void deletePhone(int idPhone){
    onlineShopService.deletePhone(idPhone);
  }
}
