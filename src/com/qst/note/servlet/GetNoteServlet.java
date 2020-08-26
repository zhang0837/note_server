package com.qst.note.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qst.note.bean.NoteBean;
import com.qst.note.dao.NoteDao;

/**
 * Servlet implementation class GetNoteServlet
 */
@WebServlet("/GetNoteServlet")
public class GetNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetNoteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");//设置编码格式
		
		int id=Integer.valueOf(request.getParameter("id"));//将获取的参数id转换成int类型
		NoteDao dao=new NoteDao();
		NoteBean note=dao.getNoteByID(id);
		Gson gson=new Gson();
		
		response.getWriter().append(gson.toJson(note));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
