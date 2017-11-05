package pdsu.bbm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import pdsu.bbm.model.Book;

public class BookDao {
	// 添加图书信息
	public static int insertBook(Book book) {
		int result = 0;
		CommonDao dao = new CommonDao();
		String sql = "insert into book values(?,?,?,?,?,?,?,?)";
		try {
			result = dao.update(sql, book.getISBN(), book.getTypeid(),book.getBookname(),
					book.getAuthor(), book.getPublish(), book.getPublishdate().toLocaleString(),
					book.getPublishtime(), book.getUnitprice());
		} catch (Exception e) {
			e.printStackTrace();
		}
		dao.close();
		return result;
	}

	// 查询所有图书信息
	public static List<Book> selectBook() {
		List<Book> list = null;
		CommonDao dao = new CommonDao();
		String sql = "select * from book";
		ResultSet rs = dao.query(sql);
		list=new ArrayList<Book>();
		try {
			while (rs.next()) {
				Book book = new Book();
				book.setISBN(rs.getString("ISBN"));
				book.setTypeid(rs.getString("typeid"));
				book.setBookname(rs.getString("bookname"));
				book.setAuthor(rs.getString("author"));
				book.setPublish(rs.getString("publish"));
				book.setPublishdate(rs.getDate("publishdate"));
				book.setPublishtime(rs.getInt("publishtime"));
				book.setUnitprice(rs.getInt("unitprice"));
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		dao.close();
		return list;
	}

	// 根据ISBN编号查询
	public static Book selectBookByISBN(String ISBN) {
		Book book = null;
		ResultSet rs=null;
		CommonDao dao = new CommonDao();
		String sql = "select * from book where ISBN=?";
		 rs = dao.query(sql, ISBN);
		try {
			if (rs.next()) {
				book = new Book();
				book.setISBN(rs.getString("ISBN"));
				book.setTypeid(rs.getString("typeid"));
				book.setBookname(rs.getString("bookname"));
				book.setAuthor(rs.getString("author"));
				book.setPublish(rs.getString("publish"));
				book.setPublishdate(rs.getDate("publishdate"));
				book.setPublishtime(rs.getInt("publishtime"));
				book.setUnitprice(rs.getInt("unitprice"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dao.close();
		return book;
	}

	// 根据图书名模糊
	public static List<Book> selectBookByName(String name) {
		List<Book> list = null;
		CommonDao dao = new CommonDao();
		String bname = "%" + name + "%";
		String sql = "select * from book where bookname like ?";
		ResultSet rs = dao.query(sql, bname);
		list =new ArrayList<Book>();
		try {
			while(rs.next()){
				Book book = new Book();
				book.setISBN(rs.getString("ISBN"));
				book.setTypeid(rs.getString("typeid"));
				book.setBookname(rs.getString("bookname"));
				book.setAuthor(rs.getString("author"));
				book.setPublish(rs.getString("publish"));
				book.setPublishdate(rs.getDate("publishdate"));
				book.setPublishtime(rs.getInt("publishtime"));
				book.setUnitprice(rs.getInt("unitprice"));
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dao.close();
		return list;
	}
	//根据图书类型查询
	public static List<Book> selectBookByType(String type){

		List<Book> list = null;
		CommonDao dao = new CommonDao();
		String btype = "%" + type + "%";
		String sql = "select * from book where typeid like ?";
		ResultSet rs = dao.query(sql, btype);
		list =new ArrayList<Book>();
		try {
			while(rs.next()){
				Book book = new Book();
				book.setISBN(rs.getString("ISBN"));
				book.setTypeid(rs.getString("typeid"));
				book.setBookname(rs.getString("bookname"));
				book.setAuthor(rs.getString("author"));
				book.setPublish(rs.getString("publish"));
				book.setPublishdate(rs.getDate("publishdate"));
				book.setPublishtime(rs.getInt("publishtime"));
				book.setUnitprice(rs.getInt("unitprice"));
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dao.close();
		return list;
	}
	//根据作者查询
	public static List<Book> selectBookByAuthor(String author){


		List<Book> list = null;
		CommonDao dao = new CommonDao();
		String bauthor = "%" + author + "%";
		String sql = "select * from book where author like ?";
		ResultSet rs = dao.query(sql, bauthor);
		list =new ArrayList<Book>();
		try {
			while(rs.next()){
				Book book = new Book();
				book.setISBN(rs.getString("ISBN"));
				book.setTypeid(rs.getString("typeid"));
				book.setBookname(rs.getString("bookname"));
				book.setAuthor(rs.getString("author"));
				book.setPublish(rs.getString("publish"));
				book.setPublishdate(rs.getDate("publishdate"));
				book.setPublishtime(rs.getInt("publishtime"));
				book.setUnitprice(rs.getInt("unitprice"));
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dao.close();
		return list;
	}
	
	//根据出版社查询
	public static List<Book> selectBookByPublish(String Publish){
		List<Book> list = null;
		CommonDao dao = new CommonDao();
		String bPublish = "%" + Publish + "%";
		String sql = "select * from book where publish like ?";
		ResultSet rs = dao.query(sql, bPublish);
		list =new ArrayList<Book>();
		try {
			while(rs.next()){
				Book book = new Book();
				book.setISBN(rs.getString("ISBN"));
				book.setTypeid(rs.getString("typeid"));
				book.setBookname(rs.getString("bookname"));
				book.setAuthor(rs.getString("author"));
				book.setPublish(rs.getString("publish"));
				book.setPublishdate(rs.getDate("publishdate"));
				book.setPublishtime(rs.getInt("publishtime"));
				book.setUnitprice(rs.getInt("unitprice"));
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dao.close();
		return list;
	}
	//修改图书信息
	public static int update(Book book){
		int result=0;
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-WW-dd");

		String sql="update book set typeid=?,bookname=?,author=?,publish=?,publishdate=?,publishtime=?,unitprice=?  where ISBN=?";
		CommonDao dao =new CommonDao();
		try {
			result=dao.update(sql,book.getTypeid(),book.getBookname(),book.getAuthor(),book.getPublish(),sim.format(book.getPublishdate()),book.getPublishtime(),book.getUnitprice(),book.getISBN());
		} catch (Exception e) {
			e.printStackTrace();
		}
		dao.close();
		return result;
	}
	
	public static void main(String[] args) {
			System.out.println(BookDao.selectBookByISBN("10101010"));
	}

}
