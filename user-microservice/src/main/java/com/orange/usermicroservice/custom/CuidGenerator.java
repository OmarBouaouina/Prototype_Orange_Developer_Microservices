package com.orange.usermicroservice.custom;

import java.security.SecureRandom;

public class CuidGenerator {

    private static final String alphaString = "abcdefghijklmnopqrstuvwxyz";

    private static final String numString = "0123456789";

    public static SecureRandom random = new SecureRandom();

    public static String generateId(){
        StringBuilder sb = new StringBuilder(8);
        for(int i = 0; i < 4; i++){
            int rndCharAt = random.nextInt(alphaString.length());
            char rndChar = alphaString.charAt(rndCharAt);
            sb.append(rndChar);
        }
        for(int i = 0; i < 4; i++){
            int rndCharAt = random.nextInt(numString.length());
            char rndChar = numString.charAt(rndCharAt);
            sb.append(rndChar);
        }
        return sb.toString();
    }
}
