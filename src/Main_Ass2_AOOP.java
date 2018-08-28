
import java.util.*;

public class Main_Ass2_AOOP {

	static String[] fullNameList = new String[3];
	static String[] schoolList = new String[3];
	static String[] commentList = new String[2];
	static List<Department> departmentList = new ArrayList<Department>();
	static List<Quiz> quizList = new ArrayList<Quiz>();
	static List<Fresher> fresherList = new ArrayList<Fresher>();
	static Scanner sc = new Scanner(System.in);
	static Random random = new Random();
	static ReaderWriter rw = new ReaderWriter();
	static final String FRESHER_FILE_NAME = "fresher.txt";
	static final String DEPARTMENT_FILE_NAME = "department.txt";
	static final String QUIZ_FILE_NAME = "quiz.txt";

	// **********DUMP DATA
	// **********METHODS*****************************************************************/
	static void dumpData() {
		fullNameList[0] = "Tang Hoai Nam";
		fullNameList[1] = "LuCas Waston";
		fullNameList[2] = "a b c";

		schoolList[0] = "Hutech";
		schoolList[1] = "Fpt University";
		schoolList[2] = "MIT";

		commentList[0] = "moving in.";
		commentList[1] = "has arrived!";

		departmentList.add(new Department("D0", "JAVA", "JDEV"));
		departmentList.add(new Department("D1", "DOT NET", "DNDEV"));
		quizList.add(
				new Quiz("Q1", "How many times can you call a method?", "a. As many as you want\nb. One\nc. Only two"));
		quizList.add(new Quiz("Q2", "Which of the following are valid access modifiers?",
				"a. protected\nb. hidden\nc. private\nd. public"));

	}

	static String getCode() {
		Random rand = new Random();
		int year = Calendar.getInstance().get(Calendar.YEAR) % 100;
		int month = Calendar.getInstance().get(Calendar.MONTH);
		String months;
		if (month < 10) {
			months = String.format("%02d", month);
		} else {
			months = String.format("%d", month);
		}
		int num = rand.nextInt(900) + 100;
		return "F" + year + months + num;
	}

	static void initDumpComment() {
		Date date = new Date();
		for (Fresher s : fresherList) {
			for (int i = 0; i < 2; i++) {
				s.addComment(new Comment(date, s.getFullName() + " " + commentList[i]));
			}

		}
	}

	static void initDumpDepartment() {
		int i = 0;
		for (Fresher s : fresherList) {
			if (i > 1) {
				i = 0;
			}
			Department d = departmentList.get(i);
			String c = d.getCode();
			String n = d.getName();
			String sn = d.getShortName();
			Department dm = new Department(c, n, sn);
			s.setDepartment(dm);
			i++;
		}
	}

	static void initDumpQuizPoint() {
		for (Fresher s : fresherList) {
			for (Quiz q : quizList) {
				int rd = random.nextInt(10) + 1;
				s.addQuizPoint(new QuizPoint(q, rd));
			}
		}
	}

	static void initDumpMPP() {
		for (Fresher s : fresherList) {
			int rd = random.nextInt(10) + 1;
			s.setMockProjectpoint(rd);
		}
	}

	static void initDumpData() {
		int i = 0;
		boolean added;
		String name, code, schoolName;
		do {
			added = false;
			while (!added) {
				name = fullNameList[i];
				code = getCode();
				schoolName = schoolList[i];
				fresherList.add(new Fresher(code, name, i, schoolName));
				i++;
				added = true;

			}
		} while (i < 3);

		initDumpComment();
		initDumpDepartment();
		initDumpQuizPoint();
		initDumpMPP();
		rw.writeToFile(DEPARTMENT_FILE_NAME, departmentList);
		rw.writeToFile(FRESHER_FILE_NAME, fresherList);
		rw.writeToFile(QUIZ_FILE_NAME, quizList);
	}

	static void readDataFromFile() {
		quizList = rw.readListFromFile(Quiz.class, QUIZ_FILE_NAME);
		departmentList = rw.readListFromFile(Department.class, DEPARTMENT_FILE_NAME);
		fresherList = rw.readListFromFile(Fresher.class, FRESHER_FILE_NAME);

	}
	// ***********END DUMP DATA
	// ***********METHODS*****************************************************************/

	// ***********CHECK
	// ***********METHODS*****************************************************************/

	static boolean isAboveOrEqualFive(Fresher f) {
		if (f.getAveragePoint() >= 5)
			return true;
		return false;
	}

	static boolean isAboveOrEqualEight(Fresher f) {
		if (f.getMockProjectpoint() >= 8)
			return true;
		return false;
	}

