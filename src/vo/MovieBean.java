package vo;

import java.sql.Date;

public class MovieBean {
	private int movie_idx;
	private String movie_title;
	private String movie_content;
	private int movie_time;
	private int movie_hall_num;
	private Date movie_start_day;
	private Date movie_end_day;
	
	
	public int getMovie_idx() {
		return movie_idx;
	}
	public void setMovie_idx(int movie_idx) {
		this.movie_idx = movie_idx;
	}
	public String getMovie_title() {
		return movie_title;
	}
	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}
	public String getMovie_content() {
		return movie_content;
	}
	public void setMovie_content(String movie_content) {
		this.movie_content = movie_content;
	}
	public int getMovie_time() {
		return movie_time;
	}
	public void setMovie_time(int movie_time) {
		this.movie_time = movie_time;
	}
	public int getMovie_hall_num() {
		return movie_hall_num;
	}
	public void setMovie_hall_num(int movie_hall_num) {
		this.movie_hall_num = movie_hall_num;
	}
	public Date getMovie_start_day() {
		return movie_start_day;
	}
	public void setMovie_start_day(Date movie_start_day) {
		this.movie_start_day = movie_start_day;
	}
	public Date getMovie_end_day() {
		return movie_end_day;
	}
	public void setMovie_end_day(Date movie_end_day) {
		this.movie_end_day = movie_end_day;
	}
	
}
