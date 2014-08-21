package Main;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import Main.Helper.Modifiable;
import Main.Helper.Tickable;
import Staff.Teacher;
import UI.GUI;

public class Course implements Tickable {
	
	public Teacher teacher;
	public Subject subject;
	public int level; // needed knowledge (level/12*speed).floor = year
	public int speed;
	public String name;
	
	public ArrayList<Student> students;
	
	public Course(Teacher teacher, Subject subject, int level, int speed, String name, ArrayList<Student> students) {
		this.teacher = teacher;
		this.subject = subject;
		this.level = level;
		this.speed = speed;
		this.name = name;
		this.students = students;
		GUI.school.time.addObserver(this);
	}
	
	public void tick() {
		level = level + speed;
				
		for (int i=0;i<students.size();i++) {
			Random random = new Random();
			int grade = (int) Math.round(3 // starting point for grade is 3, all influences weighed randomly
					- (random.nextInt(2)*(new Modifiable().value/10)) // random influence 
					- (random.nextInt(2)*(students.get(i).getTalent(subject).value/10)+teacher.discipline.value) // talent and discipline influence
					- (random.nextInt(2)*(students.get(i).motivation.value+teacher.likeability.value)) // motivation and likeability influence
					- (random.nextInt(2)*((students.get(i).getKnowledge(subject).value-level)/2)+teacher.didactic.value) // knowledge and didactic influence
					- (3-speed)); // speed influence
			// TODO: normalize grades somehow. Too many grades at the ends of the spectrum.
			if (grade < 1) { grade = 1; }
			if (grade > 6) { grade = 6; }
			students.get(i).getKnowledge(subject).value += 3+Math.floor((3-grade)/2);
			students.get(i).grades.add(new Grade(GUI.school.getTime(), this, grade));
		}
	}
	
	public void startYear() {
		if (subject.targetKnowledge<=level) {
			// drop
			GUI.school.courses.remove(this);
			GUI.school.time.deleteObserver(this);
		}
		if (students.size()>GUI.school.maxStudents) {
			// debug output
			// TODO: log
			System.out.println(subject.name+" "+(level/(12*speed)+1)+name+" hat zu viele Schüler.");
			if (speed>3) {
				System.out.println("... und es handelt sich um einen schnellen Kurs.");
			}
			if (speed<3) {
				System.out.println("... und es handelt sich um einen langsamen Kurs.");
			}
			int over = students.size()-GUI.school.maxStudents;
			int free = 0;
			int mostFree = 0;
			ArrayList<Course> parallels = getParallelCourses(this);
			for (int j=0; j<parallels.size(); j++) {
				free += GUI.school.maxStudents-parallels.get(j).students.size();
				if (GUI.school.maxStudents-parallels.get(j).students.size()>mostFree) {
					mostFree = GUI.school.maxStudents-parallels.get(j).students.size();
				}
			}
			if (free>over) {
				// allocate fairly
				while (students.size()>GUI.school.maxStudents) {
					Boolean done = false;
					for (int j=0; j<parallels.size(); j++) {
						if (GUI.school.maxStudents-parallels.get(j).students.size()>=mostFree
								&& !done) {
							parallels.get(j).students.add(students.get(0));
							students.remove(0);
							mostFree--;
							done = true;
						}
					}
				}
				// debug output
				// TODO: log
				System.out.println(subject.name+" "+(level/(12*speed)+1)+name+"s überzählige Schüler wurden auf "+parallels.size()+" Parallelkurse aufgeteilt.");
			} else {
				// split
				// TODO: split in as many courses as necessary!
				ArrayList<Student> half = new ArrayList<Student>();
				for (int i=(int) Math.floor(students.size()/2); i<students.size(); i++) {
					half.add(students.get(i));
				}
				students.removeAll(half);
				Course newCourse = new Course(teacher, subject, level, speed, 
						"abcdefghijklmnopqrstuvwxyz".substring(
								Course.getSemiParallelCourses(this).size()+1, 
								Course.getSemiParallelCourses(this).size()+2
						), 
						half);
				GUI.school.courses.add(newCourse);
				// debug output
				// TODO: log
				System.out.println(subject.name+" "+(level/(12*speed)+1)+name+" wurde aufgeteilt und "+newCourse.name+" gestartet.");
				newCourse.startYear();
				this.startYear();
			}
		}
		else if (students.size()<GUI.school.minStudents) {
			// debug output
			// TODO: log
			System.out.println(subject.name+" "+(level/(12*speed)+1)+name+" hat zu wenig Schüler.");
			int over = students.size();
			int free = 0;
			int mostFree = 0;
			ArrayList<Course> parallels = getParallelCourses(this);
			for (int j=0; j<parallels.size(); j++) {
				free += GUI.school.maxStudents-parallels.get(j).students.size();
				if (GUI.school.maxStudents-parallels.get(j).students.size()>mostFree) {
					mostFree = GUI.school.maxStudents-parallels.get(j).students.size();
				}
			}
			if (free>over) {
				// allocate fairly
				while (students.size()>0) {
					Boolean done = false;
					for (int j=0; j<parallels.size(); j++) {
						if (GUI.school.maxStudents-parallels.get(j).students.size()>=mostFree
								&& !done) {
							parallels.get(j).students.add(students.get(0));
							students.remove(0);
							mostFree--;
							done = true;
						}
					}
				}
				GUI.school.courses.remove(this);
				GUI.school.time.deleteObserver(this);
				// debug output
				// TODO: log
				System.out.println(subject.name+" "+(level/(12*speed)+1)+name+"s Schüler wurden auf "+parallels.size()+" Parallelkurse aufgeteilt.");
			}
		}
		
		// TODO: keep fail quota somewhere for statistics (calculate by log entries?)
	}
	
