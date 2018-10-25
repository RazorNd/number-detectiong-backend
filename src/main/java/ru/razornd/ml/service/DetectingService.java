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

import java.awt.image.BufferedImage;

/**
 * Интерфейс сервиса распознавания чисел на картинке.
 *
 * @author Daniil <razornd> Razorenov
 */
public interface DetectingService {

    /**
     * Метод распознает число изображенное на картинке.
     *
     * @param image изображение с числом.
     * @return число изображенное на картинке.
     */
    int predict(BufferedImage image);
}
