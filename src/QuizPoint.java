import java.io.Serializable;

public class QuizPoint extends Quiz implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int point;
	private Quiz quiz;

	public QuizPoint(Quiz q, int p) {
		super(q);
		setPoint(p);
	}

	@Override
	public String toString() {
		return String.format("%-5s| %-10s\t", super.getCode(), point);
	}

	public String quizToString() {
		return super.toString();
	}

	public String toStringWithColor() {
		if (point > 5) {
			return "\033[32m " + point;
		} else
			return "\033[31m " + point;
	}

	// *********************GET && SET***********************************/

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

}
