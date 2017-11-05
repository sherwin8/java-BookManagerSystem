package pdsu.bbm.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.TableModel;

import pdsu.bbm.dao.BookDao;
import pdsu.bbm.dao.BookTypeDao;
import pdsu.bbm.model.Book;
import pdsu.bbm.model.BookType;

public class BookSelectandModify extends JFrame implements ActionListener {
	// 设置序列号
	private static final long serialVersionUID = 1L;
	private JTabbedPane jtabbedPane; // 标签面板
	// selectJP查询面板 select_conditionJP下拉列表面板 select_resultJP结果按钮面板updateJP修改面板
	// updateJP修改面板,bookJP中间面板 buttonJP2按钮面板
	private JPanel selectJP, select_conditionJP, select_resultJP, buttonJP1,
			bookJP, updateJP, buttonJP2;//
	private JTextField selectJTF, ISBNJTF, booknameJTF, authorJTF,
			printtimeJTF, publishJTF, publishdateJTF, unitpriceJTF;
	private JLabel ISBNJL, booknameJL, authorJL, categoryJL, printtimeJL,
			publishJL, publishdateJL, unitpriceJL;
	// 重点!
	private JTable jtable;// 定义表格
	private JComboBox choiceJCB, booktypeJCB;
	private JScrollPane jscrollPane;
	private JButton selectJB, exitJB, updateJB, resetJB;// 查询按钮，退出按钮,修改按钮，关闭按钮
	private TableModel getSelect;
	private String[] title = { "ISBN", "图书序列", "图书名称", "作者", "     出版社      ",
			"出版日期", "印刷次数", "单价" };
	Image icon = new ImageIcon("image/icon.png").getImage();

