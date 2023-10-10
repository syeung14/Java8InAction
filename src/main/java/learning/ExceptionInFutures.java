package learning;

import java.util.concurrent.CompletableFuture;

public class ExceptionInFutures {

    public static void main(String[] args) {

    }

    void exceptionTest() {
        CompletableFuture.supplyAsync(() -> {
            // Code which might throw an exception
            return "Some result";
        }).exceptionally(ex -> {
            ex.printStackTrace();
            return "";
        }).thenApply(result -> {
            return "processed result";
        }).exceptionally(eex -> {
            return "xx";
        }).thenApply(result -> {
            return "result after further processing";
        }).thenAccept(result -> {
            // do something with the final result
        });
    }

}
