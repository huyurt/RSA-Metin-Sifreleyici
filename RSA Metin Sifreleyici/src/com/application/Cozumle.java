package com.application;

import org.springframework.security.crypto.codec.Base64;

import java.math.BigInteger;
import java.nio.charset.Charset;

public class Cozumle {
    private BigInteger N;
    private BigInteger D;
    private String sifreliMetin;

    private String orjinalMetin = "";

    public Cozumle(BigInteger n, BigInteger d, String sifreliMetin) {
        N = n;
        D = d;
        this.sifreliMetin = sifreliMetin;
        cozumle(sifreliMetin);
    }

    public String getOrjinalMetin() {
        return orjinalMetin;
    }

    private void cozumle(String metin) {
        byte[] decodedBytes = Base64.decode(metin.getBytes());
        BigInteger sifre = new BigInteger(new String(decodedBytes, Charset.forName("UTF-8")));
        BigInteger orjinal = sifre.modPow(D, N);
        orjinalMetin = new String(orjinal.toByteArray());
    }
}