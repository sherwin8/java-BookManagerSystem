package pdsu.bbm.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;

import pdsu.bbm.dao.BookTypeDao;
import pdsu.bbm.model.BookType;
import pdsu.bbm.model.Reader;

public class BookTypeManager extends JFrame implements ActionListener,MouseListener{
	JLabel booktype, btid, btname;
	JTextField btField, btidField, btnameField;

	JButton select, insert, update, delete, exit, reflush;
	String[] columnNames = { "图书类型编号", "图书类型名称" };
	JPanel jp = new JPanel(null);
	JScrollPane jcrollpanel = new JScrollPane();
	Image icon = new ImageIcon("image/icon.png").getImage();

	private JTable table ;
	BookTypeDao bt = new BookTypeDao();
	List<BookType> list2 = bt.selectBookType();
	public BookTypeManager() {
		setSize(510, 400);
		setTitle("图书类型管理");
		setIconImage(icon);
		init();
		Object[][] data = getSelect(list2);
		table = new JTable(data,columnNames);
		//table.setAutoResizeMode(table.AUTO_RESIZE_OFF);
		//jcrollpanel.setPreferredSize(new Dimension(100, 100));
		jcrollpanel.setBounds(45, 5, 400,150);
		jcrollpanel.setViewportView(table);
		table.addMouseListener(this);
		jp.add(jcrollpanel);
		add(jp,BorderLayout.CENTER);
		
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);

	}

	public void init() {
		booktype = new JLabel("图书类型");
		booktype.setFont(new Font("宋体",Font.PLAIN,20));
		btField = new JTextField(15);
		select = new JButton("查询");
		select.setFont(new Font("宋体",Font.PLAIN,20));
		select.addActionListener(this);
		JPanel jptop = new JPanel();
		jptop.add(booktype);
		jptop.add(btField);
		jptop.add(select);
	
		add(jptop, BorderLayout.NORTH);

		JPanel jpbottom = new JPanel(null);
		jpbottom.setPreferredSize(new Dimension(550, 160));
		btid = new JLabel("图书类型编号:");
		btid.setFont(new Font("宋体",Font.PLAIN,20));
		btid.setBounds(80, 10, 150, 30);
		btidField = new JTextField();
		btidField.setBounds(220, 10, 200, 30);
		btname = new JLabel("图书类型名称:");
		btname.setFont(new Font("宋体",Font.PLAIN,20));
		btname.setBounds(80, 50, 180, 30);
		btnameField = new JTextField(20);
		btnameField.setBounds(220, 50, 200, 30);
		insert = new JButton("添加");
		insert.setFont(new Font("宋体",Font.PLAIN,20));
		insert.setBounds(80, 100, 75, 30);
		insert.addActionListener(this);
		update = new JButton("修改");
		update.setFont(new Font("宋体",Font.PLAIN,20));
		update.setBounds(170, 100, 75, 30);
		update.addActionListener(this);
		delete = new JButton("删除");
		delete.setFont(new Font("宋体",Font.PLAIN,20));
		delete.setBounds(260, 100, 75, 30);
		delete.addActionListener(this);
		exit = new JButton("退出");
		exit.setFont(new Font("宋体",Font.PLAIN,20));
		exit.setBounds(350, 100, 75, 30);
		exit.addActionListener(this);

		jpbottom.add(btid);
		jpbottom.add(btidField);
		jpbottom.add(btname);
		jpbottom.add(btnameField);
		jpbottom.add(update);
		jpbottom.add(delete);
		jpbottom.add(exit);
		jpbottom.add(insert);
		add(jpbottom, BorderLayout.SOUTH);
		revalidate();
	}

	
	private Object[][] getSelect(List<BookType> list) {
		Object[][] data = new Object[list.size()][2];
		for (int i = 0; i < list.size(); i++) {
			BookType booktype = list.get(i);
			data[i][0] = booktype.getId();
			data[i][1] = booktype.getTypename();
		}
		return data;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		if (e.getSource() == select) {		
			String nam = btField.getText();
			if (nam.equals("")) {
				List<BookType> list = bt.selectBookType();
				Object[][] data = getSelect(list);
				table = new JTable(data,columnNames);
				
				jcrollpanel.setBounds(45, 0, 400,150);
				jcrollpanel.setViewportView(table);
				table.addMouseListener(this);
				jp.add(jcrollpanel);
				add(jp,BorderLayout.CENTER);	
				return;
			}
			List<BookType> lis = bt.selectBookType(nam);
			Object[][] data = getSelect(lis);
			table = new JTable(data,columnNames);
			
			jcrollpanel.setBounds(45, 0, 400,150);
			jcrollpanel.setViewportView(table);
			table.addMouseListener(this);
			jp.add(jcrollpanel);
			add(jp,BorderLayout.CENTER);
			Iterator<BookType> li = lis.iterator();
			while (li.hasNext()) {
				BookType booktype = li.next();
				int id = booktype.getId();
				String id2 = id + " ";
				String name2 = booktype.getTypename();
				btidField.setText(id2);
				btnameField.setText(name2);
			}						
			
			table.addMouseListener(this);			
		}
		if (e.getSource() == insert) {
			String id = btidField.getText().trim();
			String name2 = btnameField.getText();
			if (id.equals("")||name2.equals("")) {
				JOptionPane.showMessageDialog(this, "请填写完整信息");
				return;		
			}
			int id2 = Integer.parseInt(id);
		int j =	bt.insertBookType(id2, name2);
		if(j==0){
			JOptionPane.showMessageDialog(this, "添加失败，该类型已存在");
		}
			List<BookType> list3 = bt.selectBookType();
			Object[][] data = getSelect(list3);
			table = new JTable(data,columnNames);
			jcrollpanel.setBounds(45, 0, 400,150);
			jcrollpanel.setViewportView(table);
			table.addMouseListener(this);
			jp.add(jcrollpanel);
			add(jp,BorderLayout.CENTER);
		}
		if (e.getSource() == update) {
			String id = btidField.getText().trim();
			String name2 = btnameField.getText();
			if (id.equals("")||name2.equals("")) {
				JOptionPane.showMessageDialog(this, "请填写完整信息");
				return;
			}
			int id2 = Integer.parseInt(id);
		int m=bt.updateBookType(id2, name2);
		if(m!=0){
			JOptionPane.showMessageDialog(this,"修改成功!");
		}
			List<BookType> list3 = bt.selectBookType();
			Object[][] data = getSelect(list3);
			table = new JTable(data,columnNames);
			
			jcrollpanel.setBounds(45, 0, 400,150);
			jcrollpanel.setViewportView(table);
			table.addMouseListener(this);
			jp.add(jcrollpanel);
			add(jp,BorderLayout.CENTER);
		}
		if (e.getSource() == delete) {
			String id = btidField.getText().trim();
			if(id.equals("")){
				JOptionPane.showMessageDialog(this, "请输入编号");				
			}
			int id2 = 0;
			try {
				id2 = Integer.parseInt(id);
			int k =	bt.deleteBookType(id2);
			if(k==0){
				JOptionPane.showMessageDialog(this, "该数据不存在");
			}else if(k!=0){
				JOptionPane.showMessageDialog(this, "删除成功!");

			}
				List<BookType> list3 = bt.selectBookType();
				Object[][] data = getSelect(list3);
				table = new JTable(data,columnNames);
				jcrollpanel.setBounds(45, 0, 400,150);
				jcrollpanel.setViewportView(table);
				table.addMouseListener(this);
				jp.add(jcrollpanel);
				add(jp,BorderLayout.CENTER);
				
			} catch (NumberFormatException e1) {

			}	
		}
		if (e.getSource() == exit) {
			this.dispose();
			new MainWindow();
		}
	}
	public static void main(String[] args) {
		new BookTypeManager();
	}
	public void mouseClicked(MouseEvent e1) {
		if(e1.getSource()==table){
			int k = table.getSelectedRow();
			String id = table.getValueAt(k, 0)+"";
			String name = (String) table.getValueAt(k, 1);
			btidField.setText(id);
			btnameField.setText(name);			
		}
	}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) { }
	public void mouseReleased(MouseEvent arg0) { }
}
