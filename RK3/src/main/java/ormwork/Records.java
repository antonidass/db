package ormwork;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

@Entity
public class Records {
    private Integer id;
    private Date dateRecord;
    private String dayWeek;
    private Time timeEvent;
    private Integer typeEvent;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date_record", nullable = true)
    public Date getDateRecord() {
        return dateRecord;
    }

    public void setDateRecord(Date dateRecord) {
        this.dateRecord = dateRecord;
    }

    @Basic
    @Column(name = "day_week", nullable = true, length = -1)
    public String getDayWeek() {
        return dayWeek;
    }

    public void setDayWeek(String dayWeek) {
        this.dayWeek = dayWeek;
    }

    @Basic
    @Column(name = "time_event", nullable = true)
    public Time getTimeEvent() {
        return timeEvent;
    }

    public void setTimeEvent(Time timeEvent) {
        this.timeEvent = timeEvent;
    }

    @Basic
    @Column(name = "type_event", nullable = true)
    public Integer getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(Integer typeEvent) {
        this.typeEvent = typeEvent;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Records records = (Records) o;
        return Objects.equals(id, records.id) && Objects.equals(dateRecord, records.dateRecord) && Objects.equals(dayWeek, records.dayWeek) && Objects.equals(timeEvent, records.timeEvent) && Objects.equals(typeEvent, records.typeEvent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateRecord, dayWeek, timeEvent, typeEvent);
    }
}
