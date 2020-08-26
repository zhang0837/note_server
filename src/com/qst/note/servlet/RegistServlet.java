package com.qst.note.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qst.note.result.Result;
import com.google.gson.Gson;
import com.qst.note.bean.UserBean;
import com.qst.note.dao.UserDao;

/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/regist")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		UserBean use=new UserBean();
		use.setName(request.getParameter("name"));
		use.setPass(request.getParameter("pass"));
		use.setTel(request.getParameter("tel"));
		use.setQq(request.getParameter("qq"));
		use.setWechat(request.getParameter("wechat"));
		UserDao userDao=new UserDao();
		Boolean b=userDao.regist(use);
		Result r=new Result();
		r.isSuccess=b;
		if(b){
			r.msg="¹§Ï²Äú×¢²á³É¹¦";
		}else{
			r.msg="×¢²áÊ§°Ü£¬¸ÃÊÖ»úºÅÒÑ±»×¢²á";
		}
		Gson gson=new Gson();
		String result=gson.toJson(r);
		response.getWriter().append(result);
		
		
	}

}