	public BookSelectandModify() {
		super();
		setIconImage(icon);
		setTitle("图书查询与修改");
		setBounds(100, 100, 555, 400);
		setLocationRelativeTo(null);
		// JTabbedPane选项卡
		jtabbedPane = new JTabbedPane();
		add(jtabbedPane);
		selectJP = new JPanel();
		selectJP.setLayout(new BorderLayout());
		jtabbedPane.add("图书信息查询", selectJP);

		// 查询条件面板
		select_conditionJP = new JPanel();
		choiceJCB = new JComboBox();
		String array[] = { "ISBN", "图书名称", "图书序列", "作者", "出版社","所有" };// 设置为一维数组
		for (int i = 0; i < array.length; i++) {
			choiceJCB.addItem(array[i]);
		}
		select_conditionJP.add(choiceJCB);
		selectJTF = new JTextField(20);
		select_conditionJP.add(selectJTF);
		selectJP.add(select_conditionJP, BorderLayout.NORTH);
		// 查询结果面板
		select_resultJP = new JPanel();
		BookDao dao = new BookDao();
		List<Book> list = dao.selectBook();
		jtable = new JTable(getSelect(list), title);

		// !设置表格大小不变
		jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// !设置列宽
		jtable.getColumnModel().getColumn(4).setPreferredWidth(175);
		jscrollPane = new JScrollPane(jtable);// 把表格加入滚动面板
		// 显示滚动面板边框
		jscrollPane.setPreferredSize(new Dimension(450, 170));
		select_resultJP.add(jscrollPane);
		selectJP.add(select_resultJP, BorderLayout.CENTER);
		// 查询按钮面板
		buttonJP1 = new JPanel();
		selectJB = new JButton("查询");
		selectJB.setFont(new Font("宋体", Font.PLAIN, 20));

		selectJB.addActionListener(this);
		buttonJP1.add(selectJB);
		exitJB = new JButton("退出");
		exitJB.setFont(new Font("宋体", Font.PLAIN, 20));
		exitJB.addActionListener(this);
		buttonJP1.add(exitJB);
		selectJP.add(buttonJP1, BorderLayout.SOUTH);
		// 信息修改页面
		updateJP = new JPanel();
		updateJP.setLayout(new BorderLayout());
		jtabbedPane.addTab("图书信息修改", updateJP);
		bookJP = new JPanel();
		final GridLayout gridLayout = new GridLayout(8, 2);
		// 设置边框间的距离
		gridLayout.setVgap(8);
		gridLayout.setHgap(8);
		bookJP.setLayout(gridLayout);
		ISBNJL = new JLabel("ISBN:");
		ISBNJL.setFont(new Font("宋体",Font.PLAIN,20));
		ISBNJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookJP.add(ISBNJL);
		ISBNJTF = new JTextField(20);
		ISBNJTF.addActionListener(this);
		bookJP.add(ISBNJTF);
		categoryJL = new JLabel("类  别:");
		categoryJL.setFont(new Font("宋体",Font.PLAIN,20));
		categoryJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookJP.add(categoryJL);
		// 下拉列表
		List<BookType> list1 = BookTypeDao.selectBookType();
		Iterator<BookType> it = list1.iterator();
		String ty[] = new String[list1.size()];
		int i = 0;
		booktypeJCB = new JComboBox();
		while (it.hasNext()) {
			ty[i] = it.next().getTypename();
			booktypeJCB.addItem(ty[i]);
			i++;
		}

		bookJP.add(booktypeJCB);

		booknameJL = new JLabel("书  名:");
		booknameJL.setFont(new Font("宋体",Font.PLAIN,20));
		booknameJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookJP.add(booknameJL);
		booknameJTF = new JTextField();
		booknameJTF.setColumns(20);
		bookJP.add(booknameJTF);

		authorJL = new JLabel("作  者:");
		authorJL.setFont(new Font("宋体",Font.PLAIN,20));
		authorJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookJP.add(authorJL);
		authorJTF = new JTextField();
		authorJTF.setColumns(20);
		bookJP.add(authorJTF);

		publishJL = new JLabel("出版社:");
		publishJL.setFont(new Font("宋体",Font.PLAIN,20));
		publishJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookJP.add(publishJL);
		publishJTF = new JTextField();
		bookJP.add(publishJTF);

		publishdateJL = new JLabel("出版日期:");
		publishdateJL.setFont(new Font("宋体",Font.PLAIN,20));
		publishdateJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookJP.add(publishdateJL);
		publishdateJTF = new JTextField();
		publishdateJTF.setHorizontalAlignment(SwingConstants.LEFT);
		bookJP.add(publishdateJTF);

		printtimeJL = new JLabel("印刷次数:");
		printtimeJL.setFont(new Font("宋体",Font.PLAIN,20));
		printtimeJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookJP.add(printtimeJL);
		printtimeJTF = new JTextField();
		bookJP.add(printtimeJTF);

		unitpriceJL = new JLabel("单  价:");
		unitpriceJL.setFont(new Font("宋体",Font.PLAIN,20));
		unitpriceJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookJP.add(unitpriceJL);
		unitpriceJTF = new JTextField();
		bookJP.add(unitpriceJTF);
		// 按钮面板
		// 按钮面板设计
		buttonJP2 = new JPanel();
		updateJB = new JButton("修改");
		updateJB.setFont(new Font("宋体", Font.PLAIN, 20));

		updateJB.addActionListener(this);
		resetJB = new JButton("关闭");
		resetJB.setFont(new Font("宋体", Font.PLAIN, 20));

		resetJB.addActionListener(this);
		buttonJP2.add(updateJB);
		buttonJP2.add(resetJB);
		updateJP.add(bookJP, BorderLayout.CENTER);
		updateJP.add(buttonJP2, BorderLayout.SOUTH);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// 将查询表格加入面板
	private Object[][] getSelect(List<Book> list) {
		// TODO Auto-generated method stub
		Object[][] objects = new Object[list.size()][title.length];
		for (int i = 0; i < list.size(); i++) {
			Book book = list.get(i);
			objects[i][0] = book.getISBN();
			objects[i][1] = book.getTypeid();// 图书序列
			objects[i][2] = book.getBookname();// 图书名称
			objects[i][3] = book.getAuthor();// 图书作者
			objects[i][4] = book.getPublish();// 出版社
			objects[i][5] = book.getPublishdate();// 出版日期
			objects[i][6] = book.getPublishtime();// 印刷次数
			objects[i][7] = book.getUnitprice();// 单价
		}

		return objects;

	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==ISBNJTF){
			String isbn=ISBNJTF.getText();
			String typename=null;
			String id=BookDao.selectBookByISBN(isbn).getTypeid();
			
			typename=BookTypeDao.selectById(id);
			System.out.println(typename);
			booktypeJCB.setSelectedItem(typename);
			booknameJTF.setText(BookDao.selectBookByISBN(isbn).getBookname());
			authorJTF.setText(BookDao.selectBookByISBN(isbn).getAuthor());
			publishJTF.setText(BookDao.selectBookByISBN(isbn).getPublish());
			publishdateJTF.setText(BookDao.selectBookByISBN(isbn).getPublishdate()+"");
			printtimeJTF.setText(BookDao.selectBookByISBN(isbn).getPublishtime()+"");
			unitpriceJTF.setText(BookDao.selectBookByISBN(isbn).getUnitprice()+"");
			
		}
		if (e.getSource() == selectJB) {// 按照ISBN编码查找
			int r = choiceJCB.getSelectedIndex();
			if (r == 0) {
				String name = selectJTF.getText().trim();
				// 强制转换为线性表类型
				List<Book> list = new ArrayList<Book>();
				Book book = BookDao.selectBookByISBN(name);
				if (book == null) {
					JOptionPane.showMessageDialog(this, "该编码不存在!");
				} else {
					list.add(book);
					Object[][] data = getSelect(list);
					jtable = new JTable(data, title);
					jtable.getColumnModel().getColumn(4).setPreferredWidth(175);
					jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

					jscrollPane.setViewportView(jtable);

				}
			}

			if (r == 1) {// 根据图书名称查询信息
				String name = selectJTF.getText().toString();
				List<Book> list = BookDao.selectBookByName(name);
				// 判断线性表是否为空
				if (list == null || list.size() == 0) {
					JOptionPane.showMessageDialog(this, "该图书不存在!");
				} else {
					Object[][] data = getSelect(list);
					jtable = new JTable(data, title);
					tianjia();
				}
			}
			if (r == 2) {// 根据图书序列查询信息
				String name = selectJTF.getText().toString();
				List<Book> list = BookDao.selectBookByType(name);
				// 判断线性表是否为空
				if (list == null || list.size() == 0) {
					JOptionPane.showMessageDialog(this, "该图书不存在!");
				} else {
					Object[][] data = getSelect(list);
					jtable = new JTable(data, title);
					tianjia();
				}
			}
			if (r == 3) { // 根据作者进行查找
				String name = selectJTF.getText().toString();
				// 根据作者进行查找
				List<Book> list = BookDao.selectBookByAuthor(name);
				// 判断线性表是否为空
				if (list == null || list.size() == 0) {
					JOptionPane.showMessageDialog(this, "该图书不存在!");
				} else {
					Object[][] data = getSelect(list);
					jtable = new JTable(data, title);
					tianjia();
				}
			}
			if (r == 4) {// 根据出版社进行查找
				String name = selectJTF.getText().toString();
				List<Book> list = BookDao.selectBookByPublish(name);
				if (list == null || list.size() == 0) {
					JOptionPane.showMessageDialog(this, "该图书不存在!");
				} else {
					Object[][] data = getSelect(list);
					jtable = new JTable(data, title);
					tianjia();

				}
			}
			if(r==5){
				List<Book> list =BookDao.selectBook();
			}
		}
		if (e.getSource() == updateJB) {
			String ISBN = ISBNJTF.getText().trim();
			String typename = ((String) booktypeJCB.getSelectedItem()).trim();
			String id = BookTypeDao.selectByTypename(typename);
			String bookname = booknameJTF.getText();
			String author = authorJTF.getText();
			String publish = publishJTF.getText();
			String publishdate = publishdateJTF.getText();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date regDate = null;
			try {
				regDate = sdf.parse(publishdate);
			} catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(this, "请输入正确的日期格式!");
			}
			if (regDate == null)
				return;
			int publishtime = Integer.parseInt(printtimeJTF.getText());
			int unitprice = Integer.parseInt(unitpriceJTF.getText());
			// 封装对象
			Book book = new Book();
			book.setISBN(ISBN);
			book.setTypeid(id);
			book.setBookname(bookname);
			book.setAuthor(author);
			book.setPublish(publish);
			book.setPublishdate(regDate);
			book.setPublishtime(publishtime);
			book.setUnitprice(unitprice);
			book.setTypename(typename);
			int result = BookDao.update(book);
			List<Book> list2 = new ArrayList<Book>();
			list2.add(book);

			// System.out.println(list2);

			if (result == 1) {
				JOptionPane.showMessageDialog(this, "修改成功!");
				BookDao bk=new BookDao();
				List<Book> list =bk.selectBook();
				jtable =new JTable(getSelect(list),title);
				tianjia();
				
			} else {
				JOptionPane.showMessageDialog(this, "修改失败!");
			}
		}
		if (e.getSource() == resetJB) {
			dispose();
			new MainWindow();
		}
		if (e.getSource() == exitJB) {
			dispose();
			new MainWindow();
		}
	}

	private void tianjia() {
		jtable.getColumnModel().getColumn(4).setPreferredWidth(175);
		jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		jscrollPane.setViewportView(jtable);

	}

	public static void main(String[] args) {
		new BookSelectandModify();

	}
}
