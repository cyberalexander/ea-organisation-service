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

import com.eagle.eye.organisation.model.Organisation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Created : 05/12/2022 09:14
 * Project : ea-organisation-service
 * IDE : IntelliJ IDEA
 *
 * @author CyberAlexander
 * @version 1.0
 */
@Service
public class OrganisationService {

    private final CrudRepository<Organisation, UUID> repository;

    public OrganisationService(CrudRepository<Organisation, UUID> repository) {
        this.repository = repository;
    }

    public Optional<Organisation> get(UUID organisationId) {
        return repository.findById(organisationId);
    }

    public Organisation save(Organisation organisation) {
        return repository.save(
                new Organisation(
                        UUID.randomUUID(),
                        organisation.getName(),
                        organisation.getContactName(),
                        organisation.getContactEmail(),
                        organisation.getContactPhone()
                )
        );
    }

    public Organisation update(Organisation organisation) {
        return repository.save(organisation);
    }

    public void delete(UUID organisationId) {
        repository.deleteById(organisationId);
    }
}
