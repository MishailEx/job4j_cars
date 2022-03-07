package servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.*;
import service.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FilterFindServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        AdsRepository store = AdsStore.instOf();
        List<Announcement> allCar = store.findAllCar();
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        String anns = GSON.toJson(allCar);
        output.write(anns.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Mark mark = GSON.fromJson(req.getParameter("mark"), Mark.class);
        Transmission transmission = GSON.fromJson(req.getParameter("transmission"), Transmission.class);
        Body body = GSON.fromJson(req.getParameter("body"), Body.class);
        Engine engine = GSON.fromJson(req.getParameter("engine"), Engine.class);
        Model model = GSON.fromJson(req.getParameter("model"), Model.class);
        Year yearStart = GSON.fromJson(req.getParameter("yearStart"), Year.class);
        Year yearEnd = GSON.fromJson(req.getParameter("yearEnd"), Year.class);
        Car car = new Car(null, new Year(0), 0, 0, mark, transmission, body, model, engine);
        AdsRepository store = AdsStore.instOf();
        List<Announcement> ann = store.filterFindCar(car, yearStart, yearEnd);
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        String anns = GSON.toJson(ann);
        output.write(anns.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}
