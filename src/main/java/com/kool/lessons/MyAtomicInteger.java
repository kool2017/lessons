package com.kool.lessons;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author luyu
 */
public class MyAtomicInteger {
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static final long valueOffset;

    static {
        try {
            valueOffset = unsafe.objectFieldOffset
                    (AtomicInteger.class.getDeclaredField("value"));
        } catch (Exception ex) { throw new Error(ex); }
    }

    private volatile int value;

    public final int getAndIncrement() {
        return unsafe.getAndAddInt(this, valueOffset, 1);
    }

    public static void main(String[] args) throws NoSuchFieldException {
        AtomicInteger inta = new AtomicInteger(10);
        AtomicInteger intb = new AtomicInteger(1000);
        inta.addAndGet(1);
        Field field = AtomicInteger.class.getDeclaredField("value");
        System.out.println(field);

        MyAtomicInteger myAtomicInteger = new MyAtomicInteger();
        int cnt =myAtomicInteger.getAndIncrement();
        System.out.println(cnt);
    }
}
