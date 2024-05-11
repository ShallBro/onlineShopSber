package com.example.onlineShop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.onlineShop.dao.AvailableStoreDAO;
import com.example.onlineShop.dao.PhoneDAO;
import com.example.onlineShop.dao.impl.PhoneDAOImpl;
import com.example.onlineShop.entity.PhoneEntity;
import com.example.onlineShop.model.Phone;
import com.example.onlineShop.service.AvailableStoreService;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PhoneDAOTest {

	@Mock
	private SessionFactory sessionFactory;

	@Mock
	private AvailableStoreDAO availableStoreDAO;

	@Mock
	private AvailableStoreService availableStoreService;

	@Mock
	private Session session;

	private PhoneDAO phoneDAO;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		phoneDAO = new PhoneDAOImpl(sessionFactory, availableStoreDAO, availableStoreService);
	}

	@Test
	void testCreatePhone() {
		Phone phone = new Phone(1L, "TestModel", List.of("Test","Test1"), 100);

		when(sessionFactory.getCurrentSession()).thenReturn(session);

		PhoneEntity phoneEntityMock = new PhoneEntity("TestModel", 100, 1L);

		doNothing().when(session).persist(any(PhoneEntity.class));

		phoneDAO.create(phone);

		verify(session).persist(any(PhoneEntity.class));

		when(session.load(PhoneEntity.class, phone.getId())).thenReturn(phoneEntityMock);

		PhoneEntity newPhoneEntity = session.load(PhoneEntity.class, phone.getId());

		assertPhoneEntity("TestModel", 100, 1L, newPhoneEntity);
	}

	@Test
	void testUpdatePhone() {
		Phone phone = new Phone(1L, "UpdatedModel", List.of("Test","Test1"), 200);
		PhoneEntity phoneEntity = new PhoneEntity("TestModel", 100, 1L);

		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.get(PhoneEntity.class, phone.getId())).thenReturn(phoneEntity);

		phoneDAO.update(phone);

		verify(session).merge(any(PhoneEntity.class));

		PhoneEntity updatedPhoneEntity = session.get(PhoneEntity.class, phone.getId());

		assertPhoneEntity("UpdatedModel", 200, 1L, updatedPhoneEntity);
	}

	@Test
	void testGetAllPhones() {
		List<PhoneEntity> phoneEntities = new ArrayList<>();
		phoneEntities.add(new PhoneEntity("TestModel1", 100, 1L));
		phoneEntities.add(new PhoneEntity("TestModel2", 200, 2L));

		Query query = mock(Query.class);
		when(query.getResultList()).thenReturn(phoneEntities);
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.createQuery("FROM PhoneEntity")).thenReturn(query);

		List<PhoneEntity> result = phoneDAO.get();
		assertEquals(2, result.size());

		assertPhoneEntity("TestModel1", 100, 1L, result.get(0));
		assertPhoneEntity("TestModel2", 200, 2L, result.get(1));
	}

	@Test
	void testDeletePhone() {
		PhoneEntity phoneEntity = new PhoneEntity("TestModel", 100, 1L);

		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.get(PhoneEntity.class, 1)).thenReturn(phoneEntity);

		phoneDAO.delete(1);

		verify(session).remove(phoneEntity);
	}

	private void assertPhoneEntity(String phone, Integer cost, Long id, PhoneEntity phoneEntity) {
		assertEquals(phone, phoneEntity.getModel());
		assertEquals(cost, phoneEntity.getCost());
		assertEquals(id, phoneEntity.getId());
	}

}
