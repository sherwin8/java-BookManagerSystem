package pdsu.bbm.view;


import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Fine extends JFrame implements ActionListener{

	private JPanel contentPane;
	public  static JTextField textField=new JTextField();
	JButton button,button_1;
	public static void main(String[] args) {
		new Fine();
	}
	public Fine() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((Toolkit.getDefaultToolkit().getScreenSize().width-400)/2,(Toolkit.getDefaultToolkit().getScreenSize().height-200)/2,400,200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("罚金:");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(114, 48, 54, 24);
		contentPane.add(label);
		
		textField.setBounds(164, 50, 54, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("元/天");
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(217, 49, 54, 22);
		contentPane.add(label_1);
		
		 button = new JButton("设置");
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		button.setBounds(127, 117, 80, 30);
		contentPane.add(button);
		button.addActionListener(this);
		
		 button_1 = new JButton("关闭");
		button_1.setFont(new Font("宋体", Font.PLAIN, 20));
		button_1.setBounds(217, 117, 80, 30);
		contentPane.add(button_1);
		button_1.addActionListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button_1){
			dispose();
			new MainWindow();
		}
		if(e.getSource()==button){
			if(textField.getText().equals("")){
				JOptionPane.showMessageDialog(this,"请输入罚金!");
			}
			else JOptionPane.showMessageDialog(this,"罚金设置成功");
		}
			
	}
}

