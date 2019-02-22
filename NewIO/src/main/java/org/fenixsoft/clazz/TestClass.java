package org.fenixsoft.clazz;

public class TestClass {
    private int m;

    public int inc() {
        return m + 1;
    }

    public static void main(String[] args) {
        int concurrencyLevel = 16;
        int sshift = 0;
        int ssize = 1;
        while (ssize < concurrencyLevel) {
            ++sshift;
            ssize <<= 1;
        }

        System.out.println(sshift);
        System.out.println(ssize);
    }
}