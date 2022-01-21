package zidonghuaceshikuangjia.testcase;


import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Testmock {
    @Test
    public void testMock(){
        given().when().get("http://127.0.0.1:9999").then().log().all();
    }
}
