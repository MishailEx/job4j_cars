package servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Mark;
import model.Model;
import service.CarRepository;
import service.CarStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class LoadingServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        CarRepository store = CarStore.instOf();
        Map<String, List<Object>> all = store.loadAllSelect();
        List<Object> findAllBody = all.get("body");
        List<Object> findAllMark = all.get("mark");
        List<Object> findAllEngine = all.get("engine");
        List<Object> findAllTransmission = all.get("transmission");
        List<Object> findAllYear = all.get("year");
        String body = GSON.toJson(findAllBody);
        String mark = GSON.toJson(findAllMark);
        String engine = GSON.toJson(findAllEngine);
        String transmission = GSON.toJson(findAllTransmission);
        String year = GSON.toJson(findAllYear);
        String allParam = "[" + body + "," + mark + "," + engine + "," + transmission + "," + year + "]";
        output.write(allParam.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        CarRepository store = CarStore.instOf();
        String markS = req.getParameter("name");
        Mark mark = GSON.fromJson(markS, Mark.class);
        List<Model> model = store.findModelOnMark(mark);
        String modelList = GSON.toJson(model);
        output.write(modelList.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}
