package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import vo.MovieBean;
import vo.MovieReservationBean;

import static db.JdbcUtil.*;

public class MovieDAO {
	private static MovieDAO instance;
	private MovieDAO() {};
	
	public static MovieDAO getInstace() {
		if(instance == null) {
			instance = new MovieDAO();
		}
		
		return instance;
	}
	
	Connection con;
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public ArrayList<MovieBean> getMovieList(String sort, String listType) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MovieBean> movieList = null;
		
		String sql = "";
		
		if(listType.equals("") || listType.equals("now")) {
			//where 절을 사용하여 현재 날짜가 포함된 범위 내의 데이터를 검색
			if(sort.equals("")) {
				sql = "select * from movie_info where movie_start_day <= now() and movie_end_day >= now()";
			}else if (sort.equals("title")) {
				sql = "select * from movie_info where movie_start_day <= now() and movie_end_day >= now() order by movie_title asc";
			}else if (sort.equals("date")) {
				sql = "select * from movie_info where movie_start_day <= now() and movie_end_day >= now() order by movie_start_day desc ";
			}
		}else if (listType.equals("soon")) {
			//where 절을 사용하여 현재 날짜보다 개봉일이 이후인 데이터를 검색
			if(sort.equals("")) {
				sql = "select * from movie_info where movie_start_day > now() ";
			}else if (sort.equals("title")) {
				sql = "select * from movie_info  where movie_start_day > now() order by movie_title asc";
			}else if (sort.equals("date")) {
				sql = "select * from movie_info  where movie_start_day > now() order by movie_start_day desc";
			}
		}
		
		
		
