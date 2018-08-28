import java.io.Serializable;

public class Department implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String name;
	private String shortName;

	public Department(String c, String n, String sn) {
		setCode(c);
		setName(n);
		setShortName(sn);
	}
	
	public Department(Department d) {
		setCode(d.getCode());
		setName(d.getName());
		setShortName(d.getShortName());
	}
	
	
	public String toString() {
		return String.format("%-5s| %-10s| %-20s", code,shortName,name);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

}