	static boolean isNotNull(String s) {
		if (s == "" || s == null)
			return false;
		return true;
	}
	// ***********END CHECK
	// ***********METHODS*********************************************************/

	// ***********DISPLAY
	// ***********METHODS*********************************************************/

	static void displayAllFresher() {
		System.out.println(
				"============================================================================================");
		System.out.println("List of All Fresher.\n");
		System.out.printf("%-5s| %-10s\t| %-20s| %-20s| %s\n", "ID", "CODE", "FULL NAME", "SCHOOL NAME", "DEPARTMENT");
		System.out.println(
				"--------------------------------------------------------------------------------------------");
		for (Fresher s : fresherList) {
			System.out.println(s.withDepartmenttoString());
		}
	}

	static void displayAllQuiz() {
		System.out.println(
				"============================================================================================");
		for (Quiz q : quizList) {
			System.out.println(q.toString());
		}
	}

	static void displayFresherQPAboveOrEqualFive() {
		int k = 0;
		System.out.println(
				"============================================================================================");
		System.out.println("List of all fresher have average quizzes point is 5 or more.\n");
		System.out.printf("%-5s| %-10s\t| %-20s| %-20s| %s\n", "ID", "CODE", "FULL NAME", "SCHOOL NAME",
				"AVERAGE POINT");
		System.out.println(
				"--------------------------------------------------------------------------------------------");
		for (Fresher s : fresherList) {
			if (!isAboveOrEqualFive(s)) {
				k++;
			} else if (isAboveOrEqualFive(s)) {
				System.out.println(s.toString() + String.format("| %d", s.getAveragePoint()));
			}
			if (k == fresherList.size()) {
				System.out.println("No Fresher made into this List");
			}
		}
	}

	static void displayFresherQPAboveOrEqualEight() {
		int k = 0;
		System.out.println(
				"============================================================================================");
		System.out.println("List of all fresher have mock project’s point is 8 or more.\n");
		System.out.printf("%-5s| %-10s\t| %-20s| %-21s| %s\n", "ID", "CODE", "FULL NAME", "SCHOOL NAME",
				"MOCKPROJECT's POINT");
		System.out.println(
				"--------------------------------------------------------------------------------------------");
		for (Fresher s : fresherList) {
			if (!isAboveOrEqualEight(s)) {
				k++;
			} else if (isAboveOrEqualEight(s)) {
				System.out.println(s.toString() + String.format("| %d", s.getMockProjectpoint()));
			}
			if (k == fresherList.size()) {
				System.out.println("No Fresher made into this List");
			}
		}
	}

	static void displayFresherAscQP() {
		Collections.sort(fresherList, Fresher.ascQuizPoint);
		System.out.println(
				"============================================================================================");
		System.out.println("Display fresher information in ascending order of average quizzes point.\n");
		System.out.printf("%-5s| %-10s\t| %-20s| %-20s| %s\n", "ID", "CODE", "FULL NAME", "SCHOOL NAME",
				"AVERAGE POINT");
		System.out.println(
				"--------------------------------------------------------------------------------------------");
		for (Fresher s : fresherList) {
			System.out.println(s.toString() + String.format("| %d", s.getAveragePoint()));
		}
	}

	static void displayFresherDscMPP() {
		Collections.sort(fresherList, Fresher.dscMockProjectPoint);
		System.out.println(
				"============================================================================================");
		System.out.println("Display fresher information in descending order of mock project’s point.\n");
		System.out.printf("%-5s| %-10s\t| %-20s| %-19s| %s\n", "ID", "CODE", "FULL NAME", "SCHOOL NAME",
				"MOCKPROJECT's POINT");
		System.out.println(
				"--------------------------------------------------------------------------------------------");
		for (Fresher s : fresherList) {
			System.out.println(s.toString() + "| " + s.getMockProjectpoint());
		}

	}

	static void displayAllDepartment() {
		System.out.println(
				"============================================================================================");
		System.out.printf("%-5s| %-10s| %-20s\n", "CODE", "SHORT NAME", "NAME");
		System.out.println("----------------------------------------------------------------");
		for (Department d : departmentList) {
			System.out.println(d.toString());
		}
	}

	static void displayQuiz() {
		System.out.println(
				"============================================================================================");
		System.out.printf("%-5s| %s\n", "CODE", "POINT");
		System.out.println("----------------------------------------------------------------");
		for (Quiz q : quizList) {
			System.out.println(q.toString());
		}
	}

