package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "year")
public class Year {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int name;

    public Year() {
    }

    public Year(int name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Year year = (Year) o;
        return id == year.id && Objects.equals(name, year.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Year{ id=" + id + ", name='" + name + '}';
    }
}
