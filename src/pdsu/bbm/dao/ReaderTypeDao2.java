 package pdsu.bbm.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pdsu.bbm.model.ReaderType;
    
public class ReaderTypeDao2 {
	   //查询所有读者信息
		public static List<ReaderType> getAllReaderTypes(){
			List <ReaderType> list=null;
			ReaderType reader=null;
			String sql="select * from readertype";
			CommonDao dao=new CommonDao();
			ResultSet rs=dao.query(sql); 
			
			list=new ArrayList<ReaderType>();
			
			try {
				while (rs.next()) {
					reader=new ReaderType();
					
					reader.setId(rs.getInt("id"));
					reader.setTypename(rs.getString("typename"));
					reader.setMaxborrownum(rs.getInt("maxborrownum"));
					reader.setLimit(rs.getInt("limit"));
					
					list.add(reader);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
      //查找指定类型名的读者信息
		public static List<ReaderType> queryReaderTypeByname(String typename){
			List<ReaderType> list=null;
			
			ReaderType reader=null;
			String sql="select * from readertype where typename like ?";
			CommonDao dao=new CommonDao();
			String name="%"+typename+"%";
			ResultSet rs=dao.query(sql, name);
			
			list=new ArrayList<ReaderType>();
			try {
				while(rs.next()){
					int id=rs.getInt("id");  //调用ResultSet中的getInt方法，从数据库中获取“id”的值
					String typename1=rs.getString("typename");
			        int maxborrownum=rs.getInt("maxborrownum");
			        int limit=rs.getInt("limit");
					
					reader=new ReaderType();
					reader.setId(id);
					reader.setTypename(typename1);
					reader.setMaxborrownum(maxborrownum);
					reader.setLimit(limit);
					
					list.add(reader);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			dao.close();
			return list;
		
		}
		//添加读者的类型
		public static int insertReaderType(ReaderType reader){
				int result=0;
				String sql="insert into readertype values(?,?,?,?) ";
				CommonDao dao=new CommonDao();
				
				int id=reader.getId();
				String typeName=reader.getTypename();
				int maxborrownum=reader.getMaxborrownum();
				int limit=reader.getLimit();
				
				result=dao.update(sql, id,typeName,maxborrownum,limit);
				
				
				dao.close();
				return result;
				
			}
		
		//修改读者的所有信息
	public static int updateReaderType(ReaderType reader){
			int result=0;
			String sql="update readertype set typename=?,maxborrownum=?,limit=? where id=?";
			CommonDao dao=new CommonDao();
			
			int id=reader.getId();
			String typeName=reader.getTypename();
			int maxborrownum=reader.getMaxborrownum();
			int limit=reader.getLimit();
			
			result=dao.update(sql,typeName,maxborrownum,limit,id);
			
			dao.close();
			return result;
			
		}

	
	
	//删除读者信息
	public static int deleteReaderType(int id){
		int result=0;
		String sql="delete from readertype where id=?";
		CommonDao dao=new CommonDao();
		
		result=dao.update(sql, id);
		
		dao.close();
		return result;
		
	}
		
	}

