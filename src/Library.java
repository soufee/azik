import java.time.Duration;
import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private Map<Integer, Person> readers = new HashMap<>();
    private Map<String, Book> books = new HashMap<>();
    private Map<String, Order> orders = new HashMap<>();

    public Person getPerson(int id) {
        Person person = readers.get(id);
        if (person != null) return person;
        else
            throw new IllegalArgumentException("Читатель с id " + id + " не зарегистрирован в системе. Вызовите метод регистрации");
    }

    public Person registerPerson(String firstName, String lastName, String middleName, LocalDate dateBirth, Address address, String email) {
        Person person = new Person(firstName, lastName, middleName, dateBirth, address, LocalDate.now(), email);
        readers.put(person.getId(), person);
        return person;
    }

    public List<Person> getAllReaders() {
        return new ArrayList<>(readers.values());
    }

    public Book getBook(String isbn) {
        Book book = books.get(isbn);
        if (book != null) return book;
        else throw new IllegalArgumentException("Книга с isbn " + isbn + " не найдена");
    }

    public Book registerBook(String isbn, String author, String title, Year year, List<String> genre, boolean forAdultOnly) {
        Book book = new Book(isbn, author, title, year, genre);
        if (forAdultOnly) book.setAgeMarc(18);
        books.put(isbn, book);
        sendInfoEmail(book);
        return book;
    }

    public void withdrawBook(Book book) {
        books.remove(book.getIsbn());
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    public List<Book> getAllAvailableBooks() {
        return getAllBooks().stream().filter(Book::isAvailable).collect(Collectors.toList());
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

    public Order getOrder(String id) {
        return orders.get(id);
    }

    public List<Order> getAllOpenOrders() {
        return getAllOrders().stream().filter(o -> !o.isReturnedBack()).collect(Collectors.toList());
    }

    public void returnBook(String id) {
        Order order = orders.get(id);
        order.setFactReturnDate(LocalDate.now());
        order.getBook().setAvailable(true);
        System.out.println("Книга " + order.getBook() + " возвращена");

    }

    public void returnBook(Book book) {
        List<Order> list = getAllOpenOrders().stream().filter(o -> o.getBook().getIsbn().equals(book.getIsbn())).collect(Collectors.toList());
        if (list.size() != 1) {
            throw new IllegalStateException("Ожидается единственный заказ с книгой " + book.getIsbn() + ", но найдено " + list.size() + " заказов!");
        } else {
            returnBook(list.get(0).getId());
        }
    }

    public void takeBook(Book book, Person person) {
        takeBook(book.getIsbn(), person);
    }

    public void takeBook(String isbn, Person person) {
        try {
            validateBook(isbn);
            validatePerson(person);
            validateAgeLimits(isbn, person);
            Order order = new Order(person, books.get(isbn));
            orders.put(order.getId(), order);
            order.getBook().setAvailable(false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void validateAgeLimits(String isbn, Person person) throws Exception {
        Book book = getBook(isbn);
        LocalDate db = person.getDateBirth();
        LocalDate now = LocalDate.now();
        int diff = (int) ChronoUnit.YEARS.between(db, now);
        if (diff < book.getAgeMarc()) {
            throw new Exception("Данная книга имеет возрастное ограничение " + book.getAgeMarc() + ", а возраст получателя книги " + diff);
        }

    }

    private void validatePerson(Person person) throws Exception {
        List<Order> credits = getAllOpenOrders();
        List<Order> creditsOfClient = credits.stream().filter(o -> o.getOrderer().equals(person)).collect(Collectors.toList());
        if (creditsOfClient.size() > 0) {
            StringBuilder sb = new StringBuilder("У клиента есть невозвращенные долги. ");
            for (Order order : creditsOfClient) {
                sb.append(order.getBook()).append(" ");
            }
            throw new Exception(sb.toString());
        }

    }


    private void validateBook(String isbn) throws Exception {
        Book book = books.get(isbn);
        if (book == null) {
            throw new IllegalArgumentException("В библиотеке нет книги с isbn " + isbn);
        } else if (!book.isAvailable()) {
            throw new Exception("Книга сейчас не доступна");
        }
    }

    public List<Book> getAllOnHandBooks() {
        return getAllOpenOrders().stream().map(Order::getBook).collect(Collectors.toList());
    }

    public List<Order> getAllExpiredOrders() {
        return getAllOpenOrders().stream().filter(o -> {
            LocalDate expected = o.getExpectedReturnDate();
            LocalDate now = LocalDate.now();
            int diff = getDiff(expected, now);
            return diff < 0;
        }).collect(Collectors.toList());
    }

    private int getDiff(LocalDate expected, LocalDate now) {
        return (int) ChronoUnit.DAYS.between(now, expected);
    }

    public void registerBook(Book book) {
        registerBook(book.getIsbn(), book.getAuthor(), book.getTitle(), book.getYear(), book.getGenre(), book.getAgeMarc() >= 18);
    }

    public void registerPerson(Person person) {
        registerPerson(person.getFirstName(), person.getLastName(), person.getMiddleName(), person.getDateBirth(), person.getAddress(), person.getEmail());
    }

    public Map<Person, Book> getPersonsWithHandOnBooks() {
        return getAllOpenOrders().stream().collect(Collectors.toMap(Order::getOrderer, Order::getBook));
    }

    public void sendEmailToCreditors() {
        for (Order order : getAllExpiredOrders()) {
            EmailTemplate.CREDITS.completeCreditLetter(order);
        }
    }

    public void sendInfoEmail(Book book) {
        EmailTemplate.NEW_BOOKS.completeInfoLetter(getAllReaders(), book);
    }

    public void showReportByPerson(Person person) {
        List<Order> collect = getAllOrders().stream().filter(o -> o.getOrderer().getId() == person.getId()).collect(Collectors.toList());
        System.out.printf("======= ОТЧЕТ ПО ЧИТАТЕЛЮ %s =========\n", person.getFirstName()+ " "+person.getMiddleName()+" "+person.getLastName());
        for (Order order : collect) {
            System.out.printf("Книга %s\n", order.getBook().getAuthor()+" "+order.getBook().getTitle());
            System.out.printf("Взято %s\n", order.getOrderDate().toString());
            if (order.isReturnedBack()) {
                System.out.printf("Возврат состоялся %s\n", order.getFactReturnDate().toString());
            } else if (order.getExpectedReturnDate().isBefore(LocalDate.now())){
                System.out.printf("Возврат просрочен на %d дней\n", getDiff(LocalDate.now(), order.getExpectedReturnDate()));
            } else {
                System.out.printf("Книга ожидается к возврату %s\n", order.getExpectedReturnDate());
            }
            System.out.println("-----------------");
        }
    }

    public void showReportByBook(Book book) {

    }
}
