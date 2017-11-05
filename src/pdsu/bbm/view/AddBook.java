package pdsu.bbm.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pdsu.bbm.dao.BookDao;
import pdsu.bbm.dao.BookTypeDao;
import pdsu.bbm.dao.Dao;
import pdsu.bbm.model.Book;
import pdsu.bbm.model.BookType;

public class AddBook extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel label_1;
	private JLabel lbll;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	JPanel centerpPanel;
	JButton button1, button2, button3;
	private JComboBox comboBox_1;
	Image icon = new ImageIcon("image/icon.png").getImage();
	private JTextField ISBNfiled;
	private JTextField publishField;
	private JTextField publishDateField;
	private JTextField publishTime;
	private JTextField unitPriceField;
	private JTextField bookNameField;
	private JTextField authorFiled;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new AddBook();
	}

	public AddBook() {
		setTitle("添加图书信息");
		setSize(555, 333);
		setIconImage(icon);
		setLocationRelativeTo(null);

		setTitle("添加图书信息");
		setSize(555, 334);
		setLocationRelativeTo(null);
		setVisible(true);
		centerpPanel = new JPanel();
		centerpPanel.setLayout(null);
		JLabel label1 = new JLabel("ISBN:");
		label1.setFont(new Font("宋体", Font.PLAIN, 20));
		label1.setBounds(71, 26, 95, 42);
		centerpPanel.add(label1);

		ISBNfiled = new JTextField();
		ISBNfiled.setBounds(120, 30, 110, 30);
		centerpPanel.add(ISBNfiled);
		ISBNfiled.setColumns(10);

		JLabel label = new JLabel("类  别:");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(306, 30, 116, 35);
		centerpPanel.add(label);

		label_1 = new JLabel("书  名:");
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(50, 75, 154, 50);
		centerpPanel.add(label_1);

		lbll = new JLabel("作  者:");
		lbll.setFont(new Font("宋体", Font.PLAIN, 20));
		lbll.setBounds(306, 75, 137, 50);
		centerpPanel.add(lbll);

		label_2 = new JLabel("出版社:");
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		label_2.setBounds(50, 130, 154, 50);
		centerpPanel.add(label_2);

		label_3 = new JLabel("出版日期:");
		label_3.setFont(new Font("宋体", Font.PLAIN, 20));
		label_3.setBounds(285, 135, 137, 50);
		centerpPanel.add(label_3);

		publishField = new JTextField();
		publishField.setColumns(10);
		publishField.setBounds(120, 143, 110, 30);
		centerpPanel.add(publishField);

		publishDateField = new JTextField();
		publishDateField.setColumns(10);
		publishDateField.setBounds(380, 143, 120, 30);
		centerpPanel.add(publishDateField);

		label_4 = new JLabel("印刷次数:");
		label_4.setFont(new Font("宋体", Font.PLAIN, 20));
		label_4.setBounds(28, 190, 154, 50);
		centerpPanel.add(label_4);

		publishTime = new JTextField();
		publishTime.setColumns(10);
		publishTime.setBounds(120, 203, 110, 30);
		centerpPanel.add(publishTime);

		label_5 = new JLabel("单  价:");
		label_5.setFont(new Font("宋体", Font.PLAIN, 20));
		label_5.setBounds(305, 194, 84, 42);
		centerpPanel.add(label_5);

		unitPriceField = new JTextField();
		unitPriceField.setColumns(10);
		unitPriceField.setBounds(380, 203, 120, 30);
		centerpPanel.add(unitPriceField);
		getContentPane().add(centerpPanel, BorderLayout.CENTER);

		bookNameField = new JTextField();
		bookNameField.setColumns(10);
		bookNameField.setBounds(120, 86, 110, 30);
		centerpPanel.add(bookNameField);

		authorFiled = new JTextField();
		authorFiled.setColumns(10);
		authorFiled.setBounds(380, 86, 120, 30);
		centerpPanel.add(authorFiled);

		List<BookType> rs = BookTypeDao.selectBookType();
		Iterator<BookType> iterator = rs.iterator();
		String[] AllTypeName = new String[rs.size()];
		int i = 0;
		while (iterator.hasNext()) {
			String typename = iterator.next().getTypename();
			AllTypeName[i] = typename;
			i++;
		}
		comboBox_1 = new JComboBox(AllTypeName);
		comboBox_1.setBounds(380, 30, 120, 30);
		comboBox_1.setFont(new Font("宋体", Font.PLAIN, 16));

		centerpPanel.add(comboBox_1);
		ButtonGroup group = new ButtonGroup();
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		button1 = new JButton("添加");
		button1.setFont(new Font("宋体", Font.PLAIN, 20));

		button2 = new JButton("关闭");
		button2.setFont(new Font("宋体", Font.PLAIN, 20));
		button3 = new JButton("重置");
		button3.setFont(new Font("宋体", Font.PLAIN, 20));

		panel2.add(button1);
		panel2.add(button3);
		panel2.add(button2);
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		getContentPane().add(panel2, BorderLayout.SOUTH);

		setVisible(true);
	}

	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1) {
			String Isbn = ISBNfiled.getText().toString();

			String bookname = bookNameField.getText();
			String author = authorFiled.getText();
			String selectType = comboBox_1.getSelectedItem().toString();
			String publish = publishField.getText();
			String publishdate = publishDateField.getText();
			String time = publishTime.getText().trim();
			String price = unitPriceField.getText().trim();

			if (Isbn.equals("") || bookname.equals("") || author.equals("")
					|| selectType.equals("") || publish.equals("")
					|| publishdate.equals("") || time.equals("")
					|| price.equals("")) {
				JOptionPane.showMessageDialog(this, "请输入完整信息");
				return;
			} else {
				BookType bt = new BookType();
				Book book = new Book();
				book.setISBN(Isbn);
				book.setBookname(bookname);
				book.setAuthor(author);
				bt.setTypename(selectType);
				int m = 0;
				List<BookType> list = BookTypeDao
						.selectIdByTypename(selectType);
				Iterator<BookType> ite = list.iterator();
				while (ite.hasNext()) {
					m = ite.next().getId();
				}
				book.setTypeid(m + "");
				book.setPublish(publish);
				try {
					book.setPublishtime(Integer.parseInt(time));
					book.setUnitprice(Integer.parseInt(price));
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				}
				
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date regDate = null;
					try {
						regDate =sdf.parse(publishdate);
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(this, "请输入正确的日期");
						publishDateField.setText("");
					}
					
					book.setPublishdate(regDate);
				if(regDate==null) return;
					
			     if (BookDao.selectBookByISBN(Isbn) != null) {
					JOptionPane.showMessageDialog(this, "该ISBN编号已存在");
					return;
				} else if (BookDao.insertBook(book) != 0) {
					JOptionPane.showMessageDialog(this, "添加成功");
				} else
					JOptionPane.showMessageDialog(this, "添加失败");
			}
		}
		if (e.getSource() == button3) {
			ISBNfiled.setText("");
			bookNameField.setText("");
			authorFiled.setText("");
			publishField.setText("");
			publishDateField.setText("");
			publishTime.setText("");
			unitPriceField.setText("");
		}
		if (e.getSource() == button2) {
			dispose();
			new MainWindow();

		}
	}

}
