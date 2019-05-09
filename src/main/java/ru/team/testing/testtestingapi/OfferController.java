package ru.team.testing.testtestingapi;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://demo.ru:3000", allowCredentials = "true")
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
