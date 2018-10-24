/*
 * Copyright (C) 2018
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

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.IOException;

@RestController
@RequestMapping("/api/number-detection")
@Slf4j
public class PredictionController {

    private final NumberDetection numberDetection;
    private final ImageConverter imageConverter;

    public PredictionController(NumberDetection numberDetection, ImageConverter imageConverter) {
        this.numberDetection = numberDetection;
        this.imageConverter = imageConverter;
    }

    @PostMapping
    public long predict(@RequestParam("image") MultipartFile image) throws IOException {
        return numberDetection.detect(imageConverter.resize(ImageIO.read(image.getInputStream()), 28));
    }
}
