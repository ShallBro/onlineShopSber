package com.example.onlineShop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class OnlineShopSberApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineShopSberApplication.class, args);
	}

}
