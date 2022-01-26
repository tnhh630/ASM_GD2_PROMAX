package dao.impl;

import java.util.List;
import java.util.Map;

import constant.NamedStored;
import dao.AbstractDao;
import dao.UserDao;
import entities.User;

public class UserDaoImpl extends AbstractDao<User> implements UserDao{

	@Override
	public User findById(Integer Id) {
		// TODO Auto-generated method stub
		return super.findbyId(User.class, Id);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		String sql ="SELECT o FROM User o WHERE o.email = ?0";
		return super.findOne(User.class, sql, email);
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		String sql ="SELECT o FROM User o WHERE o.username = ?0";
		return super.findOne(User.class, sql, username);
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		String sql ="SELECT o FROM User o WHERE o.username = ?0 AND o.password = ?1";
		return super.findOne(User.class, sql, username, password);
	}

	@Override
	public List<User> FindAll() {
		// TODO Auto-generated method stub
		return super.findAll(User.class, true);
	}

	@Override
	public List<User> FindAll(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return super.findAll(User.class, true, pageNumber, pageSize);
	}

	@Override
	public List<User> findUsersLikedByVideoHref(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return super.callStored(NamedStored.FIND_USERS_LIKED_BY_VIDEO_HREF, params);
	}

	
	
	
}
