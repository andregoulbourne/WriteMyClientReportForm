package com.andre.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.andre.model.SummaryVO;

@Service
public class WriteCommentService {
	
	public String writeComment(List<SummaryVO> list) {
		StringBuilder rs = new StringBuilder();
		for(int i=0; i<list.size();i++) {
			SummaryVO object = list.get(i);
			String summary= "On a scale from 1-3, "+object.getStudent()+" confidence level with the material was at a "+object.getStatus()+" at the beginning of the session.\r\n"
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
