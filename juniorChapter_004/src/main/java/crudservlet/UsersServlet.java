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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        StringBuilder sb = new StringBuilder("<table>");
        for (User user : this.logic.findAll()) {
            sb.append("<tr><td>" + user.getId() + "</td>");
            sb.append("<td>" + user.getName() + "</td>");
            sb.append("<td> <a  href=\"" +  req.getContextPath() + "/edit?id=" + user.getId() + "\" >Edit</a>" +
                    "</td>");
            sb.append("<td><form method=\"post\" action=\"" + req.getContextPath() + "/list?id=" + user.getId() + "\">" +
                    "    <button type=\"submit\">Delete</button>" +
                    "</form></td>");

            sb.append("</tr>");
        }

        sb.append("</table>");

        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>Users</title>" +
                "</head>" +
                "<body>" + sb.toString() +
                        "<form action=\"" + req.getContextPath() + "/create\">" +
                        "    <button type=\"submit\">Create User</button>" +
                        "</form>" +
                "</body>" +
                "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.logic.delete(Long.parseLong(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
