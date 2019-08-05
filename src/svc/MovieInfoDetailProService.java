package svc;

import java.sql.Connection;

import dao.MovieDAO;
import vo.MovieBean;
import static db.JdbcUtil.*;

public class MovieInfoDetailProService {

	
	public MovieBean getMovieInfo(int movie_idx) {
		System.out.println("MovieInfoDetailProServce의 getMovieInfo() 메서드");
		Connection con = getConnection();
		
		MovieDAO movieDAO = MovieDAO.getInstace();
		movieDAO.setConnection(con);
		
		MovieBean movieBean = movieDAO.getMovieInfo(movie_idx);
		
		close(con);
		
		return movieBean;
	}
}
