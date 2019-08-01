package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.MemberBean;

public class MemberDAO {
	private static MemberDAO instance;
	private MemberDAO() {};
	
	public static MemberDAO getInstance() {
		if(instance == null) {
			instance = new MemberDAO();
		}
		
		return instance;
	}
	
	Connection con;
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public boolean selectLoginMember(String id, String password) {
		boolean isLoginMember = false;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from movie_member where member_id = ? and member_password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				isLoginMember = true;
			}
			
			
		} catch (Exception e) {
			System.out.println("selectLoginMember 실패!" + e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return isLoginMember;
		
		
	}
	
	
	// 회원 가입
	public int insertMember(MemberBean mb) {
		int insertCount = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO movie_member values(null, ?,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb.getMember_name());
			pstmt.setString(2, mb.getMember_id());
			pstmt.setString(3, mb.getMember_password());
			pstmt.setString(4, mb.getMember_gender());
			pstmt.setString(5, mb.getMember_jumin());
			pstmt.setString(6, mb.getMember_email());
			pstmt.setString(7, mb.getMember_phone());
			insertCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return insertCount;
	}
	
	
	
	//회원정보 불러오기 
	public MemberBean memberInfo(String sId) {
		System.out.println("memberInfo DAO 파일");
		MemberBean mb = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			String sql = "SELECT * FROM movie_member where member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mb = new MemberBean();
				mb.setMember_name(rs.getString("member_name"));
				mb.setMember_id(rs.getString("member_id"));
				mb.setMember_gender(rs.getString("member_gender"));
				mb.setMember_jumin(rs.getString("member_jumin"));
				mb.setMember_email(rs.getString("member_email"));
				mb.setMember_phone(rs.getString("member_phone"));
			}
			
		} catch (Exception e) {
			System.out.println("memberInfo 실패!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return mb;
	}
	
	
	
	
}
