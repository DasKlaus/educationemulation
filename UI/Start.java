//package UI;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//
//import Main.Course;
//import Main.Grade;
//import Main.School;
//import Main.Staff;
//import Main.Student;
//import Staff.Teacher;
//
//public class Start{
//	public static School school;
//	
//	public static void main(String[] args) {
//		school = new School();
//		school.init();
//		showTick();
//	}
//	
//	public static void headline() {
//		System.out.println((int) Math.floor(school.time/12) + "-" + school.time%12 + "      Schüler: " + school.students.size() + "      Angestellte: " + school.staff.size() + "      (Enter) nächster Monat       (0) zurück");
//	}
//	
//	public static void showTick() {
//		headline();
//		// Main Menu
//		System.out.println("(1) Schüler anzeigen       (2) Angestellte anzeigen       (3) Kurse anzeigen");
//		for (int i=0; i<school.log.size(); i++) {
//			if (school.log.get(i).length()==1) {
//				System.out.print(school.log.get(i));
//			} 
//			else {
//				System.out.println(school.log.get(i));
//			}
//		}
//		school.log = new ArrayList<String>();
//		// all the happenings!!! (how to see the grades?) (Noten einfach hintereinanderweg anzeigen, Liste der Ereignisse mit Pause durchgehen (Pause aus Gesamtzeit berechnen?)
//		int action = getInput(3);
//		switch (action) {
//		case -1: school.tick();
//				showTick();
//				break;
//		case 0: showTick();
//				break;
//		case 1: showStudents();
//				break;
//		case 2: showStaff();
//				break;
//		case 3: showCourses();
//				break;
//		default: showTick();
//				break;
//		}
//	}
//	
//	public static void showStudents() {
//		headline();
//		for (int i=0; i<school.students.size(); i++) {
//			System.out.println(fill("("+Integer.toString(i+1)+") ", 6) + 
//					fill(school.students.get(i).name, 20) + 
//					fill(Integer.toString((int) Math.floor(school.students.get(i).age/12))+" Jahre", 10) +
//					"   IQ: " + fillLeft(Integer.toString(school.students.get(i).intelligence.value+100), 3) +
//					"      Charisma: " + fillLeft(Integer.toString(school.students.get(i).charisma.value), 3) +
//					"      Depression: " + fillLeft(Integer.toString(school.students.get(i).depression.value), 3) +
//					"      Agression: " + fillLeft(Integer.toString(school.students.get(i).agression.value), 3) +
//					"      Motivation: " + fillLeft(Integer.toString(school.students.get(i).motivation.value), 3));
//		}
//		int action = getInput(school.students.size());
//		switch (action) {
//		case -1: school.tick();
//				showTick();
//				break;
//		case 0: showTick();
//				break;
//		default: showStudent(school.students.get(action-1));
//				break;
//		}
//	}
//	
//	public static void showStaff() {
//		headline();
//		for (int i=0; i<school.staff.size(); i++) {
//			System.out.println(fill("("+Integer.toString(i+1)+") ", 6) + 
//					fill(school.staff.get(i).name, 20) + 
//					"   " + fill(((Teacher) school.staff.get(i)).subjects.toString(), 20) +
//					"      Charisma: " + fillLeft(Integer.toString(((Teacher) school.staff.get(i)).likeability.value), 3) +
//					"      Disziplin: " + fillLeft(Integer.toString(((Teacher) school.staff.get(i)).discipline.value), 3) +
//					"      Didaktik: " + fillLeft(Integer.toString(((Teacher) school.staff.get(i)).didactic.value), 3));
//		}
//		int action = getInput(school.staff.size());
//		switch (action) {
//		case -1: school.tick();
//				showTick();
//				break;
//		case 0: showTick();
//				break;
//		default: showEmployee(school.staff.get(action-1));
//				break;
//		}
//	}
//	
//	public static void showCourses() {
//		headline();
//		for (int i=0; i<school.courses.size(); i++) {
//			System.out.println(fill("("+Integer.toString(i+1)+") ", 6) + 
//					fill(school.courses.get(i).subject.name+" "+(int) (Math.floor(school.courses.get(i).level/36)+1)+school.courses.get(i).name, 17) +
//					fill("mit "+school.courses.get(i).teacher.name, 25) +
//					fill("Level: "+school.courses.get(i).level, 12) +
//					fill("Schüler: "+school.courses.get(i).students.size(), 15) +
//					fill("Schnitt: "+Grade.average(school.courses.get(i)), 15)
//					);
//		}
//		int action = getInput(school.courses.size());
//		switch (action) {
//		case -1: school.tick();
//				showTick();
//				break;
//		case 0: showTick();
//				break;
//		default: showCourse(school.courses.get(action-1));
//				break;
//		}
//	}
//	
//	public static void showCourse(Course course) {
//		headline();
//		System.out.println(course.subject.name+" "+(int) (Math.floor(course.level/36)+1)+course.name+"   mit "+course.teacher.name+"    Level: "+course.level+"    Schnitt: "+Grade.average(course));
//		for (int i=0; i<course.students.size(); i++) {
//			System.out.print(
//					fill("("+Integer.toString(i+1)+") ", 6)+
//					fill(course.students.get(i).name, 20)+
//					"Schnitt: "+fill(Double.toString(Grade.average(course.students.get(i))), 10)+
//					"Level: "+fill(Integer.toString(course.students.get(i).getKnowledge(course.subject).value),6)+
//					"Talent: "+fill(Integer.toString(course.students.get(i).getTalent(course.subject).value),6)
//					);
//			String igrades = "";
//			for (int j=0; j<course.students.get(i).grades.size(); j++) {
//				if (course.students.get(i).grades.get(j).subject == course.subject) {
//					igrades += course.students.get(i).grades.get(j).value+" ";
//				}
//			}
//			System.out.println("Noten: "+igrades);
//		}
//		int action = getInput(course.students.size());
//		switch (action) {
//		case -1: school.tick();
//				showTick();
//				break;
//		case 0: showCourses();
//				break;
//		default: showStudent(course.students.get(action-1));
//				break;
//		}
//	}
//	
//	public static void showEmployee(Staff staff) {
//		headline();
//		System.out.print(staff.name+"    ");
//		if (staff.getClass().getName().equals("Teacher")) {
//			Teacher teacher = (Teacher) staff;
//			System.out.println("Lehrer für "+teacher.subjects+"   Didaktik: "+teacher.didactic+"   Disziplin: "+teacher.discipline+"   Charisma: "+teacher.likeability);
//			for (int i=0; i<teacher.courses.size(); i++) {
//				System.out.println(
//						fill("("+Integer.toString(i+1)+") ", 6)+
//						fill(teacher.courses.get(i).subject+" "+(int) (Math.floor(teacher.courses.get(i).level/36)+1)+teacher.courses.get(i).name, 20)+
//						fill("Level: "+teacher.courses.get(i).level, 12) +
//						fill("Schüler: "+teacher.courses.get(i).students.size(), 15) +
//						fill("Schnitt: "+Grade.average(teacher.courses.get(i)), 15)
//				);
//			}
//			int action = getInput(teacher.courses.size());
//			switch(action) {
//			case -1: school.tick();
//					showTick();
//					break;
//			case 0: showTick();
//					break;
//			default: showCourse(teacher.courses.get(action-1));
//					break;
//			}
//		}
//	}
//	
//	public static void showStudent(Student student) {
//		headline();
//		System.out.println(
//				student.name+"   Alter:"+(int) Math.floor(student.age/12)+" Jahre    "+
//				"IQ: "+student.intelligence.value+", "+
//				"Charisma: "+student.charisma.value+", " +
//				"Depression: "+student.depression.value+", " +
//				"Agression: "+student.agression.value+", " +
//				"Motivation: "+student.motivation.value
//				);
//		for (int i=0; i<student.degrees.size(); i++) {
//			System.out.println(student.degrees.get(i));
//		}
//		// System.out.println(student.grades) (current average)
//		int action = getInput(0);
//		switch (action) {
//		case -1: school.tick();
//				showTick();
//				break;
//		case 0: showTick();
//				break;
//		}
//		// each course with grades
//		// show course, back
//	}
//	
//	public static int getInput(int max) {
//		InputStreamReader isr = new InputStreamReader(System.in);
//		BufferedReader br = new BufferedReader(isr);
//		try 
//		{
//			String input = br.readLine();
//			if (input.equals("")) {
//				return -1;
//			}
//			try 
//			{
//				if (Integer.parseInt(input)<0) {
//					return getInput(max);
//				} 
//				if (Integer.parseInt(input)>max) {
//					return getInput(max);
//				}
//				return Integer.parseInt(input);
//			}
//			catch(NumberFormatException e) {
//				return getInput(max);
//			}
//		} catch (Exception e) { return getInput(max);}
//	}
//	
//	public static String fill(String value, int length) {
//        while (value.length() < length) {
//            value += " ";
//        }
//        return value;
//    }
//	
//	public static String fillLeft(String value, int length) {
//		while (value.length() < length) {
//			value = " " + value;
//		}
//		return value;
//	}
//}
