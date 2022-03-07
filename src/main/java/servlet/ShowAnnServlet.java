package servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Announcement;
import service.AdsRepository;
import service.AdsStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ShowAnnServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();
    private int idAnn = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getParameter("idAnn") != null) {
            idAnn = Integer.parseInt(req.getParameter("idAnn"));
        }
        resp.sendRedirect("http://localhost:8080/job4j_cars/showAnn.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        AdsRepository store = AdsStore.instOf();
        Announcement announcement = store.findAnnById(idAnn);
        OutputStream output = resp.getOutputStream();
        String ann = GSON.toJson(announcement);
        output.write(ann.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}
