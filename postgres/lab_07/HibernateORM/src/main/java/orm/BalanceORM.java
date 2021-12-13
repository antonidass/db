package orm;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "balanceORM")
public class BalanceORM {
    private Integer id;
    private Integer freeCash;
    private Integer stocksAmount;
    private Integer turnover;
    private Integer totalAssets;
    private Float income;

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
    @Column(name = "free_cash", nullable = true)
    public Integer getFreeCash() {
        return freeCash;
    }

    public void setFreeCash(Integer freeCash) {
        this.freeCash = freeCash;
    }

    @Basic
    @Column(name = "stocks_amount", nullable = true)
    public Integer getStocksAmount() {
        return stocksAmount;
    }

    public void setStocksAmount(Integer stocksAmount) {
        this.stocksAmount = stocksAmount;
    }

    @Basic
    @Column(name = "turnover", nullable = true)
    public Integer getTurnover() {
        return turnover;
    }

    public void setTurnover(Integer turnover) {
        this.turnover = turnover;
    }

    @Basic
    @Column(name = "total_assets", nullable = true)
    public Integer getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(Integer totalAssets) {
        this.totalAssets = totalAssets;
    }

    @Basic
    @Column(name = "income", nullable = true, precision = 0)
    public Float getIncome() {
        return income;
    }

    public void setIncome(Float income) {
        this.income = income;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BalanceORM balance = (BalanceORM) o;
        return Objects.equals(id, balance.id) && Objects.equals(freeCash, balance.freeCash) && Objects.equals(stocksAmount, balance.stocksAmount) && Objects.equals(turnover, balance.turnover) && Objects.equals(totalAssets, balance.totalAssets) && Objects.equals(income, balance.income);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, freeCash, stocksAmount, turnover, totalAssets, income);
    }

    @Override
    public String toString() {
        return "BalanceORM{" +
                "id=" + id +
                ", freeCash=" + freeCash +
                ", stocksAmount=" + stocksAmount +
                ", turnover=" + turnover +
                ", totalAssets=" + totalAssets +
                ", income=" + income +
                '}';
    }
}
