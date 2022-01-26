package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.SessionAttr;
import entities.History;
import entities.User;
import entities.Video;
import service.HistoryService;
import service.VideoService;
import service.impl.HistoryServiceImpl;
import service.impl.VideoServiceImpl;

@WebServlet(urlPatterns = "/video")
public class VideoConTroller extends HttpServlet{


	private static final long serialVersionUID = -6229131862299043407L;

	private VideoService videoService = new VideoServiceImpl();
	private HistoryService historyService = new HistoryServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		String actionParam = req.getParameter("action");
		String href = req.getParameter("id");
		HttpSession session = req.getSession();
		
		switch(actionParam){
		case "watch":
			dogetWatch(session,href,req,resp);
			break;
		case "like":
			dogetLike(session,href,req,resp);
			break;
		}
	}
	/* localhost:8080/ASM-GD2-/video?action=watch&id={href} */
	private void dogetWatch(HttpSession session ,String href,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		Video video = videoService.findByHref(href);
		req.setAttribute("video", video);
		
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		
		if(currentUser!=null) {
			History history = historyService.create(currentUser, video);
			req.setAttribute("flagLikedBtn", history.getIsLiked());
		}
		
		req.getRequestDispatcher("/views/user/video-detail.jsp").forward(req, resp);
	}
	/* localhost:8080/ASM-GD2-/video?action=like&id={href} */
	private void dogetLike(HttpSession session ,String href,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		resp.setContentType("aplication/json");
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		boolean result = historyService.updateLikeOrUnlike(currentUser, href);
		if(result==true) {
			resp.setStatus(204); // succeed but no resp data
		}else {
			resp.setStatus(400);
		}
	}	
		

}
