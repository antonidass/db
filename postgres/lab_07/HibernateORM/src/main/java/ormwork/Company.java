package ormwork;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Company {
    private String ticker;
    private String companyName;
    private String foundationDate;
    private String sector;
    private String country;
    private String shadow;

    @Id
    @Column(name = "ticker", nullable = false, length = 6)
    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @Basic
    @Column(name = "company_name", nullable = false, length = 30)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "foundation_date", nullable = false, length = 20)
    public String getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(String foundationDate) {
        this.foundationDate = foundationDate;
    }

    @Basic
    @Column(name = "sector", nullable = false, length = 22)
    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    @Basic
    @Column(name = "country", nullable = false, length = 20)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "shadow", nullable = false, length = 3)
    public String getShadow() {
        return shadow;
    }

    public void setShadow(String shadow) {
        this.shadow = shadow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(ticker, company.ticker) && Objects.equals(companyName, company.companyName) && Objects.equals(foundationDate, company.foundationDate) && Objects.equals(sector, company.sector) && Objects.equals(country, company.country) && Objects.equals(shadow, company.shadow);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticker, companyName, foundationDate, sector, country, shadow);
    }

    @Override
    public String toString() {
        return "Company{" +
                "ticker='" + ticker + '\'' +
                ", companyName='" + companyName + '\'' +
                ", sector='" + sector + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
