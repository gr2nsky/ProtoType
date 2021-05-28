package com.ymboard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ymboard.dao.LoginDao;

public class DeleteCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) {
		int no = Integer.parseInt(request.getParameter("no"));
		LoginDao dao = new LoginDao();
		dao.delete(no);
	}

}
