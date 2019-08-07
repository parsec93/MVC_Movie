package svc;

import java.sql.Connection;

import dao.MovieDAO;

import static db.JdbcUtil.*;

import vo.MovieReservationBean;

public class MovieReservationProService {
	
	public boolean addReservation(MovieReservationBean movieReservationBean) {
		System.out.println("MovieResrvationProService 의 addReservation() 메서드");
		
		Connection con = getConnection();
		MovieDAO movieDAO = MovieDAO.getInstace();
		movieDAO.setConnection(con);
		
		boolean isReservationSuccess = false;
		System.out.println("1");
		// 예매 정보 저장 위해 MovieReservationBean 객체 전달 후 int형 타입으로 결과 리턴
        int count = movieDAO.insertReservation(movieReservationBean);
        System.out.println("2");
        // 리턴받은 값이 1이면 예매 성공 => commit(), boolean 타입 변수 true 로 변경
        //       ""      0이면 예매 실패 => rollback()
        System.out.println(count);
        
        if(count > 0) {
        	commit(con);
            isReservationSuccess = true;
        } else {
            rollback(con);
        }
        close(con);
        return isReservationSuccess;
		
	}
	
}
