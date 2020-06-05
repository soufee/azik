import java.time.LocalDate;
import java.util.Objects;

public class Person {
    private static int counter = 0;

    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate dateBirth;
    private Address address;
    private LocalDate registrationDate;
    private String email;

    public Person(String firstName, String lastName, String middleName, LocalDate dateBirth, Address address, LocalDate registrationDate, String email) {
        this.id = ++counter;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.dateBirth = dateBirth;
        this.address = address;
        this.registrationDate = registrationDate;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        if (!Objects.equals(firstName, person.firstName)) return false;
        if (!Objects.equals(lastName, person.lastName)) return false;
        if (!Objects.equals(middleName, person.middleName)) return false;
        if (!Objects.equals(dateBirth, person.dateBirth)) return false;
        if (!Objects.equals(address, person.address)) return false;
        if (!Objects.equals(registrationDate, person.registrationDate)) return false;
        return Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (dateBirth != null ? dateBirth.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", dateBirth=" + dateBirth +
                ", address=" + address +
                ", registrationDate=" + registrationDate +
                ", email='" + email + '\'' +
                '}';
    }
}
