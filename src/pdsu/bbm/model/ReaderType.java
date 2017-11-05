package pdsu.bbm.model;

public class ReaderType {
	private int id; // 读者类型编号
	private String typename; // 读者类型名称
	private int maxborrownum; // 最大借书量
	private int limit; // 最长借阅天数

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

	public int getMaxborrownum() {
		return maxborrownum;
	}

	public void setMaxborrownum(int maxborrownum) {
		this.maxborrownum = maxborrownum;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
