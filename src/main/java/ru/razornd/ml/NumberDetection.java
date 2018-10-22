package ru.razornd.ml;

import org.tensorflow.SavedModelBundle;
import org.tensorflow.Tensor;

import java.nio.FloatBuffer;

public class NumberDetection {

    private static final String X = "Placeholder";
    private static final String OUTPUT = "ArgMax_1";
    private static final long[] SHAPE = new long[]{1, 28 * 28};
    private static final String SERVE = "serve";

    private final SavedModelBundle model;

    public NumberDetection(String exportDirPath) {
        model = SavedModelBundle.load(exportDirPath, SERVE);
    }

    public int detect(FloatBuffer image) {
        Tensor<Long> tensor = model.session()
                .runner()
                .feed(X, Tensor.create(SHAPE, image))
                .fetch(OUTPUT)
                .run()
                .get(0)
                .expect(Long.class);

        long[] result = new long[1];

        tensor.copyTo(result);

        return (int) result[0];
    }
}
