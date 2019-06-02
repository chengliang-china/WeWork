package com.wework.base.config;

/**
 * 常量类
 */
public class BaseCode {
	public final static int ORDER_OPENED = 10011001;//已开单，待结算
	public final static int ORDER_SETTLED = 10011002;//已结算，待评价
	public final static int ORDER_COMPLETED = 10011003;//已完成

	public final static int DEL = 1; // 删除
	public final static int UNDEL = 0; // 未删除

	public final static int TO_BE_USED = 10012001;// 待使用
	public final static int USED = 10012002;// 已使用

	public final static int  VALID = 10012004 ;// 有效 未过期
	public final static int  INVALID = 10012005 ;// 无效  已经过期

	public final static int R_CODE_SIZE = 8; // 兑换码长度

	public static enum StoreType {
		STATION("工位",10013001),
		METTING("会议室",10013002),
		OFFICE("办公室",10013003),
		SHOWHALL("路演厅",10013004);
		
		private String name;
		private int code;
		private StoreType(String name, int code) {
	        this.name = name;  
	        this.code = code;  
	    }
		public String getName() {
			return this.name;
		}
		
		public int getCode() {
			return this.code;
		}
	}
}