	public static ArrayList<Course> getParallelCourses(Course course) {
		ArrayList<Course> courses = new ArrayList<Course>();
		for (int i=0; i<GUI.school.courses.size(); i++) {
			if (GUI.school.courses.get(i).subject == course.subject 
					&& GUI.school.courses.get(i).speed == course.speed 
					&& GUI.school.courses.get(i).level == course.level
					&& GUI.school.courses.get(i) != course) {
				courses.add(GUI.school.courses.get(i));
			}
		}
		return courses;
	}
	
	public static ArrayList<Course> getSemiParallelCourses(Course course, int speed) {
		ArrayList<Course> courses = new ArrayList<Course>();
		// get courses that overlap, including parallel courses
		for (int i=0; i<GUI.school.courses.size(); i++) {
			if (GUI.school.courses.get(i).subject == course.subject
					&& GUI.school.courses.get(i).speed == speed
					&& GUI.school.courses.get(i).level > course.level-12*speed
					&& GUI.school.courses.get(i).level < course.level+12*speed) {
				courses.add(GUI.school.courses.get(i));
			}
		}
		return courses;
	}
	
	public static ArrayList<Course> getSemiParallelCourses(Course course) {
		return getSemiParallelCourses(course, course.speed);
	}
	
	public static ArrayList<Course> getCourses(Student student) {
		ArrayList<Course> courses = new ArrayList<Course>();
		for (int i=0; i<GUI.school.courses.size(); i++) {
			if (GUI.school.courses.get(i).students.contains(student)) {
				courses.add(GUI.school.courses.get(i));
			}
		}
		return courses;
	}
	
	public static ArrayList<Course> getCourses(Teacher teacher) {
		ArrayList<Course> courses = new ArrayList<Course>();
		for (int i=0; i<GUI.school.courses.size(); i++) {
			if (GUI.school.courses.get(i).teacher == teacher) {
				courses.add(GUI.school.courses.get(i));
			}
		}
		return courses;
	}
	
	public static ArrayList<Course> getCourses(Subject subject, int speed) {
		ArrayList<Course> courses = new ArrayList<Course>();
		for (int i=0; i<GUI.school.courses.size(); i++) {
			if (GUI.school.courses.get(i).subject == subject
					&& GUI.school.courses.get(i).speed == speed) {
				courses.add(GUI.school.courses.get(i));
			}
		}
		return courses;
	}
	
	public static Course getCourse(Subject subject, int level, int speed) {
		for (int i=0; i<GUI.school.courses.size(); i++) {
			if (GUI.school.courses.get(i).subject == subject
					&& GUI.school.courses.get(i).level == level
					&& GUI.school.courses.get(i).speed == speed) {
				return GUI.school.courses.get(i);
			}
		}
		return null;
	}

	@Override
	public void update(Observable time, Object newYear) {
		tick();
		if ((Boolean) newYear) {
			startYear();
		}
	}
}
