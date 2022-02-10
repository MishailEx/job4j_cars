package model;

import javax.persistence.*;
import java.util.Objects;
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

   public Car() {
   }

   public Car(Mark mark, Body body) {
      this.mark = mark;
      this.body = body;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public Set<Image> getImages() {
      return images;
   }

   public void setImages(Set<Image> images) {
      this.images = images;
   }

   public Mark getMark() {
      return mark;
   }

   public void setMark(Mark mark) {
      this.mark = mark;
   }

   public Body getBody() {
      return body;
   }

   public void setBody(Body body) {
      this.body = body;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }
      Car car = (Car) o;
      return id == car.id && Objects.equals(images, car.images) && Objects.equals(mark, car.mark) && Objects.equals(body, car.body);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, images, mark, body);
   }

   @Override
   public String toString() {
      return "Car{ id=" + id + ", images=" + images + ", mark="
              + mark + ", body=" + body + '}';
   }
}