	static void displayFresherQuizPoint(int i) {
		System.out.println(
				"============================================================================================");
		System.out.printf("%-5s| %-10s\n", "CODE", "POINT");
		System.out.println("----------------------------------------------------------------");
		for (QuizPoint p : fresherList.get(i).getQuizPointList()) {
			System.out.println(p.toString());
		}
	}

	static void displayFresherComment(int i) {
		for (Comment c : fresherList.get(i).getCommentList()) {
			System.out.println(c.toString());
		}
	}

	static void displayAllOfFresher() {
		for (Fresher s : fresherList) {
			System.out.println(s.toString());
			System.out.println(
					"--------------------------------------------------------------------------------------------");
			System.out.printf("%-5s| %-10s\t| %-21s\n", "Code", "Short Name", "Name");
			System.out.println(s.getDepartment().toString());

			System.out.println(
					"--------------------------------------------------------------------------------------------");
			System.out.printf("%-5s| %-10s\n", "Code", "Point");
			for (QuizPoint p : s.getQuizPointList()) {
				System.out.println(p.toString());
			}

			System.out.println(
					"--------------------------------------------------------------------------------------------");
			for (Comment c : s.getCommentList()) {
				System.out.println(c.toString());
			}
			System.out.println(
					"********************************************************************************************");
		}

		for (Quiz q : quizList) {
			System.out.println(q.toString());
		}
	}

	// ***********END DISPLAY
	// ***********METHODS*********************************************************/

	// ***********ADDING
	// ***********METHODS*********************************************************/

	static void addFresher() throws InputMismatchException {
		boolean added;
		System.out.printf("Quantity: ");
		int i = sc.nextInt();
		sc.nextLine();
		for (int j = 0; j < i; j++) {
			added = false;
			String code = getCode();
			int id = fresherList.size();
			while (!added) {
				System.out.printf("Type in Fresher full name: ");
				String fullName = sc.nextLine();
				System.out.printf("Type in Fresher school name: ");
				String schoolName = sc.nextLine();
				if (!fullName.isEmpty() && !schoolName.isEmpty()) {
					fresherList.add(new Fresher(code, fullName, id, schoolName));
					rw.writeToFile(FRESHER_FILE_NAME, fresherList);
					System.out.println("New Fresher has been added successfully.");
					added = true;
				} else {
					System.err.println("Full name and school name can't be null.");
				}
			}
		}
	}

	static void addQuiz() throws InputMismatchException {
		boolean added;
		System.out.printf("Quantity: ");
		int i = sc.nextInt();
		sc.nextLine();

		for (int j = 0; j < i; j++) {
			String code = "Q" + (quizList.size() + 1);
			added = false;
			while (!added) {
				System.out.printf("Type in Quiz's Name: ");
				String quizName = sc.nextLine();
				System.out.printf("Type in Quiz's Content: ");
				String quizContent = sc.nextLine();
				if (!quizName.isEmpty() && !quizContent.isEmpty()) {
					quizList.add(new Quiz(code, quizName, quizContent));
					rw.writeToFile(QUIZ_FILE_NAME, quizList);
					System.out.println("New Quiz has been added successfully");
					added = true;
				} else {
					System.err.println("Quiz's Name and Quiz's Content can't be null.");
				}
			}
		}
	}

	static void addDepartment() throws InputMismatchException {
		boolean added;
		System.out.printf("Quantity: ");
		int i = sc.nextInt();
		sc.nextLine();
		for (int j = 0; j < i; j++) {
			added = false;
			String code = "D" + departmentList.size();
			while (!added) {
				System.out.printf("Type in Department's Name: ");
				String dName = sc.nextLine();
				System.out.printf("Type in Department's Short Name: ");
				String dSName = sc.nextLine();
				if (!dName.isEmpty() && !dSName.isEmpty()) {
					departmentList.add(new Department(code, dName, dSName));
					rw.writeToFile(DEPARTMENT_FILE_NAME, departmentList);
					System.out.println("New Department has been added successfully");
					added = true;
				} else {
					System.err.println("Department's Name and Department's Short Name can't be null.");
				}
			}
		}
	}

	// ***********END ADDING
	// ***********METHODS*********************************************************/

