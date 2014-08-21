package Main;

import java.util.ArrayList;

import Staff.Teacher;
import UI.GUI;

public class LogEvent {
	public enum EventType {RETENTION, MOVESLOWER, MOVEFASTER, DROPCOURSE, NEWSTUDENTS, STARTCOURSE}; // TODO: extend
	
	// everything that triggers a log message is a log event.
	// class provides a variety of properties to store information
	
	public ArrayList<Student> students;  // students concerning the event
	public ArrayList<Teacher> teachers;  // teachers concerning the event
	public ArrayList<Course> courses;  // courses concerning the event 
	public ArrayList<Effect> effects;  // effects concerning the event
	public Subject subject;
	public double doubleValue;  // freely usable
	public int intValue;  // freely usable
	public Boolean boolValue; // freely usable
	public int time;  // date of event
	public EventType type;
	
	// dummy
	public LogEvent(EventType type) {
		students = new ArrayList<Student>();
		teachers = new ArrayList<Teacher>();
		courses = new ArrayList<Course>(); 
		effects = new ArrayList<Effect>();
		doubleValue = 0;
		intValue = 0;
		boolValue = true;
		time = GUI.school.getTime();
		this.type = type;
	}
	
	// retention, move slower, move faster
	public LogEvent(EventType type, Student student, Course oldCourse, Course newCourse) {
		this(type);
		students.add(student);
		courses.add(oldCourse);
		courses.add(newCourse);
	}
	
	// drop course
	public LogEvent(EventType type, Student student, Course oldCourse) {
		this(type);
		students.add(student);
		courses.add(oldCourse);
	}
	
	// new students
	public LogEvent(EventType type, int number, ArrayList<Student> students) {
		this(type);
		intValue = number;
		students.addAll(students);
	}
	
	// start course success
	public LogEvent(EventType type, Course course, Boolean success) {
		this(type);
		courses.add(course);
		boolValue = success;
	}
	
	// start course fail
	public LogEvent(EventType type, Subject subject, Boolean success) {
		this(type);
		this.subject = subject;
		boolValue = success;
	}
}
