package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberJoinProService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberJoinProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberJoinProAction");
		
		ActionForward forward = null;
		                                                                                            
		MemberBean mb = new MemberBean();
		mb.setMember_name(request.getParameter("name"));
		mb.setMember_id(request.getParameter("id"));
		mb.setMember_password(request.getParameter("password"));
		mb.setMember_gender(request.getParameter("gender"));
		mb.setMember_jumin(request.getParameter("jumin1") + "-" + request.getParameter("jumin2"));
		mb.setMember_email(request.getParameter("email1") + "@" + request.getParameter("email2"));
		mb.setMember_phone(request.getParameter("phone1") + "-" + request.getParameter("phone2") + "-" + request.getParameter("phone3"));                                                                 
		
		MemberJoinProService memberJoinProService = new MemberJoinProService();
		boolean isInsertSuccess = memberJoinProService.insertMember(mb);
		
		if(isInsertSuccess == false) {
			response.setContentType("text/html;/charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
            out.println("alert('회원가입 실패!')");
            out.println("history.back()");
            out.println("</script>");
		}else {
			System.out.println("회원가입 성공");
			forward = new ActionForward();
			forward.setPath("index.jsp");
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
