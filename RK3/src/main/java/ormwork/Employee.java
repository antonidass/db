package ormwork;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
public class Employee {
    private Integer id;
    private String fio;
    private Date dateBirth;
    private String dep;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "fio", nullable = true, length = -1)
    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Basic
    @Column(name = "date_birth", nullable = true)
    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    @Basic
    @Column(name = "dep", nullable = true, length = -1)
    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(fio, employee.fio) && Objects.equals(dateBirth, employee.dateBirth) && Objects.equals(dep, employee.dep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fio, dateBirth, dep);
    }
}
