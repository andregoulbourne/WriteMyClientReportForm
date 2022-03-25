package com.andre;

import com.andre.dao.ReadCsv;

public class Driver {
	
	static ReadCsv reader = new ReadCsv();

	public static void main(String[] args) {
		System.out.println("Hello World");
		
		reader.readCSVFile();
		
	}

}
