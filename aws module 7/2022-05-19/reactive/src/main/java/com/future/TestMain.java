package com.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TestMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Executor executor = Executors.newFixedThreadPool(2);
        System.out.println("Start");
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("current thread: " + Thread.currentThread().getId());
            return "Hello";
        }).thenApplyAsync(s -> {
            if (s != null) throw new RuntimeException("test");
            System.out.println("current thread: " + Thread.currentThread().getId());
            return s + " World";
        }, executor).handle((s, throwable) -> {
            if (throwable != null) {
                System.out.println("Error: " + throwable.getMessage());
                return "Error";

            }
            System.out.println("current thread: " + Thread.currentThread().getId());
            return s;
        });
        System.out.println("End");
        System.out.println(future.get());
        System.out.println("end 1");
    }
}
