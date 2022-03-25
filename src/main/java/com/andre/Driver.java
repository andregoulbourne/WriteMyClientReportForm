package com.andre;

import com.andre.dao.ReadCsv;
import com.andre.service.WriteCommentService;

public class Driver {
	
	static ReadCsv reader = new ReadCsv();
	
	static WriteCommentService service = new WriteCommentService();

	public static void main(String[] args) {
		System.out.println("Hello World");
		
		reader.setFileInPath("./src/main/resources/Summarys.csv");
		
		var list = reader.readCSVFile();
		
		System.out.println("-------------------------- Result");
		System.out.println(service.writeComment(list));
		
	}

}
