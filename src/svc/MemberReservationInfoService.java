package svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MovieDAO;

import static db.JdbcUtil.*;

import vo.MovieBean;
import vo.MovieReservationBean;

public class MemberReservationInfoService {
	
	public ArrayList getReservationList(String member_id) {
		
		System.out.println("MemberReservationService");
		Connection con = getConnection();
		MovieDAO movieDAO = MovieDAO.getInstace();
		movieDAO.setConnection(con);
		
		ArrayList<MovieBean> reservationList = null;
		
		close(con);
		
		return reservationList;
	}
	
}
