package Main;

import java.util.ArrayList;

public class Grade {
	public int time;
	public int value;
	public Course course;
	
	public Grade(int time, Course course, int value) {
		this.time = time;
		this.course = course;
		this.value = value;
	}
	
	public static double average(ArrayList<Grade> grades) {
		int sum = 0;
		for (int i=0; i<grades.size(); i++) {
			sum += grades.get(i).value;
		}
		if (grades.size()>0) {return sum/grades.size();} else {return 0;}
	}
	
	public static double average(Student student) {
		return average(student.grades);
	}
	
	public static double average(Course course) {
		int count = 0;
		double sum = 0;
		for (int i=0; i<course.students.size(); i++) {
			for (int j=0; j<course.students.get(i).grades.size(); j++) {
				if (course.students.get(i).grades.get(j).course == course) {
					sum += course.students.get(i).grades.get(j).value;
					count++;
				}
			}
		}
		return sum/count;
	}
	
	public static double average(Subject subject, Student student) {
		int count = 0;
		int sum = 0;
		for (int i=0; i<student.grades.size(); i++) {
			if (student.grades.get(i).course.subject == subject) {
				sum += student.grades.get(i).value;
				count++;
			}
		}
		if (count>0) {return sum/count;} else return 0;
	}
	
	public static double average(School school) {
		int count = 0;
		double sum = 0;
		for (int i=0; i<school.students.size(); i++) {
			sum += average(school.students.get(i));
			count ++;
		}
		if (count>0) {return sum/count;} else return 0;
	}
	
	public static ArrayList<Grade> getGrades(Student student, Course course) {
		ArrayList<Grade> grades = new ArrayList<Grade>();
		for (int i=0; i<student.grades.size(); i++) {
			if (student.grades.get(i).course == course) {
				grades.add(student.grades.get(i));
			}
		}
		return grades;
	}
}
