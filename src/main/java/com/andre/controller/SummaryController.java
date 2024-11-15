package com.andre.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.andre.constants.Constants;
import com.andre.exceptions.ArraysAreNotTheSameSizeException;
import com.andre.model.SummaryVO;
import com.andre.service.SummaryService;
import com.andre.service.WriteCommentService;
import com.andre.template.ControllerTemplate;

@RestController
@RequestMapping("summarys")
public class SummaryController extends ControllerTemplate {

	private static final Logger logger = LoggerFactory.getLogger(SummaryController.class);
	
	@Autowired
	public SummaryController(WriteCommentService writeCommentService, SummaryService summaryService) {
		this.writeCommentService=writeCommentService;
		this.summaryService=summaryService;
	}
	
	private final WriteCommentService writeCommentService;
	
	private final SummaryService summaryService;
	
	@GetMapping
	public Map<String, Object> getAllSummarys(){
		respMap = new HashMap<>();
		
		String[] keys = {Constants.DATA,Constants.MSG};
		
		Object[] values = {summaryService.getAllSummary(),Constants.SUCCESS}; 
		
		try {
			putEntriesIntoTheResponseMap(keys,values);
		} catch (ArraysAreNotTheSameSizeException e) {
			logger.error(Constants.EXCEPTION, e);
		}
		
		return respMap;
		
	}
	
	@PostMapping("/updateSummary.do")
	public Map<String, Object> updateASummary(@RequestBody SummaryVO summary){
		respMap = new HashMap<>();
		
		var status = summaryService.updateSummary(summary);
		
		respMap.put(Constants.STATUS,status);
		
		if(status == 1) {
			respMap.put(Constants.MSG, Constants.SUCCESS);
		} else {
			setErrorMsg();
		}
		
		return respMap;
		
	}
	
	@PostMapping
	public Map<String, Object> addASummary(@RequestBody SummaryVO summary){
		respMap = new HashMap<>();
		
		int status = summaryService.addSummary(summary);
		
		respMap.put(Constants.STATUS,status);
		
		if(status == 1) {
			respMap.put(Constants.MSG, Constants.SUCCESS);
		} else {
			setErrorMsg();
		}
		
		return respMap;
		
	}
	
	private void setErrorMsg() {
		var validationMsg = summaryService.getValidationMsg();
        respMap.put(Constants.MSG, Objects.requireNonNullElse(validationMsg, Constants.ERROR));
		
		summaryService.setValidationMsg(null);
	}
	
	@PostMapping("/deleteSummary.do")
	public Map<String, Object> deleteASummary(@RequestParam("id") String id){
		respMap = new HashMap<>();
		var summary = new SummaryVO();
		summary.setId(id);
		
		
		var status = summaryService.deleteSummary(summary);
		
		respMap.put(Constants.STATUS,status);
		
		if(status == 1) {
			respMap.put(Constants.MSG, Constants.SUCCESS);
		} else {
			setErrorMsg();
		}
		
		return respMap;
		
	}
	
	@PostMapping("/writeAComment.do")
	public Map<String, Object> writeAComment(@RequestBody List<SummaryVO> summarys){
		respMap = new HashMap<>();
		
		
		String[] keys = {Constants.DATA,"comment",Constants.MSG};
		
		Object[] values = {summarys,writeCommentService.writeComment(summarys),Constants.SUCCESS};
		
		try {
			putEntriesIntoTheResponseMap(keys,values);
		} catch (ArraysAreNotTheSameSizeException e) {
			logger.error(Constants.EXCEPTION, e);
		}
		
		return respMap;
		
	}
	
	

}