	// ***********UPDATE
	// ***********METHODS*********************************************************/
	static void updateFresherFullName() throws InputMismatchException {
		displayAllFresher();
		System.out.println(
				"********************************************************************************************");
		boolean updated = false;
		boolean err = true;
		while (err) {
			try {
				System.out.printf("Type in Fresher's ID: ");
				int id = sc.nextInt();
				if (id < fresherList.size()) {
					System.out.println("Fresher's Full Name: " + fresherList.get(id).getFullName());
					sc.nextLine();
					while (!updated) {
						System.out.printf("Type in Fresher's new Full Name: ");
						String fullName = sc.nextLine();
						if (!fullName.isEmpty()) {
							fresherList.get(id).setFullName(fullName);
							rw.writeToFile(FRESHER_FILE_NAME, fresherList);
							System.out.println("Fresher's Full Name has been updated successfully.");
							updated = true;
						} else {
							System.err.println("Full name can't be null.");
						}
					}
					err = false;
				} else {
					System.err.println("This Fresher is not exists.");
					err = true;
				}
			} catch (InputMismatchException ie) {
				System.err.println("Type in Integer number only");
				sc.next();
				err = true;
			}
		}
	}

	static void updateFresherCode() throws InputMismatchException {
		displayAllFresher();
		System.out.println(
				"********************************************************************************************");
		boolean updated = false;
		boolean err = true;
		String codeLetter = "F";
		while (err) {
			try {
				System.out.printf("Type in Fresher's ID: ");
				int id = sc.nextInt();
				if (id < fresherList.size()) {
					System.out.println("Fresher's Code: " + fresherList.get(id).getCode());
					sc.nextLine();
					while (!updated) {
						try {
							System.out.printf("Type in Fresher's new Code: ");
							int codeNumber = sc.nextInt();
							if (codeNumber >= 1000000 && codeNumber < 10000000) {
								String code = codeLetter + codeNumber;
								fresherList.get(id).setCode(code);
								rw.writeToFile(FRESHER_FILE_NAME, fresherList);
								System.out.println("Fresher's Code has been updated successfully.");
								updated = true;
							} else {
								System.err.println("Type in 7 digits Integer number only");
							}
						} catch (InputMismatchException ie) {
							System.err.println("Type in 7 digits Integer number only");
							sc.next();
						}
					}
					err = false;
				} else {
					System.err.println("This Fresher is not exists.");
					err = true;
				}
			} catch (InputMismatchException ie) {
				System.err.println("Type in Integer number only");
				sc.next();
				err = true;
			}
		}

	}

	static void updateFresherDepartment() throws InputMismatchException {
		displayAllFresher();
		System.out.println(
				"********************************************************************************************");
		boolean updated = false;
		boolean err = true;
		while (err) {
			try {
				System.out.printf("Type in Fresher's ID: ");
				int id = sc.nextInt();
				if (id < fresherList.size()) {
					displayAllDepartment();
					System.out.println(
							"********************************************************************************************");
					System.out.println("Fresher's Department: " + fresherList.get(id).getDepartment().getName());
					sc.nextLine();
					while (!updated) {
						try {
							System.out.printf("Type in Fresher's new Department's Code: D");
							int codeNumber = sc.nextInt();
							if (codeNumber < departmentList.size()) {
								Department d = departmentList.get(codeNumber);
								fresherList.get(id).setDepartment(d);
								rw.writeToFile(FRESHER_FILE_NAME, fresherList);
								System.out.println("Fresher's Department has been updated successfully.");
								updated = true;
							} else {
								System.err.println("This Department not exists");
							}
						} catch (InputMismatchException ie) {
							System.err.println("Type Integer number only");
							sc.next();
						}
					}
					err = false;
				} else {
					System.err.println("This Fresher is not exists.");
					err = true;
				}
			} catch (InputMismatchException ie) {
				System.err.println("Type in Integer number only");
				sc.next();
				err = true;
			}
		}

	}

	static void updateFresherSchool() throws InputMismatchException {
		displayAllFresher();
		System.out.println(
				"********************************************************************************************");
		boolean updated = false;
		boolean err = true;
		while (err) {
			try {
				System.out.printf("Type in Fresher's ID: ");
				int id = sc.nextInt();
				if (id < fresherList.size()) {
					System.out.println("Fresher's School Name: " + fresherList.get(id).getSchoolName());
					sc.nextLine();

					while (!updated) {
						System.out.printf("Type in Fresher's new School Name: ");
						String schoolName = sc.nextLine();
						if (!schoolName.isEmpty()) {
							fresherList.get(id).setSchoolName(schoolName);
							rw.writeToFile(FRESHER_FILE_NAME, fresherList);
							System.out.println("Fresher's School Name has been updated successfully.");
							updated = true;
						} else {
							System.err.println("School name can't be null.");
						}
					}
					err = false;
				} else {
					System.err.println("This Fresher is not exists.");
					err = true;
				}
			} catch (InputMismatchException ie) {
				System.err.println("Type in Integer number only");
				sc.next();
				err = true;
			}
		}

	}

