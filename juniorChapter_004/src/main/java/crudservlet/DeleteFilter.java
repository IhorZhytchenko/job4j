package crudservlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * DeleteFilter.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 05.06.2018
 */
public class DeleteFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getMethod().toUpperCase().equals("POST")) {
            HttpSession session = request.getSession();
            if (((User) session.getAttribute("user")).getRole().equals("admin")) {
                chain.doFilter(req, resp);
            } else {
                ((HttpServletResponse) resp).sendRedirect(request.getContextPath() + "/");
            }
        } else {
            chain.doFilter(req, resp);
        }

    }

    @Override
    public void destroy() {

    }
}
