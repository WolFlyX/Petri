import jpu2016.javapetri.controller.JavaPetriController;

public abstract class JavaPetriMain {

	public static void main(final String[] args) {
		final JavaPetriController javaPetri = new JavaPetriController();

		// Nouveau JavaPetri
		// javaPetri.loadNew();
		// javaPetri.save();

		// Chargement d'un JavaPetri existant
		javaPetri.load(5);

		javaPetri.start();
	}
}
