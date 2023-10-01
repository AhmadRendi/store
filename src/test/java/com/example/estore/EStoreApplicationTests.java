package com.example.estore;

import com.example.estore.Entity.Buyer;
import com.example.estore.service.impl.BuyerServiceImpl;
import org.hibernate.boot.jaxb.internal.stax.BufferedXMLEventReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
class EStoreApplicationTests {

	@Autowired
	BuyerServiceImpl buyerService;

}
