package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MovieListService;
import vo.ActionForward;
import vo.MovieBean;

public class MovieListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("MovieListAction");
		
		// 정렬 방식 파라미터를 가지고 와야함.
		String sort = "";
		
		if(request.getParameter("sort") != null) {
			sort = request.getParameter("sort");
		}
		
		//목록 종류( 현재 상영 중 or 개봉 예정) 파라미터(listType)
		String listType = "";
		if(request.getParameter("listType") != null) {
			listType = request.getParameter("listType");
		}
	
		
		
		ActionForward forward = new ActionForward();
		
		MovieListService movieListService = new MovieListService();
		
		ArrayList<MovieBean> movieList = movieListService.getMovieList(sort,listType); //정렬 방식을 전달.
		
		//저장되어 있는 쿠키 정보를 가져와서 ArrayList 객체로 생성 
		Cookie[] cookies = request.getCookies(); // 저장된 모든 쿠키를 배열로 가져오기 
		
		// 쿠키를 저장할 ArrayList 객체를 생성 
		ArrayList<String> todayMovieList = new ArrayList<String>();
		ArrayList<String> todayMovieIndexList = new ArrayList<String>(); // 영화 번호를 저장할 객체 
		
		// 쿠키 배열이 비어있지 않으면 쿠키 내의 todayXXX 에 해당하는 쿠키 모두 가져와서 ArrayList 에 저장 
		if(cookies != null) {
			for(int i = 0; i<cookies.length; i++) {
				// 쿠키의 이름이 "today" 로 시작하는 문자열을 가진 쿠키일 때
				if(cookies[i].getName().startsWith("today")) {
					todayMovieList.add(cookies[i].getValue()); // 쿠키 값 ArrayList 객체에 저장
					todayMovieIndexList.add(cookies[i].getName().substring("today".length()));
				}
			}
		}
		
		System.out.println("인덱스 번호 " + todayMovieIndexList);
		request.setAttribute("movieList", movieList);
		request.setAttribute("listType", listType);
		request.setAttribute("sort", sort);
		
		
		// 쿠키 정보를 담은 ArrayList 객체도 request 객체에 저장 
		request.setAttribute("todayMovieList", todayMovieList);
		request.setAttribute("todayMovieIndexList", todayMovieIndexList);
		
		if(movieList == null) {
			response.setContentType("text/html;/charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
            out.println("alert('영화정보 조회 실패!')");
            out.println("history.back()");
            out.println("</script>");
		} else {
			System.out.println("영화정보 조회 성공");
			forward = new ActionForward();
			forward.setPath("/movie/movieList.jsp");
			forward.setRedirect(false);
		}
		
		return forward;
	}

}
