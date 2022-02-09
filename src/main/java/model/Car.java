package model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "car")
public class Car {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   @OneToMany
   @JoinColumn(name = "car_id")
   private Set<Image> images;
   @ManyToOne
   private Mark mark;
   @ManyToOne
   private Body body;
}
