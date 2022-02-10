package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "announcement")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private boolean sold;
    @ManyToOne
    private Car car;
    @ManyToOne
    private Author author;

    public Announcement() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Announcement that = (Announcement) o;
        return id == that.id && sold == that.sold && Objects.equals(description, that.description) && Objects.equals(car, that.car) && Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, sold, car, author);
    }

    @Override
    public String toString() {
        return "Announcement{ id=" + id + ", description='" + description
                + '\'' + ", sold=" + sold + ", car=" + car
                + ", author=" + author + '}';
    }
}
