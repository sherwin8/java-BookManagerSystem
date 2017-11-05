package pdsu.bbm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pdsu.bbm.model.BookType;

public class BookTypeDao {

	
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
	
	// 查询所有图书类型信息
	public static List<BookType> selectBookType() {
		List<BookType> list = null;
		CommonDao dao = new CommonDao();
		String sql = "select * from booktype";
		ResultSet rs = dao.query(sql);
		list = new ArrayList<BookType>();
		try {
			while (rs.next()) {
				BookType bt = new BookType();
				bt.setId(rs.getInt("id"));
				bt.setTypename(rs.getString("typename"));
				list.add(bt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dao.close();
		return list;
	}

	// 查询指定图书类型的图书信息
	public static List<BookType> selectBookType(String type) {
		List<BookType> list = null;
		CommonDao dao = new CommonDao();
		String booktype = "%" + type + "%";
		String sql = "select * from booktype where typename like ?";
		ResultSet rs = dao.query(sql, booktype);
		list=new ArrayList<BookType>();
		try {
			while (rs.next()) {
				BookType bt = new BookType();
				bt.setId(rs.getInt("id"));
				 bt.setTypename(rs.getString("typename"));
				list.add(bt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dao.close();
		return list;
	}

	//
	public static String selectById(String id){
		String typename=null;
		String sql="select * from booktype where id=?";
		CommonDao dao =new CommonDao();
		
		ResultSet rs=dao.query(sql,id);
		try {
			while(rs.next()){
				typename=rs.getString("typename");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dao.close();
		return typename;
	}
	
	// 添加图书信息
	public static int insertBookType(Integer id, String typename) {
		int result = 0;
		CommonDao dao = new CommonDao();
		String sql = "insert into booktype(id,typename) values(?,?)";
		result = dao.update(sql, id, typename);
		dao.close();
		return result;
	}

	// 修改指定编号的图书类型信息
	public static int updateBookType(Integer id, String typename) {
		int result=0;
		String sql="update  Booktype set typename=? where id=?";
		CommonDao dao = new CommonDao();
		result = dao.update(sql,typename,id);
		return result;
	}

	// 删除指定类型编号的图书类型。
	public static int deleteBookType(Integer id) {
		int result = 0;
		CommonDao dao = new CommonDao();
		String sql = "delete  from booktype where id=?";
		result = dao.update(sql, id);
		dao.close();
		return result;
	}
	//根据图书名称查询图书信息
	public static String  selectByTypename(String name){
		List<BookType> list=new ArrayList<BookType>();
		BookType booktype=null;
		String sql="select * from booktype where typename=?";
		ResultSet rs=null;
		CommonDao dao=new CommonDao();
		rs=dao.query(sql,name);
//		try {
		String m=null;
			try {
				while(rs.next()){
//				booktype=new Booktype();
					try {
						m=rs.getString("id");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//				booktype.setTypename(rs.getString("typename"));
//				list.add(booktype);
				//}
//		} catch (SQLException e) {
//			e.printStackTrace();
}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return m;
		
	}
	// 查询指定图书类型的id
	public static List<BookType> selectIdByTypename(String name) {
		List<BookType> list = null;
		CommonDao dao = new CommonDao();
		String sql = "select * from booktype where typename like ?";
		ResultSet rs = dao.query(sql, "%" + name + "%");
		list = new ArrayList<BookType>();
		try {
			if (rs.next()) {
				BookType bt=new BookType();
				bt.setId(rs.getInt("id"));
				bt.setTypename(rs.getString("typename"));
				list.add(bt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dao.close();
		return list;
	}
	public static void main(String[] args) {
		System.out.println(BookTypeDao.selectIdByTypename("计算机类"));
	}

}
