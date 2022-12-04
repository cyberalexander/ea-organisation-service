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
package com.eagle.eye.organisation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * {@link EnableEurekaClient} annotation tells the Spring Boot that you’re going to use a modified RestTemplate class
 * (this isn’t how the Standard Spring RestTemplate would work out of the box) whenever you make a REST service call.
 * This RestTemplate class will allow you to pass in a logical service ID for the service you’re trying to invoke:
 * <p>
 * <code>
 * ResponseEntity<String> restExchange = restTemplate.exchange(http://logical-service-id/name/{firstName}/{lastName})
 * </code>
 * <p>
 * Under the covers, the RestTemplate class will contact the Eureka service and look up the physical location of one or
 * more of the “name” service instances. As a consumer of the service, your code never has to know where that
 * service is located.
 */
@SpringBootApplication
@EnableEurekaClient
public class EaOrganisationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EaOrganisationServiceApplication.class, args);
    }

}
