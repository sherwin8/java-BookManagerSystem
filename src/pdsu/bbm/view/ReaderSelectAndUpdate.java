package pdsu.bbm.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import pdsu.bbm.dao.Dao;
import pdsu.bbm.dao.ReaderDao;
import pdsu.bbm.dao.ReaderTypeDao;
import pdsu.bbm.model.Reader;
import pdsu.bbm.model.ReaderType;

public class ReaderSelectAndUpdate extends JFrame implements ActionListener,MouseListener {

	// 上部组件
	private JPanel selectJP;

	// 上部组建(北)
	private JPanel select_conditionJP;
	private JButton selectJB;
	private JComboBox conditionJCB;
	private JTextField select_conditionJTF;
	// 上部组件(中)
	private JTable jtable;
	private String[] title = { "编号", "类型", "姓名", "年龄", "性别", "电话", "系部", "注册日期" };
	private JPanel select_resultJP;
	private JScrollPane jscrollPane;

	// 中部组件
	private JPanel updateJP, sexJP;
	private JRadioButton JRB1, JRB2;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel IDJL, typeJL, readerNameJL, sexJL, phoneJL, deptJL, ageJL,
			regJL;
	private JTextField IDJTF, readerNameJTF, phoneJTF, deptJTF, ageJTF, regJTF;
	private JComboBox readertypeJCB;

	// 底部组件
	private JPanel buttonJP;
	private JButton updateJB, closeJB;
	Image icon = new ImageIcon("image/icon.png").getImage();

	public ReaderSelectAndUpdate() {
		setBounds(200, 200, 555, 500);
		setLocationRelativeTo(null);
		setTitle("读者信息查询与修改");
		setIconImage(icon);
		// 读者信息查询面板设计
		selectJP = new JPanel();
		selectJP.setLayout(new BorderLayout());

		// 查询条件面板
		// 查询条件下拉列表框
		select_conditionJP = new JPanel();
		conditionJCB = new JComboBox();
		String[] array = { "读者编号", "姓名", "类型", "系部","所有"};

		for (int i = 0; i < array.length; i++) {
			conditionJCB.addItem(array[i]);
			conditionJCB.setFont(new Font("宋体", Font.PLAIN, 16));
		}
		conditionJCB.addActionListener(this);
		select_conditionJP.add(conditionJCB);
		// 查询条件文本框
		select_conditionJTF = new JTextField();
		select_conditionJTF.setColumns(20);
		select_conditionJP.add(select_conditionJTF);
		// 查询条件按钮
		selectJB = new JButton("查询");
		selectJB.setFont(new Font("宋体", Font.PLAIN, 20));

		selectJB.addActionListener(this);
		select_conditionJP.add(selectJB);

		selectJP.add(select_conditionJP, BorderLayout.NORTH);

		// 查询结果面板
		select_resultJP = new JPanel();
		jscrollPane = new JScrollPane();
		jscrollPane.setPreferredSize(new Dimension(400, 200));

		// 查询读者信息并生成初始表格
		// Object[][] data = getSelect(ReaderDao.selectReader());
		List<Reader> list = ReaderDao.selectReader();

		jtable = new JTable(getSelect(list), title);
		jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jtable.addMouseListener(this);
		jscrollPane.setViewportView(jtable);

		select_resultJP.add(jscrollPane);
		selectJP.add(select_resultJP, BorderLayout.CENTER);

		// 读者信息修改面板设计
		updateJP = new JPanel();
		updateJP.setBorder(new EmptyBorder(5, 10, 5, 10));
		GridLayout gridLayout = new GridLayout(4, 4);
		gridLayout.setVgap(10);
		gridLayout.setHgap(10);
		updateJP.setLayout(gridLayout);

		IDJL = new JLabel("编  号:");
		IDJL.setFont(new Font("宋体", Font.PLAIN, 20));
		IDJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(IDJL);
		IDJTF = new JTextField();
		IDJTF.setEditable(false);
		updateJP.add(IDJTF);

		readerNameJL = new JLabel("姓  名:");
		readerNameJL.setFont(new Font("宋体", Font.PLAIN, 20));
		readerNameJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(readerNameJL);
		readerNameJTF = new JTextField();
		updateJP.add(readerNameJTF);

		typeJL = new JLabel("类  别:");
		typeJL.setFont(new Font("宋体", Font.PLAIN, 20));
		typeJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(typeJL);
		// 下拉列表
		List<ReaderType> rs = ReaderTypeDao.selectReaderType();
		Iterator<ReaderType> iterator = rs.iterator();
		String[] AllTypeName = new String[rs.size()];
		int i = 0;
		while (iterator.hasNext()) {
			String typename = iterator.next().getTypename();
			AllTypeName[i] = typename;
			i++;
		}
		readertypeJCB = new JComboBox(AllTypeName);
		readertypeJCB.setFont(new Font("宋体", Font.PLAIN, 20));

		// Iterator<ReaderType> it =
		// ReaderTypeDao.selectReaderType().iterator();
		// while(it.hasNext()){z
		// ReaderType rt = it.next();
		// readertypeJCB.addItem(rt.getName());
		// }
		updateJP.add(readertypeJCB);

		sexJL = new JLabel("性  别:");
		sexJL.setFont(new Font("宋体", Font.PLAIN, 20));
		sexJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(sexJL);
		sexJP = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		sexJP.setLayout(flowLayout);

		JRB1 = new JRadioButton();
		sexJP.add(JRB1);
		JRB1.setSelected(true);
		buttonGroup.add(JRB1);
		JRB1.setText("男");
		JRB1.setFont(new Font("宋体", Font.PLAIN, 20));
		JRB2 = new JRadioButton();
		sexJP.add(JRB2);
		buttonGroup.add(JRB2);
		JRB2.setText("女");
		JRB2.setFont(new Font("宋体", Font.PLAIN, 20));
		updateJP.add(sexJP);

		ageJL = new JLabel("年  龄:");
		ageJL.setFont(new Font("宋体", Font.PLAIN, 20));
		ageJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(ageJL);
		ageJTF = new JTextField(50);
		updateJP.add(ageJTF);

		phoneJL = new JLabel("电  话:");
		phoneJL.setFont(new Font("宋体", Font.PLAIN, 20));
		phoneJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(phoneJL);
		phoneJTF = new JTextField();
		updateJP.add(phoneJTF);

		deptJL = new JLabel("所在部门:");
		deptJL.setFont(new Font("宋体", Font.PLAIN, 20));
		deptJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(deptJL);
		deptJTF = new JTextField();
		updateJP.add(deptJTF);

		regJL = new JLabel("注册日期:");
		regJL.setFont(new Font("宋体", Font.PLAIN, 20));
		regJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(regJL);
		regJTF = new JTextField();
		updateJP.add(regJTF);
		// 登录取消按钮面板设计
		buttonJP = new JPanel();// 修改按钮面板
		updateJB = new JButton("修改");
		updateJB.addActionListener(this);
		updateJB.setFont(new Font("宋体", Font.PLAIN, 20));

		closeJB = new JButton("关闭");
		closeJB.addActionListener(this);
		closeJB.setFont(new Font("宋体", Font.PLAIN, 20));
		buttonJP.add(updateJB);
		buttonJP.add(closeJB);
		this.add(selectJP, BorderLayout.NORTH);
		this.add(updateJP, BorderLayout.CENTER);
		this.add(buttonJP, BorderLayout.SOUTH);

		this.setVisible(true);// 设置窗体显示，否则不显示。
		setResizable(false);// 取消最大化
	}

