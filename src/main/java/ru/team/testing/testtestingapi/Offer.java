package ru.team.testing.testtestingapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Offer {
    private BigDecimal monthlyPayment;
    private BigDecimal rate;
    private int        term;
}
