package otus.api;

import dto.OrderDTO;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import services.store.OrderApi;

import static org.hamcrest.Matchers.equalTo;

public class CreateOrderTest {

//Тест№1: Проверка обновления данных заказа при повторном создании с тем же ID
// Что проверяет тест:
// 1) Создаем заказ с id = 9 и начальными данными (petId = 1)
// 2) Получаем заказ по id и убеждаемся, что сохранены первоначальные данные
// 3) Повторно отправляем POST-запрос с тем же id = 9, но с измененными данными (petId = 3)
// 4) Выполняем GET /store/order/{id}
// 5) Проверяем, что данные заказа обновились и в системе сохранена последняя версия отправленных данных

    @Test
    void createOrderTest() {
        OrderApi orderApi = new OrderApi();

        OrderDTO orderDto1 = OrderDTO.builder()
                .id(9)
                .petId(1)
                .quantity(1)
                .shipDate("2025-12-07T08:13:30.538Z")
                .status("placed")
                .complete(true)
                .build();

        OrderDTO orderDto2 = OrderDTO.builder()
                .id(9)
                .petId(3)
                .quantity(1)
                .shipDate("2025-12-07T08:13:30.538Z")
                .status("placed")
                .complete(true)
                .build();

        orderApi.createOrder(orderDto1);

        orderApi.getOrderById(9)
                .then()
                .statusCode(200)
                .body("id", equalTo(9))
                .body("petId", equalTo(1));

        orderApi.createOrder(orderDto2);

        orderApi.getOrderById(9)
                .then()
                .statusCode(200)
                .body("id", equalTo(9))
                .body("petId", equalTo(3));
    }

// Тест№2: Проверка установки значений по умолчанию при создании заказа только с ID
// Что проверяет тест:
// 1) Создаем заказ, передавая только id
// 2) Выполняем GET /store/order/{id}
// 3) Проверяем, что система автоматически заполняет остальные поля
//    значениями по умолчанию
// Ожидаемое поведение:
// - petId и quantity устанавливаются в 0
// - complete устанавливается в false
// - заказ успешно сохраняется и доступен для получения

    @Test
    void createOrderOnlyIDTest() {
        OrderApi orderApi = new OrderApi();

        OrderDTO orderDto = OrderDTO.builder()
                .id(20)
                .build();

        orderApi.createOrder(orderDto);

        orderApi.getOrderById(20)
                .then()
                .statusCode(200)
                .body("id", equalTo(20))
                .body("petId", equalTo(0))
                .body("quantity", equalTo(0))
                .body("complete", equalTo(false));
    }
}