	public static void main(String[] args) {
		new ReaderSelectAndUpdate();
	}

	private Object[][] getSelect(List<Reader> list) {
		Object[][] data = new Object[list.size()][title.length];
		for (int i = 0; i < list.size(); i++) {
			Reader reader = list.get(i);

			data[i][0] = reader.getReaderid().trim();
			data[i][1] = reader.getTypename();
			data[i][2] = reader.getName().trim();
			data[i][3] = reader.getAge();
			data[i][4] = reader.getSex().trim();
			data[i][5] = reader.getPhone().trim();
			data[i][6] = reader.getDept().trim();
			data[i][7] = reader.getRegDate();
		}
		return data;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == selectJB) {
			String type = (String) conditionJCB.getSelectedItem();
			String find = select_conditionJTF.getText();
			

			 if (type.equals("读者编号")) {
					if (select_conditionJTF.getText().equals("")) 
						JOptionPane.showMessageDialog(this, "请输入查询条件");
					
				List<Reader> list = ReaderDao.selectReaderById(find);
				jtable = new JTable(getSelect(list), title);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jscrollPane.setViewportView(jtable);
				jtable.addMouseListener(this);
			}
			else if (type.equals("姓名")) {
				if (select_conditionJTF.getText().equals("")) 
					JOptionPane.showMessageDialog(this, "请输入查询条件");
				
				List<Reader> list = ReaderDao.selectReaderByName(find);
				jtable = new JTable(getSelect(list), title);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jscrollPane.setViewportView(jtable);
				jtable.addMouseListener(this);
			}
			else if (type.equals("类型")) {
				if (select_conditionJTF.getText().equals("")) 
					JOptionPane.showMessageDialog(this, "请输入查询条件");
				List<Reader> list = ReaderDao.selectReaderByType(find);
				jtable = new JTable(getSelect(list), title);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jscrollPane.setViewportView(jtable);
				jtable.addMouseListener(this);
			}
			else if (type.equals("系部")) {
				if (select_conditionJTF.getText().equals("")) 
					JOptionPane.showMessageDialog(this, "请输入查询条件");
				List<Reader> list = ReaderDao.selectReaderByDept(find);
				jtable = new JTable(getSelect(list), title);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jscrollPane.setViewportView(jtable);
				jtable.addMouseListener(this);
			}
			else if (type.equals("所有")) {
//				if (select_conditionJTF.getText().equals("")) 
//					JOptionPane.showMessageDialog(this, "请输入查询条件");
				List<Reader> list = ReaderDao.selectReader();
				jtable = new JTable(getSelect(list), title);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jscrollPane.setViewportView(jtable);
				jtable.addMouseListener(this);
			}
		}
		
