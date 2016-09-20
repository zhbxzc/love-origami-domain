package com.love.origami.common;

import org.springframework.web.multipart.MultipartFile;

public class FilesVO {
	private String _id;
	private MultipartFile file;
	private String filename;
	private String whose;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getWhose() {
		return whose;
	}
	public void setWhose(String whose) {
		this.whose = whose;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
}
