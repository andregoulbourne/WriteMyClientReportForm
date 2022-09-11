package com.andre.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andre.constants.Constants;
import com.andre.exceptions.ArraysAreNotTheSameSizeException;
import com.andre.model.SummaryVO;
import com.andre.service.SummaryService;
import com.andre.service.WriteCommentService;
import com.andre.template.ControllerTemplate;

@Controller
@RequestMapping("summarys")
public class SummaryController extends ControllerTemplate {

	private static final Logger logger = Logger.getLogger(SummaryController.class);
	
	@Autowired
	private WriteCommentService writeCommentService;
	
	@Autowired
	private SummaryService summaryService;
	
	@GetMapping
	public @ResponseBody Map<String, Object> getAllSummarys(){
		respMap = new HashMap<>();
		
		String[] keys = {Constants.DATA,Constants.MSG};
		
		Object[] values = {summaryService.getAllSummary(),Constants.SUCCESS}; 
		
		try {
			putEntriesIntoTheResponseMap(keys,values);
		} catch (ArraysAreNotTheSameSizeException e) {
			logger.error(e);
		}
		
		return respMap;
		
	}
	
	@PostMapping("/updateSummary.do")
	public @ResponseBody Map<String, Object> updateASummary(@RequestBody SummaryVO summary){
		respMap = new HashMap<>();
		
		int status = summaryService.updateSummary(summary);
		
		respMap.put(Constants.STATUS,status);
		
		if(status == 1) {
			respMap.put(Constants.MSG, Constants.SUCCESS);
		} else {
			setErrorMsg();
		}
		
		return respMap;
		
	}
	
	@PostMapping
	public @ResponseBody Map<String, Object> addASummary(@RequestBody SummaryVO summary){
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
		String validationMsg = summaryService.getValidationMsg();
		if(validationMsg != null) respMap.put(Constants.MSG, validationMsg);
		else respMap.put(Constants.MSG, Constants.ERROR);
		
		summaryService.setValidationMsg(null);
	}
	
	@PostMapping("/deleteSummary.do")
	public @ResponseBody Map<String, Object> deleteASummary(@RequestParam("id") String id){
		respMap = new HashMap<>();
		SummaryVO summary = new SummaryVO();
		summary.setId(id);
		
		
		int status = summaryService.deleteSummary(summary);
		
		respMap.put(Constants.STATUS,status);
		
		if(status == 1) {
			respMap.put(Constants.MSG, Constants.SUCCESS);
		} else {
			setErrorMsg();
		}
		
		return respMap;
		
	}
	
	@PostMapping("/writeAComment.do")
	public @ResponseBody Map<String, Object> writeAComment(@RequestBody List<SummaryVO> summarys){
		respMap = new HashMap<>();
		
		
		String[] keys = {Constants.DATA,"comment",Constants.MSG};
		
		Object[] values = {summarys,writeCommentService.writeComment(summarys),Constants.SUCCESS}; 
		
		try {
			putEntriesIntoTheResponseMap(keys,values);
		} catch (ArraysAreNotTheSameSizeException e) {
			logger.error(e);
		}
		
		return respMap;
		
	}
	
	

}
