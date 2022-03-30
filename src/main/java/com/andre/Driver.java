package com.andre;

import com.andre.dao.SummaryDao;
import com.andre.model.SummaryObjectVO;
import com.andre.service.WriteCommentService;

public class Driver {
	
	static SummaryDao reader = new SummaryDao();
	
	static WriteCommentService service = new WriteCommentService();

	public static void main(String[] args) {
		System.out.println("Hello World");
		
		reader.setFileInPath("./src/main/resources/Summarys.csv");
		
		var o = new SummaryObjectVO();
		o.setStudent("Mike");
		
		reader.updateCSVFile("1",o);
		
		var list = reader.readCSVFile();
		
		System.out.println("-------------------------- Result");
		System.out.println(service.writeComment(list));
		
	}

}
