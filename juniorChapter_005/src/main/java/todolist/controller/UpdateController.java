package todolist.controller;

import todolist.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * class UpdateController.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 26.07.2018
 */
public class UpdateController extends HttpServlet {
    private final ItemService service = ItemService.getINSTANCE();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       long id = Long.parseLong(req.getParameter("id"));
       boolean done = Boolean.parseBoolean(req.getParameter("done"));
       this.service.update(id, done);
    }
}
