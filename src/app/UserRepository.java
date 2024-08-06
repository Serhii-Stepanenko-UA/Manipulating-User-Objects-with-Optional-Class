package app;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class UserRepository {

    public static List<User> listUsers;

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
        UserRepository userRepository = new UserRepository();
        listUsers = usersList;
        id = 95;
        Optional<User> userById = userRepository.findUserById(id);
        System.out.println("\nThe user by Id (" + id + ") is: " + userById);

        email = "sara-OD-UA@.ukr.net";
        Optional<User> userByEmail = userRepository.findUserByEmail(email);
        System.out.println("\nThe user by email (" + email + ") is: " + userByEmail);

        findAllUsers();

    }

    //        Метод findUserById(int id) повертає Optional<User> з користувачем за вказаним id
    public Optional<User> findUserById(int id) {
        for (User user : listUsers) {
            if (user.getId() == id)
                return Optional.of(user);
        }
        return Optional.empty();
    }

    // Метод findUserByEmail(String email)повертає Optional<User> з користувачем за вказаною електронною поштою
    public Optional<User> findUserByEmail(String email) {
        for (User user : listUsers) {
            if (user.getEmail().equals(email))
                return Optional.of(user);
        }
        return Optional.empty();
    }

    //Метод findAllUsers(), який повертає нумерований список всіх користувачів у вигляді Optional<List<User>> та кількість користувачів
    public static void findAllUsers() {
        Optional<List<User>> optionalList = Optional.ofNullable(listUsers);
//        optionalList.ifPresent(l -> l.forEach(System.out::println));// повертає список
        if (optionalList.isPresent()) {
            System.out.println("\nAll users list: ");
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