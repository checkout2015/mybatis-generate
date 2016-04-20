package cn.xiaoniu.generate.config;

public class TableConfig {
	private String symbol = null;
	private String tablePri = null;
	private String columnPri = null;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getTablePri() {
		return tablePri;
	}

	public void setTablePri(String tablePri) {
		this.tablePri = tablePri;
	}

	public String getColumnPri() {
		return columnPri;
	}

	public void setColumnPri(String columnPri) {
		this.columnPri = columnPri;
	}

	@Override
	public String toString() {
		return "TableConfig [symbol=" + symbol + ", tablePri=" + tablePri
				+ ", columnPri=" + columnPri + "]";
	}
	
}