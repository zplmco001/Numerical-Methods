package com.mehmetcanolgun.num;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class Roots extends JFrame{
	
	private JButton btn;
	private JCheckBox nwtn, scnt;
	private int state;
	private JFrame frame = this;

	public Roots() {
		
		this.getContentPane().setLayout(null);
		JLabel lbl = new JLabel("Please select the method you want to use.");
		lbl.setBounds(5, 5, 490, 20);
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lbl);
		
		btn = new JButton("Next");
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(state == 0) {
					JOptionPane.showMessageDialog(null, "Please select the method", "Select the method", 2);
				}else{
					if(state == 1) {
						new NewtonRaphson();
					}else if(state == 2) {
						new Secant();
					}
					frame.setVisible(false);
				}
			}
			
		});
		btn.setBounds(5, 430, 490, 40);
		this.add(btn);
		
		nwtn = new JCheckBox("Newton-Raphson Method");
		nwtn.setBounds(150, 180, 200, 23);
		nwtn.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(nwtn.isSelected()) {
					scnt.setSelected(false);
					state = 1;
				}
			}
			
		});
		this.add(nwtn);
		
		scnt = new JCheckBox("Secant Method");
		scnt.setBounds(150, 210, 200, 23);
		scnt.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(scnt.isSelected()) {
					nwtn.setSelected(false);
					state = 2;
				}
			}
			
		});
		this.add(scnt);
		
		this.setSize(500, 500);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	public static void main(String[] args) {
		new Roots();
	}
	
}
class NewtonRaphson extends JFrame{
	
	private JCheckBox iter, err;
	private JTextField func, initial, iteration;
	private JLabel derivative, answer;
	private JButton getAnswer, back, seeIter;
	private JFrame frame = this;
	private JTextPane iterations;
	private Func function;
	private double initialValue ,result;
	private int iterNum;
	private static String iters = "";
	
	public NewtonRaphson() {
		
		this.getContentPane().setLayout(null);
		
		derivative = new JLabel("Derivative:");
		derivative.setBounds(40,280,450,20);
		this.add(derivative);
		
		answer = new JLabel("Answer:");
		answer.setBounds(40, 330, 450, 20);
		this.add(answer);
		
		getAnswer = new JButton("Get Answer");
		getAnswer.setBounds(340, 420, 100, 20);
		getAnswer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!iter.isSelected()&&!err.isSelected()) {
					JOptionPane.showMessageDialog(null, "Please select a type of iteration", "Select the iteration method", 2);
				}
				try {
				function = new Func(func.getText());
				derivative.setText("Derivative: "+function.getDerivative());
				initialValue = Double.parseDouble(initial.getText());
				}
				catch (java.util.InputMismatchException error) {
					JOptionPane.showMessageDialog(null, "Please enter the essential values", "You have unvalid values", 2);
					return;
				}
				catch (java.lang.NumberFormatException error) {
					JOptionPane.showMessageDialog(null, "Please enter the essential values", "You have unvalid values", 2);
					return;
				}
				
				if(iter.isSelected()) {
					iterNum = Integer.parseInt(iteration.getText());
					result = Calculate(function,initialValue,iterNum);
					
				}else {
					result = Calculate(function,initialValue);
				}
				answer.setText("Answer: "+String.valueOf(result));
				System.out.println("asdas"+function.getDerivativeValue(2));
			}
			
		});
		this.add(getAnswer);
		
		back = new JButton("Back");
		back.setBounds(20,420,100,20);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Roots();
				frame.setVisible(false);
			}
			
		});
		this.add(back);
		
		seeIter = new JButton("See Iterations");
		seeIter.setBounds(180, 420, 100, 20);
		seeIter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				iterations.setText(iters);
				if(iterations.isVisible()) {
					iterations.setVisible(false);
				}else if(!iterations.isVisible()) {
					iterations.setVisible(true);
				}
				System.out.println(iters);
			}
			
		});
		this.add(seeIter);
		
		func = new JTextField("Enter the funtion");
		func.setBounds(30, 120, 200, 20);
		this.add(func);
		
		initial = new JTextField("Enter the initial value");
		initial.setBounds(280, 120, 200, 20);
		this.add(initial);
		
		iteration = new JTextField("Enter the number of iterations");
		iteration.setBounds(30,180,200,20);
		iteration.setEnabled(false);
		this.add(iteration);
		
		iterations = new JTextPane();
		iterations.setBounds(50, 50, 400, 300);
		iterations.setVisible(false);
		iterations.setEditable(false);
		iterations.setBackground(Color.WHITE);
		iterations.setBorder(BorderFactory.createLineBorder(Color.black));
		this.getContentPane().add(iterations);
		
		iter = new JCheckBox("Use Iteration");
		iter.setBounds(30, 20, 120, 23);
		iter.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(iter.isSelected()) {
					err.setSelected(false);
					iteration.setEnabled(true);
				}
			}
			
		});
		this.add(iter);
		
		err = new JCheckBox("Calculate until find acceptable error (<5%)");
		err.setBounds(180, 20, 300, 23);
		err.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(err.isSelected()) {
					iter.setSelected(false);
					iteration.setEnabled(false);
				}
			}
			
		});
		this.add(err);
		
		this.setVisible(true);
		this.setSize(500, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
	}
	
	public static double Calculate(Func func,double initial) {
		double result = 0;
		double error = 0;
		int counter = 1;
		result = initial - (func.getValue(initial)/func.getDerivativeValue(initial));
		error = Error(initial,result);
		iters = iters + "x"+counter+" = "+initial+" x"+(counter+1)+" = "+result+" Error = "+error+"% "+"\n";
		System.out.println(iters);
		
		System.out.println("asfdasf"+error);
		counter++;
		if(error < 5) {
			return result;
		}else {
			
			result = Calculate(func,result);
		}
		return result;
		
	}
	
	public static double Calculate(Func func, double initial, int iter) {
		double result = 0;
		double error = 0;
		int counter = 1;
		for(int i=0;i<iter;i++) {
			result = initial - (func.getValue(initial)/func.getDerivativeValue(initial));
			error = Error(initial,result);
			iters = iters + "x"+counter+" = "+initial+" x"+(counter+1)+" = "+result+" Error = "+error+"% "+"\n";
			initial = result;
			counter++;
		}
		return result;
	}
	
	public static double Error(double x1,double x2) {
		double result = 0;
		result = Math.abs(((x2 - x1)/x2)*100);
		return result;
	}
	
}
class Secant extends JFrame{
	
