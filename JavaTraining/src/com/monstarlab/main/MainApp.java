package com.monstarlab.main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainApp {

	public static void main(String[] args){
		Scanner scannerIn = new Scanner(System.in);
		int x = 0;
		while(true) {
			System.out.print("Enter a number: ");

			try{
			x = scannerIn.nextInt();
			if(!(x <2 || x %2 == 0)){
				break;
			}
			} catch(InputMismatchException e){
				scannerIn.next();
			}
		}
		String tempString;
		do{
		System.out.print("Enter a String with x,y,z: ");
		 tempString= scannerIn.next();
		 tempString = tempString.toLowerCase();
		}while(!(tempString.contains("x") || tempString.contains("y")  || tempString.contains("z")));
		Printer printer = new Printer();
		printer.printAll(tempString, x);
	}

}
