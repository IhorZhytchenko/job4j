package control.controller.filters;

import control.repository.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * class DeleteUserFilter.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 03.07.2018
 */
public class DeleteUserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getMethod().toUpperCase().equals("POST")) {
            HttpSession session = request.getSession();
            if (((User) session.getAttribute("user")).getRole().getRoleName().equals("ADMIN")) {
                chain.doFilter(req, resp);
            } else {
                ((HttpServletResponse) resp).sendRedirect(request.getContextPath() + "/users");
            }
        } else {
            chain.doFilter(req, resp);
        }

    }

    @Override
    public void destroy() {

    }
}
