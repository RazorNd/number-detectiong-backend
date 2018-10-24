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

import lombok.SneakyThrows;

import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_BYTE_GRAY;

public class ImageConverterImpl implements ImageConverter {

    private final int scaleSmooth;

    public ImageConverterImpl(int scaleSmooth) {
        this.scaleSmooth = scaleSmooth;
    }

    @Override
    @SneakyThrows
    public float[] resize(BufferedImage image, int size) {
        BufferedImage bufferedImage = scaledImage(image, size);

        float[] pixels = new float[size * size];
        float[] pixel = new float[4];

        var raster = bufferedImage.getRaster();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                pixels[i * size + j] = 255.f - raster.getPixel(j, i, pixel)[0];
            }
        }

        return pixels;
    }

    private BufferedImage scaledImage(BufferedImage image, int size) {
        var scaledInstance = image.getScaledInstance(size, size, scaleSmooth);
        var bufferedImage = new BufferedImage(size, size, TYPE_BYTE_GRAY);
        var graphics = bufferedImage.createGraphics();
        graphics.drawImage(scaledInstance, 0, 0, null);
        graphics.dispose();
        return bufferedImage;
    }
}
