package ormwork;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Companyowner {
    private Integer id;
    private String ownerName;
    private Integer age;
    private String dateRegistration;
    private String sex;
    private String country;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "owner_name", nullable = false, length = 30)
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Basic
    @Column(name = "age", nullable = false)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "date_registration", nullable = false, length = 30)
    public String getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(String dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    @Basic
    @Column(name = "sex", nullable = false, length = -1)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "country", nullable = false, length = 20)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Companyowner that = (Companyowner) o;
        return Objects.equals(id, that.id) && Objects.equals(ownerName, that.ownerName) && Objects.equals(age, that.age) && Objects.equals(dateRegistration, that.dateRegistration) && Objects.equals(sex, that.sex) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerName, age, dateRegistration, sex, country);
    }
}
