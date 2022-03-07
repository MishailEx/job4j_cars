package service;

import model.Announcement;
import model.Car;
import model.Image;
import model.Year;

import java.util.List;

public interface AdsRepository {
    Announcement addAnn(Announcement an);
    Image addImg(Image image);
    Announcement findAnnById(int id);
    List<Announcement> filterFindCar(Car car, Year st, Year end);
    List<Announcement> findAllCar();

}
