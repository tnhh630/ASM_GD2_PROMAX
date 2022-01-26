package controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import constant.SessionAttr;
import dto.UserDto;
import dto.VideoLikedInfo;
import entities.User;
import service.StatsService;
import service.UserService;
import service.impl.StatsServiceImpl;
import service.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/admin","/admin/favorites"} ,name ="HomeControllerOfAdmin")
public class HomeController extends HttpServlet{


	private static final long serialVersionUID = 4851641645113863681L;
	
	private StatsService statsService = new StatsServiceImpl();
	private UserService userService = new UserServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		
		if(currentUser!=null && currentUser.getIsAdmin() == Boolean.TRUE) {
			
			String path = req.getServletPath();
			switch (path) {
			case "/admin":
				doGetHome(req, resp);
				break;
			case "/admin/favorites":
				doGetFavorite(req, resp);
				break;
			}
			
		
		}else {
			resp.sendRedirect("/index");
		}
		
		
		
	}
	//localhost:8080/ASM_GD2_PROMAX/admin/favorites?href=${videoHref}
	private void doGetFavorite(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {		
		PrintWriter out = resp.getWriter();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		String videoHref = req.getParameter("href");
		List<UserDto> users = userService.findUsersLikedVideoByVideoHref(videoHref);
		if(users.isEmpty()) {
			resp.setStatus(400);
			System.out.println("Video không có ai like");
		}else {
			//map từ dạng object sang json
			ObjectMapper mapper = new ObjectMapper();
			String dataRespose = mapper.writeValueAsString(users);
			resp.setStatus(200);/* không phải 204 như trước vì có trả về data */
			out.print(dataRespose);
			out.flush(); //đẩy nó ra #vì thao tác với data table nên cần phải biến đổi nhiều thứ ntn
		}
		
	}
	protected void doGetHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<VideoLikedInfo> videos = statsService.findVideoLikedInfo();
		req.setAttribute("videos", videos);
		req.getRequestDispatcher("/views/admin/home.jsp").forward(req, resp);
	}

}
