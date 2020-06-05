import sun.jvm.hotspot.utilities.Assert;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.UUID;

public class Order {
    private String id;
    private Person orderer;
    private Book book;
    private LocalDate orderDate;
    private LocalDate expectedReturnDate;
    private LocalDate factReturnDate;

    public Order(Person orderer, Book book) {
        this.id = UUID.randomUUID().toString();
        this.orderer = orderer;
        this.book = book;
        this.orderDate = LocalDate.now();
        this.expectedReturnDate = LocalDate.now().plus(14, ChronoUnit.DAYS);
        this.factReturnDate = null;
    }

    public Order(Person orderer, Book book, LocalDate orderDate, LocalDate expectedReturnDate) {
        Assert.that(orderDate.isBefore(expectedReturnDate), "Некорректная дата возврата");
        this.id = UUID.randomUUID().toString();
        this.orderer = orderer;
        this.book = book;
        this.orderDate = orderDate;
        this.expectedReturnDate = expectedReturnDate;
        this.factReturnDate = null;

    }

    public boolean isReturnedBack() {
        return this.factReturnDate != null;
    }

    public String getId() {
        return id;
    }

    public Person getOrderer() {
        return orderer;
    }

    public void setOrderer(Person orderer) {
        this.orderer = orderer;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(LocalDate expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public LocalDate getFactReturnDate() {
        return factReturnDate;
    }

    public void setFactReturnDate(LocalDate factReturnDate) {
        this.factReturnDate = factReturnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (!id.equals(order.id)) return false;
        if (!orderer.equals(order.orderer)) return false;
        if (!book.equals(order.book)) return false;
        if (!orderDate.equals(order.orderDate)) return false;
        if (!Objects.equals(expectedReturnDate, order.expectedReturnDate))
            return false;
        return Objects.equals(factReturnDate, order.factReturnDate);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + orderer.hashCode();
        result = 31 * result + book.hashCode();
        result = 31 * result + orderDate.hashCode();
        result = 31 * result + (expectedReturnDate != null ? expectedReturnDate.hashCode() : 0);
        result = 31 * result + (factReturnDate != null ? factReturnDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", orderer=" + orderer +
                ", book=" + book +
                ", orderDate=" + orderDate +
                ", expectedReturnDate=" + expectedReturnDate +
                ", factReturnDate=" + factReturnDate +
                '}';
    }
}
