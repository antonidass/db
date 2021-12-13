package ormwork;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Stockexchange {
    private Integer id;
    private String dateFoundation;
    private String nameStockexchange;
    private String available;

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
    @Column(name = "date_foundation", nullable = false, length = 30)
    public String getDateFoundation() {
        return dateFoundation;
    }

    public void setDateFoundation(String dateFoundation) {
        this.dateFoundation = dateFoundation;
    }

    @Basic
    @Column(name = "name_stockexchange", nullable = false, length = 30)
    public String getNameStockexchange() {
        return nameStockexchange;
    }

    public void setNameStockexchange(String nameStockexchange) {
        this.nameStockexchange = nameStockexchange;
    }

    @Basic
    @Column(name = "available", nullable = false, length = 3)
    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stockexchange that = (Stockexchange) o;
        return Objects.equals(id, that.id) && Objects.equals(dateFoundation, that.dateFoundation) && Objects.equals(nameStockexchange, that.nameStockexchange) && Objects.equals(available, that.available);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateFoundation, nameStockexchange, available);
    }
}
