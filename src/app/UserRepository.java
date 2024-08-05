package app;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class UserRepository {

    public static List<User> list;

    public static void main(String[] args) {

        int id;
        String email;

        List<User> usersList = Arrays.asList(
                new User(23, "Paul", "paul-mensch@gmail.com"),
                new User(116, "Meriam", "mer-de@hot.mail.com"),
                new User(17, "Sara", "sara-OD-UA@.ukr.net"),
                new User(95, "Kvartal", null),
                new User(279, "Lola", ""),
                new User(0, null, "null@empty.com"),
                new User()
        );

        id = 17;
        findUserById(id);

        email = "sara-OD-UA@.ukr.net";
        findUserByEmail(email);

        list = usersList;
        findAllUsers();

    }

//        Метод findUserById(int id) повертає Optional<User> з користувачем за вказаним id
    public static void findUserById(int id){
         Optional.ofNullable(list)
                .flatMap(User::getId)
                .filter(userId-> userId == id)
                .ifPresentOrElse(
                        (res)-> {
                            System.out.println("User with id " + id + " is: " + res); },
                        ()-> {
                            System.out.println("\nNo matched id data"); });
    }

// Метод findUserByEmail(String email)повертає Optional<User> з користувачем за вказаною електронною поштою
    public static void findUserByEmail(String email){
            Optional.ofNullable(list)
                    .filter(user -> user.contains(email))
                    .flatMap(String::trim)
                    .ifPresentOrElse(
                            (res)-> {
                                System.out.println("User with email " + email + " is: " + res); },
                            ()->{
                                System.out.println("\nNo matched id data"); });
    }

    //Метод findAllUsers(), який повертає нумерований список всіх користувачів у вигляді Optional<List<User>> та кількість користувачів
    public static void findAllUsers() {
        Optional<List<User>> optionalList = Optional.ofNullable(list);
//        optionalList.ifPresent(l -> l.forEach(System.out::println));// повертає список
        if (optionalList.isPresent()) {
            System.out.println("\nUsers list: ");
            AtomicInteger cnt = new AtomicInteger(1);
            optionalList.get().forEach(user ->
                    System.out.println(cnt.getAndIncrement() +
                            ") " + user));
            int userCnt = cnt.intValue() - 1;
            System.out.println("Number of users " + userCnt);
        } else
            System.out.println("\nNo List of users");
    }
}