package com.noteshare.chm.entity;
/**
  *    
  * 类名称：ChmTitle
  * 类描述：chm标题   
  * 创建人：chx   
  * 创建时间：2014-6-10 下午09:13:36   
  * 修改人：Administrator   
  * 修改时间：2014-6-10 下午09:13:36   
  * 修改备注：   
  * @version 1.1
 */
public class ChmTitle {
	/**
	 * Window Styles:<param name="Window Styles" value="0x800025">
	 */
	private String windowStyles_param;
	/**
	 * title:<param name="comment" value="title:网页制作完全手册">
	 */
	private String comment_title;
	/**
	 * base:<param name="comment" value="base:index.htm">
	 */
	private String comment_base;
	public String getWindowStyles_param() {
		return windowStyles_param;
	}
	public void setWindowStyles_param(String windowStylesParam) {
		windowStyles_param = windowStylesParam;
	}
	public String getComment_title() {
		return comment_title;
	}
	public void setComment_title(String commentTitle) {
		comment_title = commentTitle;
	}
	public String getComment_base() {
		return comment_base;
	}
	public void setComment_base(String commentBase) {
		comment_base = commentBase;
	}
}
