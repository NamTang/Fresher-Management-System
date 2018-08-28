import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Fresher implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String fullName;
	private int iD;
	private String schoolName;
	private int mockProjectpoint;
	private Department department;
	private List<QuizPoint> quizPointList = new ArrayList<QuizPoint>();
	private List<Comment> commentList = new ArrayList<Comment>();

	// ****************CONTRUCTOR*************************************/
	public Fresher(String c, String fn, int id, String sn) {
		setCode(c);
		setFullName(fn);
		setiD(id);
		setSchoolName(sn);
	}

	public Fresher(String c, String fn, int id, String sn, int mp) {
		setCode(c);
		setFullName(fn);
		setiD(id);
		setSchoolName(sn);
		setMockProjectpoint(mp);
	}
	// *******************METHODS***************************************/

	public int getAveragePoint()  throws ArithmeticException{
		int s = 0;
		if (quizPointList.size() == 0) {
			throw new ArithmeticException();
		} else {
			for (QuizPoint p : quizPointList) {
				s = s + p.getPoint();
			}
			return s / quizPointList.size();
		}

	}
	
	public static Comparator<Fresher> ascQuizPoint = new Comparator<Fresher>() {
		public int compare(Fresher f1, Fresher f2) {
			int ap1 = f1.getAveragePoint();
			int ap2 = f2.getAveragePoint();
			return ap1-ap2;
		}
	};
	
	public static Comparator<Fresher> dscMockProjectPoint = new Comparator<Fresher>() {
		public int compare(Fresher f1, Fresher f2) {
			int ap1 = f1.getMockProjectpoint();
			int ap2 = f2.getMockProjectpoint();
			return ap2-ap1;
		}
	};

	public String toString() {
		return String.format("%-5s| %-10s\t| %-20s| %-20s", iD, code, fullName, schoolName);
	}
	
	public String withDepartmenttoString() {
		return String.format("%-5s| %-10s\t| %-20s| %-20s| %-15s", iD, code, fullName, schoolName, department.getShortName());
	}

	public void addComment(Comment c) {
		commentList.add(c);
	}

	public void addQuizPoint(QuizPoint q) {
		quizPointList.add(q);
	}



	// *********************GET && SET***********************************/
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public int getMockProjectpoint() {
		return mockProjectpoint;
	}

	public void setMockProjectpoint(int mockProjectpoint) {
		this.mockProjectpoint = mockProjectpoint;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department d) {
		this.department = d;
	}

	public List<QuizPoint> getQuizPointList() {
		return quizPointList;
	}

	public void setQuizPointList(List<QuizPoint> quizList) {
		this.quizPointList = quizList;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

}
