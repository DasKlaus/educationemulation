package Main;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import Main.Helper.Knowledge;
import Main.Helper.Modifiable;
import Main.Helper.Talent;
import Main.Helper.Tickable;
import Main.LogEvent.EventType;
import UI.GUI;

public class Student implements Tickable {
	
	public final String name;
	public int age; // in ticks (months)
	public final Modifiable intelligence;
	public final Modifiable charisma;
	
	public Modifiable depression;
	public Modifiable agression;
	public Modifiable motivation;
	
	public ArrayList<Talent> talents;
	public ArrayList<Knowledge> knowledges;
	
	public ArrayList<Grade> grades;
	public ArrayList<Degree> degrees;
	
	// ArrayList sympathies/friends
	public ArrayList<Effect> effects;
	
	// shortcut to teachers, courses? redundancies, yes, but better performance?
	
	public Student() {

		Random random = new Random();
				
		// generate Name
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		int length = random.nextInt(15)+3;
		for (int i=0; i<length; i++) {
			sb.append(chars[random.nextInt(chars.length)]); 
		}
		name = sb.toString();
		
		age = random.nextInt(18)+72;
		
		intelligence = new Modifiable();
		charisma = new Modifiable();
		
		depression = new Modifiable();
		agression = new Modifiable();
		motivation = new Modifiable();
		
		talents = new ArrayList<Talent>();
		knowledges = new ArrayList<Knowledge>();
		
		grades = new ArrayList<Grade>();
		degrees = new ArrayList<Degree>();
		
		effects = new ArrayList<Effect>();
		
		GUI.school.time.addObserver(this);
		// TODO: starting effects
	}
	
	public void tick() {
		age ++;
		// TODO: calculate sympathies
		// TODO: calculate peergroup influence (needs friend list, I guess.)
		
		for (int i=0; i<effects.size(); i++) {
			effects.get(i).tick();
		}
	}
	
	public void startYear() {
		// calculate aspired graduation according to average and motivation
		Graduation aspiration = GUI.school.graduations.get(2);
		if (Grade.average(grades)-(motivation.value/10) < 2) {
			aspiration = GUI.school.graduations.get(0);
		} else if (Grade.average(grades)-(motivation.value/10) < 3.5) {
			aspiration = GUI.school.graduations.get(1);
		}
		
		while (grades.size() != 0) {
			Course course = grades.get(0).course;
			double average = Grade.average(course.subject, this);
			int requiredLevel = aspiration.getRequirement(course.subject);
			if (average<4.5) {
				// give degree
				degrees.add(new Degree(course.subject, course.level, (int) Math.round(average)));
				if (course.level >= requiredLevel && average-(motivation.value/10) > 2.5) {
					// drop out
					course.students.remove(this);
					GUI.school.log.add(new LogEvent(EventType.DROPCOURSE, this, course));
				} else {
					if (average<1.5 && Degree.getLastDegree(this, course.subject) != null 
							&& Degree.getLastDegree(this, course.subject).grade<1.5) 
					{
						// move to faster course
						course.students.remove(this);
						Course newCourse = Course.getCourse(course.subject, course.level, course.speed+1); 
						if (newCourse != null) {
							newCourse.students.add(this);
						} else {
							ArrayList<Student> students = new ArrayList<Student>();
							students.add(this);
							newCourse = new Course(course.teacher, course.subject, course.level, course.speed+1, "abcdefghijklmnopqrstuvwxyz".substring(
									Course.getSemiParallelCourses(course, course.speed-1).size()+1, 
									Course.getSemiParallelCourses(course, course.speed-1).size()+2
							), students);
							GUI.school.courses.add(newCourse); 
						}
						GUI.school.log.add(new LogEvent(EventType.MOVEFASTER, this, course, newCourse));
					}
				}
			} else {
				if (average>5 && Degree.getLastDegree(this, course.subject) != null 
						&& GUI.school.getTime()-Degree.getLastDegree(this, course.subject).time>12) {
					// move to slower course
					course.students.remove(this);
					Course newCourse = Course.getCourse(course.subject, course.level-(12*course.speed), course.speed-1); 
					if (newCourse != null) {
						newCourse.students.add(this);
					} else {
						ArrayList<Student> students = new ArrayList<Student>();
						students.add(this);
						newCourse = new Course(course.teacher, course.subject, course.level-(12*course.speed), course.speed-1, 
								"abcdefghijklmnopqrstuvwxyz".substring(
										Course.getSemiParallelCourses(course, course.speed-1).size()+1, 
										Course.getSemiParallelCourses(course, course.speed-1).size()+2
								), students);
						GUI.school.courses.add(newCourse);
					}
					GUI.school.log.add(new LogEvent(EventType.MOVESLOWER, this, course, newCourse));
				} else {
					// repeat
					course.students.remove(this);
					Course newCourse = Course.getCourse(course.subject, course.level-(12*course.speed), course.speed); 
					if (newCourse != null) {
						newCourse.students.add(this);
					} else {
						ArrayList<Student> students = new ArrayList<Student>();
						students.add(this);
						newCourse = new Course(course.teacher, course.subject, course.level-(12*course.speed), course.speed, "a", students); 
						GUI.school.courses.add(newCourse);
					}
					GUI.school.log.add(new LogEvent(EventType.RETENTION, this, course, newCourse));
				}
			}
			grades.removeAll(Grade.getGrades(this, grades.get(0).course));
		}
		
		// TODO: add courses again if dropped but needed as elective requirements
		// TODO: implement intelligent choice of courses
		// TODO: check elegibility for graduation and graduate if possible
	}
	
	public String toString() {
		// just for testing/debugging
		return name;
	}
	
	public Modifiable getTalent(Subject subject) {
		for (int i=0; i<talents.size(); i++) {
			if (talents.get(i).subject == subject) {
				return talents.get(i).value;
			}
		}
		Talent talent = new Talent(subject, new Modifiable());
		talents.add(talent);
		return talent.value;
	}
	
	public Knowledge getKnowledge(Subject subject) {
		for (int i=0; i<knowledges.size(); i++) {
			if (knowledges.get(i).subject == subject) {
				return knowledges.get(i);
			}
		}
		Knowledge knowledge = new Knowledge(subject);
		knowledges.add(knowledge);
		return knowledge;
	}
	
	@Override
	public void update(Observable time, Object newYear) {
		tick();
		if ((Boolean) newYear) {
			startYear();
		}
	}
}
