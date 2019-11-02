package com.mehmetcanolgun.crt;

import java.util.Scanner;

public class CRT {
	
	public static void main(String [] args) {
		
		int num, M=1;
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the number of values");
		num = scn.nextInt();
		
		int [] rms = new int[num];
		System.out.println("Enter the remainder values");
		for(int i=0;i<num;i++) {
			rms[i]=scn.nextInt();
		}
		
		int [] mods = new int[num];
		System.out.println("Enter the mod values");
		for(int i=0;i<num;i++) {
			mods[i]=scn.nextInt();
		}
		
		for(int i=0;i<num;i++) {
			System.out.println("X="+rms[i]+"mod"+mods[i]);
		}
		
		for(int i=0;i<num;i++) {
			M *= mods[i];
		}
		
		int ms []=new int[num];
		
		for(int i=0;i<num;i++) {
			ms[i]= M/mods[i];
		}
		
		int [] y = new int[num];
		
		for(int i=0;i<num;i++) {
			int mms;
			mms = ms[i]%mods[i];
			y[i]=1;
			while((mms*y[i])%mods[i]!=1) {
				y[i]++;
			}
			System.out.println(y[i]);
		}
		int x=0;
		for(int i=0;i<num;i++) {
			System.out.println("kalan "+rms[i]);
			System.out.println("mod "+ms[i]);
			x += (rms[i]*ms[i]*y[i]);
		}
		System.out.println("X="+x%M+"mod"+M);
		System.out.println(x);
		
		
		scn.close();
	}

}
