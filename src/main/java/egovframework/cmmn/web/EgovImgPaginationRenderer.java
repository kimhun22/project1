/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.cmmn.web;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

/**
 * @Class Name : ImagePaginationRenderer.java
 * @Description : ImagePaginationRenderer Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */
public class EgovImgPaginationRenderer extends AbstractPaginationRenderer implements ServletContextAware{

	@SuppressWarnings("unused")
	private ServletContext servletContext;

	public EgovImgPaginationRenderer() {
		// no-op
	}

	/**
	* PaginationRenderer
	*
	* @see 개발프레임웍크 실행환경 개발팀
	*/
	public void initVariables() {

		firstPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\" class='page_first'></a>";
		previousPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\" class='page_prev'></a>";
		currentPageLabel = "<a href=\"#\" class='cur'>{0}</a>";
		otherPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">{2}</a>";
		nextPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\" class='page_next'></a>";
		lastPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\" class='page_end'></a>";
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		initVariables();
	}
}
