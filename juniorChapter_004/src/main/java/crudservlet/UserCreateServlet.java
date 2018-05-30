package crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * UserCreateServlet.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 30.05.2018
 */
public class UserCreateServlet extends HttpServlet {
    private final ValidateService logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>Create User</title>" +
                "</head>" +
                "<body>" +
                "<form method=\"post\" action=\"" + req.getContextPath() + "/create\">" +
                "  <p> Name: <input  name=\"name\"></p>" +
                "  <p> Login: <input  name=\"login\"></p>" +
                "  <p> Email: <input  name=\"email\"></p>" +
                "  <p><input type=\"submit\" value=\"Save\"></p>" +
                " </form>"+
                "</body>" +
                "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.logic.add(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
