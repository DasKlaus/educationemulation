package Main;

import java.util.ArrayList;

import Main.Helper.Requirement;

public class Subject {
	public String name;
	public ArrayList<Requirement> requirements;
	public int targetKnowledge;
	
	public Subject(String name, int targetKnowledge, ArrayList<Requirement> requirements) {
		this.name = name;
		this.targetKnowledge = targetKnowledge;
		this.requirements = requirements;
	}
	
	public String toString() {
		return name;
	}
}
