package com.tupi.integrationtests.swagger;

import com.tupi.configs.TestConfigs;
import com.tupi.integrationtests.testcontainers.AbstractIntegrationTest;
import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootApplication
@SpringBootTest(classes = TestConfigs.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTests extends AbstractIntegrationTest {

    @Test
    public void showDisplaySwaggerUiPage() {
        var content = given()
                .basePath("/swagger-ui/index.html")
                .port(TestConfigs.SERVER_PORT)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body().asString();


        assertTrue(content.contains("Swagger UI"));
    }

}
