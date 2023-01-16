package api.steps;

import java.util.List;

import org.junit.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.pajeobject.Datum;
import models.pajeobject.PageContent;
import models.pajeobject.user.User;
import stash.StashesContainer;

import static io.restassured.RestAssured.given;
import static utils.StreamApiUtils.getAllFirstNames;

public class APIMethods {

    @Given("веб-страница {string}")
    public void putWebPageIntoStash(String url) {
        StashesContainer.put("MAIN_URL_FOR_API_TESTING", url);
    }

    @When("^Отправляем POST запрос и проверяем статус-код 201$")
    public void postReq() {
        String url = (String) StashesContainer.get("MAIN_URL_FOR_API_TESTING");
        List<String> jsonBody = (List<String>) StashesContainer.get("JSON_BODY");
        RestAssured.baseURI = url;

        Response response = given()
                .body(String.join("\n", jsonBody))
                .contentType(ContentType.JSON)
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .log().all()
                .extract().response();

        String postResp = response.getBody().asString();
        StashesContainer.put("POST_RESPONSE", postResp);
    }

    @When("^Отправляем GET запрос и проверяем статус-код 200$")
    public static void getRequest() {
        String url = (String) StashesContainer.get("MAIN_URL_FOR_API_TESTING");
        RestAssured.baseURI = url;

        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .get("/api/users?page=2")
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();

        String resp = response.getBody().asString();
        StashesContainer.put("GET_RESPONSE", resp);
    }

    @Then("Проверяем наличие поля page со значением {int} и поля first_name со значением {string}")
    public void checkGetResponse(int pageValue, String firstNameValue)
            throws JsonProcessingException {
        String response = (String) StashesContainer.get("GET_RESPONSE");

        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        PageContent getReqRes = om.readValue(response, PageContent.class);

        List<Datum> notes = getReqRes.getData();
        List<String> firstNames = getAllFirstNames(notes);
        Assert.assertEquals("Номер поля page не соответствует ожидаемому!", pageValue, getReqRes.getPage());
        Assert.assertTrue(firstNames.contains(firstNameValue));
    }

    @Then("Проверяем что поля ответа запроса совпадают с ранее сгенерированными строками")
    public void checkPostResponse() throws JsonProcessingException {
        String name = (String) StashesContainer.get("NAME_FIELD");
        String job = (String) StashesContainer.get("JOB_FIELD");
        String response = (String) StashesContainer.get("POST_RESPONSE");

        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        User getReqRes = om.readValue(response, User.class);

        Assert.assertEquals("Поле name не совпадает с ранее сгенерированной строкой!", name, getReqRes.getName());
        Assert.assertEquals("Поле job не совпадает с ранее сгенерированной строкой!", job, getReqRes.getJob());
    }
}
