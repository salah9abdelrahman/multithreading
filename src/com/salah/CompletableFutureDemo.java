package com.salah;

import java.util.concurrent.*;

public class CompletableFutureDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        Future<Integer> future = getSquareAsynchronously(3);
        Future<Integer> future = getSquareAsynchronouslyUsingSupplyAsync(3);
        System.out.println("This will print immediately " + Thread.currentThread().getName());
        System.out.println(future.get());
    }


    /**
     * By default, asynchronous execution uses ForkJoinPool.commonPool(), which uses daemon threads to execute the Runnable task.
     * However, if we want, we can provide our own Executor to the runAsync() method as well. Here is the code for it.
     */
    public static void main2(String[] args) throws InterruptedException, ExecutionException {
        Executor executor = Executors.newFixedThreadPool(5);
        Future<Integer> future = getSquareAsynchronously(3, executor);
        System.out.println("This will print immediately " + Thread.currentThread().getName());
        System.out.println(future.get());
    }

    public static Future<Integer> getSquareAsynchronouslyUsingSupplyAsync(int num) throws InterruptedException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Doing some processing " + Thread.currentThread().getName());

            return num * num;
        });
    }
    

    public static Future<Integer> getSquareAsynchronously(int num, Executor executor) {
        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Doing some processing " + Thread.currentThread().getName());
            completableFuture.complete(num * num);

        }, executor);

        return completableFuture;
    }


}
