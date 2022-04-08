package com.andre.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andre.model.SummaryVO;
import com.andre.service.SummaryService;
import com.andre.service.WriteCommentService;

@Controller
@RequestMapping("summarys")
public class SummaryController {
	
	private static final Logger logger = Logger.getLogger(SummaryController.class);
	
	@Autowired
	private WriteCommentService writeCommentService;
	
	@Autowired
	private SummaryService summaryService;
	
	
	private static final String SUCCESS = "SUCCESS";
	
	private static final String ERROR = "ERROR";
	
	
	private static final String DATA ="data";
	
	private static final String MSG = "msg";
	
	private Map<String,Object> respMap;
	
	@GetMapping
	public @ResponseBody Map<String, Object> getAllSummarys(){
		respMap = new HashMap<>();
		
		try {
			respMap.put(DATA,summaryService.getAllSummary());
			respMap.put(MSG, SUCCESS);
		} catch (Exception e) {
			respMap.put(DATA,new ArrayList<>());
			setErrorMsg();
			logger.error(e);
		}
		
		return respMap;
		
	}
	
	private static final String STATUS= "status";
	
	@PostMapping("/updateSummary.do")
	public @ResponseBody Map<String, Object> updateASummary(@RequestBody SummaryVO summary){
		respMap = new HashMap<>();
		
		int status = 0;
		
		try {
			status = summaryService.updateSummary(summary);
		} catch (Exception e) {
			logger.error(e);
		}
		
		respMap.put(STATUS,status);
		
		if(status == 1) {
			respMap.put(MSG, SUCCESS);
		} else {
			setErrorMsg();
		}
		
		return respMap;
		
	}
	
	@PostMapping
	public @ResponseBody Map<String, Object> addASummary(@RequestBody SummaryVO summary){
		respMap = new HashMap<>();
		
		int status = 0;
		
		try {
			status = summaryService.addSummary(summary);
		} catch (Exception e) {
			logger.error(e);
		}
		
		respMap.put(STATUS,status);
		
		if(status == 1) {
			respMap.put(MSG, SUCCESS);
		} else {
			setErrorMsg();
		}
		
		return respMap;
		
	}
	
	private void setErrorMsg() {
		String validationMsg = summaryService.getValidationMsg();
		if(validationMsg != null) respMap.put(MSG, validationMsg);
		else respMap.put(MSG, ERROR);
		
		summaryService.setValidationMsg(null);
	}
	
	@DeleteMapping
	public @ResponseBody Map<String, Object> deleteASummary(@RequestParam("id") String id){
		respMap = new HashMap<>();
		SummaryVO summary = new SummaryVO();
		summary.setId(id);
		
		int status = 0;
		
		try {
			status = summaryService.deleteSummary(summary);
		} catch (Exception e) {
			logger.error(e);
		}
		
		respMap.put(STATUS,status);
		
		if(status == 1) {
			respMap.put(MSG, SUCCESS);
		} else {
			setErrorMsg();
		}
		
		return respMap;
		
	}
	
	@PostMapping("/writeAComment.do")
	public @ResponseBody Map<String, Object> writeAComment(@RequestBody List<SummaryVO> summarys){
		respMap = new HashMap<>();
		
		try {
			respMap.put(DATA,summarys);
			respMap.put("comment", writeCommentService.writeComment(summarys));
			respMap.put(MSG, SUCCESS);
		} catch (Exception e) {
			respMap.put(DATA,new ArrayList<>());
			setErrorMsg();
			respMap.put("comment", "");
			logger.error(e);
		}
		
		return respMap;
		
	}
	
	

}