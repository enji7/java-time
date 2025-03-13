package org.example;

import java.time.Instant;

public class Main {

    public static void main(String[] args) {

        p(Instant.now());

        // seconds
        p(Instant.ofEpochSecond(2_000_000_123L));
        // milliseconds
        p(Instant.ofEpochMilli(2_000_000_123_456L));
        // nanoseconds
        p(Instant.ofEpochSecond(2_000_000_123L, 456_789_999L));

    }

    private static void p(Object o) {
        System.out.println(o.toString());
    }

}