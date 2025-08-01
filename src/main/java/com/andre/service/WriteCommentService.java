package com.andre.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.andre.constants.Constants;
import com.andre.model.SummaryVO;

/**
 * WriteCommentService is responsible for generating comments based on a list of SummaryVO objects.
 * It formats the comments according to a predefined template and logs the process.
 */

@Service
public class WriteCommentService {
	
	private static final Logger logger = LoggerFactory.getLogger(WriteCommentService.class);
	
	public String writeComment(List<SummaryVO> list) {
		try {
			StringBuilder rs = new StringBuilder();
            for (SummaryVO object : list) {
                var summary = """
                        On a scale from 1-3, %s confidence level with the material was at a %s at the beginning of the session.

                        In the session we worked on finding %s.

                        After the session %s said %s was feeling more confident with the material then prior.

                        Recommendations: %s.

                        """.formatted(object.getStudent(), object.getStatus(), object.getCoveredValue(), object.getStudent(), object.getGender(), object.getRecommendation());
                rs.append(summary);
                logger.info("Comment written Successfully ...");
            }
			return rs.toString();
		} catch (Exception e) {
			logger.error(Constants.EXCEPTION, e);
			return "";
		}
	}

	
}
