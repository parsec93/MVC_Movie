package vo;

import java.sql.Date;

public class MovieReservationBean {
	private int idx;
	private int movie_idx;
	private String member_id;
	private String movie_time;
	private int movie_people_count;
	private int pee;
	
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getMovie_idx() {
		return movie_idx;
	}
	public void setMovie_idx(int movie_idx) {
		this.movie_idx = movie_idx;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMovie_time() {
		return movie_time;
	}
	public void setMovie_time(String movie_time) {
		this.movie_time = movie_time;
	}
	public int getMovie_people_count() {
		return movie_people_count;
	}
	public void setMovie_people_count(int movie_people_count) {
		this.movie_people_count = movie_people_count;
	}
	public int getPee() {
		return pee;
	}
	public void setPee(int pee) {
		this.pee = pee;
	}
	
	
}
