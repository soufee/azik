import java.util.Objects;

public class Address {
    private String country;
    private String region;
    private String city;
    private String street;
    private String house;
    private String flat;

    public Address(String country, String region, String city, String street, String house, String flat) {
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
    }

    private Address() {

    }

    public static Builder builder() {
        return new Address().new Builder();
    }

    public class Builder {
        private Builder() {

        }

        public Address build() {
            return Address.this;
        }

        public Builder country(String country) {
            Address.this.country = country;
            return this;
        }

        public Builder region(String region) {
            Address.this.region = region;
            return this;
        }

        public Builder city(String city) {
            Address.this.city = city;
            return this;
        }

        public Builder street(String street) {
            Address.this.street = street;
            return this;
        }

        public Builder house(String house) {
            Address.this.house = house;
            return this;
        }

        public Builder flat(String flat) {
            Address.this.flat = flat;
            return this;
        }
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (!Objects.equals(flat, address.flat)) return false;
        if (!Objects.equals(country, address.country)) return false;
        if (!Objects.equals(region, address.region)) return false;
        if (!Objects.equals(city, address.city)) return false;
        if (!Objects.equals(street, address.street)) return false;
        return Objects.equals(house, address.house);
    }

    @Override
    public int hashCode() {
        int result = country != null ? country.hashCode() : 0;
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (house != null ? house.hashCode() : 0);
        result = 31 * result + (flat != null ? flat.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                ", flat=" + flat +
                '}';
    }
}
