package cn.xiaoniu.generate.config;

public class AppenderConfig {
	
	private String handler;
	private String name;
	private String template;
	private String fileName;
	private String path;
	private String dependency;
	
	public String getHandler() {
		return handler;
	}
	public void setHandler(String handler) {
		this.handler = handler;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getDependency() {
		return dependency;
	}
	public void setDependency(String dependency) {
		this.dependency = dependency;
	}
	@Override
	public String toString() {
		return "AppenderConfig [handler=" + handler + ", name=" + name
				+ ", template=" + template + ", fileName=" + fileName
				+ ", path=" + path + ", dependency=" + dependency + "]";
	}
	
}
