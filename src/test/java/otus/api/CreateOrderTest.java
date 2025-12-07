package otus.api;

import dto.OrderDTO;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import services.store.OrderApi;

import static org.hamcrest.Matchers.equalTo;

public class CreateOrderTest {

    // Тест №1: Проверяем успешное создание заказа (ожидаем статус-код 200)
    // Что проверяет тест:
    // - Отправляем запрос на создание заказа
    // - Убеждаемся, что сервер успешно принимает запрос и возвращает статус 200
    @Test
    void createOrderTest() {
        OrderApi orderApi = new OrderApi();

        OrderDTO orderDto = OrderDTO.builder()
                .id(1)
                .petId(1)
                .quantity(1)
                .shipDate("2025-12-07T08:13:30.538Z")
                .status("placed")
                .complete(true)
                .build();

        orderApi.createOrder(orderDto)
                .statusCode(HttpStatus.SC_OK); // Проверяем, что пришел статус 200
    }

    // Тест №2: Проверяем корректность данных в ответе (проверяем, что возвращается правильный id)
    // Что проверяет тест:
    // - Создаем заказ с id = 20
    // - Получаем ответ сервера
    // - Проверяем, что в response поле "id" действительно равно 20
    @Test
    void createOrderResponseBodyTest() {
        OrderApi orderApi = new OrderApi();

        OrderDTO orderDto = OrderDTO.builder()
                .id(20)
                .petId(7)
                .quantity(1)
                .shipDate("2026-01-01T00:00:00.000Z")
                .status("placed")
                .complete(false)
                .build();

        orderApi.createOrder(orderDto)
                .statusCode(200)          // Проверяем успешный запрос
                .body("id", equalTo(20)); // Проверяем правильность id в теле ответа
    }
}
