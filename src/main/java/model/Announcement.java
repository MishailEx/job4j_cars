package model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "announcement")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private boolean sold;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @ManyToOne
    private Car car;
    @ManyToOne
    private Author author;

    public Announcement() {
    }

    public Announcement(String description, boolean sold, Date created, Car car, Author author) {
        this.description = description;
        this.sold = sold;
        this.created = created;
        this.car = car;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Announcement that = (Announcement) o;
        return id == that.id && sold == that.sold && Objects.equals(description, that.description) && Objects.equals(created, that.created) && Objects.equals(car, that.car) && Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, sold, created, car, author);
    }

    @Override
    public String toString() {
        return "Announcement{ id=" + id + ", description='" + description
                + '\'' + ", sold=" + sold + ", created=" + created
                + ", car=" + car + ", author=" + author + '}';
    }
}
