package pdsu.bbm.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
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
import pdsu.bbm.model.ReaderType;



public class BorrowBookView extends JFrame implements ActionListener{
	
	JPanel readerborrowinformation,zhong,xia;
	JLabel readerid,readername,readertype;
	JTextField t_readerid,t_readername,t_readertype;
	
	JTable biao;
	GridLayout gridlayout = new GridLayout(5,4);
	
	
	JPanel bookborrow;
	JLabel ISBN,type,bookname,author,publish,publishdate,publishtime,unitprice,nowdate,reader;
	JTextField t_ISBN,t_type,t_bookname,t_author,t_publish,t_publishdate,t_publishtime,t_unitprice,t_nowdate;
	protected JTextField t_reader;
	Image icon = new ImageIcon("image/icon.png").getImage();

	JPanel button;
	JButton borrow,close;
	
	public BorrowBookView(){
		setIconImage(icon);
		setSize(600, 500);
		init();
		setTitle("图书借阅");
		
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	}

	private void init() {
		
		readerborrowinformation = new JPanel();
		
		readerborrowinformation.setLayout(new BorderLayout());
		
		TitledBorder b = new TitledBorder("读者借阅信息");
		readerborrowinformation.setBorder(b);
		
		zhong = new JPanel(new GridLayout(1,6));
		readerid = new JLabel("读者编号:");
		readerid.setFont(new Font("宋体", Font.PLAIN, 20));
		readerid.setHorizontalAlignment(SwingConstants.RIGHT);
		t_readerid = new JTextField();
		t_readerid.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String id1 = t_readerid.getText().trim();
			
				if(ReaderDao1.selectReaderById(id1)!=null){
					
				}else JOptionPane.showMessageDialog(null, "请输入正确的读者编号！");
				if(id1!=null){
//					String a = ReaderDao1.selectReaderById(id1).getType().trim();
					String m=ReaderDao1.selectReaderById(id1).getType()+"";
//					int b = Integer.parseInt(a);
					t_readername.setText(ReaderDao1.selectReaderById(id1).getTypename());
					Object [][] data = getSelect(ReaderTypeDao1.selectReaderType(m)); 
					String b = (String) data [0][1];
					
					t_readertype.setText(b);


				}
				
			}
		});
		
		
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
		readerborrowinformation.add(zhong,BorderLayout.NORTH);
		
		xia = new JPanel();
		//创建表
		String [] tetle = {"图书编号","图书名称","借书日期"};
		final String[][] data = new String[1][3];
		JTable biao = new JTable(data,tetle);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(biao);
		scrollPane.setPreferredSize(new Dimension(500, 140));
		xia.add(scrollPane);
		readerborrowinformation.add(xia,BorderLayout.CENTER);
		add(readerborrowinformation,BorderLayout.NORTH);

		gridlayout.setVgap(8);
		gridlayout.setHgap(8);
		
		bookborrow = new JPanel(gridlayout);
		
		TitledBorder c = new TitledBorder("图书借阅");
		bookborrow.setBorder(c);
		
		
		ISBN = new JLabel("ISBN:");
		ISBN.setFont(new Font("宋体", Font.PLAIN, 20));
		ISBN.setHorizontalAlignment(SwingConstants.RIGHT);
		t_ISBN = new JTextField();
		t_ISBN.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String isbn = t_ISBN.getText().trim();
				if(BookDao1.selectBookByISBN(isbn)!=null){}				
				else JOptionPane.showMessageDialog(null, "请输入正确的 ISBN !");
				if(isbn!=null){
					String a = BookDao1.selectBookByISBN(isbn).getTypeid();
					Object data[][] = getSelect1(BookTypeDao1.selectBookType(a));
					String b = (String) data[0][1];
					
					t_type.setText(b);
					t_bookname.setText(BookDao1.selectBookByISBN(isbn).getBookname());
					t_author.setText(BookDao1.selectBookByISBN(isbn).getAuthor());
					t_publish.setText(BookDao1.selectBookByISBN(isbn).getPublish());
					t_publishdate.setText(BookDao1.selectBookByISBN(isbn).getPublishdate()+"");
					t_publishtime.setText(BookDao1.selectBookByISBN(isbn).getPublishtime()+"");
					t_unitprice.setText(BookDao1.selectBookByISBN(isbn).getUnitprice()+"");
					
				}	

				
				
			}
		});
		
		type = new JLabel("类   别:");
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
		
		publish = new JLabel("出 版 社:");
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
		
		nowdate = new JLabel("当前日期:");
		nowdate.setFont(new Font("宋体", Font.PLAIN, 20));
		nowdate.setHorizontalAlignment(SwingConstants.RIGHT);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//获取时间
		String str = format.format(new java.util.Date());
		t_nowdate = new JTextField(str);
		
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
		
		
		add(bookborrow,BorderLayout.CENTER);
		
		button = new JPanel();
		borrow = new JButton("借阅");
		borrow.setFont(new Font("宋体", Font.PLAIN, 20));

		close = new JButton("关闭");
		close.setFont(new Font("宋体", Font.PLAIN, 20));
		button.add(borrow);
		button.add(close);
		add(button,BorderLayout.SOUTH);
		
		
		
		borrow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==borrow){
					if(t_readerid.getText().equals("")||t_ISBN.getText().equals("")){
						JOptionPane.showMessageDialog(BorrowBookView.this,"请输入完整的信息!");
					}else {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//获取时间
				String str = format.format(new java.util.Date());
				data [0][0] = BookDao1.selectBookByISBN(t_ISBN.getText().trim()).getISBN();
				data [0][1] = BookDao1.selectBookByISBN(t_ISBN.getText().trim()).getBookname();
				data [0][2] = str;
				
				int result = BorrowBookinfoDao.borrowBook(t_readerid.getText().trim(), t_ISBN.getText().trim(), str);
				

				if(result!=0){
				JOptionPane.showMessageDialog(null, "借书成功！");
				repaint();//刷新
				}else JOptionPane.showMessageDialog(null, "借书失败");
			}}
			}
		});
		
		
		close.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MainWindow();
			}
		});
		

		
		
		
		
	}
	
	public static void main(String[] args) {
		new BorrowBookView();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	private static Object [][] getSelect(List<ReaderType> list){
		Object [] [] data = new Object [list.size()][4];
		for(int i = 0;i<list.size();i++){
			ReaderType readertype = list.get(i);
			data[i][0] = readertype.getId();
			data[i][1] = readertype.getTypename();
			data[i][2] = readertype.getMaxborrownum();
			data[i][3] = readertype.getLimit();
		}
		
		return data;
		
	}
	private static Object [][] getSelect1(List<BookType> list){
		Object [] [] data = new Object [list.size()][2];
		for(int i = 0;i<list.size();i++){
			BookType boogtype = list.get(i);
			data[i][0] = boogtype.getId();
			data[i][1] = boogtype.getTypename();

		}

		
		
		return data;
		
	}
}
