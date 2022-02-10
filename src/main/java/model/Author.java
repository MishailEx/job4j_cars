package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany
    @JoinColumn(name = "author_id")
    private Set<Announcement> announcements = new HashSet<>();

    public Author() {
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

    public Set<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(Set<Announcement> announcements) {
        this.announcements = announcements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Author author = (Author) o;
        return id == author.id && Objects.equals(name, author.name) && Objects.equals(announcements, author.announcements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, announcements);
    }

    @Override
    public String toString() {
        return "Author{ id=" + id + ", name='" + name + '\''
                + ", announcements=" + announcements + '}';
    }
}
