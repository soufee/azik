import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Address address = Address.builder()
                .country("Чехия")
                .city("Карловы вары")
                .street("Виноградска")
                .house("15")
                .flat("7")
                .build();
        Person p = new Person(
                "Ashamaz",
                "Shomakhov",
                "Khashaovich",
                LocalDate.of(1985, Month.APRIL, 29),
                address,
                LocalDate.now().minus(180, ChronoUnit.DAYS),
                "soufee@mail.ru");

        Book book = new Book("123123123", "Jack London", "Зов предков", Year.of(1905), Collections.singletonList("Приключения"));
        Book book2 = new Book("123123124", "Александр Невзоров", "Искусство оскорблять", Year.of(2018), Collections.singletonList("Публицистика"));


        Library library = new Library();
        library.registerPerson(p);
        library.registerBook(book);
        library.registerBook(book2);
        library.takeBook(book, p);
        library.returnBook(book);
        library.takeBook(book2, p);
   //     library.getAllOpenOrders().get(0).setExpectedReturnDate(LocalDate.now().minus(2, ChronoUnit.DAYS));
//        System.out.println(library.getAllExpiredOrders());
//
//        System.out.println(library.getAllExpiredOrders());
//        System.out.println(library.getPersonsWithHandOnBooks());
   //     library.sendEmailToCreditors();
        library.showReportByPerson(p);
    }
}
