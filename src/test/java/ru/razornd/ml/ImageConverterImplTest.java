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

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;

import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.rangeClosed;
import static org.assertj.core.util.Arrays.asList;

public class ImageConverterImplTest {

    private ImageConverter imageConverter = new ImageConverterImpl(Image.SCALE_SMOOTH);

    @Test
    public void resize() {
        System.out.println(
                rangeClosed(1, 9)
                        .mapToObj(this::doResize)
                        .collect(joining(",\n"))
        );
    }

    private String doResize(int i) {
        try {
            float[] image = imageConverter.resize(ImageIO.read(new File("api/test-" + i + ".jpg")), 28);
            return "[" + asList(image)
                    .stream()
                    .map(Object::toString)
                    .collect(joining(", ")) + "]";
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

    }
}