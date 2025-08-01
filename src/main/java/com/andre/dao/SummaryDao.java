package com.andre.dao;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.andre.constants.Constants;
import com.andre.model.SummaryVO;

/**
 * SummaryDao is responsible for reading and writing SummaryVO objects to a CSV file.
 * It provides methods to read all entries, read a single entry by id, and update/add/delete entries.
 * The CSV file is expected to have a specific format with headers.
 */

@Repository
public class SummaryDao{
	
	private static final Logger logger = LoggerFactory.getLogger(SummaryDao.class);
	
	private String fileInPath;

	private String validationMsg;

	/**
	 * Reads a CSV file and returns a list of SummaryVO objects.
	 * If 'id' is provided, it returns a single entry matching that id if 'type' is SINGLE.
	 * If 'type' is ALL, it returns all entries in the CSV file.
	 *
	 * @param id   the id to filter by (can be null)
	 * @param type the type of operation (ALL or SINGLE)
	 * @return a list of SummaryVO objects
	 */
	public List<SummaryVO> readCSVFile(String id, String type) {

		try (Reader reader = Files.newBufferedReader(Path.of(fileInPath)); CSVReader csvReader = new CSVReader(reader)) {
			var rs = new ArrayList<SummaryVO>();

			String[] line;
			var firstRow = true;
			while ((line = csvReader.readNext()) != null) {
				if(!firstRow) {
					var valuesList = Arrays.asList(line);
					logger.info(Constants.CURRENT_LINE, Arrays.asList(line));

					var object = listToObject(valuesList);

						if(type.equals(Constants.ALL))
							rs.add(object);
						else if(type.equals(Constants.SINGLE) && object.getId().equals(id)) {
							rs.add(object);
							return rs;
						}

				}
				else
					firstRow = false;
				
			}
			return rs;
		} catch(Exception e) {
			logger.error(Constants.EXCEPTION, e);
			if(id==null) logger.error("id is null");
		}
		return new ArrayList<>();
	}
	
	/**
	 * Reads a CSV file and returns a single SummaryVO object based on the provided id.
	 * This method is used when the type is SINGLE.
	 *
	 * @param id the id of the entry to retrieve
	 * @return a SummaryVO object corresponding to the given id
	 */
	public SummaryVO readCSVFileSingleEntry(String id) {
		try {
			return readCSVFile(id, Constants.SINGLE).get(0);
		} catch(Exception e) {
			logger.error(Constants.EXCEPTION, e);
		}
		return new SummaryVO();
	}
	/**
	 * Updates, adds, or deletes a SummaryVO entry in the CSV file based on the provided type.
	 * Returns the number of entries updated, added, or deleted.
	 *
	 * @param o    the {@link SummaryVO summaryVo} object to process
	 * @param type the type of operation (ADD, UPDATE, DELETE)
	 * @return the number of entries affected by the operation
	 */
	public int csvFileEntry(SummaryVO o, String type) {
		List<SummaryVO> rs = null;
		if(o==null) return 0;
		if(type.equals(Constants.UPDATE))
			rs = updateCSVFilePart1(o, Constants.UPDATE);
		if(type.equals(Constants.ADD))
			 rs = updateCSVFilePart1(o, Constants.ADD);
		if(type.equals(Constants.DELETE)) {
			 rs = updateCSVFilePart1(o, Constants.DELETE);
			 if(readCSVFile(null,Constants.ALL).size() == rs.size()) return 0;
		}
		if(rs==null || rs.isEmpty()) return 0;
		return updateCSVPart2(rs);
	}
	
	// Keep all the objects plus one more to be added in the result: Add an example
	private List<SummaryVO> updateCSVFilePart1(SummaryVO o, String type) {
		var id = o.getId();
		if(id==null) return new ArrayList<>();

		try (Reader reader = Files.newBufferedReader(Path.of(fileInPath)); CSVReader csvReader = new CSVReader(reader)) {
			var rs = new ArrayList<SummaryVO>();

			String[] line;
			var firstRow = true;
			while ((line = csvReader.readNext()) != null) {
					if(!firstRow) {
						var valuesList = Arrays.asList(line);
						logger.info(Constants.CURRENT_LINE, valuesList);

						SummaryVO object = listToObject(valuesList);
							if(type.equals(Constants.UPDATE)) {
								processUpdateSummaryVO(object, o, id, valuesList, rs);
							} else if(type.equals(Constants.ADD)) {
								processAddSummaryVO(object, id, valuesList, rs);
							} else if(type.equals(Constants.DELETE) && !object.getId().equals(id)) {
								rs.add(object);
							}

						} else
							firstRow = false;

				}
			if(type.equals(Constants.ADD)) rs.add(o);
			return rs;
		} catch(Exception e) {
			logger.error(Constants.EXCEPTION, e);
			if(fileInPath==null)
				logger.error("dependency missing");
		}
		return new ArrayList<>();
	}

