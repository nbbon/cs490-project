package mum.pmp.mstore;

public class Tst {
	public static void main(String[] args) {
		// int randomPIN = (int)(Math.random()*9000)+1000;
		for (int i = 0; i < 1000; i++) {
			int randomPIN = (int) (Math.random() * 9000) + 1000;
			System.out.println(randomPIN);
		}
	}
}
