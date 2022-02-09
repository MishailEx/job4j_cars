package model;

import javax.persistence.*;

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
}
