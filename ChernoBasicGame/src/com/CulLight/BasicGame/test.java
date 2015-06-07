package com.CulLight.BasicGame;

public class test {

	public static void main(String[] args) {


		for (int i = 0; i < 19; i++) {
			System.out.println(Integer.toBinaryString(i) + "   " + (i & 8));
		}
	}

}
