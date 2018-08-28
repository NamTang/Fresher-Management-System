import java.io.Serializable;

public class Quiz implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String quizName;
	private String content;
	

	
	public Quiz(String c, String qn, String cont) {
		setCode(c);
		setQuizName(qn);
		setContent(cont);
	}
	
	public Quiz(Quiz q) {
		setCode(q.code);
		setQuizName(q.quizName);
		setContent(q.content);
	}

	public String toString() {
		return String.format("%s: %s\n%s\n", code, quizName, content);
	}
	
	public String nameToString() {
		return String.format("%-5s| %s\n", code, quizName);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getQuizName() {
		return quizName;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
