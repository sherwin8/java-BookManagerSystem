package pdsu.bbm.dao;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import pdsu.bbm.model.*;

public class BorrowBookinfoDao {
	
	
	
	public static List<BorrowBook> selectBorrowByReaderId(String readerid){
		
		List<BorrowBook> list = null;
		
		String sql = "select * from borrowbook where readerid=? and returndate is null";
		
		CommonDao dao = new CommonDao();
		ResultSet rs = dao.query(sql,readerid);
		
		list = new ArrayList<BorrowBook>();
		try {
			while(rs.next()){
				BorrowBook ci = new BorrowBook();
				ci.setReaderid(rs.getString("readerid"));
				ci.setISBN(rs.getString("ISBN"));
				ci.setBorrowdate(rs.getDate("borrowdate"));
				ci.setReturndate(rs.getDate("returndate"));
				ci.setFine(rs.getInt("fine"));
				list.add(ci);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	public static BorrowBook selectBorrowByReaderId1(String readerid){
		
		BorrowBook ci = null ;
		
		String sql = "select * from borrowbook where readerid=? and returndate is null";
		
		CommonDao dao = new CommonDao();
		ResultSet rs = dao.query(sql,readerid);
		
		
		try {
			if(rs.next()){
				ci = new BorrowBook();
				ci.setReaderid(rs.getString("readerid"));
				ci.setISBN(rs.getString("ISBN"));
				ci.setBorrowdate(rs.getDate("borrowdate"));
				ci.setReturndate(rs.getDate("returndate"));
				ci.setFine(rs.getInt("fine"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ci;
		
	}
	
	 final private static  Object [][] getSelect(List<BorrowBook> list){
		 String [] a ={"读者编号","图书编号","借书日期","还书日期","罚金"};
		Object [][] data = new Object[list.size()][5];
		
		for(int i = 0;i<list.size();i++){
			BorrowBook borrowbook = list.get(i);
			data[i][0] = borrowbook.getReaderid();
			data[i][1] = borrowbook.getISBN();
			data[i][2] = borrowbook.getBorrowdate();
			data[i][3] = borrowbook.getReturndate();
			data[i][4] = borrowbook.getFine();
		}
		return data;
	}
	
	
	
	public static int borrowBook(String readerid,String ISBN,String borrowdate){
		int result = 0;
		String sql = "insert into borrowbook(readerid,ISBN,borrowdate) values(?,?,?);";
		CommonDao dao = new CommonDao();
		result = dao.update(sql, readerid,ISBN,borrowdate);
		return result;	
	}
	
	public static int returnBook(String readerid,String ISBN,Date returndate) {
		int result = 0;

		String sql1 = "update borrowbook set returndate=? where readerid=? and ISBN=? and returndate is null ;";
		CommonDao dao = new CommonDao();
		result = dao.update(sql1, returndate,readerid,ISBN);
		return result;
	}
	
	
}
