package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "car")
public class Car {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   private String name;
   @ManyToOne
   private Year year;
   private int mileage;
   private double capacity;
   @ManyToOne
   private Mark mark;
   @ManyToOne
   private Transmission transmission;
   @ManyToOne
   private Body body;
   @ManyToOne
   private Model model;
   @ManyToOne
   private Engine engine;


   public Car() {
   }

   public Car(String name, Year year, int mileage, double capacity, Mark mark, Transmission transmission, Body body, Model model, Engine engine) {
      this.name = name;
      this.year = year;
      this.mileage = mileage;
      this.capacity = capacity;
      this.mark = mark;
      this.transmission = transmission;
      this.body = body;
      this.model = model;
      this.engine = engine;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
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

   public Model getModel() {
      return model;
   }

   public void setModel(Model model) {
      this.model = model;
   }

   public Engine getEngine() {
      return engine;
   }

   public void setEngine(Engine engine) {
      this.engine = engine;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Year getYear() {
      return year;
   }

   public void setYear(Year year) {
      this.year = year;
   }

   public int getMileage() {
      return mileage;
   }

   public void setMileage(int mileage) {
      this.mileage = mileage;
   }

   public Transmission getTransmission() {
      return transmission;
   }

   public void setTransmission(Transmission transmission) {
      this.transmission = transmission;
   }

   public double getCapacity() {
      return capacity;
   }

   public void setCapacity(double capacity) {
      this.capacity = capacity;
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
      return id == car.id && mileage == car.mileage && Double.compare(car.capacity, capacity) == 0 && Objects.equals(name, car.name) && Objects.equals(year, car.year) && Objects.equals(mark, car.mark) && Objects.equals(transmission, car.transmission) && Objects.equals(body, car.body) && Objects.equals(model, car.model) && Objects.equals(engine, car.engine);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, name, year, mileage, capacity, mark, transmission, body, model, engine);
   }

   @Override
   public String toString() {
      return "Car{ id=" + id + ", name='" + name + '\'' + ", year=" + year + ", mileage=" + mileage
              + ", capacity=" + capacity + ", mark=" + mark + ", transmission=" + transmission
              + ", body=" + body +   ", model=" + model + ", engine=" + engine + '}';
   }
}
