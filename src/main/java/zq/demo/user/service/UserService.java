package zq.demo.user.service;

import zq.demo.user.po.User;

public interface UserService {
	
	User create(String telephone, String password);
	
	User get(Long id);
	
	User get(String telephone);
	
}
