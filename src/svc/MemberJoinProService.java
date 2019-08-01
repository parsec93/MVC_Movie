package svc;

import java.sql.Connection;

import dao.MemberDAO;
import vo.MemberBean;

import static db.JdbcUtil.*;

public class MemberJoinProService {
	
	public boolean insertMember(MemberBean mb) {
		System.out.println("MemberJoinProService");
		
		boolean isInsertSuccess = false;         
		
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		int count = memberDAO.insertMember(mb);
		
		if (count ==1) {
			commit(con);
			isInsertSuccess = true;
		}else {
			rollback(con);
		}
		
		close(con);
		
		return isInsertSuccess;
	}
}
