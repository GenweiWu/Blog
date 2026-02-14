package com.njust.junit5;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Junit5RepeatedTest {

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(0);
    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    @RepeatedTest(3)
    public void testRepeat() {
        System.out.println("testRepeat");
    }

    @RepeatedTest(3)
    void testWithRepetitionInfo(RepetitionInfo repetitionInfo) {
        int currentRepetition = repetitionInfo.getCurrentRepetition();
        int totalRepetitions = repetitionInfo.getTotalRepetitions();
        System.out.println("第" + currentRepetition + "次，共" + totalRepetitions + "次");
    }

    @RepeatedTest(value = 3, name = "重复 {currentRepetition}/{totalRepetitions}")
    void testWithCustomInfo() {
        System.out.println("testWithCustomInfo");
    }

    @RepeatedTest(100)
    void testAtomicInteger(RepetitionInfo repetitionInfo) {
        atomicInteger.addAndGet(1);
        ATOMIC_INTEGER.addAndGet(1);

        if (repetitionInfo.getCurrentRepetition() == repetitionInfo.getTotalRepetitions()) {
            assertEquals(100, ATOMIC_INTEGER.get());
            assertEquals(1, atomicInteger.get());
        }
    }
}
