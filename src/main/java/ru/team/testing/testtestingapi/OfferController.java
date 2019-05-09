package ru.team.testing.testtestingapi;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OfferController {
    private final OffersRepository offersRepository;

    @GetMapping("/offers")
    public ResponseEntity<Decision> decision(@CookieValue(required = false) String userId) {
        if(userId == null) {
            return ResponseEntity.status(200).header("set-cookie", "userId=" + UUID.randomUUID())
                    .body(new Decision());
        }

        return ResponseEntity.ok(Decision.builder()
                .status(Status.SUCCESS)
                .offers(offersRepository.getOffers(userId))
                .build()
        );

    }
}
