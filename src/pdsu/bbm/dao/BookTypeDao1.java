package pdsu.bbm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pdsu.bbm.model.Book;
import pdsu.bbm.model.BookType;
import pdsu.bbm.model.ReaderType;

public class  BookTypeDao1 {
	
	
	
	public static BookType selectBooktypeDao(int id){
		BookType ci = null;
		
		String sql = "select * from booktype where id=?";
		CommonDao Dao = new CommonDao();
		ResultSet rs = Dao.query(sql, id);
		try {
			if(rs.next()){
				int ci_id = rs.getInt("id");
				String ci_type = rs.getString("typename");
				
				ci = new BookType(); 
				ci.setId(ci_id);
				ci.setTypename(ci_type);
				
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ci;
	}
	public static List<BookType> selectBookType(String type){
		BookType ci = null;
		List<BookType> list = null;
		
		String sql = "select * from booktype where id=?";
		CommonDao Dao = new CommonDao();
		ResultSet rs = Dao.query(sql, type);
		
		list = new ArrayList<>();
		
		try {
			if(rs.next()){
				int ci_id = rs.getInt("id");
				String ci_type = rs.getString("typename");
				
				ci = new BookType(); 
				ci.setId(ci_id);
				ci.setTypename(ci_type);
				list.add(ci);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;

	}
	private static Object [][] getSelect(List<BookType> list){
		Object [] [] data = new Object [list.size()][2];
		for(int i = 0;i<list.size();i++){
			BookType boogtype = list.get(i);
			data[i][0] = boogtype.getId();
			data[i][1] = boogtype.getTypename();

		}
//		Object [][] datal = getSelect(list);
		
		
		return data;
		
	}
		
}

