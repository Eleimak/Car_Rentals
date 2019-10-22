package tam.group_bbv181.car_rental.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document
public class Customer {
    @Id
    private String id;
    private Person person;
    private String address;
    private int phone;
    private String eMail;
    private int bonusPoints;
    private List<Car> carList;

    public Customer() {
    }

    public Customer(Person person, String address, int phone, String eMail) {
        this.person = person;
        this.address = address;
        this.phone = phone;
        this.eMail = eMail;
        bonusPoints = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public int getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(int bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return phone == customer.phone &&
                bonusPoints == customer.bonusPoints &&
                Objects.equals(id, customer.id) &&
                Objects.equals(person, customer.person) &&
                Objects.equals(address, customer.address) &&
                Objects.equals(eMail, customer.eMail) &&
                Objects.equals(carList, customer.carList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", person=" + person +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                ", eMail='" + eMail + '\'' +
                ", bonusPoints=" + bonusPoints +
                ", carList=" + carList +
                '}';
    }
}
