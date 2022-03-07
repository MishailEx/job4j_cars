package servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.User;
import service.UserRepository;
import service.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class RegServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserRepository userRepository = UserStore.instOf();
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        User user = userRepository.findByName(name);
        if (user == null) {
            user = new User(name, password);
            userRepository.addUser(user);
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
        } else {
            String tryMore = GSON.toJson("Пользователь с таким именем уже существует");
            output.write(tryMore.getBytes(StandardCharsets.UTF_8));
            output.flush();
            output.close();
        }
    }
}