	private JCheckBox iter, err;
	private JTextField func, initial1, initial2, iteration;
	private JLabel answer;
	private JButton getAnswer, back, seeIter;
	private JFrame frame = this;
	private JTextPane iterations;
	private Func function;
	private double init1, init2;
	private int iterate;
	private static String iters = "";
	
	public Secant() {
		
		this.getContentPane().setLayout(null);
		
		answer = new JLabel("Answer:");
		answer.setBounds(40, 320, 400, 20);
		this.add(answer);
		
		getAnswer = new JButton("Get Answer");
		getAnswer.setBounds(340, 420, 100, 20);
		getAnswer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!iter.isSelected()&&!err.isSelected()) {
					JOptionPane.showMessageDialog(null, "Please select a type of iteration", "Select the iteration method", 2);
				}
				try {
				function = new Func(func.getText());
				init1 = Double.parseDouble(initial1.getText());
				init2 = Double.parseDouble(initial2.getText());
				}
				catch (java.util.InputMismatchException error) {
					JOptionPane.showMessageDialog(null, "Please enter the essential values", "You have unvalid values", 2);
					return;
				}
				catch (java.lang.NumberFormatException error) {
					JOptionPane.showMessageDialog(null, "Please enter the essential values", "You have unvalid values", 2);
					return;
				}
				
				double result;
				if(iter.isSelected()) {
					iterate = Integer.parseInt(iteration.getText());
					result = Calculate(function,init1,init2,iterate);
				}else {
					result = Calculate(function, init1,init2);
				}
				answer.setText("Answer: "+result);
			}
			
		});
		this.add(getAnswer);
		
		back = new JButton("Back");
		back.setBounds(20,420,100,20);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Roots();
				frame.setVisible(false);
			}
			
		});
		this.add(back);
		
		iterations = new JTextPane();
		iterations.setBounds(50, 50, 400, 300);
		iterations.setVisible(false);
		iterations.setEditable(false);
		iterations.setBackground(Color.WHITE);
		iterations.setBorder(BorderFactory.createLineBorder(Color.black));
		this.getContentPane().add(iterations);
		
		seeIter = new JButton("See Iterations");
		seeIter.setBounds(180, 420, 100, 20);
		seeIter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				iterations.setText(iters);
				if(iterations.isVisible()) {
					iterations.setVisible(false);
				}else if(!iterations.isVisible()) {
					iterations.setVisible(true);
				}
				System.out.println(iters);
			}
			
		});
		this.add(seeIter);
		
		func = new JTextField("Enter the funtion");
		func.setBounds(30, 120, 200, 20);
		this.add(func);
		
		initial1 = new JTextField("Enter the first initial value");
		initial1.setBounds(280, 120, 200, 20);
		this.add(initial1);
		
		initial2 = new JTextField("Enter the second initial value");
		initial2.setBounds(280, 180, 200, 20);
		this.add(initial2);
		
		iteration = new JTextField("Enter the number of iterations");
		iteration.setBounds(30,180,200,20);
		iteration.setEnabled(false);
		this.add(iteration);
		
		iter = new JCheckBox("Use Iteration");
		iter.setBounds(30, 20, 120, 23);
		iter.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(iter.isSelected()) {
					err.setSelected(false);
					iteration.setEnabled(true);
				}
			}
			
		});
		this.add(iter);
		
		err = new JCheckBox("Calculate until find acceptable error (<5%)");
		err.setBounds(180, 20, 300, 23);
		err.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(err.isSelected()) {
					iter.setSelected(false);
					iteration.setEnabled(false);
				}
			}
			
		});
		this.add(err);
		
		this.setVisible(true);
		this.setSize(500, 500);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static double Calculate(Func function,double iter1, double iter2) {
		double result = 0;
		double error  = 0;
		int counter = 1;
		result = iter2 - (function.getValue(iter2)*(iter2-iter1)/
				(function.getValue(iter2)-function.getValue(iter1)));
		error = Error(result,iter2);
		iters = iters + "x"+counter+" = "+iter2+" x"+(counter+1)+" = "+result+" Error = "+error+"% "+"\n";
		counter++;
		if(error<5) {
			return result;
		}else {
			result = Calculate(function,iter2,result);
		}
		return result;
	}
	
	public static double Calculate(Func func, double initial1, double initial2, int iter) {
		double result = 0;
		double error = 0;
		int counter = 1;
		for(int i=0;i<iter;i++) {
			result = initial2 - (func.getValue(initial2)*(initial2-initial1)/
					(func.getValue(initial2)-func.getValue(initial1)));
			error = Error(initial2,result);
			iters = iters + "x"+counter+" = "+initial2+" x"+(counter+1)+" = "+result+" Error = "+error+"% "+"\n";
			initial1 = initial2;
			initial2 = result;
			counter++;
		}
		return result;
	}
	
	public static double Error(double x1,double x2) {
		double result = 0;
		result = Math.abs(((x2 - x1)/x2)*100);
		return result;
	}
	
}
class Func{
	
