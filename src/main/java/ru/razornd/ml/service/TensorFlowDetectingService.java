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

package ru.razornd.ml.service;

import ru.razornd.ml.component.ImageConverter;
import ru.razornd.ml.component.NumberDetection;

import java.awt.image.BufferedImage;

public class TensorFlowDetectingService implements DetectingService {

    private final ImageConverter imageConverter;
    private final NumberDetection numberDetection;

    public TensorFlowDetectingService(ImageConverter imageConverter, NumberDetection numberDetection) {
        this.imageConverter = imageConverter;
        this.numberDetection = numberDetection;
    }

    @Override
    public int predict(BufferedImage image) {
        float[] resizeImage = imageConverter.resize(image, 28);

        return (int) numberDetection.detect(resizeImage);
    }
}
