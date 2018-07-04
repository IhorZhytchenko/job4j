package control.controller;

import control.repository.entity.Role;
import control.service.RepositoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * class SearchViewController.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 01.07.2018
 */
public class SearchViewController extends HttpServlet {
    private final RepositoryService service = RepositoryService.getInstance();
    private final Map<String, String> viewPaths = new HashMap<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", this.service.findAllRoles());
        req.setAttribute("musics", this.service.findAllMusicType());
        req.getRequestDispatcher(this.viewPaths.get(req.getParameter("type"))).forward(req, resp);


    }

    @Override
    public void init() throws ServletException {
        this.viewPaths.put("role", "/WEB-INF/views/SearchByRoleView.jsp");
        this.viewPaths.put("music", "/WEB-INF/views/SearchByMusicView.jsp");
        this.viewPaths.put("address", "/WEB-INF/views/SearchByAddressView.jsp");
    }
}
