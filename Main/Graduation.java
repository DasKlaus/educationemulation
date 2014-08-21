package Main;

import java.util.ArrayList;

import Main.Helper.Requirement;

public class Graduation {
	public String name;
	public ArrayList<Requirement> compulsory;
	public ArrayList<Requirement> elective;
	public int electiveNumber;
	
	public Graduation(String name, ArrayList<Requirement> compulsory, ArrayList<Requirement> elective, int electiveNumber) {
		this.name = name;
		this.compulsory = compulsory;
		this.elective = elective;
		this.electiveNumber = electiveNumber;
	}
	
	public int getRequirement(Subject subject) {
		int requiredLevel = 0;
		for (int i=0; i<compulsory.size(); i++) {
			if (compulsory.get(i).subject == subject) {
				requiredLevel = compulsory.get(i).level;
			}
		}
		return requiredLevel;
	}
}
