
import java.util.List;

public enum EmailTemplate {
    CREDITS,
    NEW_BOOKS;

    private static String CREDIT_TEMPLATE = "Уважаемый %s!\n" +
            "У вас имеется задолженность перед библиотекой. Книга \"%s\" должна была быть возвращена %s\n" +
            "Просим вас вернуть книгу в ближайшее время, иначе мы вынуждены будем прибегнуть к санкциям.\n" +
            "С уважением, Библиотека";
    private static String NEW_BOOKS_TEMPLATE = "Уважаемый %s!\n" +
            "В нашей библиотеке новое поступление! Книга \"%s\" теперь доступна для чтения. \n" +
            "Если Вы хотите ее прочитать, наш адрес: г. Нальчик, ул. Ногмова 15";

    public void completeCreditLetter(Order order) {
        if (this != CREDITS) return;
        Person person = order.getOrderer();
        Book book = order.getBook();
        System.out.printf(CREDIT_TEMPLATE, person.getFirstName() + " " + person.getMiddleName(), book.getAuthor() + ". " + book.getTitle(), order.getExpectedReturnDate().toString());
        System.out.println();
    }

    public void completeInfoLetter(List<Person> people, Book book) {
        if (this != NEW_BOOKS) return;
        for (Person person : people) {
            System.out.printf(NEW_BOOKS_TEMPLATE, person.getFirstName() + " " + person.getMiddleName(), book.getAuthor() + ". " + book.getTitle());
            System.out.println();
        }
    }
}
