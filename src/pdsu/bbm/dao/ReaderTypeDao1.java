package pdsu.bbm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pdsu.bbm.model.ReaderType;

public class ReaderTypeDao1 {
	public static ReaderType selectReaderType(int id){
		ReaderType ci = null;
		String sql = "select * from readertype where id=? ";
		CommonDao dao = new CommonDao();
		ResultSet rs = dao.query(sql, id);
		try {
			if(rs.next()){
				ci = new ReaderType();
				
				ci.setId(rs.getInt("id"));
				ci.setTypename(rs.getString("typename"));
				ci.setMaxborrownum(rs.getInt("maxborrownum"));
				ci.setLimit(rs.getInt("limit"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ci;
	}
	
	public static ReaderType selectReaderid(String name){
		ReaderType ci = null;
		String sql = "select * from readertype where typename=? ";
		CommonDao dao = new CommonDao();
		ResultSet rs = dao.query(sql, name);
	
		try {
			if(rs.next()){
				 ci = new ReaderType();
				
				ci.setId(rs.getInt("id"));
				ci.setTypename(rs.getString("typename"));
				ci.setMaxborrownum(rs.getInt("maxborrownum"));
				ci.setLimit(rs.getInt("limit"));
		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ci;
	}
	
	
	
	public static List<ReaderType> selectReaderType(String type){
		List<ReaderType> list = null;
		ReaderType ci = null;
		String sql = "select * from readertype where id=? ";
		CommonDao dao = new CommonDao();
		ResultSet rs = dao.query(sql, type);
		list = new ArrayList<>();
		
		try {
			while(rs.next()){
				ci = new ReaderType();
				
				ci.setId(rs.getInt("id"));
				ci.setTypename(rs.getString("typename"));
				ci.setMaxborrownum(rs.getInt("maxborrownum"));
				ci.setLimit(rs.getInt("limit"));
				list.add(ci);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;

	}
	
}
