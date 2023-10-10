package learning;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * https://www.baeldung.com/java-completablefuture
 */
public class GuideCompletableFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new GuideCompletableFuture().guideFuture();
    }

    void guideFuture() throws ExecutionException, InterruptedException {
        Future<String> completableFuture =
            CompletableFuture.completedFuture("Hello");

        String res = completableFuture.get();
        System.out.println(res);
    }

}
