package com.example.consumer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@AutoConfigureStubRunner(
        ids = "com.example:productservice:+:stubs:8080", // Use + to match any version or specify 0.0.1-SNAPSHOT
        stubsMode = StubRunnerProperties.StubsMode.LOCAL // Use LOCAL if stubs are in your local Maven repo
)
public class ConsumerContractTest {
    @Test
    void testGetProductByIdStub() {
        RestTemplate restTemplate = new RestTemplate();
        String baseUrl = "http://localhost:8080";
        String response = restTemplate.getForObject(baseUrl + "/products/1", String.class);

        String expectedResponse = "{\"available\":true,\"description\":\"A classic novel written by F. Scott Fitzgerald.\",\"id\":1,\"name\":\"The Great Gatsby\",\"price\":10.99}";

        assertEquals(expectedResponse, response);
    }
}
