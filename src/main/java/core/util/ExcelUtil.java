package core.util;

import java.awt.Color;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.exception.HMException;

@SuppressWarnings({"rawtypes"})
public class ExcelUtil {

	private Logger log = LoggerFactory.getLogger(ExcelUtil.class);


	/**
	 * EXCEL 파일 생성 및 다운로드
	 *
	 * @param response
	 * @param request
	 * @param saveFileName - 저장 엑셀 파일명
	 * @param excelTitle - 타이틀
	 * @param excelHeader - 제목 목록
	 * @param excelList - 내용 목록
	 * @throws Exception
	 */
	public void listExcelFileDownload(
			HttpServletResponse response, HttpServletRequest request,
			String saveFileName, String excelTitle, LinkedHashMap<String, String> excelHeader, List<?> excelList) throws Exception {

		if  ( excelHeader.isEmpty() )  {
			throw new HMException("제목 목록 데이터가 없습니다.");
		}

		try {
			XSSFWorkbook workbook = new XSSFWorkbook();		// workbook 생성
			XSSFSheet sheet = workbook.createSheet();		// sheet 생성
			XSSFRow row = null;								// excel row
			XSSFCell cell = null;							// excel cell
			int cellNum = 1;
			int lastCellNum = 0;

			// 타이틀 cell style 세팅
			XSSFCellStyle titleCellStyle = setNomalTitleCellStyle(workbook);
			// 제목 cell style 세팅
			XSSFCellStyle headerCellStyle = setHeaderCellStyle(workbook);
			// 내용 cell style 세팅
			XSSFCellStyle listCellStyle = setAlignCenterListCellStyle(workbook);

			// 타이틀 row 생성
			row = sheet.createRow(1);
				cell = row.createCell(cellNum);
				cell.setCellValue(StringUtil.isEmpty(excelTitle) ? "" : excelTitle);
				cell.setCellStyle(titleCellStyle);

			// 제목 row 생성
			LinkedHashSet<String> excelHeaderKey = new LinkedHashSet<String>();		// 제목 필드 목록
			Iterator headerIterator = excelHeader.entrySet().iterator();
			row = sheet.createRow(3);
				while  ( headerIterator.hasNext() )  {
					Entry headerEntry = (Entry) headerIterator.next();

					cell = row.createCell(cellNum);
					cell.setCellValue(headerEntry.getValue().toString());
					cell.setCellStyle(headerCellStyle);

					excelHeaderKey.add(headerEntry.getKey().toString());

					cellNum++;
				}

			// 마지막 cell 번호 세팅
			lastCellNum = row.getLastCellNum();

			// 내용 row 생성
			if  ( !excelList.isEmpty() )  {
				int rowNum = 4;

				for  ( Object obj : excelList )  {
					cellNum = 1;
					row = sheet.createRow(rowNum++);
					for  ( String key : excelHeaderKey )  {
						Field field = null;
						try {
							field = obj.getClass().getDeclaredField(key);
							field.setAccessible(true);
						} catch (Exception e) {
							throw new HMException("존재 하지 않는 필드가 포함되어 있습니다.("+ e.getMessage() +")");
						}

						try {
							cell = row.createCell(cellNum);
							setCellValue(cell, field.getType(), field.get(obj));
						} catch (Exception e) {
							throw new HMException("데이터 세팅 중 오류가 발생했습니다.("+ e.getMessage() +")");
						}

						cell.setCellStyle(listCellStyle);

						cell.setCellStyle(listCellStyle);

						cellNum++;
					}
				}
			}

			// 제목 row 셀 병합
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, lastCellNum - 1));

			// cell width 자동 조정
			for  ( int colNum = 1 ; colNum < lastCellNum ; colNum++ )  {
				sheet.autoSizeColumn(colNum);
				sheet.setColumnWidth(colNum, sheet.getColumnWidth(colNum) + 1700);
			}

			// 엑셀 저장 파일명
			String excelName;
			if  ( StringUtil.isEmpty(saveFileName) )  {
				// 없을 경우 현재 날짜,시간으로만 파일명 생성
				excelName = new SimpleDateFormat("yyyyMMddHHmmssSSS",Locale.KOREAN).format(System.currentTimeMillis()) + ".xlsx";
			}  else  {
				excelName = saveFileName + "_" + new SimpleDateFormat("yyyyMMddHHmmssSSS",Locale.KOREAN).format(System.currentTimeMillis()) + ".xlsx";
			}
			// 파일명 인코딩
			excelName = new FileUtil().encodingFileName(request, excelName);

