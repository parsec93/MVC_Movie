package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public class MovieReservationAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MovieResrvationAction 메서드 ");
		
		ActionForward forward = new ActionForward();
		forward.setPath("/movie/movie_reservation_form.jsp");
		
		return forward;
	}

}
