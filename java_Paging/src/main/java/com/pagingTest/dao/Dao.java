package com.pagingTest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.pagingTest.dto.Dto;

public class Dao {
	
	DataSource dataSource;
	
	public Dao() {
		System.out.println("Connectiong database...");
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/paging");
			System.out.println("Database connection success");
		} catch (NamingException e) {
			System.out.println("Database connection failed");
			e.printStackTrace();
		}
	}
	//사용자가 요청한 페이지(offset)와 페이지당 표시할 게시글의 수(limit)을 매개변수로 받는다.
	public ArrayList<Dto> list(int requestPage, int numOfTuplePerPage){
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ArrayList<Dto> dtos = new ArrayList<Dto>();
		// LIMIT {OFFSET}, {LIMIT} -> 쿼리결과중 offset번째부터 limit개의 튜플을 출력  
		String query = "SELECT * FROM paging ORDER BY no DESC LIMIT ?, ?";
		//page는 1부터 시작하지만, offset은 0부터 시작.(0~9(10개), 10~19(10개)와같이 offset을 설정해야 하기 때문)
		int offset = requestPage - 1;
		
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(query);
			// 0을 나누면 에러가 발생하므로 예외처
			if (offset  == 0) {
				psmt.setInt(1, offset);
			} else {
				psmt.setInt(1, offset*numOfTuplePerPage);
			}
			psmt.setInt(2, numOfTuplePerPage);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("no");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp date = rs.getTimestamp("date");
				
				Dto dto = new Dto(no, writer, title, content, date);
				dtos.add(dto);
				System.out.println("list-data load success");
			}			
		} catch (Exception e) {
			System.out.println("list-data load fail");
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(psmt != null) psmt.close();
				if(conn != null) conn.close();
				System.out.println("< rs, psmt, conn close success>");
			} catch (Exception e) {
				System.out.println("< rs, psmt, conn close Fail>");
			}
		}
		
		return dtos;
	}
	//list에서 사용하는 릴레이션이 가진 튜플의 총 갯수를 리턴한다.
	public int countTuple() {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int count = 0;
		String query = "SELECT COUNT(*) FROM paging";
		
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(query);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
				System.out.println("list-count success");
			}			
		} catch (Exception e) {
			System.out.println("list-count fail");
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(psmt != null) psmt.close();
				if(conn != null) conn.close();
				System.out.println("< rs, psmt, conn close success>");
			} catch (Exception e) {
				System.out.println("< rs, psmt, conn close Fail>");
			}
		}
		
		return count;
	}
	
	public Dto content(int no) {
		Dto dto = null;
		String query = "SELECT * FROM paging where no = ?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setInt(1, no);
			rs = psmt.executeQuery();
			
		if(rs.next()){
				int aNo = rs.getInt("no");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp date = rs.getTimestamp("date");
				
				dto = new Dto(aNo, writer, title, content, date);
				System.out.println("<content-data load success>");
			}
			
		} catch (Exception e) {
			System.out.println("<content-data load Fail>");
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(psmt != null)
					psmt.close();
				if(conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("< rs, psmt, conn close Fail>");
				e.printStackTrace();
			}
		}
		
		return dto;
	}

}
