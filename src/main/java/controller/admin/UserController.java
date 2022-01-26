package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.SessionAttr;
import entities.User;
import service.EmailService;
import service.UserService;
import service.impl.EmailServiceImpl;
import service.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/admin/user"},name ="UserControllerOfAdmin")
public class UserController extends HttpServlet{
	
	private static final long serialVersionUID = -3560771379959792939L;
	private UserService userService = new UserServiceImpl();
	private EmailService emailService = new EmailServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User currentUser =(User) session.getAttribute(SessionAttr.CURRENT_USER);
		if(currentUser!= null && currentUser.getIsAdmin()== Boolean.TRUE) {
			String action = req.getParameter("action");
			switch (action) {
			case "view":
				doGetOverView(req,resp);
				break;
			case "delete":
				doGetDelete(req,resp);
				break;
			case "add":
				req.setAttribute("isEdit",false);
				doGetAdd(req,resp);
				break;
			case "edit":
				req.setAttribute("isEdit",true);
				doGetEdit(req,resp);
				break;
			}
		}else {
			resp.sendRedirect("index");
		}
		
	}
	
	

	
	
	//localhost:ASM_GD2_PROMAX/admin/user?action=view 
	private void doGetOverView(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
		List<User> users = userService.FindAll();
		req.setAttribute("users", users);
		req.getRequestDispatcher("/views/admin/user-overview.jsp").forward(req, resp);
	}
	//localhost:ASM_GD2_PROMAX/admin/user?action=delete&href={href}
	private void doGetDelete(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
		//dùng ajax
		resp.setContentType("application/json");
		String username = req.getParameter("username");
		User userDeleted = userService.delete(username);
		// nếu delete thành công
		if(userDeleted != null) {
			resp.setStatus(204);
		}else {
			resp.setStatus(400);
		}
		
	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		User currentUser =(User) session.getAttribute(SessionAttr.CURRENT_USER);
		if(currentUser!= null && currentUser.getIsAdmin()== Boolean.TRUE) {
			String action = req.getParameter("action");
			switch (action) {
			case "add":
				doPostAdd(session, req,resp);
				break;
			case "edit":
				doPostEdit(req,resp);
				break;	
			}
		}else {
			resp.sendRedirect("index");
		}
	}
	
	
	//localhost:ASM_GD2_PROMAX/admin/user?action=add
	private void doGetAdd(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{

		req.getRequestDispatcher("/views/admin/user-edit.jsp").forward(req, resp);
	}
	//localhost:ASM_GD2_PROMAX/admin/user?action=edit&username={username}
	private void doGetEdit(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
		String username = req.getParameter("username");
		User user = userService.findByUsername(username);
		req.setAttribute("user", user);
		req.getRequestDispatcher("/views/admin/user-edit.jsp").forward(req, resp);
	}
	private void doPostAdd(HttpSession session,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		// TODO Auto-generated method stub
		resp.setContentType("application/json");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");

		User user = userService.register(username, password, email);

		if(user != null) {
			resp.setStatus(204);
		}else {
			resp.setStatus(400);
		}
	}
	private void doPostEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		// TODO Auto-generated method stub
		resp.setContentType("application/json");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");		
	
		 User user = userService.findByUsername(username);
		 user.setUsername(username);
		 user.setPassword(password);
		 user.setEmail(email);
	
		 User userReturn = userService.update(user);
		// nếu tạo thành công
		if(userReturn != null) {
			resp.setStatus(204);
		}else {
			resp.setStatus(400);
		}
	}
}
