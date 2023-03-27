/*
 * MIT License
 *
 * Copyright (c) 2022-2023 CyberAlexander
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.eagle.eye.organisation.controller;

import com.eagle.eye.organisation.model.Organisation;
import com.eagle.eye.organisation.service.OrganisationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created : 23/03/2023 09:16
 * Project : ea-organisation-service
 * IDE : IntelliJ IDEA
 *
 * @author Aliaksandr_Leanovich
 * @version 1.0
 */
@ActiveProfiles(value = {"test"})
@WebMvcTest(controllers = {OrganisationController.class})
class OrganisationControllerTests {

    private static final EasyRandom EASY_RANDOM = new EasyRandom();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private OrganisationService serviceMock;

    @Autowired
    @InjectMocks
    private OrganisationController controller;

    @Test
    void testControllerInstantiated() {
        Assertions.assertThat(controller).isNotNull();
    }

    @Test
    @SneakyThrows
    void testGetOrganisation() {
        Organisation expected = EASY_RANDOM.nextObject(Organisation.class);

        Mockito.when(serviceMock.get(expected.getId())).thenReturn(Optional.of(expected));

        mockMvc.perform(get("/v1/organisations/{organisationId}", expected.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").hasJsonPath())
                .andExpect(jsonPath("$.contactName").hasJsonPath())
                .andExpect(jsonPath("$.contactEmail").hasJsonPath())
                .andExpect(jsonPath("$.contactPhone").hasJsonPath());
    }

    @Test
    @SneakyThrows
    void testGetOrganisation_fieldIdNotInTheResponse() {
        Organisation expected = EASY_RANDOM.nextObject(Organisation.class);

        Mockito.when(serviceMock.get(expected.getId())).thenReturn(Optional.of(expected));

        mockMvc.perform(get("/v1/organisations/{organisationId}", expected.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").doesNotHaveJsonPath());
    }

    @Test
    @SneakyThrows
    void testSaveOrganisation() {
        Organisation newOrganisation = EASY_RANDOM.nextObject(Organisation.class);

        Mockito.when(serviceMock.save(Mockito.any())).thenReturn(newOrganisation);

        mockMvc.perform(post("/v1/organisations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(newOrganisation)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(mapper.writeValueAsString(newOrganisation.getId())));
    }

    @Test
    @SneakyThrows
    void testUpdateOrganisation() {
        Organisation updated = EASY_RANDOM.nextObject(Organisation.class);

        Mockito.when(serviceMock.update(Mockito.any())).thenReturn(updated);
        Mockito.when(serviceMock.get(updated.getId())).thenReturn(Optional.of(updated));

        // 1. Test that PUT operation invoked successfully
        mockMvc.perform(put("/v1/organisations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updated)))
                .andExpect(status().isOk());

        // 2. Invoke GET operation to check that object was updated during PUT operation
        mockMvc.perform(get("/v1/organisations/{organisationId}", updated.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(updated)))
                .andExpect(jsonPath("$.name").value(updated.getName()));
    }

    @Test
    void testDeleteOrganisation() {
    }
}