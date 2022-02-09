package model;

import javax.persistence.*;
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
    Set<Announcement> announcements;
}
