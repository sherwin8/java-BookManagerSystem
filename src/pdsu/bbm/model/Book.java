package pdsu.bbm.model;

import java.util.Date;

public class Book {
	private String ISBN; // 图书isbn号
	private String typeid; // 图书类型名称
	private String bookname; // 图书名
	private String author; // 作者
	private String publish; // 出版社
	private Date publishdate; // 出版日期
	private int publishtime; // 印刷次数
	private int unitprice; // 图书单价
	private String typename;
	public String getISBN() {
		return ISBN;
	}

	
	public String getTypename() {
		return typename;
	}


	public void setTypename(String typename) {
		this.typename = typename;
	}


	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublish() {
		return publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}

	public Date getPublishdate() {
		return publishdate;
	}

	public void setPublishdate(Date date) {
		this.publishdate = date;
	}

	public int getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(int publishtime) {
		this.publishtime = publishtime;
	}

	public int getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(int unitprice) {
		this.unitprice = unitprice;
	}

	@Override
	public String toString() {
		return "Book [ISBN=" + ISBN + ", typeid=" + typeid + ", bookname="
				+ bookname + ", author=" + author + ", publish=" + publish
				+ ", publishdate=" + publishdate + ", publishtime="
				+ publishtime + ", unitprice=" + unitprice + "]";
	}

}
