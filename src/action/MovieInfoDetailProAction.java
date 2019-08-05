package action;

import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MovieInfoDetailProService;
import vo.ActionForward;
import vo.MovieBean;

public class MovieInfoDetailProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MovieInfoDetailProAction");
		
		int movie_idx = Integer.parseInt(request.getParameter("idx"));
		MovieInfoDetailProService movieInfoDetailProService = new MovieInfoDetailProService();
		
		MovieBean movieBean = movieInfoDetailProService.getMovieInfo(movie_idx);
		
		ActionForward forward = new ActionForward();
		
		request.setAttribute("movieBean", movieBean);
		
		//최근 조회한 영화 정보를 표시하기 위한 쿠키를 생성 
		Cookie todayMovieCookie = new Cookie("today" + movie_idx,URLEncoder.encode(movieBean.getMovie_title(),"UTF-8"));
		todayMovieCookie.setMaxAge(60*60*24); // 쿠키 유지 시간을 설정 (1일)
		
		// response 객체에 쿠키를 저장 
		response.addCookie(todayMovieCookie);
		
		
		if(movieBean == null) {
			response.setContentType("text/html;/charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
            out.println("alert('영화정보 조회 실패!')");
            out.println("history.back()");
            out.println("</script>");
		}else {
			System.out.println("영화정보 조회 성공");
			forward = new ActionForward();
			forward.setPath("/movie/movie_info_detail.jsp");
			forward.setRedirect(false);
		}
		
		
		
		
		return forward;
	}

}
