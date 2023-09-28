package animals;


public abstract class Animal {
	private int id;
	private String name;
	private String skills;

	public Animal(int id, String name, String skills) {
		this.id = id;
		this.name = name;
		this.skills = skills;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String updatedSkills) {
		this.skills = updatedSkills;
	}

	public abstract void displayCommands();

	public abstract void teachNewCommand(String command);
}
