package app;

public class App {
	public static void main(String[] args) {
		C1 c1 = new C1();
		C2 c2 = new C2();
		C3 c3 = new C3();
		C2 c4 = new C2();
		
		c2 = (C2)c4;
	}
}