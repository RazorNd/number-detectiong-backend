/*
 * number-detecting-backend
 * Copyright (C) 2018 Daniil Razorenov
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ru.razornd.ml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.EnableWebFlux;
import ru.razornd.ml.component.ImageConverter;
import ru.razornd.ml.component.ImageConverterImpl;
import ru.razornd.ml.component.NumberDetection;
import ru.razornd.ml.service.DetectingService;
import ru.razornd.ml.service.TensorFlowDetectingService;

import static java.awt.Image.SCALE_SMOOTH;

@SpringBootApplication
@EnableWebFlux
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public NumberDetection numberDetection() {
        return new NumberDetection("model");
    }

    @Bean
    public ImageConverter imageConverter() {
        return new ImageConverterImpl(SCALE_SMOOTH);
    }

    @Bean
    public DetectingService detectingService() {
        return new TensorFlowDetectingService(imageConverter(), numberDetection());
    }
}
