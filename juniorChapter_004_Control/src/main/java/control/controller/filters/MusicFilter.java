package control.controller.filters;

import control.repository.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * class MusicFilter.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 03.07.2018
 */
public class MusicFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        System.out.println("filter");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        String role = ((User) session.getAttribute("user")).getRole().getRoleName();
        if (role.equals("ADMIN") || role.equals("MANDATOR")) {
            chain.doFilter(req, resp);
        } else {

            ((HttpServletResponse) resp).sendRedirect(String.format("%s/", request.getContextPath()));

        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

}
