package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		List<Product> list = new ArrayList<>();

		// Source file
		System.out.println("Enter file path: ");
		String sourceFilePath = sc.nextLine();
		File sourceFile = new File(sourceFilePath);
		
		// Creating folder for the new file and getting its path
		String sourceFolderPath = sourceFile.getParent();
		boolean newFolder = new File(sourceFolderPath + "\\out").mkdir();
		String newFilePath = sourceFolderPath + "\\out\\summary.csv";
		

		try (BufferedReader br = new BufferedReader(new FileReader(sourceFilePath))) {

			String line = br.readLine();

			while (line != null) {

				String[] array = line.split(",");
				String name = array[0];
				Double price = Double.parseDouble(array[1]);
				Integer quantity = Integer.parseInt(array[2]);

				list.add(new Product(name, price, quantity));

				line = br.readLine();
			}

			try (BufferedWriter bw = new BufferedWriter(new FileWriter(newFilePath))) {

				for (Product p : list) {
					bw.write(p.getName() + String.format(", %.2f", p.total()));
					bw.newLine();
				}
				System.out.println(newFilePath + "created");
			} catch (IOException e) {
				System.out.println("Error: " + e.getMessage());
			}

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		sc.close();

	}

}