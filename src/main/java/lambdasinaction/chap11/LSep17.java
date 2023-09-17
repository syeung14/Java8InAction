package lambdasinaction.chap11;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://www.youtube.com/watch?v=0hQvWIdwnw4&ab_channel=Devoxx
 */
public class LSep17 {

    static void future() {

        create().thenAccept(d -> System.out.println(d))
                .thenRun(() -> System.out.println("this never dies"))
                .thenRun(() -> System.out.println("really, this never dies"))
                .thenRun(() -> System.out.println("r...really, this never dies"));
    }


    void randomTest() {
        ExecutorService executorService =
            new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

        Runnable r = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);

            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        };

        Callable<String> tasks = () -> {
            TimeUnit.MILLISECONDS.sleep(300);
            return "Task's execution";
        };
    }


    static CompletableFuture<Integer> create() {
        return CompletableFuture.supplyAsync(() -> 2);
    }

    public static void main(String[] args) throws IOException {
        LSep17 ls = new LSep17();

        String dd = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddHHmm"));
        System.out.println(dd);

//        List<String> lst = new ArrayList<>(Arrays.asList(""));
//        lst = IntStream.range(1, 10).mapToObj(i -> String.format("data %s", i)).collect(Collectors.toList());
//        ls.writeListString(lst);
        System.out.println("done");

    }
    void method() {
        String fileName = "this_is_large_file.txt";
        IntStream.range(0, 5).map(x -> {
            try {
                whenWriteStringUsingBufferedWritter_thenCorrect(fileName);
            } catch (IOException e) {
                throw new RuntimeException(e); // TODO
            }
            return x;
        }).forEach(System.out::println);

    }

    public void writeListString(List<String> fileListing) throws IOException {
    }

    public void whenWriteStringUsingBufferedWritter_thenCorrect(String fileName)
    throws IOException {
        String str = LocalDateTime.now() +  " Hello\n";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        writer.write(str);

        writer.close();
    }
}
