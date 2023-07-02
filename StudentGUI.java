package sdmcet.cse.oop;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

//class for costume exception
class MarksHighException extends Exception {
	public MarksHighException(String High) {
		super(High);
	}

	public String toString() {
		return "IA Marks given are above 20";
	}
}

class Grading extends JFrame implements ActionListener{

	static int marks,flag1=0,cie=-1;
	Container ContentPane;
	JTextField textfield1, textfield2, textfield3, textfield4, textfield5;
	JButton button;
	JLabel label1, label2, label3, label4, label5, label6, label7, label8;
	JPanel panel,panel1,panel2,panel3,panel4,panel5,panel6;
	
	Grading(String title){
		super(title);
		
		// Instantiating and adding panel to ContentPane
		ContentPane = this.getContentPane();
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(150, 300, 100, 37);
		ContentPane.add(panel);
		
		// Instantiating the button and adding it on the panel
		button = new JButton("calculate");
		button.setBounds(100,100,300, 300);
		button.setFocusable(false);
		button.addActionListener(this);
		panel.add(button);
		
		// Instantiation of other panels,labels and adding labels on corresponding panels for proper alignment
		panel1 = new JPanel();
		panel1.setBackground(Color.LIGHT_GRAY);
		panel1.setBounds(30, 350, 207, 45);
		ContentPane.add(panel1);
		label6=new JLabel();						//label for Total Marks
		panel1.add(label6);
		panel2 = new JPanel();
		panel2.setBackground(Color.LIGHT_GRAY);
		panel2.setBounds(215, 350, 110, 45);
		ContentPane.add(panel2);
		label7=new JLabel();						//label for grade
		panel2.add(label7);
		panel3 = new JPanel();
		panel3.setBackground(Color.LIGHT_GRAY);
		panel3.setBounds(0, 350, 100, 45);
		ContentPane.add(panel3);
		panel4 = new JPanel();
		panel4.setBackground(Color.LIGHT_GRAY);
		panel4.setBounds(325, 350, 100, 45);
		ContentPane.add(panel4);
		panel6= new JPanel();						//panel to hold the main Header
		panel6.setBounds(100, 0, 200, 40);
		ContentPane.add(panel6);
		label8=new JLabel("GRADE CALCULATOR");		//label for main Header
		panel6.add(label8);
		
		label1 = new JLabel("Enter IA-1 Marks:");
		label2 = new JLabel("Enter IA-2 Marks:");
		label3 = new JLabel("Enter IA-3 Marks:");
		label4 = new JLabel("Enter CTA Marks:");
		label5 = new JLabel("Enter SEE Marks:");
		textfield1 = new JTextField(5);
		textfield2 = new JTextField(5);
		textfield3 = new JTextField(5);
		textfield4 = new JTextField(5);
		textfield5 = new JTextField(5);
		
		// panel5 holds all the above TextFields and Labels and aligns them in GridLayout Fashion
		panel5 = new JPanel();
		panel5.setBackground(Color.WHITE);
		panel5.setBounds(27, 45, 350, 245);
		ContentPane.add(panel5);
		panel5.setLayout(new GridLayout(5, 2, 5, 5));
		panel5.add(label1);
		panel5.add(textfield1);
		panel5.add(label2);
		panel5.add(textfield2);
		panel5.add(label3);
		panel5.add(textfield3);
		panel5.add(label4);
		panel5.add(textfield4);
		panel5.add(label5);
		panel5.add(textfield5);
	}
	
	
	// Method for calculating Total Marks
	
	int cie(int ia1, int ia2, int ia3, int cta, int see) {

		if (ia1 < 0 || ia1 > 20 || ia2 < 0 || ia2 > 20 || ia3 < 0 || ia3 > 20 || cta < 0 || cta > 10 || see < 0
				|| see > 100) {
			flag1=1;
			marks = ' ';				/* Blank space in Total Marks whenever there is mistake in
											entering required details */	
			return marks;								
		}
		
		if(see==38 || see==39) {
			see=40;
		}
		
		if (see < 38) {
			marks=see;
			return marks;
		}

		float see1 = see / 2;

		if (ia1 > ia2 && ia3 > ia2) {
			cie = ia1 + ia2 + cta;
		} else if (ia2 > ia3 && ia1 > ia3) {
			cie = ia2 + ia1 + cta;
		} else {
			cie = ia2 + ia3 + cta;
		}
		
//		 Exception is handled if Students CIE is smaller than 20
		if (cie < 20) {
			try {
				throw new MarksHighException("Marks Out Of Range");
			} catch (MarksHighException mthe) {
				JOptionPane.showMessageDialog(null, "Student is detained from taking SEE!!",
						"INFORMATION MESSAGE",	JOptionPane.INFORMATION_MESSAGE);
				textfield5.setText("");
			}
		}
		if(cie<20) {
			flag1=1;
			marks = ' ';				/* Blank space in Total Marks whenever there is mistake in
											entering required details */	
			return marks;
		}

		if (see1 % 1 >= 0.5) {
			see1 = see1 + 1;
		}
		marks = (int) (cie + see1);

		return marks;
	}
	
