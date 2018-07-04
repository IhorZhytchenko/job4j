package control.controller;

import control.repository.entity.User;
import control.service.RepositoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
/**
 * class UsersController.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 01.07.2018
 */
public class UsersController extends HttpServlet {
    private final RepositoryService service = RepositoryService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = this.service.findAllUsers();
        req.setAttribute("users", users);
        req.setAttribute("message", "All Users");
        req.getRequestDispatcher("/WEB-INF/views/UsersView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.service.deleteUser(Long.parseLong(req.getParameter("id")));
        resp.sendRedirect(String.format("%s/users", req.getContextPath()));
    }
}
