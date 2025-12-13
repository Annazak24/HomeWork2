package services.store;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static services.store.OrderApi.BASE_PATH;
import static services.store.OrderApi.BASE_URL;

public class DeleteOrderApi {
    private RequestSpecification spec;

    public DeleteOrderApi() {
        spec = given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .log().all();
    }

    public Response deleteOrderById(long orderId) {
        return given(spec)
                .basePath(BASE_PATH + "/deleteOrder")
                .when()
                .get()
                .then()
                .log().all()
                .extract().response();
    }
}
