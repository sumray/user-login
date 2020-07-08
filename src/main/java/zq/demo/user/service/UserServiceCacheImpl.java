package zq.demo.user.service;

import org.springframework.stereotype.Service;
import zq.demo.user.po.User;

@Service
public class UserServiceCacheImpl implements UserService {

	@Override
	public User create(String telephone, String password) {
		return null;
	}

	@Override
	public User get(Long id) {
		return null;
	}

	@Override
	public User get(String telephone) {
		return null;
	}
}
