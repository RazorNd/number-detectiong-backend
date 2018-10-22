package ru.razornd.ml;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.Optional;

@RestController
@RequestMapping("/api/number-detection")
public class PredictionController {

    private final NumberDetection numberDetection;

    public PredictionController(NumberDetection numberDetection) {
        this.numberDetection = numberDetection;
    }

    @PostMapping
    public int predict(@RequestParam("image") MultipartFile image) throws IOException {
        return numberDetection.detect(readImage(image.getInputStream()));
    }

    @SneakyThrows
    private static FloatBuffer readImage(InputStream input) {
        BufferedImage read = ImageIO.read(input);

        Image scaledInstance = read.getScaledInstance(28, 28, Image.SCALE_SMOOTH);

        BufferedImage bufferedImage = new BufferedImage(28, 28, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.drawImage(scaledInstance, 0, 0, null);
        graphics.dispose();

        DataBuffer dataBuffer = bufferedImage.getData().getDataBuffer();

        byte[] bytes = Optional.of(dataBuffer)
                .filter(DataBufferByte.class::isInstance)
                .map(DataBufferByte.class::cast)
                .map(DataBufferByte::getData).orElseThrow();

        return ByteBuffer.wrap(bytes).asFloatBuffer();
    }
}
