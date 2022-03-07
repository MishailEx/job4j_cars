package service;

import model.*;

import java.util.List;
import java.util.Map;

public interface CarRepository {
    Car addCar(Car car);
    List<Model> findModelOnMark(Mark mark);
    Map<String, List<Object>> loadAllSelect();
    Car findCar(Car car);
}
