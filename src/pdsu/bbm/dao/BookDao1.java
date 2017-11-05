package pdsu.bbm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import pdsu.bbm.model.Book;
public class BookDao1 {
	// 查询所有图书信息
	public static List<Book> selectBook() {
		List<Book> list = null;
		list = new ArrayList<Book>();
		Book book =null;
		String sql = "select * from book";
		CommonDao dao = new CommonDao();
		ResultSet rs = dao.query(sql);
		try {
			while (rs.next()) {
				book = new Book();
				book.setISBN(rs.getString("ISBN"));//
				book.setTypeid(rs.getString("typeid"));//序列
				book.setBookname(rs.getString("bookname"));//名称
				book.setAuthor(rs.getString("author"));//作者
				book.setPublish(rs.getString("publish"));//出版社
				book.setPublishdate(rs.getDate("publishdate"));//日期
				book.setPublishtime(rs.getInt("publishtime"));//次数
				book.setUnitprice(rs.getInt("unitprice"));//单价
				list.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	//根据ISBN查询图书信息
	public static Book selectBookByISBN(String ISBN){
		ResultSet rs=null;
		Book book=null;
		String sql="select * from book where ISBN=?";
		CommonDao dao=new CommonDao();
		rs=dao.query(sql,ISBN );
		try {
			while(rs.next()){//rs的next会为空,但是rs永远不会为空.所以不能根据rs判断是否查找成功
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;//在主方法里可以根据book是否为空来判断是否查找成功
	}
	
	//根据图书名字进行查询,支持模糊查询
	public static List<Book> selectBookByName(String name){
		List<Book> list=null;
		String sql="select * from book where bookname like ?";
		CommonDao dao=new CommonDao();
		String cname="%"+name+"%";
		ResultSet rs=dao.query(sql,cname );
		list=new ArrayList<Book>();
		try {
			while(rs.next()){//rs的next会为空,但是rs永远不会为空.所以不能根据rs判断是否查找成功
				Book	book = new Book();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;	
	}
	//根据图书类型进行查找
	public static List<Book> selectBookByType(String type){
		List<Book> list=null;
		String sql="select * from book where typeid= ?";
		CommonDao dao=new CommonDao();
		ResultSet rs=dao.query(sql,type );
		list=new ArrayList<Book>();
		try {
			while(rs.next()){//rs的next会为空,但是rs永远不会为空.所以不能根据rs判断是否查找成功
				Book	book = new Book();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;	
	}
	//根据图书作者进行查找
	public static List<Book> selectBookByAuthor(String author){
		List<Book> list=null;
		String sql="select * from book where author= ?";
		CommonDao dao=new CommonDao();
		ResultSet rs=dao.query(sql,author );
		list=new ArrayList<Book>();
		try {
			while(rs.next()){//rs的next会为空,但是rs永远不会为空.所以不能根据rs判断是否查找成功
				Book	book = new Book();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;	
	}
	
	
	//根据出版社进行查找
	public static List<Book> selectBookByPublish(String publish){
		List<Book> list=null;
		String sql="select * from book where publish= ?";
		CommonDao dao=new CommonDao();
		ResultSet rs=dao.query(sql,publish);
		list=new ArrayList<Book>();
		try {
			while(rs.next()){//rs的next会为空,但是rs永远不会为空.所以不能根据rs判断是否查找成功
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
			//	System.out.println(list);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.close();
		return list;	
	}
	//修改图书信息
	public static int update(Book book ){//将book里面的属性传进来(下面使用get方法得到属性)
		int result=0;
		//book =new Book();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-WW-dd");
		
		String sql="update book set typeid=?,bookname=?,author=?,publish=?,publishdate=?,publishtime=?,unitprice=? where ISBN=?";
		CommonDao dao=new CommonDao();
result=dao.update(sql,book.getTypeid(),book.getBookname(),book.getAuthor(),book.getPublish(),simpleDateFormat.format(book.getPublishdate()),book.getPublishtime(),book.getUnitprice(),book.getISBN());
		System.out.println(result+"行受影响!");
		dao.close();
		return result;	
		
	}
}
	
	
		
	






