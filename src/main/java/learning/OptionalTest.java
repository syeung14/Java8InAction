package learning;

import java.util.Optional;

/**
 * https://www.callicoder.com/java-8-optional-tutorial/
 */

public class OptionalTest {

    public static void main(String[] args) {
        new OptionalTest().optionalTest();

    }
    void optionalTest() {
        Optional<User> o = findUserId("");

        o.ifPresentOrElse(f -> System.out.println(f), () -> {System.out.println("run in thread." + Thread.currentThread().getName());});


        User user1 = null;

        Optional<User> optUser = Optional.ofNullable(user1);

        User finalUser = (user1 == null ? new User() : user1);

        finalUser = optUser.orElse(new User());
        Optional<String> optUserName = optUser.map(User::getName);
        Optional<String> optUserPolo = optUserName.filter(a -> a.contains("Polo"));

        Optional<String> val = Optional.ofNullable(null);

    }


    Optional<User> findUserId(String userid) {
        return Optional.empty();
    }
}
