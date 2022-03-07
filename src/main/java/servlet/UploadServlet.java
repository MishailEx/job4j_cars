package servlet;

import model.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.UserStore;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = UserStore.getUser(req);
        String nameAnnouncement = user.getName() + user.getNumAnnouncement();
        HttpSession session = req.getSession();
        session.setAttribute("nameAnnouncement", nameAnnouncement);
        if (new File("c:\\images\\" + nameAnnouncement).exists()) {
            List<String> images = new ArrayList<>();
            for (File name : new File("c:\\images\\" + nameAnnouncement).listFiles()) {
                images.add(name.getName());
            }
            req.setAttribute("images", images);
        }
        req.getRequestDispatcher("/addAnnouncement.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        String nameAnnouncement = UserStore.getNameAnn(req);
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            File dir = new File("c:\\images\\" + nameAnnouncement);
            if (!dir.exists()) {
                dir.mkdir();
            }
            List<FileItem> items = upload.parseRequest(req);
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    File file = new File(dir + File.separator + item.getName());
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        out.write(item.getInputStream().readAllBytes());
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        doGet(req, resp);
    }
}
