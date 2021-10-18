package dao;
import com.lendSys.ApiApplication;
import com.lendSys.dao.UserDao;
import com.lendSys.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)

public class UserDaoTest {
    @Resource
    private UserDao userDao;
    @Test
    public void searchUserByName(){
        User user = userDao.searchUserByName("MM");
        System.out.println(user);
    }

}