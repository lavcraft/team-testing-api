package ru.team.testing.testtestingapi;

import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.Cookie;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@RunWith(SpringRunner.class)
public class OfferControllerTest {
    @Autowired MockMvc          mockMvc;
    @MockBean  OffersRepository offersRepository;

    @Test
    public void should_return_status() throws Exception {
        mockMvc.perform(get("/offers"))
                .andExpect(
                        status().isOk()
                )
                .andExpect(jsonPath("$.status", equalTo("IN_PROGRESS")));
    }

    @Test
    public void should_change_status_when_you_wait_so_long() throws Exception {
        mockMvc.perform(get("/offers"))
                .andExpect(
                        status().isOk()
                )
                .andExpect(jsonPath("$.status", equalTo("IN_PROGRESS")));

        when(offersRepository.getOffers(anyString())).thenReturn(Arrays.asList(
                new Offer(),
                new Offer()
        ));

        mockMvc.perform(get("/offers").cookie(new Cookie("userId", "пятачок")))
                .andExpect(
                        status().isOk()
                )
                .andExpect(jsonPath("$.status", equalTo("SUCCESS")))
                .andExpect(jsonPath("$.offers", IsCollectionWithSize.hasSize(2)));
    }

    @Test
    public void should_set_user_id_in_cookie() throws Exception {
        mockMvc.perform(get("/offers"))
                .andExpect(
                        cookie().value("userId", notNullValue())
                );
    }
}
