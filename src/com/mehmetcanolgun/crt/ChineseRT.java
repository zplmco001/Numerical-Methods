package com.mehmetcanolgun.crt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ChineseRT extends JFrame{
	
	private JButton add, calculate, newW;
	private ArrayList <Panel> pnls = new ArrayList<Panel>();
	private ChineseRT crt = this;
	private int iter = 2, pos = 100, num, M=1, yPos=15;
	private int [] rms, mods, ms, y;
	private ArrayList<JLabel> table = new ArrayList<JLabel>(); 

	//CONSTRUCTOR METHOD TO INITIALIZE THE FRAME.
	public ChineseRT() {
		
		this.getContentPane().setLayout(null);
		
		newW = new JButton("New");
		newW.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ChineseRT();
				crt.setVisible(false);
			}
			
		});
		newW.setBounds(170, 115, 70, 20);
		
		calculate = new JButton("Calculate");
		calculate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Calculate();
			}
			
		});
		calculate.setBounds(170, 65, 70, 20);
		
		add = new JButton("Add");
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pnls.add(new Panel(200,40));
				pnls.get(iter).setBounds(0, pos, 200, 40);
				crt.add(pnls.get(iter));
				crt.repaint();
				iter++;
				pos += 50;
				
			}
			
		});
		add.setBounds(170, 15, 70, 20);
		
		pnls.add(new Panel(200,50));
		pnls.get(0).setBounds(0, 0, 200, 40);
		
		pnls.add(new Panel(200,40));
		pnls.get(1).setBounds(0, 50, 200, 40);
		
		this.add(add);
		this.add(calculate);
		this.add(newW);
		this.add(pnls.get(0));
		this.add(pnls.get(1));
		
		this.setSize(450, 400);
		this.setTitle("Chinese Remainder Calculator");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	//METHOD THAT CALCULATES THE CHINESE REMAINDER THEOREM
	public void Calculate() {
		
		//FOR THE THEOREM X=a1*m1*y1 + a2*m2*y2 + ... + an*mn*yn
		num=pnls.size();//NUMBER OF ENTERED X VALUES 
		rms = new int[num];//ARRAY FOR REMAINDERS
		mods = new int[num];//ARRAY FOR MODS
		ms = new int[num];//ARRAY FOR EACH m VALUES
		y = new int[num];//ARRAY FOR y VALUES
		
		//LOOP THAT GETS THE VALUES FROM TEXTFIELDS
		for(int i=0;i<num;i++) {
			rms[i]=Integer.parseInt(pnls.get(i).remain.getText());
			mods[i]=Integer.parseInt(pnls.get(i).modulus.getText());
		}
		
		//LOOP FOR FINDING M VALUE
		for(int i=0;i<num;i++) {
			M *= mods[i];
		}
		//LOOP FOR FINDING m VALUES
		for(int i=0;i<num;i++) {
			ms[i]= M/mods[i];
		}
		
		//LOOP FOR FINDING y VALUES
		for(int i=0;i<num;i++) {
			int mms;
			mms = ms[i]%mods[i];
			y[i]=1;
			while((mms*y[i])%mods[i]!=1) {
				y[i]++;
			}
		}
		
		//BELOW CODES APPLY THE FORMULA AND FIND RESULT
		int x=0;
		for(int i=0;i<num;i++) {
			x += (rms[i]*ms[i]*y[i]);
		}
		
		//LOOP FOR SHOWING TABLE OF VALUES
		for(int i=0;i<num;i++) {
			table.add(new JLabel("a"+(i+1)+" = "+rms[i]+"  m"+(i+1)+" = "+ms[i]+"  y"+(i+1)+" = "+y[i]));
			table.get(i).setBounds(270, yPos, 200, 20);
			crt.add(table.get(i));
			yPos += 50;
		}
		table.add(new JLabel("Result = "+x%M));
		table.get(table.size()-1).setBounds(270, yPos, 200, 20);
		crt.add(table.get(table.size()-1));
		
		add.setEnabled(false);
		calculate.setEnabled(false);
		crt.repaint();
		
		
	}

	public static void main(String[] args) {
		new ChineseRT();
	}
	
	class Panel extends JPanel{
		
		JLabel x ;
		JTextField remain ;
		JLabel mod ;
		JTextField modulus ;
		
		public Panel(int width, int height) {
			
			this.setSize(width, height);
			this.setLayout(null);
			
			x = new JLabel("X = ");
			x.setBounds(10, 10, 40, 30);
			x.setHorizontalTextPosition(SwingConstants.CENTER);
			this.add(x);
			
			remain = new JTextField();
			remain.setBounds(40, 15, 40, 20);
			this.add(remain);
			
			mod = new JLabel("mod");
			mod.setBounds(85, 10, 70, 30);
			mod.setHorizontalTextPosition(SwingConstants.CENTER);
			this.add(mod);
			
			modulus = new JTextField();
			modulus.setBounds(120, 15, 40, 20);
			this.add(modulus);
			
		}
	}
}
