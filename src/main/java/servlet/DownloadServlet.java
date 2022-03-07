package servlet;

import model.Image;
import service.UserStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String nameAnn = UserStore.getNameAnn(req);
        if (nameAnn == null) {
            nameAnn = req.getParameter("nameAnn");
        }
        File downloadFile = null;
        String path = "c:\\images\\" + nameAnn;
        if (!new File(path).exists()) {
            path = "c:\\images\\default";
        }
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
}
