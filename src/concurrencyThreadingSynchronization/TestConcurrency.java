package concurrencyThreadingSynchronization;

import java.io.IOException;

/**
 * Created by BSV on 17.01.2017.
 */
class TestConcurrency {



    private Object key1 = new Object();
    private Object key2 = new Object();

    public static void main(String[] args) {

        Runtime rt = Runtime.getRuntime();
        rt.addShutdownHook(new TestConcurrency().new ClosingThreadMy());


        TestConcurrency test = new TestConcurrency();
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                test.method1();
            }
        };

/*
    looks like racing. and at the end stackOverflow
 */

        Runnable r2 = () -> test.method2();
        Runnable r3 = () -> test.method3();
        Thread thr1 = new Thread(r1);
        Thread thr2 = new Thread(r2);
        Thread thr3 = new Thread(r3);
        thr1.setName("Thread1");
        thr2.setName("Thread2");
        thr3.setName("Thread3");
        thr1.start();
//        thr2.start();
//        thr3.start();

        System.out.println("we are  ThreadName - " + Thread.currentThread().getName());

    }

    public void method1() {
        synchronized (key1) {
            System.out.println("we are doing method 1. ThreadName - " + Thread.currentThread().getName());
            System.out.println("we are doing method 1. Thread Stack Trace - " + Thread.currentThread().getStackTrace());
            method2();
        }
    }

    public void method2() {
        synchronized (key2) {
            System.out.println("we are doing method 2. ThreadName - " + Thread.currentThread().getName());
            method1();
        }
    }

    public void method3() {
        synchronized (key1) {
            System.out.println("we are doing method 3. ThreadName - " + Thread.currentThread().getName());
        }

    }


    /*
    this is class whose instance of class will be used by
    Runtime.addShutdownHook to run method run() after program is stopped
     */
    private class ClosingThreadMy extends Thread{
        public void run(){
            System.out.println("\n**** Runtime.ShutdownHook ****");
            System.out.println("**** Runtime.ShutdownHook. Closing thread is here !****");
            System.out.println("**** Runtime.ShutdownHook. Thread name - " + Thread.currentThread().getName() + " ***");
            System.out.println("**** Runtime.ShutdownHook. Done nothing ****");

            /*
            to run app (notepad)
             */

            try {
                Runtime.getRuntime().exec("notepad");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
