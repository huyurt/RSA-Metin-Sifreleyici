package com.application;

import java.math.BigInteger;
import java.util.Random;

public class AsalSayi {
    private Random random = new Random();
    private int bit;
    private BigInteger asalSayi;

    public AsalSayi(int bit) {
        this.bit = bit;
        rastgeleAsalSayiUret(2147483647);
    }

    public BigInteger getAsalSayi() {
        return asalSayi;
    }

    private void setAsalSayi(BigInteger asalSayi) {
        this.asalSayi = asalSayi;
    }

    private BigInteger rastgeleAsalSayiUret(int certainty) {
        byte[] uzunluk = new byte[bit / 8];
        random.nextBytes(uzunluk);
        BigInteger bigInteger = new BigInteger(uzunluk);
        if (bigInteger.signum() == -1) {
            bigInteger = bigInteger.negate();
        }
        if (bigInteger.isProbablePrime(certainty)) {
            setAsalSayi(bigInteger);
            return bigInteger;
        } else {
            return rastgeleAsalSayiUret(certainty);
        }
    }

    public BigInteger hesaplaE(BigInteger Fi) {
        byte[] uzunluk = new byte[bit / 8];
        random.nextBytes(uzunluk);
        BigInteger bigInteger = new BigInteger(uzunluk);
        if (bigInteger.signum() == -1) {
            bigInteger = bigInteger.negate();
        }
        if (bigInteger.equals(BigInteger.ZERO) || bigInteger.equals(BigInteger.ONE) || Fi.subtract(bigInteger).signum() == -1) {
            return hesaplaE(Fi);
        } else {
            if (!Fi.gcd(bigInteger).equals(BigInteger.ONE)) {
                return hesaplaE(Fi);
            }
            return bigInteger;
        }
    }

    public BigInteger hesaplaD(BigInteger Fi, BigInteger E) {
        return E.modInverse(Fi);
    }
}