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

package com.eagle.eye.organisation.service;

import com.eagle.eye.organisation.controller.OrganisationController;
import com.eagle.eye.organisation.model.Organisation;
import com.eagle.eye.organisation.repository.OrganisationRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * Created : 31/03/2023 09:47
 * Project : ea-organisation-service
 * IDE : IntelliJ IDEA
 *
 * @author Aliaksandr_Leanovich
 * @version 1.0
 */
@Slf4j
@ExtendWith(MockitoExtension.class)
class OrganisationServiceTests {

    private static final EasyRandom R = new EasyRandom();

    @Mock
    private OrganisationRepository repository;

    @MockBean
    @InjectMocks
    private OrganisationService service;

    @Test
    void testServiceInstantiated() {
        log.info("{} instantiated : {}", OrganisationController.class.getName(), !Objects.isNull(service));
        Assertions.assertThat(service).isNotNull();
    }

    @Test
    void testGet() {
        Optional<Organisation> queried = Optional.of(R.nextObject(Organisation.class));
        Mockito.when(repository.findById(queried.get().getId())).thenReturn(queried);
        Assertions.assertThat(service.get(queried.get().getId())).isEqualTo(queried);
    }

    @Test
    void testSave() {
        Organisation created = R.nextObject(Organisation.class);
        Organisation saved = new Organisation(
                UUID.randomUUID(),
                created.getName(),
                created.getContactName(),
                created.getContactEmail(),
                created.getContactPhone()
        );
        log.debug("Created Id: {}; Saved Id: {}", created.getId(), saved.getId());

        Mockito.when(repository.save(Mockito.any())).thenReturn(saved);
        Assertions.assertThat(service.save(created)).isEqualTo(saved);
    }

    @Test
    void testUpdate() {
        Optional<Organisation> updated = Optional.of(R.nextObject(Organisation.class));

    }

    @Test
    void testDelete() {
        Optional<Organisation> deleted = Optional.of(R.nextObject(Organisation.class));

    }
}