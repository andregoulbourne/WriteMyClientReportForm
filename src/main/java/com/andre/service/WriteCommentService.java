package com.andre.service;

import java.util.List;

import com.andre.model.SummaryVO;

public class WriteCommentService {
	
	public String writeComment(List<SummaryVO> list) {
		var rs = new StringBuilder();
		for(var i=0; i<list.size();i++) {
			var object = list.get(i);
			var summary= "On a scale from 1-3, "+object.getStudent()+" confidence level with the material was at a "+object.getStatus()+" at the beginning of the session.\r\n"
					+ "\r\n"
					+ "In the session we worked on finding "+object.getCoveredValue()+".\r\n"
					+ "\r\n"
					+ "After the session "+object.getStudent()+" said "+object.getGender()+" was feeling more confident with the material then prior.\r\n"
					+ "\r\n"
					+ "Recommendations: "+object.getRecomendation()+"."
					+ "\r\n"
					+ "\r\n";
			rs.append(summary);
		}
		return rs.toString();
	}

	
}
