package com.tupi.integrationtests.controllers.withjson;

import com.tupi.configs.TestConfigs;
import com.tupi.integrationtests.testcontainers.AbstractIntegrationTest;
import com.tupi.integrationtests.vo.PersonVO;
import com.tupi.integrationtests.vo.security.AccountCredentialsVO;
import com.tupi.integrationtests.vo.security.TokenVO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.DeserializationFeature;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;

@SpringBootTest(classes = TestConfigs.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerIntegrationTests extends AbstractIntegrationTest {

    private static RequestSpecification requestSpecification;
    private static ObjectMapper mapper;
    private static PersonVO person;
    private static String accessToken;

    @BeforeAll
    public static void Setup() {
        ObjectMapper objectMapper = new ObjectMapper();
        mapper = objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        person = new PersonVO();
    }

    @Test
    @Order(0)
    public void auth() throws IOException {
        AccountCredentialsVO user = new AccountCredentialsVO("tupi", "admin123");

//        requestSpecification = new RequestSpecBuilder()
//                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_TUPI)
//                .setBasePath("/auth/signIn")
//                .setPort(TestConfigs.SERVER_PORT)
//                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
//                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
//                .build();

        var accessToken = given()
                .basePath("/auth/signIn")
                .port(TestConfigs.SERVER_PORT)
                .body(user)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(TokenVO.class)
                .getAccessToken();

        System.out.println("Access token: " + accessToken);


        this.accessToken = accessToken;
    }

    @Test
    @Order(1)
    public void testCreate() throws IOException {
        this.mockPerson();
        this.setSpecification("/api/v1/person");

        var content = given()
                .spec(requestSpecification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(person)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        PersonVO createdPerson = mapper.readValue(content, PersonVO.class);
        person = createdPerson;

        Assertions.assertNotNull(createdPerson);
        Assertions.assertNotNull(createdPerson.getId());
        Assertions.assertNotNull(createdPerson.getFirstName());
        Assertions.assertNotNull(createdPerson.getLastName());
        Assertions.assertNotNull(createdPerson.getGender());
        Assertions.assertNotNull(createdPerson.getAddress());

        Assertions.assertTrue(createdPerson.getId() > 0);

        Assertions.assertEquals("João", createdPerson.getFirstName());
        Assertions.assertEquals("Tupinambá", createdPerson.getLastName());
        Assertions.assertEquals("Male", createdPerson.getGender());
        Assertions.assertEquals("São Luís", createdPerson.getAddress());
    }

    @Test
    @Order(2)
    public void testCreateWithWrongOrigin() throws IOException {
        this.setSpecification("/api/v1/person", TestConfigs.ORIGIN_WRONG);

        var content = given()
                .spec(requestSpecification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(person)
                .when()
                .post()
                .then()
                .statusCode(403)
                .extract()
                .body()
                .asString();


        Assertions.assertNotNull(content);
        Assertions.assertEquals("Invalid CORS request", content);
    }

    @Test
    @Order(3)
    public void testFindById() throws IOException {
        this.setSpecification("/api/v1/person");

        var content = given()
                .spec(requestSpecification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .pathParam("id", person.getId())
                .when()
                .get("{id}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        PersonVO createdPerson = mapper.readValue(content, PersonVO.class);
        person = createdPerson;

        Assertions.assertNotNull(createdPerson);
        Assertions.assertNotNull(createdPerson.getId());
        Assertions.assertNotNull(createdPerson.getFirstName());
        Assertions.assertNotNull(createdPerson.getLastName());
        Assertions.assertNotNull(createdPerson.getGender());
        Assertions.assertNotNull(createdPerson.getAddress());

        Assertions.assertTrue(createdPerson.getId() > 0);

        Assertions.assertEquals("João", createdPerson.getFirstName());
        Assertions.assertEquals("Tupinambá", createdPerson.getLastName());
        Assertions.assertEquals("Male", createdPerson.getGender());
        Assertions.assertEquals("São Luís", createdPerson.getAddress());
    }

    @Test
    @Order(4)
    public void testFindAll() throws IOException {
        this.setSpecification("/api/v1/person/findAll");

        var content = given()
                .spec(requestSpecification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();


        List<PersonVO> response = List.of(mapper.readValue(content, PersonVO[].class));
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.size() > 0);

        Assertions.assertTrue(response.stream().anyMatch(p -> p.getId().equals(person.getId())));
        Assertions.assertTrue(response.stream().anyMatch(p -> p.getFirstName().equals(person.getFirstName())));
        Assertions.assertTrue(response.stream().anyMatch(p -> p.getLastName().equals(person.getLastName())));
        Assertions.assertTrue(response.stream().anyMatch(p -> p.getGender().equals(person.getGender())));
        Assertions.assertTrue(response.stream().anyMatch(p -> p.getAddress().equals(person.getAddress())));

        Assertions.assertInstanceOf(List.class, response);
    }



    private void mockPerson(Long id) {
        person.setId(id);
        person.setFirstName("João");
        person.setLastName("Tupinambá");
        person.setGender("Male");
        person.setAddress("São Luís");
    }

    private void mockPerson() {
        mockPerson(1L);
    }

    private void setSpecification(String basePath) {
        this.setSpecification(basePath, TestConfigs.ORIGIN_TUPI);
    }

    private void setSpecification(String basePath, String origin) {

        requestSpecification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ACCESS_AUTHORIZATION, "Bearer " + this.accessToken)
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, origin)
                .setBasePath(basePath)
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();
    }

}
