package ru.team.testing.testtestingapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
public class OffersRepositoryTest {
    OffersRepository offersRepository = new OffersRepository();

    @Test
    @Repeat(1000)
    public void should_return_offers_by_userid() {
        List<Offer> пятачок = offersRepository.getOffers("пятачок");

        assertThat(пятачок.size()).isGreaterThan(0);
        assertThat(пятачок).extracting(Offer::getMonthlyPayment).doesNotContainNull();
        assertThat(пятачок).extracting(Offer::getRate).doesNotContainNull();
        assertThat(пятачок).extracting(Offer::getTerm).allMatch(term -> term > 0);
        assertThat(пятачок).extracting(Offer::getMonthlyPayment).allMatch(payment -> payment.compareTo(BigDecimal.valueOf(30000)) > 0);
    }
}
