package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import vo.MovieBean;

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
	
	
}
