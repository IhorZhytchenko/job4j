package control.controller;

import control.repository.entity.MusicType;
import control.service.RepositoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
/**
 * class MusicsController.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 01.07.2018
 */
public class MusicsController extends HttpServlet {
    private final RepositoryService service = RepositoryService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MusicType> musics = this.service.findAllMusicType();
        req.setAttribute("musics", musics);
        req.getRequestDispatcher("/WEB-INF/views/MusicsView.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.service.deleteMusicType(Long.parseLong(req.getParameter("id")));
        resp.sendRedirect(String.format("%s/musics", req.getContextPath()));
    }
}
