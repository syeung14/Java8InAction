package learning;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ThenCombineFutures {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new ThenCombineFutures().thenCombineTest();
    }

    void thenCombineTest() throws ExecutionException, InterruptedException {
        System.out.println("Retrieving weight.");
        CompletableFuture<Double> weightInKgFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 65.0;
        });

        System.out.println("Retrieving height.");
        CompletableFuture<Double> heightInCmFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 177.8;
        });

        System.out.println("Calculating BMI.");
        CompletableFuture<Double> combinedFuture = weightInKgFuture.thenCombine(
            heightInCmFuture,
            (weightInKg, heightInCm) -> {
                Double heightInMeter = heightInCm / 100;
                return weightInKg / (heightInMeter * heightInMeter);
            });

        System.out.println("Your BMI is - " + combinedFuture.get());
    }
}
