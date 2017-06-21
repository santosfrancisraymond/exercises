package com.monstarlab.junit;

public class LetterX extends BaseLetter implements LetterDrawable {

	public LetterX(Integer size) {
		this.setSize(size);
	}

	@Override
	public String draw() {
		
		String returnString =  super.draw();;

		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if (row == col || row + col == size - 1) {
					//System.out.print("X");
					returnString.concat("X");
				} else {
					returnString.concat(" ");
					//System.out.print(" ");
				}
			}
			returnString.concat("\n");
			//System.out.println();
		}
		
		System.out.println(returnString);
		return returnString;

	}

}
