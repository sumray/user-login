package zq.demo.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import zq.demo.common.IdGenerator;
import zq.demo.user.dao.UserDao;
import zq.demo.user.po.User;

@Service
@Primary
public class UserServiceDbImpl implements UserService {
	
	@Autowired
	private IdGenerator idGenerator;
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public User create(String telephone, String password) {
		User toDb = new User();
		toDb.setTelephone(telephone);
		toDb.setPassword(password);
		toDb.setId(idGenerator.generate());
		userDao.create(toDb);
		return toDb;
	}

	@Override
	public User get(Long id) {
		return userDao.get(id);
	}

	@Override
	public User get(String telephone) {
		return userDao.getByTel(telephone);
	}
	
}