	static void updateFresherQuizPoint() throws InputMismatchException {
		displayAllFresher();
		System.out.println(
				"********************************************************************************************");
		boolean updated = false;
		boolean err = true;
		while (err) {
			try {
				System.out.printf("Type in Fresher's ID: ");
				int id = sc.nextInt();
				if (id < fresherList.size()) {
					displayFresherQuizPoint(id);
					System.out.println(
							"********************************************************************************************");
					sc.nextLine();
					while (!updated) {
						try {
							System.out.printf("Type in Fresher's Quiz's Code: Q");
							int codeNumber = sc.nextInt();
							if (codeNumber < (quizList.size() + 1) && codeNumber > 0) {
								System.out.printf("Type in Fresher's Quiz's Point: ");
								int point = sc.nextInt();
								if (point >= 0 && point <= 10) {
									fresherList.get(id).getQuizPointList().get((codeNumber - 1)).setPoint(point);
									rw.writeToFile(FRESHER_FILE_NAME, fresherList);
									System.out.println("Fresher's Department has been updated successfully.");
									displayFresherQuizPoint(id);
									updated = true;
								} else {
									System.err.println("Point must be Integer and 0<=Point<=10");
								}
							} else {
								System.err.println("This Quiz is not exists");
							}
						} catch (InputMismatchException ie) {
							System.err.println("Type Integer number only");
							sc.next();
						}
					}
					err = false;
				} else {
					System.err.println("This Fresher is not exists.");
					err = true;
				}
			} catch (InputMismatchException ie) {
				System.err.println("Type in Integer number only");
				sc.next();
				err = true;
			}
		}
	}

	static void updateFresherMPP() {
		displayAllFresher();
		System.out.println(
				"********************************************************************************************");
		boolean updated = false;
		boolean err = true;
		while (err) {
			try {
				System.out.printf("Type in Fresher's ID: ");
				int id = sc.nextInt();
				if (id < fresherList.size()) {
					System.out.println("Fresher's Mock Project's Point: " + fresherList.get(id).getMockProjectpoint());
					sc.nextLine();
					while (!updated) {
						try {
							System.out.printf("Type in Fresher's new Mock Project's Point: ");
							int point = sc.nextInt();
							if (point >= 0 && point <= 10) {
								fresherList.get(id).setMockProjectpoint(point);
								rw.writeToFile(FRESHER_FILE_NAME, fresherList);
								System.out.println("Fresher's Mock Project's Point has been updated successfully.");
								updated = true;
							} else {
								System.err.println("Point must be Integer and 0<=Point<=10");
							}
						} catch (InputMismatchException ie) {
							System.err.println("Type Integer number only");
							sc.next();
						}
					}
					err = false;
				} else {
					System.err.println("This Fresher is not exists.");
					err = true;
				}
			} catch (InputMismatchException ie) {
				System.err.println("Type in Integer number only");
				sc.next();
				err = true;
			}
		}
	}

	static void updateFresherComment() throws InputMismatchException {
		displayAllFresher();
		System.out.println(
				"********************************************************************************************");
		boolean updated = false;
		boolean err = true;
		while (err) {
			try {
				System.out.printf("Type in Fresher's ID: ");
				int id = sc.nextInt();
				if (id < fresherList.size()) {
					sc.nextLine();
					while (!updated) {
						System.out.printf("Type in Trainner's new Comment: ");
						String cmt = sc.nextLine();
						if (!cmt.isEmpty()) {
							Date d = new Date();
							Comment c = new Comment(d, cmt);
							fresherList.get(id).getCommentList().add(c);
							rw.writeToFile(FRESHER_FILE_NAME, fresherList);
							System.out.println("Comment has been updated successfully.");
							displayFresherComment(id);
							updated = true;
						} else {
							System.err.println("Comment can't be null.");
						}
					}
					err = false;
				} else {
					System.err.println("This Fresher is not exists.");
					err = true;
				}
			} catch (InputMismatchException ie) {
				System.err.println("Type in Integer number only");
				sc.next();
				err = true;
			}
		}
	}

	static void updateDepartmentName() throws InputMismatchException {
		displayAllDepartment();
		System.out.println(
				"********************************************************************************************");
		boolean updated = false;
		boolean err = true;
		while (err) {
			try {
				System.out.printf("Type in Department's code: D");
				int code = sc.nextInt();
				if (code < departmentList.size()) {
					System.out.println("Department's Full Name: " + departmentList.get(code).getName());
					sc.nextLine();
					while (!updated) {
						System.out.printf("Type in Department new Name: ");
						String name = sc.nextLine();
						if (!name.isEmpty()) {
							departmentList.get(code).setName(name);
							rw.writeToFile(DEPARTMENT_FILE_NAME, departmentList);
							System.out.println("Department Name's has been updated successfully.");
							displayAllDepartment();
							updated = true;
						} else {
							System.err.println("Name can't be null.");
						}
					}
					err = false;
				} else {
					System.err.println("This Department is not exists.");
					err = true;
				}
			} catch (InputMismatchException ie) {
				System.err.println("Type in Integer number only");
				sc.next();
				err = true;
			}
		}
	}

