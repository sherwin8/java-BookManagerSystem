package pdsu.bbm.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import pdsu.bbm.dao.BookDao1;
import pdsu.bbm.dao.BookTypeDao1;
import pdsu.bbm.dao.BorrowBookinfoDao;
import pdsu.bbm.dao.ReaderDao1;
import pdsu.bbm.dao.ReaderTypeDao1;
import pdsu.bbm.model.BookType;
import pdsu.bbm.model.BorrowBook;
import pdsu.bbm.model.ReaderType;

public class ReturnBookView extends JFrame implements ActionListener,
		MouseListener {

	JPanel readerborrowinformation, zhong, xia;

	JLabel readerid, readername, readertype;
	JTextField t_readerid, t_readername, t_readertype;

	JTable biao = null;
	GridLayout gridlayout = new GridLayout(5, 4);

	JPanel bookborrow;
	JLabel ISBN, type, bookname, author, publish, publishdate, publishtime,
			unitprice, nowdate, reader;
	JTextField t_ISBN, t_type, t_bookname, t_author, t_publish, t_publishdate,
			t_publishtime, t_unitprice, t_nowdate, t_reader;

	JPanel button;
	JButton borrow, close;
	final String[] tetle = { "图书编号", "借书日期", "还书日期", "罚金" };
	Image icon = new ImageIcon("image/icon.png").getImage();

	public ReturnBookView() {
		setIconImage(icon);
		setSize(600, 500);
		init();
		setTitle("图书归还");
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void init() {

		readerborrowinformation = new JPanel();

		readerborrowinformation.setLayout(new BorderLayout());

		TitledBorder b = new TitledBorder("读者借阅信息");
		readerborrowinformation.setBorder(b);

		zhong = new JPanel(new GridLayout(1, 6));
		readerid = new JLabel("读者编号:");
		readerid.setFont(new Font("宋体", Font.PLAIN, 20));
		readerid.setHorizontalAlignment(SwingConstants.RIGHT);
		t_readerid = new JTextField();
		t_readerid.addActionListener(this);

		readername = new JLabel("读者姓名:");
		readername.setFont(new Font("宋体", Font.PLAIN, 20));
		readername.setHorizontalAlignment(SwingConstants.RIGHT);
		t_readername = new JTextField();

		readertype = new JLabel("读者类别:");
		readertype.setFont(new Font("宋体", Font.PLAIN, 20));
		readertype.setHorizontalAlignment(SwingConstants.RIGHT);
		t_readertype = new JTextField();

		zhong.add(readerid);
		zhong.add(t_readerid);
		zhong.add(readername);
		zhong.add(t_readername);
		zhong.add(readertype);
		zhong.add(t_readertype);
		readerborrowinformation.add(zhong, BorderLayout.NORTH);

		biao = new JTable();
		//
		xia = new JPanel();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(biao);
		scrollPane.setPreferredSize(new Dimension(500, 140));
		scrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		xia.add(scrollPane);
		readerborrowinformation.add(xia, BorderLayout.CENTER);

		add(readerborrowinformation, BorderLayout.NORTH);

		// 设置gridlayout的间隙吧
		gridlayout.setVgap(8);
		gridlayout.setHgap(8);

		bookborrow = new JPanel(gridlayout);

		TitledBorder c = new TitledBorder("图书归还");
		bookborrow.setBorder(c);

		ISBN = new JLabel("ISBN:");
		ISBN.setFont(new Font("宋体", Font.PLAIN, 20));
		ISBN.setHorizontalAlignment(SwingConstants.RIGHT);
		t_ISBN = new JTextField();

		type = new JLabel("类  别:");
		type.setFont(new Font("宋体", Font.PLAIN, 20));
		type.setHorizontalAlignment(SwingConstants.RIGHT);
		t_type = new JTextField();

		bookname = new JLabel("书  名:");
		bookname.setFont(new Font("宋体", Font.PLAIN, 20));
		bookname.setHorizontalAlignment(SwingConstants.RIGHT);
		t_bookname = new JTextField();

		author = new JLabel("作  者:");
		author.setFont(new Font("宋体", Font.PLAIN, 20));
		author.setHorizontalAlignment(SwingConstants.RIGHT);
		t_author = new JTextField();

		publish = new JLabel("出版社:");
		publish.setFont(new Font("宋体", Font.PLAIN, 20));
		publish.setHorizontalAlignment(SwingConstants.RIGHT);
		t_publish = new JTextField();

		publishdate = new JLabel("出版日期:");
		publishdate.setFont(new Font("宋体", Font.PLAIN, 20));
		publishdate.setHorizontalAlignment(SwingConstants.RIGHT);
		t_publishdate = new JTextField();

		publishtime = new JLabel("印刷次数:");
		publishtime.setFont(new Font("宋体", Font.PLAIN, 20));
		publishtime.setHorizontalAlignment(SwingConstants.RIGHT);
		t_publishtime = new JTextField();

		unitprice = new JLabel("单  价:");
		unitprice.setFont(new Font("宋体", Font.PLAIN, 20));
		unitprice.setHorizontalAlignment(SwingConstants.RIGHT);
		t_unitprice = new JTextField();

		nowdate = new JLabel("罚  金:");
		nowdate.setFont(new Font("宋体", Font.PLAIN, 20));
		nowdate.setHorizontalAlignment(SwingConstants.RIGHT);

		t_nowdate = new JTextField();

		reader = new JLabel("操作人员:");
		reader.setFont(new Font("宋体", Font.PLAIN, 20));
		reader.setHorizontalAlignment(SwingConstants.RIGHT);
		t_reader = new JTextField();
		String id = new String();

		t_reader.setText(Login.textField.getText());

		t_reader.setEditable(false);

		bookborrow.add(ISBN);
		bookborrow.add(t_ISBN);
		bookborrow.add(type);
		bookborrow.add(t_type);
		bookborrow.add(bookname);
		bookborrow.add(t_bookname);
		bookborrow.add(author);
		bookborrow.add(t_author);
		bookborrow.add(publish);
		bookborrow.add(t_publish);
		bookborrow.add(publishdate);
		bookborrow.add(t_publishdate);
		bookborrow.add(publishtime);
		bookborrow.add(t_publishtime);
		bookborrow.add(unitprice);
		bookborrow.add(t_unitprice);
		bookborrow.add(nowdate);
		bookborrow.add(t_nowdate);
		bookborrow.add(reader);
		bookborrow.add(t_reader);

		add(bookborrow, BorderLayout.CENTER);

		button = new JPanel();
		borrow = new JButton("归还");
		borrow.setFont(new Font("宋体", Font.PLAIN, 20));
		borrow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (t_readerid.getText().equals("")) {
					JOptionPane.showMessageDialog(ReturnBookView.this,
							"请输入正确的读者编号!");
				}
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");// 获取时间
				String str = format.format(new java.util.Date());
				String nowdate = str + " 0:00:00";

				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd H:mm:ss");
				java.util.Date date_util = null;
				try {
					date_util = sdf.parse(nowdate);
				} catch (ParseException e1) {
					e1.printStackTrace();
				} // 转换为util.date
				java.sql.Date date_sql = new java.sql.Date(date_util.getTime());// 转换为sql.date
			
				BorrowBookinfoDao.returnBook(t_readerid.getText().trim(),
						t_ISBN.getText().trim(), date_sql);
				// JOptionPane.showMessageDialog(null, "还书成功！");

				xia.removeAll();
				List<BorrowBook> list = BorrowBookinfoDao
						.selectBorrowByReaderId(t_readerid.getText().trim());
				Object[][] data = getSelect(list);

				biao = new JTable(data, tetle);

				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setViewportView(biao);
				scrollPane.setPreferredSize(new Dimension(500, 140));
				scrollPane
						.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				biao.addMouseListener(ReturnBookView.this);
				xia.add(scrollPane);
				JOptionPane.showMessageDialog(ReturnBookView.this, "还书成功");
				validate();
			}
		});
		close = new JButton("关闭");
		close.setFont(new Font("宋体", Font.PLAIN, 20));
		close.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
				new MainWindow();
			}
		});

		button.add(borrow);
		button.add(close);

		add(button, BorderLayout.SOUTH);

	}

	// list -> 数组
	private Object[][] getSelect(List<BorrowBook> list) {
		// String [] a ={"读者编号","图书编号","借书日期","还书日期","罚金"};
		Object[][] data = new Object[list.size()][4];

		for (int i = 0; i < list.size(); i++) {
			BorrowBook borrowbook = list.get(i);
			// data[i][0] = borrowbook.getReaderid();
			data[i][0] = borrowbook.getISBN();
			data[i][1] = borrowbook.getBorrowdate();
			data[i][2] = borrowbook.getReturndate();
			data[i][3] = borrowbook.getFine();
		}

		return data;
	}

	public static void main(String[] args) {
		new ReturnBookView();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String id1 = t_readerid.getText().trim();
		if (ReaderDao1.selectReaderById(id1) != null) {

		} else {
			JOptionPane.showMessageDialog(null, "请输入正确的读者编号！");
			return;
		}
		xia.removeAll();
		if (id1 != null) {

			String m = ReaderDao1.selectReaderById(id1).getType() + "";

			t_readername
					.setText(ReaderDao1.selectReaderById(id1).getTypename());
			Object[][] data1 = getSelect1(ReaderTypeDao1.selectReaderType(m));
			String b = (String) data1[0][1];

			t_readertype.setText(b);

			List<BorrowBook> list = BorrowBookinfoDao
					.selectBorrowByReaderId(id1);
			Object[][] data = getSelect(list);

			biao = new JTable(data, tetle);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setViewportView(biao);
			scrollPane.setPreferredSize(new Dimension(500, 140));
			scrollPane
					.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			xia.add(scrollPane);
			validate();
			biao.addMouseListener(ReturnBookView.this);

		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		if (e.getClickCount() == 1) {
			int row = biao.getSelectedRow();
			t_ISBN.setText((String) biao.getValueAt(row, 0));
			String isbn = t_ISBN.getText().trim();
			if (isbn != null) {
				String a = BookDao1.selectBookByISBN(isbn).getTypeid().trim();
				Object[][] data = getSelect2(BookTypeDao1.selectBookType(a));
				String b = (String) data[0][1];

				t_type.setText(b);

				t_bookname.setText(BookDao1.selectBookByISBN(isbn)
						.getBookname());
				t_author.setText(BookDao1.selectBookByISBN(isbn).getAuthor());
				t_publish.setText(BookDao1.selectBookByISBN(isbn).getPublish());
				t_publishdate.setText(BookDao1.selectBookByISBN(isbn)
						.getPublishdate() + "");
				t_publishtime.setText(BookDao1.selectBookByISBN(isbn)
						.getPublishtime() + "");
				t_unitprice.setText(BookDao1.selectBookByISBN(isbn)
						.getUnitprice() + "");

				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");// 获取时间
				// String str = format.format(new java.util.Date());

				Date d1 = null;

				String date = biao.getValueAt(row, 1).toString();

				try {
					d1 = format.parse(date);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				long daysBetween = (new Date().getTime() - d1.getTime() + 1000000)
						/ (3600 * 24 * 1000);
			
				t_nowdate.setText(Math.max(daysBetween - 30, 0) * 0.5 + " ");

			}

		}
		validate();

	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	private static Object[][] getSelect1(List<ReaderType> list) {
		Object[][] data = new Object[list.size()][4];
		for (int i = 0; i < list.size(); i++) {
			ReaderType readertype = list.get(i);
			data[i][0] = readertype.getId();
			data[i][1] = readertype.getTypename();
			data[i][2] = readertype.getMaxborrownum();
			data[i][3] = readertype.getLimit();
		}

		return data;

	}

	private static Object[][] getSelect2(List<BookType> list) {
		Object[][] data = new Object[list.size()][2];
		for (int i = 0; i < list.size(); i++) {
			BookType boogtype = list.get(i);
			data[i][0] = boogtype.getId();
			data[i][1] = boogtype.getTypename();

		}

		return data;

	}
}
