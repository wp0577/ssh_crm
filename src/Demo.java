import com.wp.domain.User;
import com.wp.service.CustomerService;
import com.wp.util.PageBean;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:ApplicationContext.xml")
public class Demo {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Test
    public void test() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        String hql = "from User";
        Query query = session.createQuery(hql);
        List<User> users = query.list();
        System.out.println(users);

        tx.commit();
        session.close();
    }

    @Resource(name = "userService")
    private CustomerService customerService;

    @Test
    public void test1() {

        PageBean pageBean = new PageBean(2, 4, 0);
        System.out.println(pageBean.getCurrentPage());
        System.out.println(pageBean.getPageSize());
    }

}
