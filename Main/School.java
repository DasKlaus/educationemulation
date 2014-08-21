package Main;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import Main.LogEvent.EventType;
import Main.Helper.Tickable;
import Main.Helper.Ticker;
import Staff.Teacher;

public class School implements Tickable, Observer {

	public Ticker time; // in months since simulation start
	
	public ArrayList<Student> students;
	public ArrayList<Staff> staff;
	public ArrayList<Course> courses;
	public ArrayList<Subject> subjects;
	
	public ArrayList<LogEvent> log;
	
	public ArrayList<Graduation> graduations;

	public int maxStudents;
	public int minStudents;
	
	public School() {
		time = new Ticker();
		time.addObserver(this);
		subjects = new ArrayList<Subject>();
		students = new ArrayList<Student>();
		staff = new ArrayList<Staff>();
		log = new ArrayList<LogEvent>();
		courses = new ArrayList<Course>();
		graduations = new ArrayList<Graduation>();
		maxStudents = 10;
		minStudents = 4;
		// different class sizes for testing
		//maxStudents = 30;
		//minStudents = 14;
		// TODO: set Number of new Students each year
		// TODO: facilities (low priority)
	}
	
	public void init() {
		subjects = Generator.generateSubjects();
		//staff.addAll(Generator.generateStaff(10));
		staff.addAll(Generator.generateStaff(3));
		graduations = Generator.generateGraduations();
		startYear();
	}

	public int getTime() {
		return time.getTime();
	}
	
	public void tick() {
		time.tick();
	}

	public void startYear() {
		Random random = new Random();
		int newStudentCount = random.nextInt(10)+5;
		// different student pool sizes for testing
		// int newStudentCount = random.nextInt(20)+35;
		// int newStudentCount = 60;
		ArrayList<Student> newStudents = Generator.generateStudents(newStudentCount);
		students.addAll(newStudents);
		log.add(new LogEvent(EventType.NEWSTUDENTS, newStudentCount, newStudents));
		for (int i=0; i<subjects.size(); i++) {
			try {
				// TODO: only add eligible students! 
				// TODO: check requirements and find students who don't have this course yet but need it
				courses.add(new Course(Teacher.findTeacher(subjects.get(i)), subjects.get(i), 0, 3, "a", (ArrayList<Student>) newStudents.clone()));
				log.add(new LogEvent(EventType.STARTCOURSE, courses.get(courses.size()-1), true));
			} catch (Exception e) {
				log.add(new LogEvent(EventType.STARTCOURSE, subjects.get(i), false));
			}
		}
		int newTeacherCount = (int) Math.floor(random.nextInt(11)/10);
		staff.addAll(Generator.generateStaff(newTeacherCount));
		// TODO: log new staff
	}

	@Override
	public void update(Observable time, Object newYear) {
		if ((Boolean) newYear) {
			startYear();
		}
	}
}
