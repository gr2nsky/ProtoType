package com.pagingTest.dto;

import java.sql.Timestamp;

public class Dto {
	int no;
	String writer;
	String title;
	String content;
	Timestamp date;
	
	public Dto(){
		
	}

	public Dto(int no, String name, String title, String content, Timestamp date) {
		super();
		this.no = no;
		this.writer = name;
		this.title = title;
		this.content = content;
		this.date = date;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	
}
