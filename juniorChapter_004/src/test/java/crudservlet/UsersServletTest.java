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
 * UsersServletTest.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 06.06.2018
 */
public class UsersServletTest {

    @Test
    public void deleteUser() throws ServletException, IOException {
        UsersServlet servlet = new UsersServlet();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        DBStore.getInstance().init();
        List<User> users = DBStore.getInstance().findAll();
        int size = users.size();
        if (size > 0) {
            Long id = users.get(size - 1).getId();

            when(req.getParameter("id")).thenReturn(id.toString());

            servlet.doPost(req, resp);
            size--;
            assertThat(DBStore.getInstance().findAll().size(), is(size));
            DBStore.getInstance().close();
        }
    }

}