		try {
			//전체 영화 목록을 조회( 실제로는 사용하지 않음. 왠만한 경우 select * 을 하지 않음. 데이터가 수십만개라서 멈춰버림. 최소한 where 절 또는 select "컬럼명" 을 사용 )
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			movieList = new ArrayList<MovieBean>();
			
			while(rs.next()) {
				MovieBean mb = new MovieBean();
				mb.setMovie_idx(rs.getInt("movie_idx"));
				mb.setMovie_title(rs.getString("movie_title"));
				mb.setMovie_content(rs.getString("movie_content"));
				mb.setMovie_time(rs.getInt("movie_time"));
				mb.setMovie_hall_num(rs.getInt("movie_hall_num"));
				mb.setMovie_start_day(rs.getDate("movie_start_day"));
				mb.setMovie_end_day(rs.getDate("movie_end_day"));
				
				movieList.add(mb);
				
				System.out.println(mb.getMovie_idx());
				System.out.println(mb.getMovie_time());
				System.out.println(mb.getMovie_content());
				System.out.println(mb.getMovie_time());
				System.out.println(mb.getMovie_hall_num());
				System.out.println(mb.getMovie_start_day());
				System.out.println(mb.getMovie_end_day());
			}
			
		} catch (Exception e) {
			System.out.println("getMovieList 에러" + e.getMessage());
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return movieList;
		
	}
	
	
	public MovieBean getMovieInfo(int movie_idx) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MovieBean movieBean = null;
		try {
			String sql = "SELECT * FROM movie_info where movie_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, movie_idx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				movieBean = new MovieBean();
				movieBean.setMovie_idx(rs.getInt("movie_idx"));
				movieBean.setMovie_title(rs.getString("movie_title"));
				movieBean.setMovie_content(rs.getString("movie_content"));
				movieBean.setMovie_time(rs.getInt("movie_time"));
				movieBean.setMovie_hall_num(rs.getInt("movie_hall_num"));
				movieBean.setMovie_start_day(rs.getDate("movie_start_day"));
				movieBean.setMovie_end_day(rs.getDate("movie_end_day"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
				
		return movieBean;	
	}   
	
	public int insertReservation(MovieReservationBean movieReservationBean) {
        int count = 0;
        
        PreparedStatement pstmt = null;
        
        // 영화번호, 아이디, 영화시간, 인원수, 예매금액 DB에 저장
        String sql = "INSERT INTO reservation_info VALUES (null,?,?,?,?,?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, movieReservationBean.getMovie_idx());
            pstmt.setString(2, movieReservationBean.getMember_id());
            pstmt.setString(3, movieReservationBean.getMovie_time());
            pstmt.setInt(4, movieReservationBean.getMovie_people_count());
            pstmt.setInt(5, movieReservationBean.getPee());
            count = pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("insertReservation 에러! - " + e.getMessage());
        }finally {
        	close(pstmt);
        }
        
        return count;
    }
	
	

// 	 public ArrayList selectReservationList(String member_id) {
//      // [movie_info : 영화제목, 상영관번호] [reservation_info : 상영시각, 예매인원, 결제금액] 순으로 
//      // ArrayList 객체에 추가
//      // => member_id 를 사용하여 영화번호(movie_idx), 상영시각(movie_time), 예매인원(movie_people_count),
//      //    결제금액(pee) 를 조회한 후, 영화번호(movie_idx) 에 해당하는 
//      //    영화제목(movie_title), 상영관번호(movie_hall_num) 를 따로 조회하여 합치기
//      
//      
//      ArrayList reservationList = null;
//      
//      PreparedStatement pstmt = null;
//      ResultSet rs = null;
//      
//      // member_id 를 기준으로 예약 목록 전부 조회
//      String sql = "SELECT movie_idx,movie_time,movie_people_count,pee "
//                      + "FROM reservation_info WHERE member_id=?";
//      try {
//          pstmt = con.prepareStatement(sql);
//          pstmt.setString(1, member_id);
//          rs = pstmt.executeQuery();
//          
//          int movie_idx = 0;
//          String movie_time = "";
//          int movie_people_count = 0;
//          int pee = 0;
//          String movie_title = "";
//          int movie_hall_num = 0;
//          
//          reservationList = new ArrayList(); // 예약 목록 전체를 저장할 ArrayList
//          
//          while(rs.next()) {
//              ArrayList reservationInfo = new ArrayList(); // 영화 1개의 예약 정보를 저장할 ArrayList
//              // 각 변수에 조회된 정보 저장
//              movie_idx = rs.getInt("movie_idx");
//              movie_time = rs.getString("movie_time");
//              movie_people_count = rs.getInt("movie_people_count");
//              pee = rs.getInt("pee");
//              
//              // 조회된 정보 중 영화 번호를 기준으로 해당 번호에 대한 영화 정보 다시 조회
//              sql = "SELECT movie_title,movie_hall_num FROM movie_info WHERE movie_idx=?";
//              pstmt = con.prepareStatement(sql);
//              pstmt.setInt(1, movie_idx);
//              // 주의! while 문에서 ResultSet rs 변수를 사용중이므로
//              // 다른 조회 정보는 별도의 ResultSet 타입 변수를 선언해서 사용해야한다!
//              ResultSet rs2 = pstmt.executeQuery();
//              
//              if(rs2.next()) {
//                  // 연계되어 조회된 영화제목, 상영관번호도 변수에 저장
//                  movie_title = rs2.getString("movie_title");
//                  movie_hall_num = rs2.getInt("movie_hall_num");
//              }
//              
//              // 전체 조회 결과(영화 1개 정보)를 ArrayList 에 저장 
//              reservationInfo.add(movie_title);
//              reservationInfo.add(movie_hall_num);
//              reservationInfo.add(movie_time);
//              reservationInfo.add(movie_people_count);
//              reservationInfo.add(pee);
//              
//              // 영화 1개 정보를 전체 예약 목록 ArrayList 객체에 저장
//              reservationList.add(reservationInfo);
//              
//              close(rs2);
//          }
//          
//      } catch (SQLException e) {
//          System.out.println("selectReservationList 에러! - " + e.getMessage());
//      } finally {
//          close(rs);
//          close(pstmt);
//      }
//      
//      
//      return reservationList;
//  }
	
	

	public ArrayList selectReservationList(String member_id) {
      // [movie_info : 영화제목, 상영관번호] [reservation_info : 상영시각, 예매인원, 결제금액] 순으로 
	  // ArrayList 객체에 추가 => inner join 사용하여 한 번에 조회 
      
      
      ArrayList reservationList = null;
      
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      String sql = "SELECT movie_info.movie_title, movie_info.movie_hall_num, reservation_info.movie_time,reservation_info.movie_people_count, reservation_info.movie_idx, reservation_info.pee FROM movie_info JOIN reservation_info ON movie_info.movie_idx = reservation_info.movie_idx  WHERE member_id=?";
    		  
      try {
    	  pstmt = con.prepareStatement(sql);
    	  pstmt.setString(1, member_id);
    	  rs = pstmt.executeQuery();
    	  
    	  reservationList = new ArrayList();
      
    	  while(rs.next()) {
    		  ArrayList reservationInfo = new ArrayList(); // 영화 1개의 예약 정보를 저장할 ArrayList
    		  reservationInfo.add(rs.getString("movie_title"));
    		  reservationInfo.add(rs.getInt("movie_hall_num"));
    		  reservationInfo.add(rs.getString("movie_time"));
    		  reservationInfo.add(rs.getInt("movie_people_count"));
    		  reservationInfo.add(rs.getInt("pee"));
    		  reservationInfo.add(rs.getInt("movie_idx"));
    		  
    		  reservationList.add(reservationInfo);
    	  }
          
      } catch (SQLException e) {
          System.out.println("selectReservationList 에러! - " + e.getMessage());
      } finally {
          close(rs);
          close(pstmt);
      }
      
      
      return reservationList;
  }
	
	
	
	
	
	
}
