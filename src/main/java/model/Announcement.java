package model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "announcement")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private int price;
    private boolean sold;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd HH:mm:ss", timezone = "Europe/Moscow")
    private Date created;
    @ManyToOne
    private Car car;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userss;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "ann_id")
    private List<Image> images = new ArrayList<>();

    public Announcement() {
    }

    public Announcement(String name, String description, int price, boolean sold, Date created, Car car, User userss) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.sold = sold;
        this.created = created;
        this.car = car;
        this.userss = userss;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUserss() {
        return userss;
    }

    public void setUserss(User userss) {
        this.userss = userss;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
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
        return id == that.id && price == that.price && sold == that.sold && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(created, that.created) && Objects.equals(car, that.car) && Objects.equals(userss, that.userss);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, sold, created, car, userss);
    }

    @Override
    public String toString() {
        return "Announcement{ id=" + id + ", name='" + name + '\'' + ", description='" + description + '\''
                + ", price=" + price + ", sold=" + sold + ", created=" + created + ", car=" + car + ", userss="
                + userss + '}';
    }
}
