package cn.xiaoniu.generate.model;

public class JavaVo extends FileVo{
	
	private String strPackage;
	
	private String shortName;
	
	private String aliasName;
	
	private String fullName;

	public String getStrPackage() {
		return strPackage;
	}

	public void setStrPackage(String strPackage) {
		this.strPackage = strPackage;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "JavaVo [strPackage=" + strPackage + ", shortName=" + shortName
				+ ", aliasName=" + aliasName + ", fullName=" + fullName + "]";
	}
	
}