	static void updateDepartmentShortName() throws InputMismatchException {
		displayAllDepartment();
		System.out.println(
				"********************************************************************************************");
		boolean updated = false;
		boolean err = true;
		while (err) {
			try {
				System.out.printf("Type in Department's code: D");
				int code = sc.nextInt();
				if (code < departmentList.size()) {
					System.out.println("Department's Short Name: " + departmentList.get(code).getShortName());
					sc.nextLine();
					while (!updated) {
						System.out.printf("Type in Department new Short Name: ");
						String name = sc.nextLine();
						if (!name.isEmpty()) {
							departmentList.get(code).setShortName(name);
							rw.writeToFile(DEPARTMENT_FILE_NAME, departmentList);
							System.out.println("Department's Short Name has been updated successfully.");
							displayAllDepartment();
							updated = true;
						} else {
							System.err.println("Short Name can't be null.");
						}
					}
					err = false;
				} else {
					System.err.println("This Department is not exists.");
					err = true;
				}
			} catch (InputMismatchException ie) {
				System.err.println("Type in Integer number only");
				sc.next();
				err = true;
			}
		}
	}

	static void updateQuizName() throws InputMismatchException {
		displayAllQuiz();
		System.out.println(
				"********************************************************************************************");
		boolean updated = false;
		boolean err = true;
		while (err) {
			try {
				System.out.printf("Type in Quiz code: Q");
				int code = sc.nextInt();
				if (code < (quizList.size() + 1)) {
					System.out.println("Quiz's Name: " + quizList.get((code) - 1).getQuizName());
					sc.nextLine();
					while (!updated) {
						System.out.printf("Type in Quiz new Name: ");
						String name = sc.nextLine();
						if (!name.isEmpty()) {
							quizList.get((code - 1)).setQuizName(name);
							rw.writeToFile(QUIZ_FILE_NAME, quizList);
							System.out.println("Quiz's Name has been updated successfully.");
							updated = true;
						} else {
							System.err.println("Name can't be null.");
						}
					}
					err = false;
				} else {
					System.err.println("This Quiz is not exists.");
					err = true;
				}
			} catch (InputMismatchException ie) {
				System.err.println("Type in Integer number only");
				sc.next();
				err = true;
			}
		}
	}

	static void updateQuizContent() throws InputMismatchException {
		displayAllQuiz();
		System.out.println(
				"********************************************************************************************");
		boolean updated = false;
		boolean err = true;
		while (err) {
			try {
				System.out.printf("Type in Quiz code: Q");
				int code = sc.nextInt();
				if (code < (quizList.size() + 1)) {
					System.out.println("Quiz's Name: " + quizList.get((code) - 1).getContent());
					sc.nextLine();
					while (!updated) {
						System.out.printf("Type in Quiz new Name: ");
						String cont = sc.nextLine();
						if (!cont.isEmpty()) {
							quizList.get((code - 1)).setQuizName(cont);
							rw.writeToFile(QUIZ_FILE_NAME, quizList);
							System.out.println("Quiz's Content has been updated successfully.");
							updated = true;
						} else {
							System.err.println("Content can't be null.");
						}
					}
					err = false;
				} else {
					System.err.println("This Quiz is not exists.");
					err = true;
				}
			} catch (InputMismatchException ie) {
				System.err.println("Type in Integer number only");
				sc.next();
				err = true;
			}
		}
	}

	// ***********END UPDATE
	// ***********METHODS*********************************************************/

	// ************MENU METHODS********************************************/

	static void printDisplayMenu() {
		System.out.println(
				"============================================================================================");
		System.out.println("DISPLAY MENU");
		System.out.println("1. All of fresher.");
		System.out.println("2. All the department.");
		System.out.println("3. All of quiz.");
		System.out.println("4. All of fresher have average quizzes point is 5 or more.");
		System.out.println("5. All of fresher have mock project’s point is 8 or more.");
		System.out.println("6. Display fresher information in ascending order of average quizzes point.");
		System.out.println("7. Display fresher information in descending order of mock project’s point.");
		System.out.println("8. Return to Main Menu.");
		System.out.println("0. Quit.");
	}

