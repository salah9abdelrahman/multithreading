package com.salah;

public class ThreadJoin {
    public static void main( String args[] ) throws Exception {
        ExecuteMe executeMe = new ExecuteMe();
        Thread innerThread = new Thread(executeMe);
        innerThread.start();
        // to suspend the execution of the current thread(main now) and complete another thread(innerThread)
        innerThread.join();
        System.out.println("Main thread exiting.");
    }
    static class ExecuteMe implements Runnable {

        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println("Hello. innerThread going to sleep");
            } catch (InterruptedException ie) {
                // swallow interrupted exception
            }
        }
    }
}
