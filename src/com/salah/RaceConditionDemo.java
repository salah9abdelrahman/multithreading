package com.salah;

import java.util.*;


class RaceConditionDemo {
    public static void main(String[] args) throws InterruptedException {
        RaceConditionDemo.runTest();

    }

    int randInt;
    Random random = new Random(System.currentTimeMillis());

    void printer() {

        int i = 1000000;
        while (i != 0) {
            if (randInt % 5 == 0) {
                if (randInt % 5 != 0)
                    System.out.println(randInt);
            }
            i--;
        }
    }

    void modifier() {

        int i = 1000000;
        while (i != 0) {
            randInt = random.nextInt(1000);
            i--;
        }
    }

    public static void runTest() throws InterruptedException {


        final RaceConditionDemo rc = new RaceConditionDemo();
        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                rc.printer();
            }
        });
        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                rc.modifier();
            }
        });


        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}


class RaceConditionSolvedUsingSynchronized {
    public static void main(String args[]) throws InterruptedException {
        RaceConditionSolvedUsingSynchronized.runTest();
    }
    int randInt;
    Random random = new Random(System.currentTimeMillis());

    void printer() {

        int i = 1000000;
        while (i != 0) {
            synchronized(this) {
                if (randInt % 5 == 0) {
                    if (randInt % 5 != 0)
                        System.out.println(randInt);
                }
            }
            i--;
        }
    }

    void modifier() {

        int i = 1000000;
        while (i != 0) {
            synchronized(this) {
                randInt = random.nextInt(1000);
                i--;
            }
        }
    }

    public static void runTest() throws InterruptedException {


        final RaceConditionSolvedUsingSynchronized rc = new RaceConditionSolvedUsingSynchronized();
        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                rc.printer();
            }
        });
        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                rc.modifier();
            }
        });


        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}