package com.acn.driver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.acn.testbase.TestBase;

public class ExcelDriver {

	public static String strTCname = null;
	private static List<String> dataListComponent = new ArrayList<String>();;

	@SuppressWarnings("resource")
	public static void generateFeature() {
		// public static void main(String[] args) {
		FileInputStream file = null;
		FileInputStream file1 = null;
		try {

			deleteFiles();
			File myFileMain = new File("src/test/java/com/acn/driver/Executor.xlsx");
			file1 = new FileInputStream(myFileMain);
			XSSFWorkbook workbookMain = new XSSFWorkbook(file1);
			XSSFSheet fileName = workbookMain.getSheet("FileName");

			int rowCountMain = fileName.getLastRowNum();
			for (int y = 1; y <= rowCountMain; y++) {
				XSSFCell fileNameCell = fileName.getRow(y).getCell(0);
				XSSFCell yesOrNo = fileName.getRow(y).getCell(1);
				String excelFileName = fileNameCell.getStringCellValue();
				String status = yesOrNo.getStringCellValue();
				if (status.equalsIgnoreCase("y")) {
					File myFile = new File("src/test/java/com/acn/driver/" + excelFileName + ".xlsx");
					file = new FileInputStream(myFile);

					// Create Workbook instance holding reference to .xlsx file
					XSSFWorkbook workbook = new XSSFWorkbook(file);

					// Get first/desired sheet from the workbook
					XSSFSheet scenario = workbook.getSheet("scenarios");
					// component update
					XSSFSheet components = workbook.getSheet("test cases");
					XSSFSheet repo = workbook.getSheet("repo");
					XSSFSheet urls = workbook.getSheet("global");
					Map<String, String> urlMap = parseUrl(urls);
					// Iterate through each rows one by one
					Iterator<Row> rowIterator = scenario.iterator();
					int rowNum = 1;// added
					// component update
					// Iterator<Row> rowIteratorComponent =
					// components.iterator();
					Row row = null;
					List<String> headerList = new ArrayList<String>();
					// check the row
					while (rowIterator.hasNext()) {
						boolean featureHeader = false;

						List<String> dataList = new ArrayList<String>();

						List<String> globalHeaderList = new ArrayList<String>();

						row = rowIterator.next();
						StringBuilder featureBuilder = new StringBuilder();
						if (row.getRowNum() == 0) {
							// For each row, iterate through all the columns
							Iterator<Cell> cellIterator = row.cellIterator();

							while (cellIterator.hasNext()) {
								Cell cell = cellIterator.next();
								headerList.add(cell.getStringCellValue());

							}
						} else {
							// added
							XSSFCell cellTemp = scenario.getRow(rowNum).getCell(2);
							String tcName = cellTemp.getStringCellValue();
							rowNum++;
							String filename = "src/test/java/com/acn/scenarios/feature/Test" + tcName + "Create"
									+ ".feature";
							File file2 = new File(filename);
							FileWriter fw = new FileWriter(file2.getAbsoluteFile());
							BufferedWriter bw = new BufferedWriter(fw);
							createOutputFile(row, file2);
							// For each row, iterate through all the columns
							Iterator<Cell> cellIterator = row.cellIterator();

							while (cellIterator.hasNext()) {
								Cell cell = cellIterator.next();
								switch (cell.getCellType()) {
								case Cell.CELL_TYPE_NUMERIC:
									dataList.add(Double.toString(cell.getNumericCellValue()));
									break;
								case Cell.CELL_TYPE_STRING:

									dataList.add(cell.getStringCellValue());

								}

							}
							for (int i = 0; i < headerList.size(); i++) {
								if (i < 2) {
									featureBuilder.append(headerList.get(i) + ":" + dataList.get(i));
									featureBuilder.append("\n");
								} else if (i == 3) {
									strTCname = dataList.get(i);
								} else if (i == 5) {
									// featureBuilder.append("When user opens
									// \"<BrowserType>\" and navigates \"<URL>\"
									// \n
									// And");

									// update
									// updated
									String[] componentName = dataList.get(i).split("\n");
									// String[] testCaseName
									// componentName = dataList.get(i);
									XSSFCell cell, cellComponent, variableComponents;
									String cellValue = null;
									int RowCount = components.getLastRowNum();
									// while(rowIteratorComponent.hasNext()){
									for (int ctr = 0; ctr < componentName.length; ctr++) {

										for (int row1 = 1; row1 <= RowCount; row1++) {
											cell = components.getRow(row1).getCell(0);
											cellValue = cell.getStringCellValue();
											// System.out.println(cellValue);
											if (cellValue.equals(componentName[ctr])) {
												// System.out.println(componentName[ctr]);
												// cell =
												// components.getRow(row1).getCell(2);
												// System.out.println("Success");
												cellComponent = components.getRow(row1).getCell(1);
												// System.out.println(cellComponent.getStringCellValue());
												String[] sGherkinStatement = cellComponent.getStringCellValue()
														.split("\n");
												// System.out.println(sGherkinStatement);
												if (sGherkinStatement[0]
														.contains("Given browser is open and navigates to \"<URL>\"")) {
													featureBuilder.append(
															"Given for TC Name \"<TCName>\", browser \"<BrowserType>\" is open and navigates to \"<URL>\" \n");
												} else if (sGherkinStatement[0].contains(
														"Given browser is open and navigates to \"<site>\"")) {
													featureBuilder.append(
															"Given for TC Name \"<TCName>\", browser \"<BrowserType>\" is open and navigates to \"<site>\" \n");
												} else if (sGherkinStatement[0].contains(
														"Given browser \"<BrowserType>\" is open and navigates to \"<URL>\"")) {
													featureBuilder.append(
															"Given for TC Name \"<TCName>\", browser \"<BrowserType>\" is open and navigates to \"<URL>\" \n");
												} else {
													featureBuilder.append(sGherkinStatement[0] + "\n");
												}

												for (int x = 1; x < sGherkinStatement.length; x++) {
													featureBuilder.append(sGherkinStatement[x] + "\n");

												}
												cellComponent = components.getRow(row1).getCell(2);
												variableComponents = components.getRow(row1).getCell(3);
												// System.out.println(cellComponent);
												// System.out.println(variableComponents);
												// for(ctr=0; ctr<)
												String[] temp = cellComponent.getStringCellValue().split("\n");
												String[] tempvar = variableComponents.getStringCellValue().split("\n");
												String[] varParts;
												// String[] temp1;
												// if (variableComponents!=null)

												for (String string : temp) {
													// System.out.println(string);

													if (string.contains("*")) {
														varParts = string.split("=");
														for (String string1 : tempvar) {
															if (string1.contains(varParts[1])) {
																string = (varParts[0] + "=" + string1.split("=")[1]);
																break;
															}
														}
														if (string.contains("*")) {
															System.out.println(
																	"FAIL : The variable you entered is not defined.\n"
																	+"Component Name : " + componentName[ctr] +"\nVariable : " + string.split("=")[1]);
															System.exit(0);
														} else
															dataListComponent.add(string);
													} else
														dataListComponent.add(string);
												}
												// System.out.println(dataListComponent);
												// featureBuilder.append(dataList.get(i));
												// String[] globalSt =
												// dataList.get(i).split(" ");
												// globalHeaderList = new
												// ArrayList<String>();
												// for(int x=0;
												// x<dataListComponent.length;
												// x++)
												// System.out.println(dataListComponent[x]);
												for (String string : sGherkinStatement) {
													if (string.indexOf("<") > 0 && string.indexOf(">") > 0) {
														string = string.substring(string.indexOf("<") + 1,
																string.indexOf(">"));
														globalHeaderList.add(string);
													}
												}
												// System.out.println(globalHeadDerList);

												featureBuilder.append("\n");
												break;
											}

										}
									}
									// }
									// commented by ralph

								} else if (i == 6) {
									featureHeader = createBrowserDependentdata(featureHeader, dataList, repo, i,
											featureBuilder, urlMap, "IE", globalHeaderList);
								} else if (i == 7) {
									featureHeader = createBrowserDependentdata(featureHeader, dataList, repo, i,
											featureBuilder, urlMap, "Chrome", globalHeaderList);
								} else if (i == 8) {
									featureHeader = createBrowserDependentdata(featureHeader, dataList, repo, i,
											featureBuilder, urlMap, "Mozilla", globalHeaderList);
								}

							}

							bw.write(featureBuilder.toString());
							bw.close();
						}

					}
					file.close();

				}

			}
			file1.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void createOutputFile(Row row, File file2) throws IOException {
		// if file doesnt exists, then create it
		if (!file2.exists()) {
			file2.createNewFile();
		}
	}

	private static void deleteFiles() {
		try {
			File file = new File("src/test/java/com/acn/scenarios/feature/");
			FileUtils.cleanDirectory(file);
		} catch (Exception e) {

		}

	}

	private static Map<String, String> parseUrl(XSSFSheet urls) {
		Map<String, String> urlMap = new HashMap<String, String>();
		String key = null;
		String value = null;
		// Iterate through each rows one by one
		Iterator<Row> rowIterator = urls.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			// For each row, iterate through all the columns
			Iterator<Cell> cellIterator = row.cellIterator();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				if (cell.getColumnIndex() == 0) {
					key = cell.getStringCellValue();
				}
				if (cell.getColumnIndex() == 1) {
					value = cell.getStringCellValue();

				}
			}
			urlMap.put(key, value);
		}
		return urlMap;
	}

