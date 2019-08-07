package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberReservationInfoService;
import vo.ActionForward;
import vo.MovieReservationBean;

public class MemberReservationInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberReservationInfoAction");
		
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String sId = (String)session.getAttribute("sId");
		
		if(sId == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
            out.println("alert('잘못된 접근입니다!')");
            out.println("location.href='index.jsp'");
            out.println("</script>");
		}else {
			// MemberReservationInfoService 클래스의 getReservationList() 메서드 호출 
			MemberReservationInfoService memberReservationInfoService = new MemberReservationInfoService();
		
			ArrayList reservationList = new ArrayList();
			
			reservationList = memberReservationInfoService.getReservationList(sId);
			// => 세션 아이디 전달, ArrayList 타입으로 리턴 받음 
			//	   리턴받은 ArrayList 객체를 reqeust 객체에 저장 
			// 	 ActionForward 객체 생성하여 member 폴더의 member_reservation_list.jsp 페이지 포워딩 
			request.setAttribute("reservationList", reservationList);
			
			forward = new ActionForward();
			forward.setPath("/member/member_reservation_list.jsp");
			
		}
		
		
		return forward;
	}

}
