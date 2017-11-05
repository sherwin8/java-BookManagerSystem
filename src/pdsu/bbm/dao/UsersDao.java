package pdsu.bbm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pdsu.bbm.model.Users;

public class UsersDao {
	//判断用户名和密码是否有效
	public static Users check(Users users) {
		Users u = null;
		CommonDao dao = new CommonDao();
		String sql = "select * from users where name=? and password=?";
		String name = users.getName();
		String password = users.getPassword();

		ResultSet rs = dao.query(sql, name, password);
		try {
			if (rs.next()) {
				u = new Users();
				u.setName(rs.getString("name").trim());
				u.setPassword(rs.getString("password").trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return u;

	}
	//检查密码
	public static Users checkPassword(Users users){
		Users u=null;
		CommonDao dao=new CommonDao();
		String sql ="select password from users where name=?";
		String password=users.getPassword();
		ResultSet rs=dao.query(sql, users.getName());
		try {
			if(rs.next()){
				u=new Users();
				u.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	//检查名字
	public static Users checkName(Users users){
		Users u=null;
		CommonDao dao =new CommonDao();
		String sql="select name from users where name=?";
		ResultSet rs=dao.query(sql, users.getName());
		try {
			if(rs.next()){
				u=new Users();
				u.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	
	//查询所有用户信息；
	public static List<Users> selectUser(){
		List<Users> list =null;
		CommonDao dao =new CommonDao();
		String sql="select * from users";
		ResultSet rs=dao.query(sql);
		list =new ArrayList<Users>();
		try {
			while(rs.next()){
				Users u=new Users();
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("name").trim());
				u.setPassword(rs.getString("password").trim());
				list.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dao.close();
		return list;
	}
	//添加用户
	public static int insertUser(Users users){
		int result=0;
		CommonDao dao =new CommonDao();
		String sql="insert into users(name,password) values(?,?)";
		result=dao.update(sql,users.getName(),users.getPassword());
		dao.close();
		return result;
	}
	//修改指定编号的用户的密码
	public static int updateUserPWD(Users users){
		int result=0;
		CommonDao dao =new CommonDao();
		String sql="update users set name=? password=? where id=?";
		result=dao.update(sql, users.getName(),users.getPassword());
		dao.close();
		return result;
		
	} 
	//修改指定用户的密码、
	public static int updateUserPWDByName(Users users){
		int result=0;
		CommonDao dao =new CommonDao();
		String sql="update users set password=? where name=?";
		result=dao.update(sql,users.getPassword(),users.getName());
		dao.close();
		return result;
		
	} 
	
	//删除指定编号的用户信息。
	public static int deleteUser(Integer id){
		int result=0;
		CommonDao dao =new CommonDao();
		String sql="delete   from users where id=?";
		try {
			result=dao.update(sql,id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		dao.close();
		return result;
		
	}
}