	private static boolean createBrowserDependentdata(boolean featureHeader, List<String> dataList, XSSFSheet repo,
			int i, StringBuilder featureBuilder, Map<String, String> urlMap, String browserType,
			List<String> globalHeaderList) {
		if (dataList.get(i).equals("Y")) {
			int noOfResults = 0;
			boolean globaldata = false;
			List<String> featureHeaderList = new ArrayList<String>();
			String[] ieArray = new String[dataListComponent.size()];
			// updated
			int ctr = 0;
			for (String str : dataListComponent) {

				// str = str.replace("|", "");
				// System.out.println(str);
				ieArray[ctr] = str.replace("|", "");
				ctr++;
			}
			// for(String temp:dataListComponent){
			// System.out.println(dataListComponent);
			// dataListComponent = temp.replace("|", "\n");
			//// System.out.println(dataListComponent[ctr]);
			//// System.out.println(ieArray[ctr]);
			// }
			// System.out.println(ieArray);

			Map<String, List<String>> headerValueMap = new HashMap<String, List<String>>();
			String keyHeader = "";
			if (ieArray.length == 1) {
				globaldata = true;
			} else {
				for (String string : ieArray) {
					// System.out.println(string);
					String[] arryOfString = string.split("=");
					
					keyHeader = arryOfString[0];
					// System.out.println(keyHeader);
					featureHeaderList.add(keyHeader);
					String[] arryOfdata = arryOfString[1].split(";");
					// System.out.println(arryOfString[1]);
					List<String> ieValueList = new ArrayList<String>();
//					ieValueList.get()
					for (String string2 : arryOfdata) {
						ieValueList.add(string2);
						// System.out.println(string2);
					}
					if (noOfResults == 0) {
						noOfResults = ieValueList.size();
					}
					headerValueMap.put(keyHeader, ieValueList);

				}
			}
			// StringBuilder header =new StringBuilder("|BrowserType|URL|");
			StringBuilder header = new StringBuilder("|BrowserType|").append("TCName|");
			// StringBuilder data =new
			// StringBuilder("|").append(browserType).append("|").append(urlMap.get("URL")).append("|");
			StringBuilder data = new StringBuilder("|").append(browserType).append("|").append(strTCname).append("|");
			Map<String, String> repoMap = parseRepo(repo);
			List<String> list = new ArrayList<String>();
			// featureHeaderList
			// if(globaldata){
			if (noOfResults == 0) {
				noOfResults = 1;

			}
			for (int k = 0; k < noOfResults; k++) {
				for (String string : globalHeaderList) {
					list = new ArrayList<String>();
					if (k == 0) {
						header.append(string.trim()).append("|");
					}
					// System.out.println(string);
					// System.out.println(headerValueMap.values());
					if (headerValueMap.containsKey(string.trim())) {
						list = headerValueMap.get(string);
						// System.out.println(string);
					} else if (!StringUtils.equalsIgnoreCase("URL", string) && (urlMap.containsKey(string.trim()))) {
						list.add(urlMap.get(string.trim()));
					} else if (urlMap.containsKey(string.trim())) {
						list.add(urlMap.get(string));

					}

					if ((!list.isEmpty()) && (k < list.size()) && StringUtils.isNotBlank(list.get(k))) {
						data = data.append(list.get(k).trim()).append(";").append(repoMap.get(string.trim()))
								.append(";").append(string.trim()).append("|");
					} else if ((!list.isEmpty()) && StringUtils.isNotBlank(list.get(0))) {
						data = data.append(list.get(0).trim()).append(";").append(repoMap.get(string.trim()))
								.append(";").append(string.trim()).append("|");
					} else {
						data = data.append(repoMap.get(string.trim())).append(";").append(string.trim()).append("|");
					}
					// System.out.println(data);
				}
				if (k != noOfResults - 1) {
					// data.append("\n|").append(browserType).append("|").append(urlMap.get("URL")).append("|");
					data.append("\n|").append(browserType).append("|");
				}
			}
			// }
			// else {
			// for (int k = 0; k < noOfResults; k++) {
			// for (String string : featureHeaderList) {
			// if(k==0){
			// header.append(string.trim()).append("|");
			// }
			// if(!StringUtils.equalsIgnoreCase("URL", string) &&
			// (urlMap.containsKey(string.trim()))){
			// list.add(urlMap.get(string.trim()));
			// }else{
			// list.add(urlMap.get(string));
			// }
			// if(StringUtils.isNotBlank(list.get(k))){
			// data =
			// data.append(list.get(k).trim()).append(";").append(repoMap.get(string.trim())).append("|");
			// }else{
			// data = data.append(repoMap.get(string.trim())).append("|");
			// }
			// }
			// if(k!=noOfResults-1){
			// data.append("\n|").append(browserType).append("|").append(urlMap.get("URL")).append("|");
			// }
			// }
			// }
			if (!featureHeader) {
				featureBuilder.append("Examples:");
				featureBuilder.append("\n");
				featureBuilder.append(header.toString());
				featureHeader = true;
			}
			featureBuilder.append("\n");
			featureBuilder.append(data.toString());
		}

		return featureHeader;
	}

	private static Map<String, String> parseRepo(XSSFSheet repo) {
		Map<String, String> repoMap = new HashMap<String, String>();
		// Iterate through each rows one by one
		Iterator<Row> rowIterator = repo.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			if (row.getRowNum() > 0) {
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
				String key = null;
				String value = null;
				String locatorType = null;
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if (cell.getColumnIndex() == 2) {
						key = cell.getStringCellValue();
					}
					if (cell.getColumnIndex() == 3) {
						value = cell.getStringCellValue();
					}
				}
				if (null != key) {
					repoMap.put(key.trim(), value.trim());
				}
			}
		}
		return repoMap;
	}

}
