package control.controller;

import control.repository.entity.MusicType;
import control.repository.entity.Role;
import control.repository.entity.User;
import control.service.RepositoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
/**
 * class EditUserController.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 01.07.2018
 */
public class EditUserController extends HttpServlet {
    private final RepositoryService service = RepositoryService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Role> roles = this.service.findAllRoles();
        List<MusicType> musics = this.service.findAllMusicType();
        User user = this.service.findUserById(Long.parseLong(req.getParameter("id")));
        req.setAttribute("roles", roles);
        req.setAttribute("musics", musics);
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/views/EditUserView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.service.updateUser(req.getParameter("firstName"), req.getParameter("lastName"), req.getParameter("login"), req.getParameter("password"), req.getParameter("address"), Long.parseLong(req.getParameter("roleId")),  req.getParameterValues("musics"), Long.parseLong(req.getParameter("id")));
        resp.sendRedirect(String.format("%s/users", req.getContextPath()));
    }
}
