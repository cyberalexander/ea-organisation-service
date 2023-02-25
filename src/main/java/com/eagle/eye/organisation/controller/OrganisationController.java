/*
 * MIT License
 *
 * Copyright (c) 2022 cyberalexander
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

import com.eagle.eye.organisation.config.ControllerProperties;
import com.eagle.eye.organisation.model.Organisation;
import com.eagle.eye.organisation.service.OrganisationService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created : 05/12/2022 09:21
 * Project : ea-organisation-service
 * IDE : IntelliJ IDEA
 *
 * @author Aliaksandr_Leanovich
 * @version 1.0
 */
@RestController
@RequestMapping(value = "v1/organisations")
public class OrganisationController {

    private final ControllerProperties controllerProperties;

    private final OrganisationService service;

    public OrganisationController(ControllerProperties controllerProperties, OrganisationService service) {
        this.controllerProperties = controllerProperties;
        this.service = service;
    }

    @GetMapping(value = "/{organisationId}")
    public Organisation getOrganisation(@PathVariable("organisationId") UUID organisationId) {
        System.out.println("Organisation SSN: " + controllerProperties.getOrganisationSsn());
        return service.get(organisationId)
                .orElseThrow(() -> new RuntimeException("Organisation with id " + organisationId + " not found."));
    }
    @PostMapping
    public void saveOrganisation(@RequestBody Organisation request) {
        System.out.println("Organisation SSN: " + controllerProperties.getOrganisationSsn());
        service.save(request);
    }

    @PutMapping
    public Organisation updateOrganisation(@RequestBody Organisation request) {
        System.out.println("Organisation SSN: " + controllerProperties.getOrganisationSsn());
        return service.update(request);
    }

    @DeleteMapping(value="/{organisationId}")
    public void deleteOrganisation( @PathVariable("organisationId") UUID organisationId) {
        System.out.println("Organisation SSN: " + controllerProperties.getOrganisationSsn());
        service.delete(organisationId);
    }
}
