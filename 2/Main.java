import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		String a;
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter the text to echo: ");
		a = scanner.nextLine();
		scanner.close();
		
		System.out.println(a);
		
	}

}