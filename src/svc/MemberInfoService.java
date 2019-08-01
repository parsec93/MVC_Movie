package svc;

import java.sql.Connection;

import dao.MemberDAO;
import vo.MemberBean;

import static db.JdbcUtil.*;

public class MemberInfoService {
	
	public MemberBean memberInfo(String sId){
		System.out.println("memberInfoSerivce");
		
		
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		MemberBean mb = memberDAO.memberInfo(sId);
		
		
		close(con);
		
		return mb;
		
	}
}
