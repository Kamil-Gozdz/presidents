package com.presidents.controller;


import com.presidents.model.dto.PresidentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.presidents.util.TestUtils.toJson;
import static java.sql.Timestamp.from;
import static java.time.Instant.ofEpochMilli;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PresidentControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void whenPostRequestForPresidentSave_thenCorrectResponse() throws Exception {
        //given
        var president = PresidentDto
                .builder()
                .name("Jan")
                .surname("Kowal")
                .termFrom(from(ofEpochMilli(150000)))
                .termTo(from(ofEpochMilli(160000)))
                .politicalParty("Republican").build();
        //when && then
        mockMvc.perform(post("/presidents/save").content(toJson(president))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("Jan")))
                .andExpect(jsonPath("$.surname", equalTo("Kowal")));
    }

    @Test
    public void whenPostIncorrectPresidentName_thenIncorrectResponse() throws Exception {
        //given
        var president = PresidentDto
                .builder().name("").build();
        //when && then
        mockMvc.perform(post("/presidents/save")
                        .content(toJson(president))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsInAnyOrder("Name must be between 1 and 255 characters")));
    }
}
