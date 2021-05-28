package com.pagingTest.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pagingTest.dao.Dao;
import com.pagingTest.dto.Dto;

public class ContentCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) {
		int no = Integer.parseInt(request.getParameter("no"));
		
		Dao dao = new Dao();
		Dto dto = dao.content(no);
		
		request.setAttribute("content", dto);

	}

}
