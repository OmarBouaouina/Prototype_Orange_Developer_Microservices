package com.orange.usermicroservice.custom;

import java.nio.ByteBuffer;
import java.util.UUID;

public class IdGenerator {
    private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    private static String encodeBase62(byte[] input){
        char[] chars = new char[input.length];
        for(int i=0; i < input.length; i++){
            chars[i] = BASE62[((input[i] & 0xFF) % BASE62.length)];
        }
        return new String(chars);
    }

    public static String generateId(){
        UUID uuid = UUID.randomUUID();
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return encodeBase62(bb.array());
    }
}
