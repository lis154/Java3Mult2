package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by i.lapshinov on 04.09.2018.
 */
public class Car implements Runnable {
   // public static CountDownLatch cb = new CountDownLatch(1);
    private static int CARS_COUNT;
    private CyclicBarrier cb2;
    private static AtomicInteger ai = new AtomicInteger(0);
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed, CyclicBarrier cb2) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.cb2 = cb2;

    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(50 + (int)(Math.random() * 80));

            System.out.println(this.name + " готов");
            cb2.await();
            //cb.await();
            cb2.await();

        //cb.countDown();

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        Thread.sleep(1000);
        if (ai.incrementAndGet() == 1)
        {
            System.out.println(name + " WIN");
        }
        cb2.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}