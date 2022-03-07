package servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.*;
import service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnnouncementServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        UserRepository store = UserStore.instOf();
        AdsRepository adsStore = AdsStore.instOf();
        String name = UserStore.getNameAnn(req);
        User user = UserStore.getUser(req);
        Mark mark = GSON.fromJson(req.getParameter("mark"), Mark.class);
        Body body = GSON.fromJson(req.getParameter("body"), Body.class);
        Engine engine = GSON.fromJson(req.getParameter("engine"), Engine.class);
        Model model = GSON.fromJson(req.getParameter("model"), Model.class);
        Transmission transmission = GSON.fromJson(req.getParameter("transmission"), Transmission.class);
        Year year = GSON.fromJson(req.getParameter("year"), Year.class);
        String nameCar = mark.getName() + " " + model.getName() + " " + year.getName() + " года";
        int mileage = Integer.parseInt(req.getParameter("mileage"));
        double capacity = Double.parseDouble(req.getParameter("capacity"));
        String disc = req.getParameter("description");
        int price = Integer.parseInt(req.getParameter("price"));
        CarRepository storeCar = CarStore.instOf();
        Car car = storeCar.addCar(new Car(nameCar, year, mileage, capacity, mark, transmission, body, model, engine));
        Announcement announcement = new Announcement(name, disc, price, true, new Date(), car, user);
        List<Image> images = new ArrayList<>();
        if (new File("c:\\images\\" + name).exists()) {
            for (File f : new File("c:\\images\\" + name).listFiles()) {
                Image image = adsStore.addImg(new Image(f.getName()));
                images.add(image);
            }
        } else {
            images.add(new Image("default.jpg"));
        }
        announcement.getImages().addAll(images);
        AdsRepository ads = AdsStore.instOf();
        ads.addAnn(announcement);
        store.incNumAnn(req);
    }
}
