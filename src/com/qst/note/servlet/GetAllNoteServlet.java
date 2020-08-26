package com.qst.note.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qst.note.bean.NoteBean;
import com.qst.note.dao.NoteDao;

/**
 * Servlet implementation class GetAllNoteServlet
 */
@WebServlet("/GetAllNoteServlet")
public class GetAllNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllNoteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");//设置编码格式
		
		String tel=request.getParameter("tel");//获取请求参数tel
		ArrayList<NoteBean> all=new NoteDao().getAllNotes(tel);
		
		Gson gson=new Gson();
		
		response.getWriter().append(gson.toJson(all));//将结果解析成JSON数据通过相应对象。返回给客户端

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
