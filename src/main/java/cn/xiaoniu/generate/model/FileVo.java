package cn.xiaoniu.generate.model;

public class FileVo {
	private String filePath;
	private String fileName;
	private String templateFile;
	
	public String getTemplateFile() {
		return templateFile;
	}
	public void setTemplateFile(String templateFile) {
		this.templateFile = templateFile;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Override
	public String toString() {
		return "FileVo [filePath=" + filePath + ", fileName=" + fileName
				+ ", templateFile=" + templateFile + "]";
	}
}
