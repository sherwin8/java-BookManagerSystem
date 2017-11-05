package pdsu.bbm.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import pdsu.bbm.dao.UsersDao;
import pdsu.bbm.model.Users;

public class Login extends JFrame implements ActionListener, MouseListener,KeyListener,FocusListener{
	JLabel label1, label2, label3, name_label, name_errorLabel, label4;
	JButton button1, button2;
	public static JTextField textField=new JTextField();;
	JPasswordField passwordField;
	Dimension dimension = new Dimension(800, 500);
	ImageIcon image = new ImageIcon("image/background.jpg");
	Image icon = new ImageIcon("image/icon.png").getImage();
	Users users = new Users();
	public Login() {
		init();
		setUndecorated(true);
		setSize(dimension);
		setTitle("图书管理系统—登录界面");
		setIconImage(icon);
		setLocationRelativeTo(null);
		// setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void init() {
		Font f = new Font("宋体", Font.BOLD, 20);
		int x = 260, y = 190;
		BJpanl panel = new BJpanl(dimension, image.getImage());
		panel.setLayout(null);
		label1 = new JLabel("用户名:");
		label1.setBounds(x, y, 80, 40);
		label1.setFont(f);
		label2 = new JLabel("密  码:");
		label2.setBounds(x, y + 60, 110, 40);
		label2.setFont(f);

		label3 = new JLabel("图书借阅系统");
		label3.setFont(new Font("宋体", Font.BOLD, 50));
		label3.setBounds(x + 0, y - 240, 500, 200);

		name_label = new JLabel(new ImageIcon("image/ok.png"));
		name_label.setBounds(x + 230, y - 6, 50, 50);
		name_label.setVisible(false);

		name_errorLabel = new JLabel(new ImageIcon("image/error.png"));
		name_errorLabel.setBounds(x + 230, y - 6, 50, 50);
		name_errorLabel.setVisible(false);
		label4 = new JLabel("用户登录");
		label4.setBounds(x + 60, y - 170, 300, 200);
		// label1.setHorizontalAlignment(SwingConstants.CENTER);
		label4.setFont(new Font("黑体", Font.BOLD, 35));

		textField = new JTextField();
		textField.setBounds(x + 80, y + 8, 150, 30);
		textField.addMouseListener(this);
		textField.addKeyListener(this);
		textField.addFocusListener(this);
		passwordField = new JPasswordField();
		passwordField.setBounds(x + 80, y + 66, 150, 30);
		passwordField.addMouseListener(this);
		passwordField.addFocusListener(this);
		button1 = new JButton("登录");
		button1.setBounds(x + 30, y + 150, 80, 40);
		button1.setFont(f);
		// button1.setBackground(new Color(255,255,200));
		button2 = new JButton("重置");
		button2.setFont(f);
		button2.setBounds(x + 150, y + 150, 80, 40);
		button1.addActionListener(this);
		button2.addActionListener(this);

		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		panel.add(name_label);
		panel.add(name_errorLabel);
		panel.add(label4);
		panel.add(textField);
		panel.add(passwordField);
		panel.add(button1);
		panel.add(button2);

		add(panel, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		new Login();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1) {
			String name = textField.getText().toString();
			String password = new String(passwordField.getPassword());
			users.setName(name);
			// users.setPassword(password);
			if (name.equals("")) {
				JOptionPane.showMessageDialog(this, "请输入用户名", "提示",
						JOptionPane.ERROR_MESSAGE);
				name_label.setVisible(false);
			} else if (password.equals("")) {
				JOptionPane.showMessageDialog(this, "请输入密码", "提示",
						JOptionPane.ERROR_MESSAGE);
			} else if ((UsersDao.checkName(users)) != null) {
				// name_label.setVisible(true);
				users.setPassword(password);
				if (UsersDao.check(users) != null) {
					dispose();
					new MainWindow();
//					JOptionPane.showMessageDialog(this, "登录成功");
				} else {
					JOptionPane.showMessageDialog(this, "密码错误");
					passwordField.setText("");
				}
			} else {
				JOptionPane.showMessageDialog(this, "不存在");
				textField.setText("");
				passwordField.setText("");
				name_label.setVisible(false);
				name_errorLabel.setVisible(false);
			}

		}
		if (e.getSource() == button2) {
			textField.setText("");
			passwordField.setText("");
			name_label.setVisible(false);
			name_errorLabel.setVisible(false);

		}
	}

	public void mouseClicked(MouseEvent e) {
		String name = textField.getText().toString();
		// String password = new String(passwordField.getPassword());
		users.setName(name);
		// users.setPassword(password);
		if (e.getSource() == passwordField) {
			if (!textField.equals("")) {
				if ((UsersDao.checkName(users)) != null) {
					name_label.setVisible(true);
				} else {
					name_label.setVisible(false);
					name_errorLabel.setVisible(true);
				}
			}

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

	class BJpanl extends JPanel {
		Dimension d;
		Image image;

		public BJpanl(Dimension d, Image image) {
			this.d = d;
			this.image = image;
		}

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, d.width, d.height, null);
		}
	}
	public void keyPressed(KeyEvent e) {
		if(e.getSource()==textField){
			if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
				if(textField.getText().length()<2||textField.getText().equals("")){
					name_errorLabel.setVisible(false);
					name_label.setVisible(false);
				}
			}
		}
	}
	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
	}

	public void focusGained(FocusEvent e) {
		if(e.getSource()==textField&&textField.getText().equals("")){
			name_errorLabel.setVisible(false);
			name_label.setVisible(false);
		}
		if(e.getSource()==passwordField&&textField.getText().equals("")){
			name_errorLabel.setVisible(false);
			name_label.setVisible(false);
		}
	}

	public void focusLost(FocusEvent arg0) {
		
	}

}
