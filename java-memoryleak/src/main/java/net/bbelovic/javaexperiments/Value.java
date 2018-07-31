package net.bbelovic.javaexperiments;

import java.util.Arrays;
import java.util.Random;

import static java.lang.String.format;

public final class Value {
    private final Object [] val;
    private final Random random = new Random();

    Value(int size) {
        val = new Object[size];
        for (var i = 0; i < size; i++) {
            val[i] = randomString();
        }
    }

    public Object[] getVal() {
        return val;
    }

    @Override
    public String toString() {
        return format("Value[val=%s]", Arrays.toString(val));
    }

    private String randomString() {
        return "XXX" + random.nextInt(1_000_000_000);
    }
}
