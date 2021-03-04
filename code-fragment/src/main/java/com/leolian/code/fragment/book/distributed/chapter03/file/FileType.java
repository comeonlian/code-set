package com.leolian.code.fragment.book.distributed.chapter03.file;

/**
 * 常用的文件类型对应对应的文件头十六进制编码
 * @Description: 
 * @author lianliang
 * @date 2017年10月23日 上午10:27:46
 */
public enum FileType {

	JPEG("FFD8FF"),

	PNG("89504E47"),

	GIF("47494638"),

	// TIFF
	TIFF("49492A00"),

	BMP("424D"),

	// CAD
	DWG("41433130"),

	// photoshop
	PSD("38425053"),

	XML("3C3F786D6C"),

	HTML("68746D6C3E"),

	PDF("255044462D312E"),

	ZIP("504B0304"),

	RAR("52617221"),

	WAV("57415645"),

	AVI("41564920");

	private String value = "";

	private FileType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}