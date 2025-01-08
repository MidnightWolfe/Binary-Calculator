package v0;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class test {

	public static void main(String arg[]) throws FileNotFoundException{
		Calculator cal = new Calculator();
		cal.run(arg[0]);
		

	}
}
