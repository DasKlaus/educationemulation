package Main;

import UI.GUI;

public class Degree {
	public int time;
	public Subject subject;
	public int level;
	public int grade;
	
	public Degree(Subject subject, int level, int grade) {
		this.time = GUI.school.getTime();
		this.subject = subject;
		this.level = level;
		this.grade = grade;
	}
	
	public String toString() {
		return subject+" "+(int) Math.floor(level/36) + " mit " + grade + " abgeschlossen." ;
	}
	
	public static Degree getLastDegree(Student student, Subject subject) {
		Degree degree = null;
		int closestTime = 0;
		for (int i=0; i<student.degrees.size(); i++) {
			if (student.degrees.get(i).subject == subject &&
					GUI.school.getTime()-student.degrees.get(0).time<GUI.school.getTime()-closestTime) {
				closestTime = student.degrees.get(i).time;
				degree = student.degrees.get(i);
			}
		}
		return degree;
	}
}
