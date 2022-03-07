package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String pathToImg;

    public Image() {
    }

    public Image(String pathToImg) {
        this.pathToImg = pathToImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPathToImg() {
        return pathToImg;
    }

    public void setPathToImg(String pathToImg) {
        this.pathToImg = pathToImg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Image image = (Image) o;
        return id == image.id && Objects.equals(pathToImg, image.pathToImg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pathToImg);
    }

    @Override
    public String toString() {
        return "Image{ id=" + id + ", pathToImg='" + pathToImg + '}';
    }
}
