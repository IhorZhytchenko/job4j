package crudservlet;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
/**
 * UserUpdateServletTest.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 06.06.2018
 */
public class UserUpdateServletTest {

    @Test
    public void updateUser() throws ServletException, IOException {
        UserUpdateServlet servlet = new UserUpdateServlet();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        DBStore.getInstance().init();
        List<User> users = DBStore.getInstance().findAll();
        if (users.size() > 0) {
            Long id = users.get(users.size() - 1).getId();

            when(req.getParameter("id")).thenReturn(id.toString());
            when(req.getParameter("name")).thenReturn("UpdateName");
            when(req.getParameter("login")).thenReturn("UpdateLogin");
            when(req.getParameter("email")).thenReturn("UpdateEmail");
            when(req.getParameter("password")).thenReturn("UpdatePassword");
            when(req.getParameter("role")).thenReturn("user");
            servlet.doPost(req, resp);
            User user = DBStore.getInstance().findById(id);
            assertThat(user.getName(), is("UpdateName"));
            assertThat(user.getLogin(), is("UpdateLogin"));
            assertThat(user.getEmail(), is("UpdateEmail"));
            assertThat(user.getPassword(), is("UpdatePassword"));
            assertThat(user.getRole(), is("user"));
            DBStore.getInstance().close();
        }
    }
}