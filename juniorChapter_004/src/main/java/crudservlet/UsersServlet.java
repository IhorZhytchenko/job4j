package crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * UsersServlet.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 30.05.2018
 */
public class UsersServlet extends HttpServlet {
    private final ValidateService logic = ValidateService.getInstance();

    @Override
    public void destroy() {
        DBStore.getInstance().close();
    }

    @Override
    public void init() throws ServletException {
        DBStore.getInstance().init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users",this.logic.findAll());
        req.getRequestDispatcher("/WEB-INF/views/UsersView.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.logic.delete(Long.parseLong(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
