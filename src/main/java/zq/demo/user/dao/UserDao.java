package zq.demo.user.dao;

import zq.demo.user.po.User;

public interface UserDao {
	
	User getByTel(String tel);
	
	void create(User user);
	
	User get(Long id);
	
}
