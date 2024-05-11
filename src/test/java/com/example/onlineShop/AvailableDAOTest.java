package com.example.onlineShop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.onlineShop.dao.AvailableStoreDAO;
import com.example.onlineShop.dao.impl.AvailableStoreDAOImpl;
import com.example.onlineShop.entity.AvailableStoreEntity;
import com.example.onlineShop.entity.PhoneEntity;
import com.example.onlineShop.model.Phone;
import com.example.onlineShop.service.LoggerService;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AvailableDAOTest {

  private AvailableStoreDAO availableStoreDAO;

  @Mock
  private LoggerService loggerService;

  @Mock
  private SessionFactory sessionFactory;

  @Mock
  private Session session;

  @Captor
  private ArgumentCaptor<AvailableStoreEntity> captor;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    availableStoreDAO = new AvailableStoreDAOImpl(sessionFactory, loggerService);
  }

  @Test
  void testCreateAvailableStores() {
    PhoneEntity phoneEntityMock = new PhoneEntity("TestModel", 100, 1L);

    when(sessionFactory.getCurrentSession()).thenReturn(session);

    Phone phone = new Phone(1L, "TestModel", List.of("Test", "Test1"), 100);

    availableStoreDAO.create(phone, phoneEntityMock);

    verify(session, times(2)).persist(captor.capture());

    List<AvailableStoreEntity> capturedEntities = captor.getAllValues();

    assertStoreEntity(capturedEntities.get(0), phoneEntityMock, "Test");
    assertStoreEntity(capturedEntities.get(1), phoneEntityMock, "Test1");
  }

  @Test
  void testUpdate() {
    List<AvailableStoreEntity> updatedStores = new ArrayList<>();
    PhoneEntity phoneEntity = new PhoneEntity("TestModel", 100, 1L);

    AvailableStoreEntity store1 = new AvailableStoreEntity(phoneEntity, "Store1");
    AvailableStoreEntity store2 = new AvailableStoreEntity(phoneEntity, "Store2");
    phoneEntity.setAvailableStoreEntities(List.of(store1, store2));

    updatedStores.add(store1);

    when(session.get(PhoneEntity.class, phoneEntity.getId())).thenReturn(phoneEntity);

    availableStoreDAO.update(updatedStores, phoneEntity, session);

    verify(session, times(1)).remove(any());
  }

  @Test
  void testGet() {
    PhoneEntity phoneEntity = new PhoneEntity("TestModel", 100, 1L);
    List<AvailableStoreEntity> stores = List.of(new AvailableStoreEntity(phoneEntity, "Store1"),
      new AvailableStoreEntity(phoneEntity, "Store2"));

    phoneEntity.setAvailableStoreEntities(stores);

    List<AvailableStoreEntity> retrievedStores = availableStoreDAO.get(phoneEntity);

    assertEquals(2,retrievedStores.size());
  }

  private void assertStoreEntity(AvailableStoreEntity entity, PhoneEntity phoneEntity, String storeName) {
    assertEquals(phoneEntity, entity.getPhoneEntityId());
    assertEquals(storeName, entity.getName());
  }

}
