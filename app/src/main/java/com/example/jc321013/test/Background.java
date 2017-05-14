package com.example.jc321013.test;

/**
 * Created by jc321013 on 14/05/2017.
 */

class Background {
    static void run(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.start();
    }
}