package com.ericsson.tgf.kyzen;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@AutoConfigureMockMvc
@SpringBootTest
class KyzenApplicationTests {

	@Autowired
	private KyzenApplication kyzenApplication;


	@Test
	void contextLoads() throws Exception {

		assertThat(kyzenApplication,is(notNullValue()));


	}

}
