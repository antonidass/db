package ormwork;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Stockmarket {
    private Integer idMarket;
    private String weekend;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_market", nullable = false)
    public Integer getIdMarket() {
        return idMarket;
    }

    public void setIdMarket(Integer idMarket) {
        this.idMarket = idMarket;
    }

    @Basic
    @Column(name = "weekend", nullable = false, length = 3)
    public String getWeekend() {
        return weekend;
    }

    public void setWeekend(String weekend) {
        this.weekend = weekend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stockmarket that = (Stockmarket) o;
        return Objects.equals(idMarket, that.idMarket) && Objects.equals(weekend, that.weekend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMarket, weekend);
    }
}
