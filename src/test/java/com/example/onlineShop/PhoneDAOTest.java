package com.example.onlineShop;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.onlineShop.dao.AvailableStoreDAO;
import com.example.onlineShop.dao.PhoneDAO;
import com.example.onlineShop.dao.impl.PhoneDAOImpl;
import com.example.onlineShop.entity.PhoneEntity;
import com.example.onlineShop.model.Phone;
import com.example.onlineShop.service.AvailableStoreService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
	public void testCreatePhone() {
		Phone phone = new Phone();
		phone.setModelPhone("TestModel");
		phone.setCost(100);
		PhoneEntity phoneEntity = new PhoneEntity("TestModel", 100, 1L);

		when(sessionFactory.getCurrentSession()).thenReturn(session);
		phoneDAO.create(phone);

		verify(session).persist(any(PhoneEntity.class));
	}

}
