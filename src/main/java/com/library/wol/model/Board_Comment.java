package com.library.wol.model;

import java.sql.Timestamp;

public class Board_Comment {
	private int bno;
	private String user_id;
	private String board_Comment;
	private String writetime;
	private int comment_no;
	
	
	
	public int getComment_no() {
		return comment_no;
	}
	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getBoard_Comment() {
		return board_Comment;
	}
	public void setBoard_Comment(String board_Comment) {
		this.board_Comment = board_Comment;
	}
	public String getWritetime() {
		return writetime;
	}
	public void setWritetime(String writetime) {
		this.writetime = writetime;
	}
}
