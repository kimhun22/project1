package core.web.jstl;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.tags.form.TagWriter;

import core.exception.HMException;

@SuppressWarnings("serial")
public class MultiUploadedFiles extends TagSupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(MultiUploadedFiles.class);

	private Object listFiles;

	private String idFieldName = "id";

	private String seqFieldName = "seq";

	private String nameFieldName = "originalName";

	private Tag parent;

	public Object getListFiles() {
		return listFiles;
	}

	public void setListFiles(Object listFiles) {
		this.listFiles = listFiles;
	}

	public String getIdFieldName() {
		return idFieldName;
	}

	public void setIdFieldName(String idFieldName) {
		this.idFieldName = idFieldName;
	}

	public String getSeqFieldName() {
		return seqFieldName;
	}

	public void setSeqFieldName(String seqFieldName) {
		this.seqFieldName = seqFieldName;
	}

	public String getNameFieldName() {
		return nameFieldName;
	}

	public void setNameFieldName(String nameFieldName) {
		this.nameFieldName = nameFieldName;
	}

	public Tag getParent() {
		return parent;
	}

	@Override
	public int doStartTag() throws JspException {
		TagWriter writer = new TagWriter(pageContext);

		if(parent == null){
			throw new JspTagException("MultiUploadedFiles Tag는 MultiUpload Tag안에 존재하여야 합니다.");
		}

		//file list setting
		if(listFiles != null){
			if(listFiles instanceof List<?>){
				@SuppressWarnings("rawtypes")
				List list = (List) listFiles;

				if(list.size() > 0){
					writer.startTag("ul");

					for(Object obj : list){
						try {
							String fileId = PropertyUtils.getProperty(obj, idFieldName).toString();
							String seq = PropertyUtils.getProperty(obj, seqFieldName).toString();
							String name = PropertyUtils.getProperty(obj, nameFieldName).toString();

							writer.startTag("li");
							writer.writeAttribute("class", "hmcore-file-area-li");
							writer.appendValue(name);
							writer.startTag("span");
							writer.writeAttribute("class", "hmcore-file-del");
							writer.writeAttribute("fileId", fileId);
							writer.writeAttribute("seq", seq);
							writer.appendValue("");
							writer.endTag();
							writer.endTag();

						} catch (NullPointerException ne) {
							LOGGER.error(ne.getMessage());
							throw new HMException(ne);
						} catch (Exception e) {
							throw new JspTagException("MultiUploadedFiles Tag의 listFiles 객체의 Field 값을 가져오는데 실패하였습니다.", e);
						}
					}

					writer.endTag();
				}
			} else {
				if(!listFiles.toString().trim().equals("")){
					throw new JspTagException("MultiUploadedFiles Tag의 listFiles 속성은 반드시 List<Object> Type 이어야 합니다.");
				}
			}
		}


		return SKIP_BODY;
	}

	public void setParent(Tag parent){
		this.parent = parent;
	}
}
