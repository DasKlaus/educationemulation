package Staff;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import Main.Course;
import Main.Subject;
import Main.Helper.Modifiable;
import Main.Staff;
import UI.GUI;

public class Teacher extends Staff {
	
	public Modifiable likeability;
	public Modifiable discipline;
	public Modifiable didactic;
	
	public ArrayList<Subject> subjects;
	public ArrayList<Course> courses;
	
	public Teacher() {
		likeability = new Modifiable();
		discipline = new Modifiable();
		didactic = new Modifiable();
		subjects = new ArrayList<Subject>();
		Random random = new Random();
		for (int i=0; i<random.nextInt(2)+1;i++) {
			Subject subject = GUI.school.subjects.get(random.nextInt(GUI.school.subjects.size()));
			if (!subjects.contains(subject)) { subjects.add(subject); }
		}
		courses = new ArrayList<Course>();
	}
	
	public static Teacher findTeacher(Subject subject) throws Exception{
		for (int i=0; i<GUI.school.staff.size(); i++) {
			if (GUI.school.staff.get(i).getClass() == new Teacher().getClass()) {
				if (((Teacher) GUI.school.staff.get(i)).subjects.contains(subject)) {
					return (Teacher) GUI.school.staff.get(i);
				}
			}
		}
		throw new Exception(); // TODO: make custom exception
	}
	
	public void tick() {
		// TODO: detect effects
	}
	
	@Override
	public void update(Observable time, Object newYear) {
		tick();
		if ((Boolean) newYear) {
			startYear();
		}
	}
}
