import java.time.LocalDate;
import java.time.Year;
import java.util.Arrays;
import java.util.List;

public class Book {
    private String isbn;
    private String author;
    private String title;
    private Year year;
    private List<String> genre;
    private LocalDate incomeDate = LocalDate.now();
    private boolean available = true;
    private int ageMarc = 0;

    public Book(String isbn, String author, String title, Year year, List<String> genre, LocalDate incomeDate, boolean available, int ageMarc) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.incomeDate = incomeDate;
        this.available = available;
        this.ageMarc = ageMarc;
    }

    public Book(String isbn, String author, String title, Year year, List<String> genre) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.year = year;
        this.genre = genre;
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public void setGenre(String... genres) {
        this.genre = Arrays.asList(genres);
    }

    public LocalDate getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(LocalDate incomeDate) {
        this.incomeDate = incomeDate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getAgeMarc() {
        return ageMarc;
    }

    public void setAgeMarc(int ageMarc) {
        this.ageMarc = ageMarc;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", genre=" + genre +
                ", incomeDate=" + incomeDate +
                ", available=" + available +
                ", ageMarc=" + ageMarc +
                '}';
    }
}
