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

import org.tensorflow.SavedModelBundle;
import org.tensorflow.Tensor;

public class NumberDetection {

    private static final String X = "Placeholder";
    private static final String OUTPUT = "ArgMax_1";
    private static final String SERVE = "serve";

    private final SavedModelBundle model;

    public NumberDetection(String exportDirPath) {
        model = SavedModelBundle.load(exportDirPath, SERVE);
    }

    public long detect(float[] image) {
        float[][] images = {image};
        return detect(images)[0];
    }

    public long[] detect(float[][] image) {
        var tensor = model.session()
                .runner()
                .feed(X, Tensor.create(image))
                .fetch(OUTPUT)
                .run()
                .get(0)
                .expect(Long.class);

        long[] result = new long[tensor.numDimensions()];

        tensor.copyTo(result);

        return result;
    }
}
