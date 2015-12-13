import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class mainFrame implements ActionListener {
	JTextArea file = new JTextArea(20, 40);
	JTextArea show = new JTextArea(20, 40);
	JTextArea error = new JTextArea(12, 90);
	JButton button = new JButton("compile");
	LR0 lr = new LR0();
	AddPoint addPoint = new AddPoint();

	public mainFrame() {
		JFrame frame = new JFrame();
		Container contentPane = frame.getContentPane();
		JPanel mainPanel = new JPanel();
		JPanel errorPanel = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		mainPanel.setLayout(new FlowLayout());
		button.setMnemonic('C');
		p1.add(new JScrollPane(file));
		p2.add(new JScrollPane(show));
		p1.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.gray, 2), "请在此处输入文法",
				TitledBorder.LEFT, TitledBorder.TOP));
		p2.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.gray, 2), "自动机",
				TitledBorder.LEFT, TitledBorder.TOP));
		errorPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.gray, 2), "LR0 分析表",
				TitledBorder.LEFT, TitledBorder.TOP));
		mainPanel.add(p1);
		mainPanel.add(button);
		mainPanel.add(p2);
		button.addActionListener(this);
		errorPanel.add(new JScrollPane(error));
		contentPane.setLayout(new BorderLayout());
		contentPane.add(mainPanel, BorderLayout.CENTER);
		contentPane.add(errorPanel, BorderLayout.SOUTH);
		JMenu fileMenu = new JMenu("File");
		JMenu helpMenu = new JMenu("帮助");
		JMenuItem newM = new JMenuItem("New");
		JMenuItem open = new JMenuItem("Open");
		JMenuItem exit = new JMenuItem("Exit");
		JMenuItem save = new JMenuItem("save");
		JMenuItem help1 = new JMenuItem("	帮助");
		JMenuItem about = new JMenuItem("关于本系统");
		helpMenu.add(help1);
		helpMenu.add(about);
		JMenuBar MBar = new JMenuBar();
		MBar.add(helpMenu);
		frame.setJMenuBar(MBar);
		frame.getRootPane().setDefaultButton(button);
		frame.setTitle("LR(0)");
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		help1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane
						.showMessageDialog(null,
								"输入文法时以‘>’代替箭头\n什么都不输入表示空串\n可以加入任意的空格与换行符输入\n输入完点击‘compile’按钮或点击回车键查看结果。");
			}
		});
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane
						.showMessageDialog(null,
								"name:LR0语法分析程序\nversio:1.0\nauthor:褚江\n2010 7.5-7.16\n");
			}
		});
	}

	public static void main(String[] args) {
		new mainFrame();

	}

	public void actionPerformed(ActionEvent e) {

		String s = file.getText();
		StringBuffer output = new StringBuffer();
		int m = 0;
		StringBuffer[][] I0 = lr.Lr0(s);
		for (int i = 0; i < I0.length; i++) {
			if (!I0[i][0].toString().equals("")) {

				output.append("I" + m + ":");
				m++;
				for (int j = 0; j < I0[i].length; j++)
					if (!I0[i][j].toString().equals("")) {
						int point = (int) I0[i][j].charAt(0) - 46;
						for (int n = 1; n < I0[i][j].length(); n++) {
							if (I0[i][j].charAt(n) == '>')
								output.append("→");
							else {
								if (n == point) {
									output.append("·");
								}
								output.append(I0[i][j].charAt(n));
							}
						}
						if (point == I0[i][j].length()) {
							output.append("·");
						}
						output.append("\n");
					}

				output.append("\n");
			}
		}
		LR0Table lrt = new LR0Table();
		StringBuffer stb = new StringBuffer();
		String[][] lr0table = lrt.lr0Table(s);
		for (int i = 0; i < Integer.parseInt(lr0table[99][29]) + 1; i++) {
			stb.append(lr0table[98][i] + "\t");
		}
		stb.append("\n");
		for (int i = 0; i < Integer.parseInt(lr0table[99][28]); i++) {

			stb.append(i + "\t");
			for (int j = 0; j < Integer.parseInt(lr0table[99][29]); j++) {
				if (lr0table[i][j] == (null))
					stb.append("\t");
				else
					stb.append(lr0table[i][j] + "\t");

			}
			stb.append("\n");
		}
		error.setText(stb.toString());
		show.setText(output.toString());

	}

}
