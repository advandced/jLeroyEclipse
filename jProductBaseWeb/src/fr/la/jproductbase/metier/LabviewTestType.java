package fr.la.jproductbase.metier;

public enum LabviewTestType {
	TF1("Test fonctionnel 1"), DIEL("Continuité des masses"), TestDyn(
			"Déverminage Dynamique"), TF2("Test fonctionnel 2");

	private String name;

	/** Constructeur */
	LabviewTestType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
