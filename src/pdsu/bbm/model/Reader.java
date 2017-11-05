package pdsu.bbm.model;

import java.util.Date;

public class Reader {
	private String readerid; // 读者编号
	private int  type; // 读者类型
	private String name; // 读者姓名
	private int age; // 年龄
	private String sex; // 性别
	private String phone; // 电话
	private String dept; // 所在院系
	private Date regDate; // 注册日期
	   private String typename;
	    private int limit;
	    private int maxborrownum;
	
	public String getReaderid() {
		return readerid;
	}
	public void setReaderid(String readerid) {
		this.readerid = readerid;
	}


	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getMaxborrownum() {
		return maxborrownum;
	}
	public void setMaxborrownum(int maxborrownum) {
		this.maxborrownum = maxborrownum;
	}
	
	@Override
	public String toString() {
		return "Reader [readerid=" + readerid + ", type=" + type + ", name="
				+ name + ", age=" + age + ", sex=" + sex + ", phone=" + phone
				+ ", dept=" + dept + ", regDate=" + regDate + ", typename="
				+ typename + ", limit=" + limit + ", maxborrownum="
				+ maxborrownum + "]";
	}
	



}