			new FileUtil().downloadInfoSet(response, excelName);

			OutputStream fileOutput = response.getOutputStream();

			workbook.write(fileOutput);
			fileOutput.flush();
			fileOutput.close();

			log.debug("[------------------------ SET EXCEL FILE SCCUESS ------------------------]");
		} catch (Exception e) {

			log.error("[------------------------ SET EXCEL FILE FAIL ------------------------]");
			log.error(e.getMessage());

			throw new HMException("엑셀파일 생성 중 오류가 발생했습니다.");
		}

	}

	/**
	 * 기본 타이틀 cell 스타일 세팅
	 *
	 * @param workbook
	 * @return
	 */
	public XSSFCellStyle setNomalTitleCellStyle(XSSFWorkbook workbook) {

		// CellStyle 생성
		XSSFCellStyle titleCellStyle = workbook.createCellStyle();
			// 정렬
			titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
			titleCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			// 폰트
			XSSFFont titleCellFont = workbook.createFont();
			titleCellFont.setBold(true);
			titleCellFont.setFontHeightInPoints((short) 14);
			titleCellStyle.setFont(titleCellFont);

		return titleCellStyle;

	}

	/**
	 * 밑줄 타이틀 cell 스타일 세팅
	 * @param workbook
	 * @return
	 */
	public XSSFCellStyle setBottomLineTitleCellStyle(XSSFWorkbook workbook) {

		// CellStyle 생성
		XSSFCellStyle titleCellStyle = workbook.createCellStyle();
			// 실선
			titleCellStyle.setBorderBottom(BorderStyle.DOUBLE);
			// 정렬
			titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
			titleCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			// 폰트
			XSSFFont titleCellFont = workbook.createFont();
			titleCellFont.setBold(true);
			titleCellFont.setFontHeightInPoints((short) 14);
			titleCellStyle.setFont(titleCellFont);

		return titleCellStyle;

	}

	/**
	 * 제목 cell 스타일 세팅
	 *
	 * @param workbook
	 * @return
	 */
	public XSSFCellStyle setHeaderCellStyle(XSSFWorkbook workbook) {

		// CellStyle 생성
		XSSFCellStyle headerCellStyle = workbook.createCellStyle();
			// 실선
			headerCellStyle.setBorderTop(BorderStyle.THIN);
			headerCellStyle.setBorderLeft(BorderStyle.THIN);
			headerCellStyle.setBorderRight(BorderStyle.THIN);
			headerCellStyle.setBorderBottom(BorderStyle.THIN);
			// 배경색
			headerCellStyle.setFillForegroundColor(new XSSFColor(new Color(191, 191, 191)));
			headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			// 정렬
			headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
			headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			// 폰트
			XSSFFont headerCellFont = workbook.createFont();
			headerCellFont.setBold(true);
			headerCellStyle.setFont(headerCellFont);
			// 줄 바꿈
			headerCellStyle.setWrapText(true);

		return headerCellStyle;

	}

	/**
	 * 내용(가운데 정렬) cell 스타일 세팅
	 *
	 * @param workbook
	 * @return
	 */
	public XSSFCellStyle setAlignCenterListCellStyle(XSSFWorkbook workbook) {

		// CellStyle 생성
		XSSFCellStyle listCellStyle = workbook.createCellStyle();
			// 실선
			listCellStyle.setBorderTop(BorderStyle.THIN);
			listCellStyle.setBorderLeft(BorderStyle.THIN);
			listCellStyle.setBorderRight(BorderStyle.THIN);
			listCellStyle.setBorderBottom(BorderStyle.THIN);
			// 정렬
			listCellStyle.setAlignment(HorizontalAlignment.CENTER);
			listCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			// 줄 바꿈
			listCellStyle.setWrapText(true);

		return listCellStyle;

	}

	/**
	 * 내용(왼쪽 정렬) cell 스타일 세팅
	 * @param workbook
	 * @return
	 */
	public XSSFCellStyle setAlignLeftListCellStyle(XSSFWorkbook workbook) {
		// CellStyle 생성
		XSSFCellStyle listCellStyle = workbook.createCellStyle();
			// 실선
			listCellStyle.setBorderTop(BorderStyle.THIN);
			listCellStyle.setBorderLeft(BorderStyle.THIN);
			listCellStyle.setBorderRight(BorderStyle.THIN);
			listCellStyle.setBorderBottom(BorderStyle.THIN);
			// 정렬
			listCellStyle.setAlignment(HorizontalAlignment.LEFT);
			listCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			// 줄 바꿈
			listCellStyle.setWrapText(true);

		return listCellStyle;
	}

	/**
	 * 내용(오른쪽 정렬) cell 스타일 세팅
	 * @param workbook
	 * @return
	 */
	public XSSFCellStyle setAlignRightListCellStyle(XSSFWorkbook workbook) {
		// CellStyle 생성
		XSSFCellStyle listCellStyle = workbook.createCellStyle();
			// 실선
			listCellStyle.setBorderTop(BorderStyle.THIN);
			listCellStyle.setBorderLeft(BorderStyle.THIN);
			listCellStyle.setBorderRight(BorderStyle.THIN);
			listCellStyle.setBorderBottom(BorderStyle.THIN);
			// 정렬
			listCellStyle.setAlignment(HorizontalAlignment.RIGHT);
			listCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			// 줄 바꿈
			listCellStyle.setWrapText(true);

		return listCellStyle;
	}

	/**
	 * 캡션 cell 스타일 세팅
	 * @param workbook
	 * @return
	 */
	public XSSFCellStyle setCaptionCellStyle(XSSFWorkbook workbook) {
		// CellStyle 생성
		XSSFCellStyle captionCellStyle = workbook.createCellStyle();
			// 정렬
			captionCellStyle.setAlignment(HorizontalAlignment.RIGHT);
			captionCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

		return captionCellStyle;
	}

	/**
	 * cell value 세팅
	 *
	 * @param cell
	 * @param fieldType
	 * @param obj
	 */
	public void setCellValue(XSSFCell cell, Class<?> fieldType, Object obj) {

		// 필드 타입이 String 일때
		if  ( fieldType == java.lang.String.class )  {
			cell.setCellValue(StringUtil.nvl(obj, ""));
			cell.setCellType(CellType.STRING);
		}
		// 필드 타입이 int 일때
		else if  ( fieldType == int.class || fieldType == Integer.class )  {
			cell.setCellValue(Double.parseDouble(StringUtil.nvl(obj, "0")));
			cell.setCellType(CellType.NUMERIC);
		}
		// 필드 타입이 Double 일때
		else if  ( fieldType == Double.class )  {
			cell.setCellValue(Double.parseDouble(StringUtil.nvl(obj, "0")));
			cell.setCellType(CellType.NUMERIC);
		}
		// 필드 타입이 Date 일때
		else if  ( fieldType == Date.class )  {
	    	cell.setCellValue( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREAN).format(obj) );
		}
		// 모두 해당 안될 경우 강제 String 적용
		else  {
			cell.setCellValue( obj + "" );
		}

	}

	/**
	 * cell 생성
	 * @param sheet
	 * @param rowIndex
	 * @param cellIndex
	 * @param rowspan
	 * @param colspan
	 * @param style
	 * @param fieldType
	 * @param value
	 * @return
	 */
	public int createCell(XSSFSheet sheet, int rowIndex, int cellIndex, int rowspan, int colspan, XSSFCellStyle style, Class<?> fieldType, Object value) {

		XSSFRow row = null;		// excel row
		XSSFCell cell = null;	// excel cell

		int startRowIndex = rowIndex;
		int startCellIndex = cellIndex;

		// rowspan
		for  ( int i = 0 ; i < rowspan ; i++  )  {
			cellIndex = startCellIndex;

			if  ( null == sheet.getRow(rowIndex) )
				row = sheet.createRow(rowIndex++);
			else
				row = sheet.getRow(rowIndex++);

			// colspan
			for  ( int j = 0 ; j < colspan ; j++ )  {
				cell = row.createCell(cellIndex++);
				if  ( i == 0 && j == 0 )
					setCellValue(cell, fieldType, value);
				if  ( null != style )
					cell.setCellStyle(style);
			}
		}

		if  ( rowspan > 1 || colspan > 1 )  {
			sheet.addMergedRegion(new CellRangeAddress(startRowIndex, (rowIndex - 1), startCellIndex, (cellIndex - 1)));
		}

		return (cellIndex - 1);

	}

}
