package pdsu.bbm.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
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
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import pdsu.bbm.dao.BookTypeDao;
import pdsu.bbm.dao.Dao;
import pdsu.bbm.dao.ReaderDao;
import pdsu.bbm.dao.ReaderTypeDao;
import pdsu.bbm.model.BookType;
import pdsu.bbm.model.Reader;
import pdsu.bbm.model.ReaderType;


public class AddReader extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel label_1;
	private JLabel lbll;
	private JLabel label_2;
	private JLabel label_3;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel label_4;
	private JRadioButton radioButton,radioButton_1;
	private JTextField textField_4;
	private JLabel label_5;
	private JTextField textField_5;
	JComboBox comboBox_1;
	JPanel centerpPanel;
	JButton button1,button2;
	Image icon = new ImageIcon("image/icon.png").getImage();
	public static void main(String[] args) {
	new AddReader();
	}

	public AddReader() {
		setTitle("添加读者信息");
		
		setSize(555, 333);
		setIconImage(icon);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		centerpPanel=new JPanel();
		centerpPanel.setLayout(null);
		JLabel label1 = new JLabel("编  号:");
		label1.setFont(new Font("宋体", Font.PLAIN, 20));
		label1.setBounds(50, 26, 116, 42);
		centerpPanel.add(label1);
		
		textField = new JTextField();
		textField.setBounds(122, 35, 120, 30);
		centerpPanel.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("姓  名:");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(292, 30, 116, 35);
		centerpPanel.add(label);
		
		textField_1 = new JTextField();
		textField_1.setBounds(362, 35, 120, 30);
		centerpPanel.add(textField_1);
		textField_1.setColumns(10);
		
		label_1 = new JLabel("类  别:");
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(50, 78, 154, 50);
		centerpPanel.add(label_1);
		
		
		lbll = new JLabel("性  别:");
		lbll.setFont(new Font("宋体", Font.PLAIN, 20));
		lbll.setBounds(292, 78, 70, 50);
		centerpPanel.add(lbll);
		
		label_2 = new JLabel("年  龄:");
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		label_2.setBounds(50, 130, 154, 50);
		centerpPanel.add(label_2);
		
		label_3 = new JLabel("电  话:");
		label_3.setFont(new Font("宋体", Font.PLAIN, 20));
		label_3.setBounds(292, 130, 137, 50);
		centerpPanel.add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(122, 138, 120, 30);
		centerpPanel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(362, 138, 120, 30);
		centerpPanel.add(textField_3);
		
		label_4 = new JLabel("所在系:");
		label_4.setFont(new Font("宋体", Font.PLAIN, 20));
		label_4.setBounds(50, 178, 131, 50);
		centerpPanel.add(label_4);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(122, 190, 120, 30);
		centerpPanel.add(textField_4);
		
		label_5 = new JLabel("注册日期:");
		label_5.setFont(new Font("宋体", Font.PLAIN, 20));
		label_5.setBounds(271, 178, 137, 50);
		centerpPanel.add(label_5);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(362, 190, 120, 30);
		centerpPanel.add(textField_5);
		
		List<ReaderType> rs = ReaderTypeDao.selectReaderType();
		Iterator<ReaderType> iterator = rs.iterator();
		String[] AllTypeName = new String[rs.size()];
		int i = 0;
		while (iterator.hasNext()) {
			String typename = iterator.next().getTypename();
			AllTypeName[i] = typename;
			i++;
		}
	    comboBox_1 = new JComboBox<String>(AllTypeName);
		comboBox_1.setFont(new Font("宋体", Font.PLAIN, 16));
		comboBox_1.setBounds(122, 84, 120, 30);
		comboBox_1.addActionListener(this);
		centerpPanel.add(comboBox_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(362, 85, 120, 30);
		centerpPanel.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		getContentPane().add(centerpPanel,BorderLayout.CENTER);
		 radioButton = new JRadioButton("男");
		radioButton.setFont(new Font("宋体", Font.PLAIN, 16));
		panel.add(radioButton);
		 radioButton_1 = new JRadioButton("女");
		radioButton_1.setFont(new Font("宋体", Font.PLAIN, 16));
		panel.add(radioButton_1);
		ButtonGroup group=new ButtonGroup();
		group.add(radioButton);
		group.add(radioButton_1);
		JPanel panel2=new JPanel();
		panel2.setLayout(new FlowLayout());
		button1=new JButton("添加");
		button1.setFont(new Font("宋体", Font.PLAIN, 20));
		button1.addActionListener(this);
		button2=new JButton("关闭");
		button2.setFont(new Font("宋体", Font.PLAIN, 20));
		button2.addActionListener(this);
		panel2.add(button1);
		panel2.add(button2);
		add(panel2,BorderLayout.SOUTH);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		Reader reader=new Reader();
		if(e.getSource()==button2){
			dispose();
			new MainWindow();
		}
		if(e.getSource()==button1){
			String bianhao=textField.getText().trim();
			String name =textField_1.getText().trim();
			String typename=comboBox_1.getSelectedItem()+"";
			String date=textField_5.getText().trim();
			String sex;
			if(radioButton.isSelected()){
				sex="男";
			}else sex="女";
			
			String phone=textField_3.getText().trim();
			String dept=textField_4.getText().trim();
			String age=textField_2.getText();
			Date regdate=null;
					try {
					regdate=Dao.getStringToDate(date);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
			if(regdate==null) return;
			int m=0;
			
			reader.setReaderid(bianhao);
			reader.setName(name);
			reader.setTypename(typename);
			
			
			
			List<ReaderType> list=ReaderTypeDao.selectReaderType(typename);
			Iterator<ReaderType> iterator=list.iterator();
			while (iterator.hasNext()) {
				m=iterator.next().getId();
			}
			//System.out.println(m);
			
			reader.setType(m);
			reader.setSex(sex);
			reader.setPhone(phone);
			reader.setDept(dept);
			reader.setRegDate(regdate);
			
			
			
			
			if(bianhao.equals("")||name.equals("")||sex.equals("")||age.equals("")||phone.equals("")||dept.equals("")||date.equals("")){
				JOptionPane.showMessageDialog(this,"请输入正确的信息");
				return;
			}
			else if(ReaderDao.selectReaderById2(bianhao)!=null){
				JOptionPane.showMessageDialog(this,"该编号已存在");
				System.out.println(ReaderDao.insertReader(reader));
				return;
			}
			
			else if(!age.equals("")){
				try {
					reader.setAge(Integer.parseInt(age));
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				}
				if(ReaderDao.insertReader(reader)!=0){
					JOptionPane.showMessageDialog(this,"添加成功");
				}
				else {
					JOptionPane.showMessageDialog(this,"添加失败");
				}
			}
		}
	}
}
