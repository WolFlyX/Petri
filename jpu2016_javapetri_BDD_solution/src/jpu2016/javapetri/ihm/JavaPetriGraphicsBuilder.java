package jpu2016.javapetri.ihm;

import java.awt.Graphics;

import jpu2016.javapetri.model.JavaCell;
import jpu2016.javapetri.model.JavaPetri;

public class JavaPetriGraphicsBuilder {
	private final JavaPetri javaPetri;

	public JavaPetriGraphicsBuilder(final JavaPetri javaPetri) {
		this.javaPetri = javaPetri;
	}

	private void clearGraphics(final Graphics graphics) {
		graphics.clearRect(0, 0, this.javaPetri.getWidth() * EasyFrame.ZOOM, this.javaPetri.getHeight() * EasyFrame.ZOOM);
	}

	private void drawAllJavaCell(final Graphics graphics) {
		for (final JavaCell javaCell : this.javaPetri.getCopyOfJavaCells()) {
			this.drawJavaCell(graphics, javaCell);
		}
	}

	private void drawJavaCell(final Graphics graphics, final JavaCell javaCell) {
		graphics.setColor(javaCell.getColor());
		graphics.fillRect(javaCell.getPosition().getX() * EasyFrame.ZOOM, javaCell.getPosition().getY() * EasyFrame.ZOOM, EasyFrame.ZOOM, EasyFrame.ZOOM);

	}

	public void applyModelToGraphic(final Graphics graphics) {
		this.clearGraphics(graphics);
		this.drawAllJavaCell(graphics);
	}
}
