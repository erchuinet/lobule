package com.erchuinet.common;

/**
 * 文件信息
 *
 */
public class FileVo {

	/**
	 * 文件名
	 */
	private String name;

	/**
	 * 相对路径
	 */
	private String path;

	/**
	 * 文件类型
	 */
	private String suffix;

	/**
	 * 文件大小
	 */
	private Long size;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}
}