package com.qst.note.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.qst.note.bean.NoteBean;
import com.qst.note.dao.UserDao;
import com.qst.note.util.DBUtil;

public class NoteDao {
	//向表note_table插入一条数据
	public Boolean insert(String title,String content,String noteTime,String tel){
		int id=new UserDao().getIDbyTel(tel);//根据用户电话获取用户id
		if(id<1)   //如果用户id小于1表示用户不存在，返回false
			return false;
		Connection c=DBUtil.getConnection();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
		String nowTime=sdf.format(new Date());//获取当前的时间
		
		try {
			PreparedStatement pst=(PreparedStatement)c.prepareStatement("insert into note_table(title,content,note_time,user_id,create_Time) value(?,?,?,?,?)");
			pst.setString(1, title);
			pst.setString(2, content);
			pst.setString(3, noteTime);
			pst.setInt(4, id);
			pst.setString(5,nowTime);
			pst.execute();
			DBUtil.close(c, pst, null);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public NoteBean getNoteByID(int id){
		NoteBean note=new NoteBean();
		Connection c=DBUtil.getConnection();
		try {
			PreparedStatement pst=(PreparedStatement)c.prepareStatement("select * from note_table where id=?");
			pst.setInt(1, id);
			ResultSet rs=pst.executeQuery();
			if(rs.first()){
				note.setId(id);
				note.setTitle(rs.getString("title"));
				note.setContent(rs.getString("content"));
				note.setCreateTime(rs.getString("create_time"));
				note.setUpdateTime(rs.getString("update_time"));
				note.setNoteTime(rs.getString("note_time"));
				note.setUserId(rs.getInt("user_id"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return note;
	}
	//根据id修改一条备忘录
	public boolean ModifyNote(int id,String title,String content,String noteTime){
		if(id<1)//如果用户id小于1表示用户不存在，返回false
			return false;
		Connection c=DBUtil.getConnection();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
		String nowTime=sdf.format(new Date());//获取当前时间
		
		try {
			PreparedStatement pst=(PreparedStatement)c.prepareStatement("update note_table set title=?,content=?,note_time=?,update_time=? where id=?");
			pst.setString(1,title );
			pst.setString(2, content);
			pst.setString(3, noteTime);
			pst.setString(4, nowTime);
			pst.setInt(5, id);
			pst.execute();
			DBUtil.close(c, pst, null);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	//根据电话号码，返回用户的所有备忘记录
	public ArrayList<NoteBean> getAllNotes(String tel){
		ArrayList<NoteBean> all=new ArrayList<NoteBean>();
		Connection c=DBUtil.getConnection();
		int id=new UserDao().getIDbyTel(tel);//根据用户名获取用户id
		try {
			PreparedStatement pst=(PreparedStatement)c.prepareStatement("select * from note_table where user_id=?");
			pst.setInt(1, id);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				NoteBean note=new NoteBean();
				note.setId(rs.getInt("id"));
				note.setTitle(rs.getString("title"));
				note.setContent(rs.getString("content"));
				note.setCreateTime(rs.getString("create_time"));
				note.setUpdateTime(rs.getString("update_time"));
				note.setNoteTime(rs.getString("note_time"));
				note.setUserId(rs.getInt("user_id"));
				all.add(note);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return all;
	}
	//根据id删除一条备忘记录，删除成功返回true，失败返回false
	public boolean deleteById(int id){
		Connection c=DBUtil.getConnection();
		try {
			PreparedStatement pst=(PreparedStatement)c.prepareStatement("delete from note_table where id=?");
			pst.setInt(1, id);
			pst.execute();
			DBUtil.close(c, pst, null);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
