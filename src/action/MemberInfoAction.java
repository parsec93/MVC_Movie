package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberInfoService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberInfoAction");
		
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		
		String sId = (String)session.getAttribute("sId");
		
		
		
		if(sId == null) {
			 response.setContentType("text/html;charset=UTF-8");
             PrintWriter out = response.getWriter();
             out.println("<script>");
             out.println("alert('회원정보 조회 실패!')"); 
              
             out.println("history.back()");
             out.println("</script>");
		}else {
			System.out.println("회원정보 조회 성공!");
			
			MemberInfoService memberInfoService = new MemberInfoService();
			
			MemberBean mb = memberInfoService.memberInfo(sId);
			
			if(mb != null) {
				request.setAttribute("mb", mb);
				
				forward = new ActionForward();
				forward.setPath("/member/memberinfo.jsp");
				forward.setRedirect(false);
				
				
				
			}
			
		}
		
		
		return forward;
	}

}
