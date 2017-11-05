package pdsu.bbm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import pdsu.bbm.model.Reader;
import pdsu.bbm.model.ReaderType;

public class ReaderDao1 {
	public static   Reader insertReader(String id,String name,int age,String sex,String phone,String dept,Date regDate,String typename){
		Reader ci = null;
		String sql = " insert into reader(readerid,type,name,age,sex,phone,dept,regdate) values(?,?,?,?,?,?,?,?)";
		CommonDao dao = new CommonDao();
		ResultSet rs = dao.query(sql, id,typename,name,age,sex,phone,dept,regDate);
		return ci;
		
	}
	public static Reader selectReaderById(String readerid){
		
		Reader reader=null;
		String sql="select * from reader where readerid=? ";
		CommonDao dao=new CommonDao();
		ResultSet rs=dao.query(sql,readerid);   
		
	
		
		try {
			while (rs.next()) {
				reader=new Reader();
				
				reader.setReaderid(rs.getString("readerid"));
				reader.setType(rs.getInt("type"));
				reader.setTypename(rs.getString("name"));
				reader.setAge(rs.getInt("age"));
				reader.setSex(rs.getString("sex"));
				reader.setPhone(rs.getString("phone"));
				reader.setDept(rs.getString("dept"));
				reader.setRegDate(rs.getDate("regdate"));
				
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return reader;
	}
}