	private String function;
	private String func[];
	private char chr[];
	private ArrayList<Character> chrar = new ArrayList<Character>();
	double power[];
	double coefficient[];
	
	Func(String function){
		this.function = function;
		chr = function.toCharArray();
		for(int i=0;i<chr.length;i++) {
			chrar.add(i,chr[i]);
		}
		if(chrar.get(0)=='x') {
			chrar.add(0,'1');
		}
		for(int i=0;i<chrar.size();i++) {
			if(chrar.get(i)=='-'||chrar.get(i)=='+') {
				if(chrar.get(i+1)=='x') {
					chrar.add(i+1,'1');
				}
			}
			
			if(chrar.get(i)=='-') {
				chrar.add(i,'+');
				i++;
			}
			
			if(chrar.get(i)=='x') {
				if(i+1 != chrar.size()) {
					if(chrar.get(i+1)!='^') {
						chrar.add(i+1,'^');
						chrar.add(i+2,'1');
					}
				}else {
					chrar.add(i+1,'^');
					chrar.add(i+2,'1');
				}
				
				
				if(i!=0) {
					if(chrar.get(i-1)=='-'||chrar.get(i-1)=='+') {
						chrar.add(i-1,'1');
					}
				}
			}
		}
		
		chr = new char[chrar.size()];
		for(int i=0;i<chr.length;i++) {
			chr[i] = chrar.get(i);
		}
		
		function = new String(chr);
		func = function.split("[+]");
		
		for(int i=0;i<func.length;i++) {
			
			if( !(func[i].contains("x")) ) {
				
				func[i] = func[i]+"x^0";
		
			}
		}
		
		System.out.println(function);
		coefficient = new double[func.length];
		power = new double[func.length];
		Scanner scn;
		
		for(int i=0;i<func.length;i++) {
			
			scn = new Scanner(func[i]);
			scn.useDelimiter("\\s*x\\^\\s*");
			coefficient[i]=scn.nextDouble();
			System.out.println(coefficient[i]);
			power[i] = scn.nextDouble();
			System.out.println(power[i]);
	
		}
		
	}
	
	public double[] derivate() {
		
		double deri[] = new double[coefficient.length*2];
		for(int i=0;i<deri.length;i=i+2) {
			
			deri[i]=coefficient[i/2]*power[i/2];
			deri[i+1]=power[i/2]-1;
			
		}
		return deri;
		
	}
	
	public String getDerivative() {
		
		double deri[]=this.derivate();
		String derivative = "";
		
		for(int i=0;i<deri.length;i=i+2) {
			if(deri[i]!=0.0) {
				if(deri[i+1]==0.0) {
					derivative = derivative+deri[i]+" + ";
				}
				else if(deri[i+1]==1.0) {
					derivative = derivative+deri[i]+"*x+";
				}
				else {
					derivative = derivative+deri[i]+"*x^"+deri[i+1]+" + ";
				}
			}
			
		}
		derivative = derivative.substring(0, derivative.length()-1);
		return derivative;
	}
	
	public double getDerivativeValue(double x) {
		
		double deri[]=this.derivate();
		double result=0;
		for(int i=0;i<deri.length;i=i+2) {
			
			result = result + (Math.pow(x, deri[i+1])*deri[i]);
			
		}
		return result;
	}
	
	public double getValue(double x) {
		
		double result = 0;
		for(int i=0;i<coefficient.length;i++) {
			
			result = result + (Math.pow(x, power[i])*coefficient[i]);
			
		}
		return result;
	}
	
}
