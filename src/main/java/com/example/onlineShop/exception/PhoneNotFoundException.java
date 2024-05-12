package com.example.onlineShop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение, которое выбрасывается, если телефон с заданным id не найден.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Телефон с таким id не найден")
public class PhoneNotFoundException extends RuntimeException {

  /**
   * Конструктор исключения.
   */
  public PhoneNotFoundException() {
    super();
  }
}
