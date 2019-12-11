package wangrong.wr1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wangrong.wr1.dao.UserDao;
import wangrong.wr1.domain.User;
@Service
public class UserService {
	@Autowired
	UserDao userDao;
	public User getUserById(int id)
	{
		System.out.println(id);System.out.println(userDao.getUserById(id).getName());
		return userDao.getUserById(id);
	}
	@Transactional
	public Boolean tx()
	{
		
		userDao.tx(new User(16,"wrrr"));
		userDao.tx(new User(25,"eee"));
		return true;
	}

}
