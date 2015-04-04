package com.example.home.bullscows;

public class Generator {

    int a = 0;
    int b;
    int c;
    int d;

    public int[] generate() {


        a = (int) (Math.random() * 9) + 1;
        initializeB();
        initializeC();
        initializeD();
        int[] s = {a, b, c, d};
//        s = a * 1000 + b * 100 + c * 10 + d;

        return s;
    }

    private int initializeB() {
        b = (int) (Math.random() * 10);
        if (b == a)
            initializeB();
        return b;
    }

    private int initializeC() {
        c = (int) (Math.random() * 10);
        if (c == a || c == b)
            initializeC();
        return c;
    }

    private int initializeD() {
        d = (int) (Math.random() * 10);
        if (d == a || d == b || d == c)
            initializeD();
        return d;
    }
}
