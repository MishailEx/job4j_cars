package servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ShowImageServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();
    private String folder = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        File downloadFile = null;
        String path = "c:\\images\\" + folder + "\\" + name;
        for (File file : new File(path).listFiles()) {
            if (name.equals(file.getName())) {
                downloadFile = file;
                break;
            }
        }
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + downloadFile.getName() + "\"");
        try (FileInputStream stream = new FileInputStream(downloadFile)) {
            resp.getOutputStream().write(stream.readAllBytes());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String nameFolder = req.getParameter("name");
        folder = nameFolder;
        List<String> images = new ArrayList<>();
        for (File name : new File("c:\\images\\" + nameFolder).listFiles()) {
            images.add(name.getName());
        }
        OutputStream output = resp.getOutputStream();
        String img = GSON.toJson(images);
        output.write(img.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}
