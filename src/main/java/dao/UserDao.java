package dao;

import java.util.List;
import java.util.Map;

import entities.User;

public interface UserDao {
	User findById(Integer Id);
	User findByEmail(String email);
	User findByUsername(String username);
	User findByUsernameAndPassword(String username,String password);
	List<User> FindAll();
	List<User> FindAll(int pageNumber , int pageSize);
	User create(User entity);
	User update(User entity);
	User delete(User entity);
	List<User> findUsersLikedByVideoHref(Map<String,Object>  params);
}
