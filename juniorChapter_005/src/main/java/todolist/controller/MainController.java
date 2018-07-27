package todolist.controller;

import com.alibaba.fastjson.JSON;
import todolist.Item;
import todolist.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
/**
 * class MainController.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 26.07.2018
 */
public class MainController extends HttpServlet {
    private final ItemService service = ItemService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean all = Boolean.parseBoolean(req.getParameter("all"));
        List<Item> list = null;
        if(all){
            list = this.service.findAll();
        } else {
            list = this.service.findIncomplete();
        }

        String json = JSON.toJSONString(list);
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(json);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("post");
        System.out.println(req.getParameter("desc"));
        this.service.add(req.getParameter("desc"));
    }
}