	static void printAddingMenu() {
		System.out.println(
				"============================================================================================");
		System.out.println("ADDING MENU");
		System.out.println("1. Add new Fresher.");
		System.out.println("2. Add new Quiz.");
		System.out.println("3. add new Department.");
		System.out.println("4. Return to Main Menu.");
		System.out.println("0. Quit.");
	}

	static void printUpdateMenu() {
		System.out.println(
				"============================================================================================");
		System.out.println("UPDATE MENU");
		System.out.println("1. Fresher.");
		System.out.println("2. Department.");
		System.out.println("3. Quiz");
		System.out.println("4. Return to Main Menu.");
		System.out.println("0. Quit.");
	}

	static void printUpdateFresherMenu() {
		System.out.println(
				"============================================================================================");
		System.out.println("UPDATE FRESHER MENU");
		System.out.println("1. Full Name.");
		System.out.println("2. Code.");
		System.out.println("3. Department.");
		System.out.println("4. School's name.");
		System.out.println("5. Quiz's point.");
		System.out.println("6. Mock project's point.");
		System.out.println("7. Trainer's commnet.");
		System.out.println("8. Return to Update Menu.");
		System.out.println("0. Quit.");
	}

	static void printUpdateDepartmentMenu() {
		System.out.println(
				"============================================================================================");
		System.out.println("UPDATE DEPARTMENT MENU");
		System.out.println("1. Update Department's name.");
		System.out.println("2. Update Department's short name.");
		System.out.println("3. Return to Update Menu.");
		System.out.println("0. Quit.");
	}

	static void printUpdateQuizMenu() {
		System.out.println(
				"============================================================================================");
		System.out.println("UPDATE QUIZ MENU");
		System.out.println("1. Name.");
		System.out.println("2. Content.");
		System.out.println("3. Return to Update Menu");
		System.out.println("0. Quit.");
	}

	static void printMainMenu() {
		System.out.println(
				"============================================================================================");
		System.out.println("MAIN MENU");
		System.out.println("1. Display Information Menu.");
		System.out.println("2. Adding Menu.");
		System.out.println("3. Update Information Menu.");
		System.out.println("0. Quit.");
	}

	static void displayMenu() throws InputMismatchException {
		int option;
		do {
			printDisplayMenu();
			System.out.printf("Type in Number in the Menu: ");
			option = sc.nextInt();
			switch (option) {
			case 0:
				System.exit(0);
				break;
			case 1:
				displayAllFresher();
				break;
			case 2:
				displayAllDepartment();
				break;
			case 3:
				displayAllQuiz();
				break;
			case 4:
				displayFresherQPAboveOrEqualFive();
				break;
			case 5:
				displayFresherQPAboveOrEqualEight();
				break;
			case 6:
				displayFresherAscQP();
				break;
			case 7:
				displayFresherDscMPP();
				break;
			case 8:
				break;

			default:
				System.out.println("Wrong input, please retype.");
				break;
			}

		} while (option != 8);
	}

	static void addingMenu() throws InputMismatchException {
		int option;
		boolean error;
		do {
			printAddingMenu();
			System.out.printf("Type in Number in the Menu: ");
			option = sc.nextInt();
			switch (option) {
			case 0:
				System.exit(0);
				break;
			case 1:
				error = true;
				do {
					try {
						addFresher();
						error = false;
					} catch (InputMismatchException ie) {
						System.err.println("\nType in number only");
						error = true;
						sc.next();
					}
				} while (error);
				break;

			case 2:
				error = true;
				do {
					try {
						addQuiz();
						error = false;
					} catch (InputMismatchException ie) {
						System.err.println("\nType in number only");
						error = true;
						sc.next();
					}
				} while (error);
				break;
			case 3:
				error = true;
				do {
					try {

						addDepartment();
						error = false;
					} catch (InputMismatchException ie) {
						System.err.println("\nType in number only");
						error = true;
						sc.next();
					}
				} while (error);
				break;
			case 4:
				break;
			default:
				System.out.println("Wrong input, please retype.");
				break;
			}
		} while (option != 4);
	}

	static void updateFresherMenu() throws InputMismatchException {
		int option;
		do {
			printUpdateFresherMenu();
			System.out.printf("Type in Number in the Menu: ");
			option = sc.nextInt();
			switch (option) {
			case 0:
				System.exit(0);
				break;
			case 1:
				updateFresherFullName();
				break;
			case 2:
				updateFresherCode();
				break;
			case 3:
				updateFresherDepartment();
				break;
			case 4:
				updateFresherSchool();
				break;
			case 5:
				updateFresherQuizPoint();
				break;
			case 6:
				updateFresherMPP();
				break;
			case 7:
				updateFresherComment();
				break;
			case 8:
				break;

			default:
				System.out.println("Wrong input, please retype.");
				break;
			}

		} while (option != 8);
	}

