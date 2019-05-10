package jpu2016.javapetri.model;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Observable;

public class JavaPetri extends Observable {
	private int												width;
	private int												height;
	private int												step;
	private final ArrayList<JavaCell>	javaCells;
	private String										label;

	public JavaPetri(final int width, final int height) {
		this.setWidth(width);
		this.setHeight(height);
		this.step = 0;
		this.javaCells = new ArrayList<JavaCell>();
		this.setLabel(new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(new Date()));
	}

	public int getStep() {
		return this.step;
	}

	private void incrementStep() {
		this.step++;
		this.setChanged();
		this.notifyObservers();
	}

	public int getWidth() {
		return this.width;
	}

	private void setWidth(final int width) {
		this.width = width;
	}

	public int getHeight() {
		return this.height;
	}

	private void setHeight(final int height) {
		this.height = height;
	}

	public synchronized ArrayList<JavaCell> getJavaCells() {
		return this.javaCells;
	}

	public synchronized ArrayList<JavaCell> getCopyOfJavaCells() {
		final ArrayList<JavaCell> copyOfJavaPetri = new ArrayList<JavaCell>();

		for (final JavaCell rgbCell : this.getJavaCells()) {
			copyOfJavaPetri.add(rgbCell);
		}
		return copyOfJavaPetri;
	}

	public void addJavaCell(final JavaCell javaCell) {
		this.getJavaCells().add(javaCell);
	}

	public Color getColorFromPosition(final Position position) {
		for (final JavaCell javaCell : this.getJavaCells()) {
			if ((javaCell.getPosition().getX() == position.getX()) && (javaCell.getPosition().getY() == position.getY())) {
				return javaCell.getColor();
			}
		}
		return Color.WHITE;
	}

	private void doLiveOnAllJavaCellAreAlive() {
		final ArrayList<JavaCell> oldJavaPetri = this.getCopyOfJavaCells();

		for (final JavaCell rgbCell : oldJavaPetri) {
			if (rgbCell.isAlive()) {
				rgbCell.live();
			}
		}
	}

	public synchronized int getNbJavaCellAlive() {
		int NbJavaCellAlive = 0;
		for (final JavaCell rgbCell : this.getJavaCells()) {
			if (rgbCell.isAlive()) {
				NbJavaCellAlive++;
			}
		}
		return NbJavaCellAlive;
	}

	public synchronized int getNbJavaCellDead() {
		int NbJavaCellDead = 0;
		for (final JavaCell rgbCell : this.getJavaCells()) {
			if (!rgbCell.isAlive()) {
				NbJavaCellDead++;
			}
		}
		return NbJavaCellDead;
	}

	public void animate() {
		for (this.incrementStep(); this.getNbJavaCellAlive() > 0; this.incrementStep()) {
			this.doLiveOnAllJavaCellAreAlive();
		}
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(final String label) {
		this.label = label;
	}
}
