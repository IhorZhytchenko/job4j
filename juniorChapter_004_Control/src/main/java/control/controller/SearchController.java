package control.controller;

import control.repository.entity.User;
import control.service.SearchByAddressService;
import control.service.SearchByMusicService;
import control.service.SearchByRoleService;
import control.service.SearchService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * class SearchController.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 01.07.2018
 */
public class SearchController extends HttpServlet {
    private final Map<String, SearchService> searchService = new HashMap<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        List<User> users = this.searchService.get(type).search(req.getParameter("condition"));
        String message = String.format("Search User by %s", type);
        req.setAttribute("message", message);
        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/views/UsersView.jsp").forward(req, resp);

    }

    @Override
    public void init() throws ServletException {
        this.searchService.put("role", new SearchByRoleService());
        this.searchService.put("music", new SearchByMusicService());
        this.searchService.put("address", new SearchByAddressService());
    }
}
