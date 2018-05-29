package crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * UserServlet.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 29.05.2018
 */
public class UserServlet extends HttpServlet {
    private final ValidateService logic = ValidateService.getInstance();
    private final Map<String, Consumer<HttpServletRequest>> dispatch = new HashMap<>();

    private Consumer<HttpServletRequest> add() {
        return req -> {
            this.logic.add(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
        };
    }
    private Consumer<HttpServletRequest> update() {
        return req -> {
            this.logic.update(Long.parseLong(req.getParameter("id")), req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
        };
    }
    private Consumer<HttpServletRequest> delete() {
        return req -> {
            this.logic.delete(Long.parseLong(req.getParameter("id")));
        };
    }

    @Override
    public void init() throws ServletException {
        this.dispatch.put("add", this.add());
        this.dispatch.put("update", this.update());
        this.dispatch.put("delete", this.delete());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("Users\n");
        for (User user : this.logic.findAll()) {
            writer.append(user.getId() + " " + user.getName() + "\n");
        }
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.dispatch.get(req.getParameter("action")).accept(req);
        doGet(req, resp);
    }
}
