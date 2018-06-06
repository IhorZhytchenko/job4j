package crudservlet;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
/**
 * UserCreateServletTest.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 06.06.2018
 */
public class UserCreateServletTest {

    @Test
    public void addUser() throws ServletException, IOException {
        UserCreateServlet servlet = new UserCreateServlet();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("name")).thenReturn("TestName");
        when(req.getParameter("login")).thenReturn("TestLogin");
        when(req.getParameter("email")).thenReturn("TestEmail");
        when(req.getParameter("password")).thenReturn("TestPassword");
        when(req.getParameter("role")).thenReturn("user");
        DBStore.getInstance().init();
        int index = DBStore.getInstance().findAll().size();

        servlet.doPost(req, resp);

        User user = DBStore.getInstance().findAll().get(index);

        assertThat(user.getName(), is("TestName"));
        assertThat(user.getLogin(), is("TestLogin"));
        assertThat(user.getEmail(), is("TestEmail"));
        assertThat(user.getPassword(), is("TestPassword"));
        assertThat(user.getRole(), is("user"));
        DBStore.getInstance().close();
    }

}