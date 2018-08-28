import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date date;
	private String comment;
	
	public Comment(Date d, String cmt) {
		setDate(d);
		setComment(cmt);
	}
	public String toString() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return df.format(date) + "\n\t" + comment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	

}
