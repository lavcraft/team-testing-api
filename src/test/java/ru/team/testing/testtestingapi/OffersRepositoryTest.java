package ru.team.testing.testtestingapi;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@Epic("Получение решения от банков")
@Feature("Отображение решения банка")
@RunWith(SpringRunner.class)
public class OffersRepositoryTest {
    OffersRepository offersRepository = new OffersRepository();

    /**
     * <pre>
     * (Offer::getMonthlyPayment).doesNotContainNull();
     * (Offer::getRate).doesNotContainNull();
     * (Offer::getTerm).allMatch(term -> term > 0);
     * (Offer::getMonthlyPayment).allMatch(payment -> payment.compareTo(BigDecimal.valueOf(30000)) > 0);
     * </pre>
     */
    @Test
    @Repeat(1000)
    @DisplayName("Предложения банков должны быть сроком больше 1")
    @Description(useJavaDoc = true)
    public void should_return_offers_by_userid() {
        List<Offer> пятачок = offersRepository.getOffers("пятачок");

        assertThat(пятачок.size()).isGreaterThan(0);
        assertThat(пятачок).extracting(Offer::getMonthlyPayment).doesNotContainNull();
        assertThat(пятачок).extracting(Offer::getRate).doesNotContainNull();
        assertThat(пятачок).extracting(Offer::getTerm).allMatch(term -> term > 0);
        assertThat(пятачок).extracting(Offer::getMonthlyPayment).allMatch(payment -> payment.compareTo(BigDecimal.valueOf(30000)) > 0);
    }
}
