package com.example.consumer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.LOCAL;
import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.REMOTE;


@SpringBootTest
@AutoConfigureStubRunner(
        ids = "com.example:productservice:0.0.1-SNAPSHOT:8080",
        stubsMode = StubRunnerProperties.StubsMode.REMOTE,
        repositoryRoot="git://https://github.com/duttabhishek0/temp-stubs.git",
        properties = {
                "git.branch=main"
        }
)
public final class ConsumerContractTest {
    @Test
    void testGetProductByIdStub() {
        RestTemplate restTemplate = new RestTemplate();
        String baseUrl = "http://localhost:8080";
        String response = restTemplate.getForObject(baseUrl + "/products/1", String.class);

        String expectedResponse = "{\"available\":true,\"description\":\"A classic novel written by F. Scott Fitzgerald.\",\"id\":1,\"name\":\"The Great Gatsby\",\"price\":10.99}";

        assertEquals(expectedResponse, response);
    }
}
