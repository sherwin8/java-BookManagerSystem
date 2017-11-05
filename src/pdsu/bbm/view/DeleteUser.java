package pdsu.bbm.view;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import pdsu.bbm.dao.UsersDao;
import pdsu.bbm.model.Reader;
import pdsu.bbm.model.Users;

public class DeleteUser extends JFrame implements ActionListener,MouseListener {
	JTable table;
	String title[]={"用户id","用户名","用户密码"};
	JButton button,button_1;
	int row=0,id,result;
	Image icon = new ImageIcon("image/icon.png").getImage();

	public DeleteUser() {
		String[][] data = {};
		setLayout(null);
		setIconImage(icon);
		setSize(555, 333);
		setLocationRelativeTo(null);
		setTitle("删除用户");
		List<Users> list=UsersDao.selectUser();

		DefaultTableModel dtm = new DefaultTableModel(getSelect(list), title);
		 table = new JTable(dtm);
		 table.addMouseListener(this);
		// 等效写法1
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setLocation(30, 10);
		scrollPane.setSize(480, 200);
		add(scrollPane);
		
		 button = new JButton("删除");
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		button.setBounds(185, 235, 75, 30);
		button.addActionListener(this);
		add(button);

		 button_1 = new JButton("退出");
		 button_1.addActionListener(this);
		button_1.setFont(new Font("宋体", Font.PLAIN, 20));
		button_1.setBounds(290, 235, 75, 30);
		add(button_1);
		
		setVisible(true);
	}


	
	private Object[][] getSelect(List<Users> list) {
		Object[][] data = new Object[list.size()][title.length];
		String chars="qwertyuioplkjhgfdsazxcvbnmQWERTYUIOPLKJHGFDSAZXCVBNM";
		for (int i = 0; i < list.size(); i++) {
			Users user= list.get(i);
			data[i][0]=user.getId();
			data[i][1]=user.getName();
			int r=(int) (Math.random()*10000);
			String s=user.getPassword().replaceAll("[a-zA-Z]+",String.valueOf(r));
			String s1=user.getPassword().replaceAll("[0-9]+",chars.charAt((int) (Math.random()*26))+"");
			data[i][2]=s+s1;
		}
		return data;
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource()==table) {
			 row=table.getSelectedRow();
			String stringid=table.getValueAt(row, 0)+"";
			  id=Integer.parseInt(stringid);
		}
		
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button){
//			 if(row!=0||row<0){
//				 JOptionPane.showMessageDialog(this, "请选择要删除的用户");
//			 }
//			 else {
				 if(JOptionPane.showConfirmDialog(this, "确认删除该用户吗？")==JOptionPane.OK_OPTION){
					  String name=(String) table.getValueAt(row,1);
					  if(name.equals(Login.textField.getText().toString())){
						  
						  JOptionPane.showMessageDialog(this, "该用户处于登录状态无法删除!","提示 ",JOptionPane.WARNING_MESSAGE);
					  }else{
					   result=UsersDao.deleteUser(id);
						DefaultTableModel tm =(DefaultTableModel) table.getModel();
						tm.removeRow(table.getSelectedRow());
					  }
					  System.out.println(Login.textField.getText()+row);
				 }
			}
//		}
		
		if(e.getSource()==button_1){
			dispose();
			new MainWindow();
		}
		
	}
}