		if (e.getSource() == updateJB) {
			String id = IDJTF.getText();
			String name = readerNameJTF.getText();
			String typename = (String) readertypeJCB.getSelectedItem();
			String sex;
			if (JRB1.isSelected()) {
				sex = "男";
			} else
				sex = "女";
			String ageStr = ageJTF.getText().trim();
			int age=0;
			try {
				age = Integer.parseInt(ageStr);
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			}

			String phone = phoneJTF.getText();
			String dept = deptJTF.getText();
			String regDateStr = regJTF.getText();

			Date regDate = null;
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date dt=new Date();
			try {
				regDate=sdf.parse(regDateStr);
			} catch (ParseException a) {
				JOptionPane.showMessageDialog(this, "请输入正确的日期格式！格式如2016-01-01");
				regJTF.setText("");
			}
			//System.out.println(regDateStr);

			if (regDate == null)
				return;
			int m = 0;
			// 封装对象
			Reader reader = new Reader();
			reader.setReaderid(id);
			reader.setName(name);
			reader.setTypename(typename);
			reader.setAge(age);
			reader.setRegDate(regDate);

			List<ReaderType> list = ReaderTypeDao.selectReaderType(typename);
			Iterator<ReaderType> iterator = list.iterator();
			while (iterator.hasNext()) {
				m = iterator.next().getId();
			}
			reader.setType(m);
			reader.setSex(sex);
			reader.setPhone(phone);
			reader.setDept(dept);
			
			int result = ReaderDao.updateReader(reader);
			if (result!=0) {
				JOptionPane.showMessageDialog(this, "修改成功！");
			} else {
				JOptionPane.showMessageDialog(this, "修改失败！");
			}

			List<Reader> list1 = ReaderDao.selectReader();
			
			Object[][] data = getSelect(list1);
			jtable = new JTable(data, title);
			jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			jscrollPane.setViewportView(jtable);
			jtable.addMouseListener(new MouseListener() {
				public void mouseReleased(MouseEvent e) {
					if (e.getSource() == jtable) {
						int row = jtable.getSelectedRow();
						
						String id = (String) jtable.getValueAt(row, 0);
						String typename = jtable.getValueAt(row, 1) + "";
						String name = (String) jtable.getValueAt(row, 2);
						String age = jtable.getValueAt(row, 3) + "";
						String sex = ((String) jtable.getValueAt(row, 4))
								.trim();
						String phone = (String) jtable.getValueAt(row, 5);
						String dept = (String) jtable.getValueAt(row, 6);
						
						Date regDate = (Date) jtable.getValueAt(row, 7);
						String regdatestr = Dao.getDateToString(regDate);
						
						IDJTF.setText(id);
						readerNameJTF.setText(name);
						phoneJTF.setText(phone);
						ageJTF.setText(age);
						deptJTF.setText(dept);
						regJTF.setText(regdatestr);
						
						readertypeJCB.setSelectedItem(typename);
						if (sex.equals("男")) {
							JRB1.setSelected(true);
						} else
							JRB2.setSelected(true);
					}
				}
				public void mousePressed(MouseEvent arg0) {
				}
				public void mouseExited(MouseEvent arg0) {
				}
				public void mouseEntered(MouseEvent arg0) {
				}
				public void mouseClicked(MouseEvent arg0) {
				}
			});
		}
		if(e.getSource()==closeJB){
			dispose();
			new MainWindow();
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == jtable) {
			int row = jtable.getSelectedRow();

			String id = (String) jtable.getValueAt(row, 0);
			String typename = jtable.getValueAt(row, 1) + "";
			String name = (String) jtable.getValueAt(row, 2);
			String age = jtable.getValueAt(row, 3) + "";
			String sex = ((String) jtable.getValueAt(row, 4)).trim();
			String phone = (String) jtable.getValueAt(row, 5);
			String dept = (String) jtable.getValueAt(row, 6);

			Date regDate = (Date) jtable.getValueAt(row, 7);
			String regdatestr = Dao.getDateToString(regDate);

			IDJTF.setText(id);
			readerNameJTF.setText(name);
			phoneJTF.setText(phone);
			ageJTF.setText(age);
			deptJTF.setText(dept);
			regJTF.setText(regdatestr);

			readertypeJCB.setSelectedItem(typename);
			if (sex.equals("男")) {
				JRB1.setSelected(true);
			} else
				JRB2.setSelected(true);
			IDJTF.setEditable(false);
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

}
