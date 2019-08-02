package action;

import java.io.PrintWriter;
import java.util.ArrayList;

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
		
		request.setAttribute("movieList", movieList);
		request.setAttribute("listType", listType);
		request.setAttribute("sort", sort);
		
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
