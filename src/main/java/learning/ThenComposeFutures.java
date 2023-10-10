package learning;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenComposeFutures {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new ThenComposeFutures().thenComposeTest();
    }

    void thenComposeTest() throws ExecutionException, InterruptedException {
        String userId = "name";

        CompletableFuture<CompletableFuture<Double>> result =
            getUsersDetail(userId).thenApply(user -> getCreditRating(user));

        CompletableFuture<Double> result2 =
            getUsersDetail(userId).thenCompose(user -> getCreditRating(user));

        System.out.println(result.get());
    }

    CompletableFuture<User> getUsersDetail(String userId) {
        return CompletableFuture.supplyAsync(() -> {
            return UserService.getUserDetails(userId);
        });
    }

    CompletableFuture<Double> getCreditRating(User user) {
        return CompletableFuture.supplyAsync(() -> {
            return CreditRatingService.getCreditRating(user);
        });
    }

    private static class UserService {

        public static User getUserDetails(String userId) {
            return new User();
        }
    }

    private static class CreditRatingService {

        public static Double getCreditRating(User user) {
            return 10.40;
        }
    }
}
