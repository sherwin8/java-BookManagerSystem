package pdsu.bbm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.xml.bind.Marshaller.Listener;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

public class Dao {
	private String drivername = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String url = "jdbc:sqlserver://localhost:1433;DatabaseName=library";
	private String name = "sa";
	private String password = "123456";
	private Connection con = null;

	public Dao() {
		try {
			//连接到数据库
			Class.forName(drivername);
			con = DriverManager.getConnection(url, name, password);
			//创建语句用于发送sql并获取结果1
			String sql="select * from users";
			PreparedStatement stat=null;
			stat=con.prepareStatement(sql);
			//查询操作，获取结果集
			ResultSet rs=stat.executeQuery();
			
			while(rs.next()){
				int classid= rs.getInt("id");
				String classname=rs.getString("name");
				String majorid=rs.getString("password");
				System.out.println(classid+" "+classname+" "+majorid);
			}
			 rs.close();
			 stat.close();
			 con.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	//String 型转化为Date型
	public static Date getStringToDate(String date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date dt=new Date();
		try {
			dt=sdf.parse(date);
		} catch (ParseException e) {
			//JOptionPane.showMessageDialog(null, "请输入正确的日期格式！格式如2016-01-01");
		}
		
		return (new java.sql.Date(dt.getTime()));
	}
	public static void main(String[] args) {
		new Dao();
	}
	
//Date型转化为String 型
	public static String getDateToString(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String string =new String();
		string=sdf.format(date);
		return string;
	}
	//判断手机号是否正确
	
	
}
