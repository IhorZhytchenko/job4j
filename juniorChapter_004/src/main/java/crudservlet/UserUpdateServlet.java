package crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * UserUpdateServlet.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 30.05.2018
 */
public class UserUpdateServlet extends HttpServlet {
    private final ValidateService logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      User user = this.logic.findById(Long.parseLong(req.getParameter("id")));
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/views/UpdateUserView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.logic.update(Long.parseLong(req.getParameter("id")), req.getParameter("name"), req.getParameter("login"), req.getParameter("email"), req.getParameter("password"), req.getParameter("role"), Long.parseLong(req.getParameter("addressId")));
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
