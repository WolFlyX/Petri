package jpu2016.javapetri.ihm;

import java.util.Observable;
import java.util.Observer;

import jpu2016.javapetri.model.JavaPetri;

public class JavaPetriConsole implements Observer {
	private final JavaPetri javaPetri;

	public JavaPetriConsole(final JavaPetri javaPetri) {
		this.javaPetri = javaPetri;
		javaPetri.addObserver(this);
	}

	private void show() {
		System.out.println(
				"Step : " + this.javaPetri.getStep() + "\tJavaCell alive : " + this.javaPetri.getNbJavaCellAlive() + "\tdead : " + this.javaPetri.getNbJavaCellDead());
	}

	@Override
	public void update(final Observable arg0, final Object arg1) {
		this.show();
	}
}
