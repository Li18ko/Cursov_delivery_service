package com.example.delivery_service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        // Создание экземпляра объекта MessageDigest для алгоритма SHA-256
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        // Преобразование пароля в байтовый массив
        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);

        // Хэширование пароля
        byte[] hashedBytes = digest.digest(passwordBytes);

        // Преобразование байтового массива в шестнадцатеричную строку
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : hashedBytes) {
            stringBuilder.append(String.format("%02x", b));
        }
        return stringBuilder.toString();
    }
}