	// Method for calculation of Grade
	char grade(int marks) {
	
		if (marks == 32 && flag1==1) {
			return ' ';						/* Blank space in Grade whenever there is mistake in 
												entering required details */
		}
		if (marks >= 90) {
			return 'S';
		} else if (marks <= 89 && marks >= 80) {
			return 'A';
		} else if (marks <= 79 && marks >= 70) {
			return 'B';
		} else if (marks <= 69 && marks >= 60) {
			return 'C';
		} else if (marks <= 59 && marks >= 50) {
			return 'D';
		} else if (marks <= 49 && marks >= 40) {
			return 'E';
		} else {
			return 'F';
		}
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int flag = 0;
		int ia1 = 0;
		int ia2 = 0;
		int ia3 = 0;
		int cta = 0;
		int see = 0;

		// Exception is Handled for all the above variables if the user enters String as any of the Details
		try {
			ia1 = Integer.parseInt(textfield1.getText());
		} catch (NumberFormatException ne1) {
			JOptionPane.showMessageDialog(null, "Invalid IA-1 Marks", "MESSAGE-ONLY NUMERICS",
					JOptionPane.ERROR_MESSAGE);
			textfield1.setText("");
			flag = 1;
		}
		try {
			ia2 = Integer.parseInt(textfield2.getText());
		} catch (NumberFormatException ne1) {
			JOptionPane.showMessageDialog(null, "Invalid IA-2 Marks", "MESSAGE-ONLY NUMERICS", 
					JOptionPane.ERROR_MESSAGE);
			textfield2.setText("");
			flag = 1;
		}
		try {
			ia3 = Integer.parseInt(textfield3.getText());
		} catch (NumberFormatException ne1) {
			JOptionPane.showMessageDialog(null, "Invalid IA-3 Marks", "MESSAGE-ONLY NUMERICS",
					JOptionPane.ERROR_MESSAGE);
			textfield3.setText("");
			flag = 1;
		}
		try {
			cta = Integer.parseInt(textfield4.getText());
		} catch (NumberFormatException ne1) {
			JOptionPane.showMessageDialog(null, "Invalid CTA Marks", "MESSAGE-ONLY NUMERICS", 
						JOptionPane.ERROR_MESSAGE);
			textfield4.setText("");
			flag = 1;
		}
		try {
			see = Integer.parseInt(textfield5.getText());
		} catch (NumberFormatException ne1) {
			JOptionPane.showMessageDialog(null, "Invalid SEE Marks", "MESSAGE-ONLY NUMERICS", 
						JOptionPane.ERROR_MESSAGE);
			textfield5.setText("");
			flag = 1;
		}

		// Exception is handled for all the above variables if the user enters the marks out of the range....
		if (ia1 > 20 || ia1 < 0) {
			try {
				throw new MarksHighException("Marks Out Of Range");
			} catch (MarksHighException mthe) {
				JOptionPane.showMessageDialog(null, "Given IA-1 Marks are Out Of Range(0-20)!!", "WARNING MESSAGE",
						JOptionPane.WARNING_MESSAGE);
				textfield1.setText("");
			}
		}
		if (ia2 > 20 || ia2 < 0) {
			try {
				throw new MarksHighException("Marks Out Of Range");
			} catch (MarksHighException mthe) {
				JOptionPane.showMessageDialog(null, "Given IA-2 Marks are Out Of Range(0-20)!!", "WARNING MESSAGE",
						JOptionPane.WARNING_MESSAGE);
				textfield2.setText("");
			}
		}
		if (ia3 > 20 || ia3 < 0) {
			try {
				throw new MarksHighException("Marks Out Of Range");
			} catch (MarksHighException mthe) {
				JOptionPane.showMessageDialog(null, "Given IA-3 Marks are Out Of Range(0-20)!!", "WARNING MESSAGE",
						JOptionPane.WARNING_MESSAGE);
				textfield3.setText("");
			}
		}
		if (cta < 0 || cta > 10) {
			try {
				throw new MarksHighException("Marks Out Of Range");
			} catch (MarksHighException mthe) {
				JOptionPane.showMessageDialog(null, "Given CTA Marks are Out Of Range(0-10)!!", "WARNING MESSAGE",
						JOptionPane.WARNING_MESSAGE);
				textfield4.setText("");
			}
		}
		if (see > 100 || see < 0) {
			try {
				throw new MarksHighException("Marks Out Of Range");
			} catch (MarksHighException mthe) {
				JOptionPane.showMessageDialog(null, "Given SEE Marks are Out Of Range(0-100)!!", "WARNING MESSAGE",
						JOptionPane.WARNING_MESSAGE);
				textfield5.setText("");
			}
		}
		
		if (flag == 1) {
			label6.setText("Total Marks: ");
			label7.setText("Grade: ");
		} else {
			if (cie(ia1, ia2, ia3, cta, see) == 32) {
				label6.setText("Total Marks: ");
			} else {
				label6.setText("Total Marks: " + cie(ia1, ia2, ia3, cta, see));
			}

			label7.setText("Grade: " + grade(marks));
		}
	}
}
public class StudentGUI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame=new Grading("STUDENT GRADING SYSTEM");		// Instantiating the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   /* Program should stop when ever clicked 
																	on 'X' button */
		frame.setBounds(550,200,420, 430);
		frame.setLayout(null);						
		frame.setVisible(true);									// Frame must be visible to user
						
	}

}
