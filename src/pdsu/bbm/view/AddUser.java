package pdsu.bbm.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.omg.CORBA.PRIVATE_MEMBER;

import pdsu.bbm.dao.UsersDao;
import pdsu.bbm.model.Users;

public class AddUser extends JFrame implements  ActionListener {

	private JPanel contentPane;
	JComboBox comboBox;
	JPanel centerpPanel;
	JButton button1, button2,button3;
	private JLabel label;
	private JTextField textField;
	private JLabel label_1;
	private JPasswordField passwordField;
	   Image icon = new ImageIcon("image/icon.png").getImage();

	public static void main(String[] args) {
		new AddUser();
	}

	public AddUser() {
		setTitle("添加用户名信息");
		setSize(555, 333);
		setIconImage(icon);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		centerpPanel = new JPanel();
		centerpPanel.setLayout(null);

		add(centerpPanel, BorderLayout.CENTER);

		label = new JLabel("用户名:");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(175, 67, 80, 40);
		centerpPanel.add(label);

		textField = new JTextField();
		textField.setBounds(245, 72, 120, 30);
		centerpPanel.add(textField);
		textField.setColumns(10);

		label_1 = new JLabel("密  码:");
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(175, 120, 80, 40);
		centerpPanel.add(label_1);

		passwordField  = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(245, 125, 120, 30);
		centerpPanel.add(passwordField);
		ButtonGroup group = new ButtonGroup();
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		button1 = new JButton("添加");
		button1.setFont(new Font("宋体", Font.PLAIN, 20));

		button2 = new JButton("重置");
		button2.setFont(new Font("宋体", Font.PLAIN, 20));
		button3=new JButton("关闭");
		button3.setFont(new Font("宋体", Font.PLAIN, 20));
		panel2.add(button1);
		panel2.add(button2);
		panel2.add(button3);
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		add(panel2, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button3){
			dispose();
			new MainWindow();
		}
		if(e.getSource()==button2){
			textField.setText("");
			passwordField.setText("");
		}
		if(e.getSource()==button1){
			Users users=new Users();
			String name=textField.getText().toString();
			String password=new String(passwordField.getPassword());
			 users.setName(name);
			 users.setPassword(password);
			 if(UsersDao.checkName(users)!=null){
				 JOptionPane.showMessageDialog(this, "该用户已存在");
				 textField.setText("");
				 passwordField.setText("");

			 }
			 else if(name.equals("")){
				 JOptionPane.showMessageDialog(this, "请输入用户名");
			 }
			 else if(password.equals("")){
				 JOptionPane.showMessageDialog(this, "请输入密码");
			 }
			 else{
				 if(UsersDao.insertUser(users)!=0){
					 JOptionPane.showMessageDialog(this, "添加成功");
			 }
			 }
			
			
		}
		
	}
}
