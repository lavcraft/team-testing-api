package ru.team.testing.testtestingapi;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebFluxTest
@RunWith(SpringRunner.class)
@Epic("Получение решения от банков")
public class OfferControllerTest {
    @Autowired WebTestClient    webTestClient;
    @MockBean  OffersRepository offersRepository;

    @Test
    @Step
    @Feature("Обновление информациии о решении")
    @DisplayName("IN_PROGRESS пока банки обрабатывают запрос")
    public void should_return_status() throws Exception {
        webTestClient.get()
                .uri("/offers")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.status").isEqualTo("IN_PROGRESS");
    }

    @Test
    @Feature("Обновление информациии о решении")
    @DisplayName("SUCCESS когда пришел ответ от банков для пользователя")
    public void should_change_status_when_you_wait_so_long() throws Exception {
        should_return_status();

        when(offersRepository.getOffers(anyString())).thenReturn(Arrays.asList(
                new Offer(),
                new Offer()
        ));

        webTestClient.get()
                .uri("/offers")
                .cookie("userId", "пятачок")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.status").isEqualTo("SUCCESS")
                .jsonPath("$.offers").value(hasSize(2));
    }

    @Test
    @Feature("Обновление информациии о решении")
    @DisplayName("Метим пользователя если он анонимный")
    public void should_set_user_id_in_cookie() throws Exception {

        webTestClient.get()
                .uri("/offers")
                .exchange()
                .expectHeader().value("set-cookie", containsString("userId="));
    }
}
