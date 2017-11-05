package pdsu.bbm.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import pdsu.bbm.dao.UsersDao;
import pdsu.bbm.model.Users;


public class RegistPassword extends JFrame implements ActionListener,FocusListener{

	private JPanel contentPane;
	private JTextField textField;
	private JLabel label_1;
	private JPasswordField textField_1;
	private JLabel label_2;
	private JPasswordField textField_2;
	private JLabel label_3;
	private JPasswordField textField_3;
	private JButton button;
	private JButton button_1;
	Image icon = new ImageIcon("image/icon.png").getImage();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	new RegistPassword();
	}

	/**
	 * Create the frame.
	 */
	public RegistPassword() {
		setIconImage(icon);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 555, 333);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("用 户 名:");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(165, 39, 100, 24);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(255, 37, 120, 30);
		textField.addFocusListener(this);
		contentPane.add(textField);
		textField.setColumns(10);
		
		label_1 = new JLabel("原 密 码:");
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(165, 86, 90, 24);
		contentPane.add(label_1);
		
		textField_1 = new JPasswordField();
		textField_1.setColumns(10);
		textField_1.setBounds(255, 83, 120, 30);
		textField_1.addFocusListener(this);
		contentPane.add(textField_1);
		
		label_2 = new JLabel("新 密 码:");
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		label_2.setBounds(165, 131, 90, 24);
		contentPane.add(label_2);
		
		textField_2 = new JPasswordField();
		textField_2.setColumns(10);
		textField_2.setBounds(255, 130, 120, 30);
		textField_2.addFocusListener(this);
		contentPane.add(textField_2);
		
		label_3 = new JLabel("确认密码:");
		label_3.setFont(new Font("宋体", Font.PLAIN, 20));
		label_3.setBounds(163, 175, 90, 30);
		contentPane.add(label_3);
		
		textField_3 = new JPasswordField();
		textField_3.setColumns(10);
		textField_3.setBounds(255, 178, 120, 30);
		textField_3.addFocusListener(this);
		contentPane.add(textField_3);
		
		button = new JButton("确认");
		button.addActionListener(this);
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		button.setBounds(185, 235, 75, 30);
		contentPane.add(button);
		
		button_1 = new JButton("返回");
		button_1.setFont(new Font("宋体", Font.PLAIN, 20));
		button_1.setBounds(290, 235, 75, 30);
		button_1.addActionListener(this);
		contentPane.add(button_1);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button_1){
			dispose();
			new MainWindow();
		}
		if(e.getSource()==button){
			Users users=new Users();
			String name=textField.getText();
			String oldpassword=new String(textField_1.getPassword());
			String newpassword=new String(textField_2.getPassword());
			String newpasswordconfirm=new String(textField_3.getPassword());
			users.setName(name);
			users.setPassword(oldpassword);
			if(name.equals("")||oldpassword.equals("")||newpassword.equals("")||newpasswordconfirm.equals("")){
				JOptionPane.showMessageDialog(this, "请输入完整信息");
			}
			else if(UsersDao.checkName(users)==null){
				JOptionPane.showMessageDialog(this, "用户不存在");
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
			}
			else if(UsersDao.check(users)==null){
				JOptionPane.showMessageDialog(this, "用户原密码错误");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
			}
			else if(!newpassword.equals(newpasswordconfirm)){
				JOptionPane.showMessageDialog(this, "两次设置的新密码不一致!");
				textField_2.setText("");
				textField_3.setText("");
			}
			else {
				users.setPassword(newpassword);
				if(UsersDao.updateUserPWDByName(users)!=0){
					JOptionPane.showMessageDialog(this, "修改密码成功");
				}
			}
			
		}
	}
	public void focusGained(FocusEvent e) {
		Users users=new Users();
		String name=textField.getText();
		String oldpassword=new String(textField_1.getPassword());
		String newpassword=new String(textField_2.getPassword());
		String newpasswordconfirm=new String(textField_3.getPassword());
		users.setName(name);
		if(e.getSource()==textField_1){
			if(textField.getText().equals("")){
				JOptionPane.showMessageDialog(this, "请输入用户名");
			}
			else if(UsersDao.checkName(users)==null){
//				JOptionPane.showMessageDialog(this, "用户名不存在");
			}
		}
		
	}

	public void focusLost(FocusEvent e) {
		
	}

}
