package Specification.spec;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static filter.CustomLogFilter.customLogFilter;
import static io.restassured.RestAssured.with;


public class ApiRequestSpecification {


    public static RequestSpecification requestReqresSpec = with()
            .baseUri("https://reqres.in")
            .filter(customLogFilter().withCustomTemplates())
            .log().all()
            .contentType(ContentType.JSON);


    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();
}
