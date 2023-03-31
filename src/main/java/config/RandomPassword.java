package config;

import java.security.SecureRandom;

public class RandomPassword {

    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%^&*()-_=+[]{}|;:,.<>/?";

    public static String generatePassword(int length) {
        StringBuilder passwordBuilder = new StringBuilder(length);
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            passwordBuilder.append(randomChar);
        }

        return passwordBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(generatePassword(10));
    }

}
