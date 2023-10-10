package learning;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class FilterExample {

    /**
     * https://stackoverflow.com/questions/40720461/completablefutures-and-filtering-based-on-values-that-are-inside
     */
    private <T> CompletableFuture<Stream<T>> convertToFutureOfStream(List<CompletableFuture<T>> toConvert) {
        return CompletableFuture.allOf(toConvert.stream().toArray(CompletableFuture[]::new))
                                .thenApply(
                                    v -> toConvert.stream().map(CompletableFuture::join)
                                );
    }


    void method() {

/*
        CompletableFuture<List<String>> toReturn = asyncCall()
            .thenCompose(listOfStuff -> convertToFutureOfStream(
                    listOfStuff.stream()
                               .map(this::asyncCall2)
                               .collect(Collectors.toList())
                )
                    .thenApply(stream ->
                        stream.filter(Optional::isPresent)
                              .map(Optional::get)
                              .collect(Collectors.toList())
                    )
            );
*/

    }
}
