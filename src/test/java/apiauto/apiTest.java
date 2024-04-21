package apiauto;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import java.io.File;


import java.io.File;
import java.io.IOException;

public class apiTest {

@Test
    public void getEmployeesPositifTest(){ //Positive case get all employees

    File jsonSchema =new File("src/test/resources/jsonSchema/getEmployees.json");
    RestAssured.get("https://jsonplaceholder.org/users")
            .then().log().all()
            .assertThat().statusCode(200)
            .assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
}

@Test
    public void postNewEmployeeTest() throws IOException {// mengirim tipe data yang salah yaitu email dan zipcode yang harusnya string di isi integer lalu jika gagal created harusny error 400

//    JSONObject jsonObject = new JSONObject();

    File jsonPostNewEmployee = new File("src/test/resources/postData/postNewEmployee.json");
    File jsonSchemaNewEmployee = new File ("src/test/resources/jsonSchema/postNewEmployee.json");

    String jsonBody =FileUtils.readFileToString(jsonPostNewEmployee,"UTF-8");



    RestAssured.given().header("Content-Type", "application/json")
            .header("Accept","application/json")
            .body(jsonBody)
            .when()
            .post("https://jsonplaceholder.org/users").then().log().all()
            .assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonSchemaNewEmployee))
            .assertThat().statusCode(anyOf(equalTo(400),equalTo(401)));
}

@Test
    public void postValueMaxorMin() throws IOException { //nilai maksimum email itu biasanya 254 karakter dan pada case ini di input lebih

    File jsonValueMaXMin = new File("D:\\JayJay\\Homework_module13\\src\\test\\resources\\postData\\postValueMaxMin.json");
    File jsonValidate = new File("D:\\JayJay\\Homework_module13\\src\\test\\resources\\jsonSchema\\postNewEmployee.json");

    String jsonBody = FileUtils.readFileToString(jsonValueMaXMin, "UTF-8");

RestAssured.given().when().header("Context-Type","application/json")
        .header("Accept", "application/json")
        .body(jsonBody)
        .post("https://jsonplaceholder.org/users").then().log().all()
        .assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonValidate))
        .assertThat().statusCode(anyOf(equalTo(400),equalTo(401)));

}
}
