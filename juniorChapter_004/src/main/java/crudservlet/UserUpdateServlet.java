package crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        System.out.println(req.getParameter("id"));
        User user = this.logic.findById(Long.parseLong(req.getParameter("id")));
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>Update User</title>" +
                "</head>" +
                "<body>" +
                "<form method=\"post\" action=\"" + req.getContextPath() + "/edit?id=" + user.getId() + "\">" +
                "  <p> Id: " + user.getId() + "</p>" +
                "  <p> Name: <input  name=\"name\" value=\"" + user.getName() + "\"></p>" +
                "  <p> Login: <input  name=\"login\" value=\"" + user.getLogin() + "\" ></p>" +
                "  <p> Email: <input  name=\"email\"  value=\"" + user.getEmail() + "\" ></p>" +
                "  <p> Create date: " + user.getCreateDate() + "</p>" +
                "  <p><input type=\"submit\" value=\"Save\"></p>" +
                " </form>"+
                "</body>" +
                "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.logic.update(Long.parseLong(req.getParameter("id")), req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
