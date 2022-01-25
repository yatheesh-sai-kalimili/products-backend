package com.products.backend.helper;


import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import com.products.backend.model.PriceHistory;




public class ExcelHelper {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "Date", "Price Per Lot", "Product ID"};
	static String SHEET = "Product";

	public static boolean hasExcelFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}


	public static List<PriceHistory> excelToProductsHistory(InputStream is) throws ParseException {
		try {

			Workbook workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();
			List<PriceHistory> ProductsHistorys = new ArrayList<PriceHistory>();
			boolean notEmpty = true;
			int rowNumber = 0;
			while (rows.hasNext() && notEmpty) {
				Row currentRow = rows.next();

				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();

				PriceHistory ProductsHistory = new PriceHistory();

				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();

					switch (cellIdx) {
					case 0:
						if(currentCell.getCellType().name().compareTo("STRING")==0) {
							SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy"); 
							ProductsHistory.setHistoryDate(formatter.parse(currentCell.getStringCellValue()));
							//System.out.println(currentCell);
							break;
						}
						ProductsHistory.setHistoryDate(currentCell.getDateCellValue());
						
						break;

					case 1:
						
						BigDecimal priceOnThatDay = new BigDecimal(currentCell.getNumericCellValue());
						ProductsHistory.setPriceOnThatDay(priceOnThatDay.setScale(2, RoundingMode.HALF_DOWN));
						
						break;

					case 2:
								
						ProductsHistory.setProductId((int) currentCell.getNumericCellValue());
						
						break;


					default:
						break;
					}

					cellIdx++;
				}
				if(ProductsHistory.getHistoryDate()!=null && ProductsHistory.getPriceOnThatDay()!=BigDecimal.ZERO && ProductsHistory.getProductId()!=0 ) {
					Integer productId = ProductsHistory.getProductId();

					DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
					Date date = (Date)formatter.parse(ProductsHistory.getHistoryDate().toString());
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);

					int day = cal.get(Calendar.DATE);
					int month = cal.get(Calendar.MONTH) + 1;
					Integer year =  cal.get(Calendar.YEAR);
					BigDecimal price = ProductsHistory.getPriceOnThatDay().setScale(0, RoundingMode.HALF_DOWN);

					String id = productId.toString()+year.toString()+day+month+price.toPlainString();

					ProductsHistory.setHistoryId(new BigInteger(id));
					ProductsHistorys.add(ProductsHistory);

				}
				else if(ProductsHistory.getPriceOnThatDay()==BigDecimal.ZERO || ProductsHistory.getProductId()==0) {
					notEmpty = false;
				}
			}

			workbook.close();

			return ProductsHistorys;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}
}