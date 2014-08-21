package Main;

import java.util.ArrayList;

import Main.Helper.Requirement;
import Staff.Teacher;
import UI.GUI;

public class Generator {
	
	public static ArrayList<Student> generateStudents(int number) {
		ArrayList<Student> students = new ArrayList<Student>();
		for (int i=0; i<number; i++) {
			students.add(new Student());
		}
		return students;
	}
	
	public static ArrayList<Staff> generateStaff(int number) {
		ArrayList<Staff> staff = new ArrayList<Staff>();
		for (int i=0;i<number;i++) {
			staff.add(new Teacher());
		}
		return staff;
	}
	
	public static ArrayList<Subject> generateSubjects() {
		ArrayList<Subject> subjects = new ArrayList<Subject>();
		subjects.add(new Subject("Deutsch", 360, new ArrayList<Requirement>())); // 0
		subjects.add(new Subject("Mathe", 360, new ArrayList<Requirement>())); // 1
		subjects.add(new Subject("Musik", 288, new ArrayList<Requirement>())); // 2
		subjects.add(new Subject("Kunst", 288, new ArrayList<Requirement>())); // 3
		subjects.add(new Subject("Heimatkunde", 144, new ArrayList<Requirement>())); //4
		ArrayList<Requirement> requirementsEnglisch = new ArrayList<Requirement>();
		requirementsEnglisch.add(new Requirement(144, subjects.get(0)));
		subjects.add(new Subject("Englisch", 288, requirementsEnglisch)); // 5
		ArrayList<Requirement> requirementsPhysik = new ArrayList<Requirement>();
		requirementsPhysik.add(new Requirement(144, subjects.get(1)));
		requirementsPhysik.add(new Requirement(144, subjects.get(4)));
		subjects.add(new Subject("Physik", 180, requirementsPhysik)); // 6
		ArrayList<Requirement> requirementsBiologie = new ArrayList<Requirement>();
		requirementsBiologie.add(new Requirement(144, subjects.get(4)));
		subjects.add(new Subject("Biologie", 180, requirementsBiologie)); // 7
		ArrayList<Requirement> requirementsChemie = new ArrayList<Requirement>();
		requirementsChemie.add(new Requirement(72, subjects.get(6)));
		requirementsChemie.add(new Requirement(72, subjects.get(7)));
		subjects.add(new Subject("Chemie", 180, requirementsChemie)); // 8
		ArrayList<Requirement> requirementsInformatik = new ArrayList<Requirement>();
		requirementsInformatik.add(new Requirement(144, subjects.get(1)));
		subjects.add(new Subject("Informatik", 180, requirementsInformatik)); // 9
		ArrayList<Requirement> requirementsGeografie = new ArrayList<Requirement>();
		requirementsGeografie.add(new Requirement(144, subjects.get(4)));
		subjects.add(new Subject("Geografie", 180, requirementsGeografie)); // 10
		ArrayList<Requirement> requirementsGeschichte = new ArrayList<Requirement>();
		requirementsGeschichte.add(new Requirement(144, subjects.get(0)));
		requirementsGeschichte.add(new Requirement(144, subjects.get(4)));
		subjects.add(new Subject("Geschichte", 180, requirementsGeschichte)); // 11
		ArrayList<Requirement> requirementsSozialkunde = new ArrayList<Requirement>();
		requirementsSozialkunde.add(new Requirement(144, subjects.get(0)));
		requirementsSozialkunde.add(new Requirement(144, subjects.get(4)));
		subjects.add(new Subject("Sozialkunde", 180, requirementsSozialkunde)); // 12
		ArrayList<Requirement> requirementsPhilosophie = new ArrayList<Requirement>();
		requirementsPhilosophie.add(new Requirement(252, subjects.get(0)));
		subjects.add(new Subject("Philosophie", 180, requirementsPhilosophie)); // 13
		// TODO: zweite Fremdsprache, Religion, weitere Fächer
		return subjects;
	}
	
