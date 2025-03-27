package com.example.consumer;

import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringBootTest
//@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "ProductProvider", port = "9999", pactVersion = PactSpecVersion.V3)
class ConsumerApplicationTests {

//	@Pact(consumer = "ProductConsumer")
//	public RequestResponsePact createPactForGetProducts(PactDslWithProvider builder) {
//		return builder
//				.given("Products exist")
//				.uponReceiving("A request for products")
//				.path("/products")
//				.method("GET")
//				.willRespondWith()
//				.status(200)
//				.body("[{\"id\":1,\"name\":\"The Great Gatsby\",\"description\":\"A classic novel written by F. Scott Fitzgerald.\",\"price\":10.99,\"available\":true}," +
//						"{\"id\":2,\"name\":\"1984\",\"description\":\"A dystopian social science fiction novel by George Orwell.\",\"price\":8.99,\"available\":true}," +
//						"{\"id\":3,\"name\":\"To Kill a Mockingbird\",\"description\":\"A novel by Harper Lee published in 1960.\",\"price\":7.99,\"available\":true}," +
//						"{\"id\":4,\"name\":\"The Catcher in the Rye\",\"description\":\"A novel by J.D. Salinger, partially published in 1945.\",\"price\":9.99,\"available\":false}," +
//						"{\"id\":5,\"name\":\"Moby-Dick\",\"description\":\"A novel by Herman Melville, published in 1851.\",\"price\":11.99,\"available\":true}]")
//				.toPact();
//	}
//
//	@Pact(consumer = "ProductConsumer")
//	public RequestResponsePact createPactForGetProductById(PactDslWithProvider builder) {
//		return builder
//				.given("Product with ID 1 exists")
//				.uponReceiving("A request for product by ID")
//				.path("/products/1")
//				.method("GET")
//				.willRespondWith()
//				.status(200)
//				.body("{\"id\":1,\"name\":\"The Great Gatsby\",\"description\":\"A classic novel written by F. Scott Fitzgerald.\",\"price\":10.99,\"available\":true}")
//				.toPact();
//	}
//
//	@Test
//	@PactTestFor(pactMethod = "createPactForGetProducts")
//	void testGetProducts() {
//		RestTemplate restTemplate = new RestTemplate();
//		String baseUrl = "http://localhost:9999";
//		String response = restTemplate.getForObject(baseUrl + "/products", String.class);
//
//		String expectedResponse = "[{\"id\":1,\"name\":\"The Great Gatsby\",\"description\":\"A classic novel written by F. Scott Fitzgerald.\",\"price\":10.99,\"available\":true}," +
//				"{\"id\":2,\"name\":\"1984\",\"description\":\"A dystopian social science fiction novel by George Orwell.\",\"price\":8.99,\"available\":true}," +
//				"{\"id\":3,\"name\":\"To Kill a Mockingbird\",\"description\":\"A novel by Harper Lee published in 1960.\",\"price\":7.99,\"available\":true}," +
//				"{\"id\":4,\"name\":\"The Catcher in the Rye\",\"description\":\"A novel by J.D. Salinger, partially published in 1945.\",\"price\":9.99,\"available\":false}," +
//				"{\"id\":5,\"name\":\"Moby-Dick\",\"description\":\"A novel by Herman Melville, published in 1851.\",\"price\":11.99,\"available\":true}]";
//
//		assertEquals(expectedResponse, response);
//	}
//
//	@Test
//	@PactTestFor(pactMethod = "createPactForGetProductById")
//	void testGetProductById() {
//		RestTemplate restTemplate = new RestTemplate();
//		String baseUrl = "http://localhost:9999";
//		String response = restTemplate.getForObject(baseUrl + "/products/1", String.class);
//
//		String expectedResponse = "{\"id\":1,\"name\":\"The Great Gatsby\",\"description\":\"A classic novel written by F. Scott Fitzgerald.\",\"price\":10.99,\"available\":true}";
//
//		assertEquals(expectedResponse, response);
//	}
}