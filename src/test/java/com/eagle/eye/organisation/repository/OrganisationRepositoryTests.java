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

package com.eagle.eye.organisation.repository;

import com.eagle.eye.organisation.model.Organisation;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

/**
 * Created : 03/04/2023 09:17
 * Project : ea-organisation-service
 * IDE : IntelliJ IDEA
 *
 * @author Aliaksandr_Leanovich
 * @version 1.0
 */
@Slf4j
@DataJpaTest
@ExtendWith(SpringExtension.class)
class OrganisationRepositoryTests {
    private static final EasyRandom R = new EasyRandom();

    @Autowired
    private OrganisationRepository repository;

    @Test
    void getById() {
        Organisation saved = repository.save(R.nextObject(Organisation.class));
        Organisation queried = repository.getById(saved.getId());
        Assertions.assertThat(queried).isEqualTo(saved);
    }

    @Test
    void getByName() {
        Organisation saved = repository.save(R.nextObject(Organisation.class));
        Optional<Organisation> queried = repository.getByName(saved.getName());
        Assertions.assertThat(queried.isPresent()).isTrue();
        Assertions.assertThat(queried.get()).isEqualTo(saved);
    }
}