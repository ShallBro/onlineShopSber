package com.example.onlineShop;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import com.example.onlineShop.dao.PhoneDAO;
import com.example.onlineShop.entity.PhoneEntity;
import com.example.onlineShop.model.Phone;
import com.example.onlineShop.service.LoggerService;
import com.example.onlineShop.service.OnlineShopService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OnlineShopServiceTest {

  private OnlineShopService onlineShopService;

  @Mock
  private PhoneDAO phoneDAO;

  @Mock
  private LoggerService loggerService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    onlineShopService = new OnlineShopService(phoneDAO, loggerService);
  }

  @Test
  void testUpdateInformationPhone() {
    Phone phone = new Phone(1L, "TestModel", List.of("Test", "Test1"), 100);
    PhoneEntity phoneEntity = new PhoneEntity("TestModel", 100, 2L);

    when(phoneDAO.get()).thenReturn(List.of(phoneEntity));

    PhoneEntity updatedPhoneEntity = onlineShopService.updateInformationPhone(phone);

    assertNull(updatedPhoneEntity);
  }

  @Test
  void testUpdateInformationPhone2() {
    Phone phone = new Phone(2L, "TestModel", List.of("Test", "Test1"), 100);
    PhoneEntity phoneEntity = new PhoneEntity("TestModel", 100, 2L);

    when(phoneDAO.get()).thenReturn(List.of(phoneEntity));

    PhoneEntity updatedPhoneEntity = onlineShopService.updateInformationPhone(phone);

    assertNotNull(updatedPhoneEntity);
  }

  @Test
  void testDeleteInformationPhone() {
    Phone phone = new Phone(2L, "TestModel", List.of("Test", "Test1"), 100);
    PhoneEntity phoneEntity = new PhoneEntity("TestModel", 100, 2L);

    when(phoneDAO.get()).thenReturn(List.of(phoneEntity));

    PhoneEntity updatedPhoneEntity = onlineShopService.updateInformationPhone(phone);

    assertNotNull(updatedPhoneEntity);
  }

}
