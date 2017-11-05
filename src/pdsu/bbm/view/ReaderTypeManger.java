package pdsu.bbm.view;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
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
import javax.swing.border.EmptyBorder;

import pdsu.bbm.dao.ReaderTypeDao2;
import pdsu.bbm.model.ReaderType;

public class ReaderTypeManger extends JFrame implements ActionListener,MouseListener{

	private static JPanel contentPane;
	private static JTextField textField;
	private static JTextField textField_1;
	private static JTextField textField_2;
	private static JTextField textField_3;
	private static JTextField textField_4;
	JScrollPane scrollPane=new JScrollPane();
	String  title[] ={"读者类型编号","读者类型名称","可借图书数量","可借图书期限"};
	 
	JTable table = null;
	JButton button;
	JButton button_1;
	JButton button_2 ;
	JButton button_3;
	JButton button_4;
	Image icon = new ImageIcon("image/icon.png").getImage();

   //主方法
public static void main(String[] args) {
		new ReaderTypeManger();
			
	}

//构造方法
ReaderTypeManger(){
	setBounds(100, 100, 607, 469);
	setTitle("图书类型管理");
	setIconImage(icon);
	setLocationRelativeTo(null);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); //设置面板距离边框的距离
	setContentPane(contentPane);
	contentPane.setLayout(null);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置点击关闭按钮后，结束窗口所占的应用程序
	ShowTable();
	Init();
	setVisible(true);
	validate();
	scrollPane.setBounds(95, 70, 360, 150);
}
	
	
  public void Init(){ 
	  	
	  
		JLabel label = new JLabel("读者类型:");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(93, 37, 100, 16);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(190, 30, 217, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.addActionListener(this);
		
		button = new JButton("查询");
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		button.setBounds(428, 30, 80, 30);
		contentPane.add(button);
		button.addActionListener(this);
		
		JLabel label_1 = new JLabel("读者类型编号:");
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(20, 247, 150, 36);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(148, 250, 100, 31);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_2 = new JLabel("读者类型名称:");
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		label_2.setBounds(20, 311, 150, 36);
		contentPane.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(148, 311, 100, 31);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel label_3 = new JLabel("可借图书数量:");
		label_3.setFont(new Font("宋体", Font.PLAIN, 20));
		label_3.setBounds(280, 247, 150, 36);
		contentPane.add(label_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(417, 250, 100, 31);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel label_4 = new JLabel("可借图书期限:");
		label_4.setFont(new Font("宋体", Font.PLAIN, 20));
		label_4.setBounds(280, 311, 150, 36);
		contentPane.add(label_4);
		
		textField_4 = new JTextField();
		textField_4.setBounds(417, 312, 100, 31);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		button_1 = new JButton("添加");
		button_1.setFont(new Font("宋体", Font.PLAIN, 20));
		button_1.setBounds(112, 375, 78, 31);
		contentPane.add(button_1);
		button_1.addActionListener(this);
		
		button_2 = new JButton("修改");
		button_2.setFont(new Font("宋体", Font.PLAIN, 20));
		button_2.setBounds(221, 375, 78, 31);
		contentPane.add(button_2);
		button_2.addActionListener(this);
		
		button_3 = new JButton("删除");
		button_3.setFont(new Font("宋体", Font.PLAIN, 20));
		button_3.setBounds(319, 375, 78, 31);
		contentPane.add(button_3);
		button_3.addActionListener(this);
		
		button_4 = new JButton("退出");
		button_4.setFont(new Font("宋体", Font.PLAIN, 20));
		button_4.setBounds(417, 375, 78, 31);
		contentPane.add(button_4);
		button_4.addActionListener(this);
		
	}

   
 //把查询出来的个人信息单一的显示在表格中
  	 public Object[][] ShowSingel(List<ReaderType> list){
  			Object [][]date=new Object[list.size()][title.length];
  			for(int i=0;i<list.size();i++){
  				ReaderType type=list.get(i);
  				date[i][0]=type.getId();
  				date[i][1]=type.getTypename();
  				date[i][2]=type.getLimit();
  				date[i][3]=type.getMaxborrownum();
  			}
			return date;
		}
//把数据库的内容全展示到表格中		
      public void ShowTable(){
    	  List<ReaderType> list = ReaderTypeDao2.getAllReaderTypes();
			int i=0;
			String[][] data=new String[list.size()][title.length];
			if(list!=null){
			Iterator<ReaderType> it=list.iterator();  //使用迭代器存链表内容
			while(it.hasNext()){
				ReaderType next = it.next();
				data[i][0]=String.valueOf(next.getId());
				data[i][1]=String.valueOf(next.getTypename().trim());
				data[i][2]=String.valueOf(next.getMaxborrownum());
				data[i][3]=String.valueOf(next.getLimit());
				i++;
			   }
				table=new JTable(data,title);
				table.addMouseListener(this);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//设置表格固定大小不变
				table.getColumnModel().getColumn(1).setPreferredWidth(160);
				scrollPane.setViewportView(table);     //在滚动面板中创建一个视口，并存放视图table 
				add(scrollPane);
		}
			
   }
      
      
	
      
	public void actionPerformed(ActionEvent e) {
		ReaderType reader=new ReaderType();
		if(e.getSource()==button){             //“查询”按钮事件

				String name=textField.getText();
				reader.setTypename(name);
				if(name.length()==0||name.equals("")){
					JOptionPane.showMessageDialog(this, "输入不能为空！");
					return;
					}
			//把查询到的内容显示到文本框中
				List<ReaderType> list = ReaderTypeDao2.queryReaderTypeByname(name);
				if(list.size()!=0){
				Iterator<ReaderType> it=list.iterator();
				while(it.hasNext()){
					ReaderType next = it.next();
					//获取数据库内容
					String id=String.valueOf(next.getId());
					String nn=String.valueOf(next.getTypename());
					String borrownum=String.valueOf(next.getMaxborrownum());
					String limit=String.valueOf(next.getLimit());
					//从数据库获取的内容设置到文本框里边
					textField_1.setText(id);
                    textField_2.setText(nn);
                    textField_3.setText(borrownum);
                    textField_4.setText(limit);
                 
				}
              }else{
            	  JOptionPane.showMessageDialog(this, "你输入的类型不存在");
            	  return;
              }
				//在表格中显示查询到的信息
				Object[][]date=ShowSingel(ReaderTypeDao2.queryReaderTypeByname(name));
				table=new JTable(date,title);
				table.addMouseListener(this);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.getColumnModel().getColumn(1).setPreferredWidth(160);
				scrollPane.setViewportView(table);
			}
			
//设置总变量，为以下按钮使用			
			String id=null;
			String name=null;
			String Max=null;
			String limit=null;
			ReaderType reader1=null;
		 //获取文本框内容，把它添加到reader1中
			try {
				id=textField_1.getText().trim();
				name=textField_2.getText().trim();
				Max=textField_3.getText().trim();
		        limit=textField_4.getText().trim();
				reader1=new ReaderType();
				reader1.setId(Integer.parseInt(id));
				reader1.setTypename(name);
				reader1.setMaxborrownum(Integer.parseInt(Max));
				reader1.setLimit(Integer.parseInt(limit));
			}catch (NumberFormatException e1) {
						}
				
	
			
	if(e.getSource()==button_1){        //点击“添加”按钮
		    textField_1.setEditable(true);
			if(textField_1==null||textField_1.getText().length()==0){
				JOptionPane.showMessageDialog(this, "输入不能为空");
				return;
				}
			//判断输入的用户是否存在
			List <ReaderType> list=ReaderTypeDao2.getAllReaderTypes();
			boolean booltest=true;
			for(int i=0;i<list.size();i++){
				ReaderType type=list.get(i);
				if(textField_1.getText().trim().equals(type.getId()+"")){
					JOptionPane.showMessageDialog(this, "你输入的用户已存在");
					booltest=false;
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					return;
				}
			}
			//连接添加数据的SQL语句，添加读者信息
			if (booltest) {
				System.out.println(reader1.getId());
				if(ReaderTypeDao2.insertReaderType(reader1)!=0){
					JOptionPane.showMessageDialog(this, "添加成功 ！");
					ShowTable();       //刷新表格内容
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					}
			}
		}
		
		if(e.getSource()==button_2){	
			    textField_1.setEditable(true);      //点击修改按钮后，把不可编辑的文本框设置为可编辑   
			    if(textField_2.getText().length()==0){
				JOptionPane.showMessageDialog(this, "文本框内容不能为空");
				return;
			     }
				if(ReaderTypeDao2.updateReaderType(reader1)!=0){   //修改成功后，设置文本框内容为空
					JOptionPane.showMessageDialog(this, "修改成功 ！");
					ShowTable();
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
				}
			}
			
		if(e.getSource()==button_3){           //点击“删除”按钮
			textField_1.setEditable(true);     //点击删除按钮后，把不可编辑的文本框设置为可编辑   
			if(textField_3.getText().length()==0||textField_3==null){
				JOptionPane.showMessageDialog(this, "文本框内容不能为空");
				return;
			}
			if(ReaderTypeDao2.deleteReaderType(Integer.parseInt(id))!=0){
				JOptionPane.showMessageDialog(this, "删除成功 ！");
				ShowTable();
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				}
			}
			
		if(e.getSource()==button_4){
			dispose();
			new MainWindow();
				}
	}


	public void mouseClicked(MouseEvent e) {
		if (e.getSource()==table) {
			textField_1.setEditable(false);   //点击表格中的内容后，设置文本框的编号为不可编辑
//			button_2.setEnabled(true);
//			button_3.setEnabled(true);
			int row=table.getSelectedRow();
			String typeid=table.getValueAt(row, 0)+"";
			String typename=(String) table.getValueAt(row, 1);
			String Maxborrow=table.getValueAt(row, 2)+"";
			String limit= table.getValueAt(row, 3)+"";
			
			textField_1.setText(typeid);
			textField_2.setText(typename);
			textField_3.setText(Maxborrow);
			textField_4.setText(limit);		
//			button_1.setEnabled(false);
		}
	}
	
	public void mouseEntered(MouseEvent e) {	
	}

	public void mouseExited(MouseEvent e) {
	}
	
	public void mousePressed(MouseEvent e) {
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
}