	static void updateDepartmentMenu() {
		int option;
		boolean error;
		do {
			printUpdateDepartmentMenu();
			System.out.printf("Type in Number in the Menu: ");
			option = sc.nextInt();
			switch (option) {
			case 0:
				System.exit(0);
				break;
			case 1:
				error = true;
				do {
					try {
						updateDepartmentName();
						error = false;
					} catch (InputMismatchException ie) {
						System.err.println("Type in number only");
						error = true;
						sc.next();
					}
				} while (error);
				break;
			case 2:
				error = true;
				do {
					try {
						updateDepartmentShortName();
						error = false;
					} catch (InputMismatchException ie) {
						System.err.println("Type in number only");
						error = true;
						sc.next();
					}
				} while (error);
				break;
			case 3:
				break;
			default:
				System.out.println("Wrong input, please retype.");
				break;
			}
		} while (option != 3);
	}

	static void updateQuizMenu() {
		int option;
		boolean error;
		do {
			printUpdateQuizMenu();
			System.out.printf("Type in Number in the Menu: ");
			option = sc.nextInt();
			switch (option) {
			case 0:
				System.exit(0);
				break;
			case 1:
				error = true;
				do {
					try {
						updateQuizName();
						error = false;
					} catch (InputMismatchException ie) {
						System.err.println("Type in number only");
						error = true;
						sc.next();
					}
				} while (error);
				break;
			case 2:
				error = true;
				do {
					try {
						updateQuizContent();
						error = false;
					} catch (InputMismatchException ie) {
						System.err.println("Type in number only");
						error = true;
						sc.next();
					}
				} while (error);
				break;
			case 3:
				break;
			default:
				System.out.println("Wrong input, please retype.");
				break;
			}
		} while (option != 3);
	}

	static void UpdateMenu() throws InputMismatchException {
		int option;
		boolean error;
		do {
			printUpdateMenu();
			System.out.printf("Type in Number in the Menu: ");
			option = sc.nextInt();
			switch (option) {
			case 0:
				System.exit(0);
				break;
			case 1:
				error = true;
				do {
					try {
						updateFresherMenu();
						error = false;
					} catch (InputMismatchException ie) {
						System.err.println("Type in number only");
						error = true;
						sc.next();
					}
				} while (error);
				break;
			case 2:
				error = true;
				do {
					try {
						updateDepartmentMenu();
						error = false;
					} catch (InputMismatchException ie) {
						System.err.println("Type in number only");
						error = true;
						sc.next();
					}
				} while (error);
				break;
			case 3:
				error = true;
				do {
					try {
						updateQuizMenu();
						error = false;
					} catch (InputMismatchException ie) {
						System.err.println("Type in number only");
						error = true;
						sc.next();
					}
				} while (error);
				break;
			case 4:
				break;
			default:
				System.out.println("Wrong input, please retype.");
				break;
			}
		} while (option != 4);
	}

	static void mainMenu() throws InputMismatchException {
		int option;
		boolean error;
		do {
			printMainMenu();
			System.out.printf("Type in Number in the Menu: ");
			option = sc.nextInt();
			switch (option) {
			case 0:
				System.exit(0);
				break;
			case 1:
				error = true;
				do {
					try {
						displayMenu();
						error = false;
					} catch (InputMismatchException ie) {
						System.err.println("Type in number only");
						error = true;
						sc.next();
					}
				} while (error);
				break;
			case 2:
				error = true;
				do {
					try {
						addingMenu();
						error = false;
					} catch (InputMismatchException ie) {
						System.err.println("Type in number only");
						error = true;
						sc.next();
					}
				} while (error);
				break;
			case 3:
				error = true;
				do {
					try {
						UpdateMenu();
						error = false;
					} catch (InputMismatchException ie) {
						System.err.println("Type in number only");
						error = true;
						sc.next();
					}
				} while (error);
				break;
			case 4:
				break;
			default:
				System.out.println("Wrong input, please retype.");
				break;
			}
		} while (option != 4);
	}

	// ************END MENU
	// ************METHODS*******************************************************/

	public static void main(String[] args) {

		// dumpData();
		// initDumpData();
		readDataFromFile();
		boolean error;
		do {
			try {
				mainMenu();
				error = false;
			} catch (InputMismatchException ie) {
				System.err.println("Type in number only");
				error = true;
				sc.next();
			}
		} while (error);
	}

}
