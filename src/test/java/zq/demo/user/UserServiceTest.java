package zq.demo.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import zq.demo.LoginApplication;
import zq.demo.user.po.User;
import zq.demo.user.service.UserService;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LoginApplication.class})
@Transactional
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	private Long id;
	
	@Before
	public void setup() {
		User user = userService.create("123123", "3123123");
		assertNotNull(user.getId());
		this.id = user.getId();
	}

	@Test
	public void testGet() {
		User user = userService.get(id);
		assertEquals("3123123", user.getPassword());
	}
}