	private void processUpdateSummaryVO(SummaryVO object, SummaryVO o, String id, List<String> currentValues, List<SummaryVO> rs){
		if(object.getId().equals(id)) {
			logger.info(Constants.CURRENT_LINE, currentValues);
			SummaryVO rsObject = updateObjectNullFields(object, o);
			rs.add(rsObject);
		} else {
			rs.add(object);
		}
	}

	private void processAddSummaryVO(SummaryVO object, String id, List<String> currentValues, List<SummaryVO> rs){
		if(object.getId().equals(id)) {
			logger.info(Constants.CURRENT_LINE, currentValues);
			validationMsg = "Summary already exist ...";
			logger.info(validationMsg);
			throw new IllegalStateException(validationMsg);
		} else {
			rs.add(object);
		}
	}
	
	private String[] translateSummaryVOObjectToStringArray(SummaryVO o) {
		return objectToStringArray(o);
	}
	
	private SummaryVO updateObjectNullFields(SummaryVO existing, SummaryVO rs) {
		if(rs.getId()==null) rs.setId(existing.getId());
		if(rs.getCoveredValue()==null) rs.setCoveredValue(existing.getCoveredValue());
		if(rs.getGender()==null) rs.setGender(existing.getGender());
		if(rs.getRecommendation()==null) rs.setRecommendation(existing.getRecommendation());
		if(rs.getStatus()==null) rs.setStatus(existing.getStatus());
		if(rs.getStudent()==null) rs.setStudent(existing.getStudent());
		logger.info("new object {}", rs);
		return rs;
	}
	
	private int updateCSVPart2(List<SummaryVO> list) {
		List<String[]> rs = new ArrayList<>();

		var header = "ID,STUDENT_NAME,STATUS,MADE_A_DIFFERENCE,COVERED_VALUE,RECOMMENDATION,GENDER".split(",");
		rs.add(header);

		for(SummaryVO o: list) {
			var entryArray= translateSummaryVOObjectToStringArray(o);
			if(entryArray.length != 0)
					rs.add(entryArray);
		}

		return writeLineByLine(rs, Path.of(fileInPath));
	}


	private int writeLineByLine(List<String[]> lines, Path path) {
		var result = 0;

		try (CSVWriter writer = new CSVWriter(new FileWriter(path.toString()))) {
			for (String[] line : lines) {
				writer.writeNext(line);
			}
			result = 1;
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			logger.error(Constants.EXCEPTION, e);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}

		return result;
	}
	
	private SummaryVO listToObject(List<String> list) {
		var summaryObject = new SummaryVO();

		try {
			for(int i=0; i<list.size(); i++) {
				int a = i+1;
				if(a==1) {
					summaryObject.setId(list.get(i));
				}else if(a==2) {
					summaryObject.setStudent(list.get(i));
				} else if(a==3) {
					summaryObject.setStatus(list.get(i));
				} else if(a==4) {
					setMadeDifference(list.get(i), summaryObject);
				} else if(a==5) {
					summaryObject.setCoveredValue(list.get(i));
				} else if(a==6) {
					summaryObject.setRecommendation(list.get(i));
				} else if(a==7) {
					setGender(list.get(i), summaryObject);
				}
			}
			logger.info("List to object is ... ");
			return summaryObject;
		} catch(Exception e) {
			logger.error(Constants.EXCEPTION, e);
			return summaryObject;
		}
	}
	
	private String[] objectToStringArray(SummaryVO o){
		var rs = new String[7];
		try {
			rs[0] = o.getId();
			rs[1] = o.getStudent();
			rs[2] = o.getStatus();

			if(o.isMadeADifference())
				rs[3] = "1";
			else
				rs[3] = "0";

			rs[4] = o.getCoveredValue();
			rs[5] = o.getRecommendation();

			if("he".equalsIgnoreCase(o.getGender()))
				rs[6] = "M";
			if("she".equalsIgnoreCase(o.getGender()))
				rs[6] = "F";

		} catch(Exception e) {
			logger.error(Constants.EXCEPTION, e);
		}
		
		return rs;
	}
	
	private void setMadeDifference(String in, SummaryVO summaryObject) {
		if(in.equals("1"))
			summaryObject.setMadeADifference(true);
		if(in.equals("0"))
			summaryObject.setMadeADifference(false);
	}

	private void setGender(String in, SummaryVO summaryObject) {
		if("M".equalsIgnoreCase(in))
			summaryObject.setGender("he");
		if("F".equalsIgnoreCase(in))
			summaryObject.setGender("she");
	}

	/**
	 * @param fileInPath
	 * Sets the file path where the CSV file is stored.
	 */
	public void setFileInPath(String fileInPath) {
		this.fileInPath = fileInPath;
	}

	public String getValidationMsg() {
		return validationMsg;
	}

	public void setValidationMsg(String validationMsg) {
		this.validationMsg = validationMsg;
	}
}