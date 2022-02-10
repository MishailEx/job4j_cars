package model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private byte[] imageData;
    private String imageFileName;

    public Image() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
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
        return id == image.id && Arrays.equals(imageData, image.imageData) && Objects.equals(imageFileName, image.imageFileName);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, imageFileName);
        result = 31 * result + Arrays.hashCode(imageData);
        return result;
    }

    @Override
    public String toString() {
        return "Image{ id=" + id + ", imageData=" + Arrays.toString(imageData)
                + ", imageFileName='" + imageFileName + '}';
    }
}
