package com.gizmo.utils;

public enum CharsetEnum {

	EUCKR("EUC-KR"),
	UTF8("UTF-8"),
	UTF16("UTF16"),
	UTF16LE("UTF-16LE");

	private String charset;

	CharsetEnum(String charset){
		this.charset = charset;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}


}
