package ru.team.testing.testtestingapi;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class OffersRepository {
    public List<Offer> getOffers(String userId) {
        Random random = new Random();
        return IntStream.range(1, random.nextInt(20) + 5)
                .mapToObj(operand -> Offer.builder()
                        .monthlyPayment(BigDecimal.valueOf(random.nextInt(60000) + 40000))
                        .rate(BigDecimal.valueOf(random.nextFloat() * 2 + 9))
                        .term(operand)
                        .build()
                )
                .collect(Collectors.toList());

    }
}
