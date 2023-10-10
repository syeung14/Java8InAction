package learning;

import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * https://www.callicoder.com/java-8-completablefuture-tutorial/
 */
@Log4j2
public class CompletablefutureTutorial {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<String> data = Arrays.asList("one", "two");

        CompletablefutureTutorial ct = new CompletablefutureTutorial();
        ct.thenApplyTest(data);
    }

    void thenApplyTest(List<String> data) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = getStringCompletableFuture();
        CompletableFuture<String> nfuture =
            future.thenApply(n -> {return "enriched " + n;})
                  .thenApply(g -> " 1 more " + g);

        System.out.println("new data:" + nfuture.get());
    }

    String supplyAsyncTest(List<String> data) {
        CompletableFuture<String> future = getStringCompletableFuture();

        String res = future.join();
        log.info("supplyAsync finished:" + res);
        return res;
    }

    private static CompletableFuture<String> getStringCompletableFuture() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {

            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                return "Get and return ";
            }
        });
        return future;
    }

    void runAsyncTest(List<String> data) {

        log.info("beginning");
        List<CompletableFuture<Void>> jobList =
            data.stream().map(r -> CompletableFuture.runAsync(() -> {
                log.info("running {}", r);
            })).collect(Collectors.toList());
        jobList.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

}
