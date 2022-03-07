package service;

import model.User;

import javax.servlet.http.HttpServletRequest;

public interface UserRepository {
    User addUser(User user);
    User updateUser(User user, int id);
    User findByName(String name);
    void incNumAnn(HttpServletRequest req);
}
