package svc;
import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MovieDAO;
import vo.MovieBean;

public class MovieListService {
	
	
	public ArrayList<MovieBean> getMovieList(String sort, String listType) {
		System.out.println("MovieListSesrvice의 getMovieList() 메서드");
		Connection con = getConnection();
		
		MovieDAO movieDAO = MovieDAO.getInstace();
		movieDAO.setConnection(con);
		
		ArrayList<MovieBean>movieList = movieDAO.getMovieList(sort, listType);
		
		close(con);
		
		return movieList;
		
	}
	
	
}
