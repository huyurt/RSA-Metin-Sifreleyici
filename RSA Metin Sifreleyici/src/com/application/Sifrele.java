package com.application;

import org.springframework.security.crypto.codec.Base64;

import java.math.BigInteger;
import java.nio.charset.Charset;

public class Sifrele {
    private BigInteger N;
    private BigInteger E;
    private String orjinalMetin;

    private String sifreliMetin = "";

    public Sifrele(BigInteger n, BigInteger e, String orjinalMetin) {
        N = n;
        E = e;
        this.orjinalMetin = orjinalMetin;
        sifrele(orjinalMetin);
    }

    public String getSifreliMetin() {
        return sifreliMetin;
    }

    private void sifrele(String metin) {
        BigInteger orjinal = new BigInteger(metin.getBytes());
        String sMetin = orjinal.modPow(E, N).toString();
        byte[] encodedBytes = Base64.encode(sMetin.getBytes());
        sifreliMetin = new String(encodedBytes, Charset.forName("UTF-8"));
    }
}