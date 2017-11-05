package pdsu.bbm.model;

public class BookType {
	private int id; // 图书类型编号
	private String typename;// 图书类型名称
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	@Override
	public String toString() {
		return "BookType [id=" + id + ", typename=" + typename + "]";
	}



	
}
