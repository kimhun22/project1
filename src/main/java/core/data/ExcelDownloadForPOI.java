package core.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

/**
 * 엑셀 다운로드 뷰 생성을위한 Class
 *
 * <pre>
 * [ 개정이력(Modification Information) ]
 * --------------  --------  ----------------------
 * 2014. 10. 13.    ysKo      신규생성
 * --------------  --------  ----------------------
 * </pre>
 *
 * @version 1.0
 * @Copyright : Heart Media CO.LTD.
 * @Project : hm-core-egov30
 * @Name : ExcelDownloadForPOI 
 * @Desc : 생성 후 getView("viewName") 메서드로 AbstractView 리턴함
 *
 * @param <T>
 */
public class ExcelDownloadForPOI<T> {
	private List<T> listData;
	private String title;
	private String excelFileName;
	private T excelObj;
	
	
	public List<T> getListData() {
		
		List<T> listDataCp = new ArrayList<T>();
		listDataCp.addAll(listData);
		return listDataCp;
			
	}

	public void setListData(List<T> listData) {
		this.listData =  new ArrayList<T>();
		for  ( T data : listData )  {
			this.listData.add(data);
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}

	public T getExcelObj() {
		return excelObj;
	}

	public void setExcelObj(T excelObj) {
		this.excelObj = excelObj;
	}

	public ExcelDownloadForPOI(List<T> listData, T excelObj, String excelFileName){
		for  ( T data : listData )  {
			this.listData.add(data);
		}
		this.excelFileName = excelFileName;
		this.excelObj = excelObj;
	}
	
	public ExcelDownloadForPOI(List<T> listData, T excelObj, String title, String excelFileName){
		for  ( T data : listData )  {
			this.listData.add(data);
		}
		this.title = title;
		this.excelFileName = excelFileName;
		this.excelObj = excelObj;
	}
	
	/**
	 * Excel Download View 리턴함
	 * 
	 * @since
	 * @작성자 ysKo
	 * @작성일 2014. 10. 13.
	 * @수정자
	 * @수정일
	 * @수정내용
	 *
	 * @param viewName
	 * @return
	 */
	public ModelAndView getView(String viewName){
		ModelAndView mav = new ModelAndView(viewName);
		
		mav.addObject("list", this.listData);
		mav.addObject("title", this.title);
		mav.addObject("excelFileName", excelFileName);
		mav.addObject("excelObj", excelObj);
		
		return mav;
	}
}