	public static ArrayList<Graduation> generateGraduations() {
		ArrayList<Graduation> graduations = new ArrayList<Graduation>();
		ArrayList<Requirement> compulsoryHigh = new ArrayList<Requirement>();
		compulsoryHigh.add(new Requirement(360, GUI.school.subjects.get(0))); // Deutsch
		compulsoryHigh.add(new Requirement(360, GUI.school.subjects.get(1))); // Mathe
		compulsoryHigh.add(new Requirement(288, GUI.school.subjects.get(5))); // Englisch
		compulsoryHigh.add(new Requirement(144, GUI.school.subjects.get(2))); // Musik
		compulsoryHigh.add(new Requirement(144, GUI.school.subjects.get(3))); // Kunst
		compulsoryHigh.add(new Requirement(108, GUI.school.subjects.get(6)));
		compulsoryHigh.add(new Requirement(108, GUI.school.subjects.get(7)));
		compulsoryHigh.add(new Requirement(108, GUI.school.subjects.get(8)));
		compulsoryHigh.add(new Requirement(108, GUI.school.subjects.get(9)));
		compulsoryHigh.add(new Requirement(108, GUI.school.subjects.get(10)));
		compulsoryHigh.add(new Requirement(108, GUI.school.subjects.get(11)));
		compulsoryHigh.add(new Requirement(108, GUI.school.subjects.get(12)));
		compulsoryHigh.add(new Requirement(108, GUI.school.subjects.get(13)));
		ArrayList<Requirement> electiveHigh = new ArrayList<Requirement>();
		electiveHigh.add(new Requirement(288, GUI.school.subjects.get(2))); // Musik
		electiveHigh.add(new Requirement(288, GUI.school.subjects.get(3))); // Kunst
		electiveHigh.add(new Requirement(180, GUI.school.subjects.get(6)));
		electiveHigh.add(new Requirement(180, GUI.school.subjects.get(7)));
		electiveHigh.add(new Requirement(180, GUI.school.subjects.get(8)));
		electiveHigh.add(new Requirement(180, GUI.school.subjects.get(9)));
		electiveHigh.add(new Requirement(180, GUI.school.subjects.get(10)));
		electiveHigh.add(new Requirement(180, GUI.school.subjects.get(11)));
		electiveHigh.add(new Requirement(180, GUI.school.subjects.get(12)));
		electiveHigh.add(new Requirement(180, GUI.school.subjects.get(13)));
		graduations.add(new Graduation("Abitur", compulsoryHigh, electiveHigh, 5));

		ArrayList<Requirement> compulsoryMiddle = new ArrayList<Requirement>();
		compulsoryMiddle.add(new Requirement(288, GUI.school.subjects.get(0))); // Deutsch
		compulsoryMiddle.add(new Requirement(288, GUI.school.subjects.get(1))); // Mathe
		compulsoryMiddle.add(new Requirement(180, GUI.school.subjects.get(5))); // Englisch
		compulsoryMiddle.add(new Requirement(72, GUI.school.subjects.get(6)));
		compulsoryMiddle.add(new Requirement(72, GUI.school.subjects.get(7)));
		compulsoryMiddle.add(new Requirement(72, GUI.school.subjects.get(8)));
		compulsoryMiddle.add(new Requirement(72, GUI.school.subjects.get(9)));
		compulsoryMiddle.add(new Requirement(72, GUI.school.subjects.get(10)));
		compulsoryMiddle.add(new Requirement(72, GUI.school.subjects.get(11)));
		compulsoryMiddle.add(new Requirement(72, GUI.school.subjects.get(12)));
		compulsoryMiddle.add(new Requirement(72, GUI.school.subjects.get(13)));
		ArrayList<Requirement> electiveMiddle = new ArrayList<Requirement>();
		electiveMiddle.add(new Requirement(116, GUI.school.subjects.get(2))); // Musik
		electiveMiddle.add(new Requirement(116, GUI.school.subjects.get(3))); // Kunst
		electiveMiddle.add(new Requirement(180, GUI.school.subjects.get(6)));
		electiveMiddle.add(new Requirement(180, GUI.school.subjects.get(7)));
		electiveMiddle.add(new Requirement(180, GUI.school.subjects.get(8)));
		electiveMiddle.add(new Requirement(180, GUI.school.subjects.get(9)));
		electiveMiddle.add(new Requirement(180, GUI.school.subjects.get(10)));
		electiveMiddle.add(new Requirement(180, GUI.school.subjects.get(11)));
		electiveMiddle.add(new Requirement(180, GUI.school.subjects.get(12)));
		electiveMiddle.add(new Requirement(180, GUI.school.subjects.get(13)));
		graduations.add(new Graduation("Mittlere Reife", compulsoryMiddle, electiveMiddle, 8));
		
		ArrayList<Requirement> compulsoryLow = new ArrayList<Requirement>();
		compulsoryLow.add(new Requirement(180, GUI.school.subjects.get(0))); // Deutsch
		compulsoryLow.add(new Requirement(180, GUI.school.subjects.get(1))); // Mathe
		ArrayList<Requirement> electiveLow = new ArrayList<Requirement>();
		electiveLow.add(new Requirement(288, GUI.school.subjects.get(0))); // Deutsch
		electiveLow.add(new Requirement(288, GUI.school.subjects.get(1))); // Mathe
		electiveLow.add(new Requirement(144, GUI.school.subjects.get(5))); // Englisch
		electiveLow.add(new Requirement(72, GUI.school.subjects.get(2))); // Musik
		electiveLow.add(new Requirement(72, GUI.school.subjects.get(3))); // Kunst
		electiveLow.add(new Requirement(180, GUI.school.subjects.get(6)));
		electiveLow.add(new Requirement(180, GUI.school.subjects.get(7)));
		electiveLow.add(new Requirement(180, GUI.school.subjects.get(8)));
		electiveLow.add(new Requirement(180, GUI.school.subjects.get(9)));
		electiveLow.add(new Requirement(180, GUI.school.subjects.get(10)));
		electiveLow.add(new Requirement(180, GUI.school.subjects.get(11)));
		electiveLow.add(new Requirement(180, GUI.school.subjects.get(12)));
		electiveLow.add(new Requirement(180, GUI.school.subjects.get(13)));
		graduations.add(new Graduation("Hauptschule", compulsoryLow, electiveLow, 5));
		return graduations;
	}
}
