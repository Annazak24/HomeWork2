package services.store;

import dto.OrderDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class OrderApi {

    static final String BASE_URL = System.getProperty("base.url", "${base.url}");
    static final String BASE_PATH = "/store/order/";
    private RequestSpecification spec;

    public OrderApi() {
        spec = given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .log().all();
    }

    public ValidatableResponse createOrder(OrderDTO orderDTO) {
        return given(spec)
                .basePath(BASE_PATH)
                .body(orderDTO)
                .when()
                .post()
                .then()
                .log()
                .all();

    }

    public Response getOrderById(long orderId) {
        return given(spec)
                .basePath(BASE_PATH + orderId)
                .when()
                .get()
                .then()
                .log().all()
                .extract().response();
    }
}
