	package service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import dto.UserDto;
import entities.User;
import service.UserService;

public class UserServiceImpl implements UserService{

	private UserDao dao;
	
	public UserServiceImpl() {
		dao = new UserDaoImpl();
	}
	
	@Override
	public User findById(Integer Id) {
		// TODO Auto-generated method stub
		return dao.findById(Id);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return dao.findByEmail(email);
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return dao.findByUsername(username);
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		return dao.findByUsernameAndPassword(username, password);
		//ma hoa pass word bcrypt md5
	}

	@Override
	public User resetPassword(String email) {
		// TODO Auto-generated method stub
		User existUser = findByEmail(email);
		if(existUser!=null) {
			//1000 - 9999
			// (random * (max- min) +1) + min
			String newPass = String.valueOf((int)(Math.random()*((9999-1000)+1)) + 1000);
			existUser.setPassword(newPass);
			return dao.update(existUser);
		}
		return null;
	}

	@Override
	public List<User> FindAll() {
		// TODO Auto-generated method stub
		return dao.FindAll();
	}

	@Override
	public List<User> FindAll(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return dao.FindAll(pageNumber, pageSize);
	}

	@Override
	public User register(String username, String password, String email) {
		// TODO Auto-generated method stub
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(password); //ma hoa pass word bcrypt md5
		newUser.setEmail(email);
		newUser.setIsAdmin(Boolean.FALSE);
		newUser.setIsActive(Boolean.TRUE);
		return dao.create(newUser);
	}

	@Override
	public User update(User entity) {
		// TODO Auto-generated method stub
		return dao.update(entity);
	}

	@Override
	public User delete(String username) {
		// TODO Auto-generated method stub
		User user = dao.findByUsername(username);
		user.setIsActive(Boolean.FALSE);
		return dao.update(user);
	}

	@Override
	public List<UserDto> findUsersLikedVideoByVideoHref(String href) {
		Map<String, Object> params = new HashMap<>();
		params.put("videoHref", href); /* tự tao constant cho videoHref */
		List<User> users = dao.findUsersLikedByVideoHref(params);
		List<UserDto> result = new ArrayList<>();
		users.forEach(user -> {
			UserDto dto = new UserDto();
			dto.setUsername(user.getUsername());
			dto.setEmail(user.getEmail());
			result.add(dto);
		});
		return result;
	}

}
