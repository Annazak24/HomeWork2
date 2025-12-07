package otus.api;

import dto.OrderDTO;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Test;
import services.store.OrderApi;

import static org.hamcrest.Matchers.equalTo;

public class GetOrderByIdTest {

    // Тест №1: Проверяем успешное получение заказа по id (ожидаем статус 200)
    // Что проверяет тест:
    // 1) Создаем заказ с конкретными данными
    // 2) Выполняем GET по ID созданного заказа
    // 3) Проверяем:
    //    - что вернулся статус 200
    //    - что поля в response соответствуют тем, что мы отправили
    @Test
    void getOrderStatusCodeTest() {
        OrderApi orderApi = new OrderApi();

        OrderDTO orderDto = OrderDTO.builder()
                .id(30)
                .petId(3)
                .quantity(1)
                .shipDate("2025-10-10T00:00:00.000+0000")
                .status("placed")
                .complete(true)
                .build();

        // Создаем заказ
        orderApi.createOrder(orderDto);

        // Получаем заказ и проверяем содержимое
        orderApi.getOrderById(30)
                .then()
                .statusCode(200) // Проверяем успешный GET
                .body("petId", equalTo(3))
                .body("quantity", equalTo(1))
                .body("shipDate", equalTo("2025-10-10T00:00:00.000+0000"))
                .body("status", equalTo("placed"))
                .body("complete", equalTo(true));
    }

    // Тест №2: Проверяем соответствие ответа JSON-схеме
    // Что проверяет тест:
    // 1) Создаем заказ
    // 2) Выполняем GET /store/order/{id}
    // 3) Проверяем, что структура JSON ответа строго соответствует схеме
    //    (наличие всех полей, корректные типы данных, обязательные элементы)
    @Test
    void getOrderByIdResponseBodyTest() {
        OrderApi orderApi = new OrderApi();

        // Создаем заказ
        OrderDTO orderDto = OrderDTO.builder()
                .id(40)
                .petId(99)
                .quantity(5)
                .shipDate("2027-07-07T00:00:00.000Z")
                .status("approved")
                .complete(true)
                .build();

        orderApi.createOrder(orderDto);

        // Проверяем JSON-схему
        orderApi.getOrderById(40)
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/CreateOrder.JSON"));
    }
}
