package jpu2016.javapetri.model;

import java.awt.Color;
import java.util.ArrayList;

public class JavaCell {

	private final JavaPetri	javaPetri;
	private final Position	position;
	private Boolean					alive;
	private final int				strength;
	private final Color			color;

	public JavaCell(final JavaPetri javaPetri, final Position position, final int strength, final Color color) {
		this.javaPetri = javaPetri;
		this.position = position;
		this.strength = strength;
		this.alive = true;
		this.color = color;
	}

	public void live() {
		this.reproduce();
		this.die();
	}

	private void reproduce() {
		for (final Position position : this.getPositionNear()) {
			if (this.okReproduce(position)) {
				this.getJavaPetri().addJavaCell(new JavaCell(this.getJavaPetri(), position, this.getStrength(), this.getReproduceColor(position)));
			}
		}
	}

	public Boolean isAlive() {
		return this.alive;
	}

	public void die() {
		this.alive = false;
	}

	public JavaPetri getJavaPetri() {
		return this.javaPetri;
	}

	public Position getPosition() {
		return this.position;
	}

	public int getStrength() {
		return this.strength;
	}

	private final Boolean isPositionAvailable(final Position position) {
		return ((position.getX() >= 0) && (position.getX() <= this.getJavaPetri().getWidth()) && (position.getY() >= 0)
				&& (position.getY() <= this.getJavaPetri().getHeight()));
	}

	private ArrayList<Position> getPositionNear() {
		return this.getPositionNear(this.getPosition());
	}

	private ArrayList<Position> getPositionNear(final Position position) {
		final ArrayList<Position> positionNear = new ArrayList<>();
		for (int xi = -1; xi <= 1; xi++) {
			for (int yi = -1; yi <= 1; yi++) {
				if ((Math.abs(xi) != Math.abs(yi)) || ((this.getJavaPetri().getStep() % 2) == 1)) {
					final Position p = new Position(position.getX() + xi, position.getY() + yi);
					if (this.isPositionAvailable(p)) {
						positionNear.add(p);
					}
				}
			}
		}
		return positionNear;
	}

	public Color getColor() {
		return this.color;
	}

	protected Boolean okReproduce(final Position position) {
		return this.getJavaPetri().getColorFromPosition(position).getRGB() == Color.WHITE.getRGB();
	}

	protected Color getReproduceColor(final Position position) {
		int red = this.getColor().getRed() * this.getStrength();
		int green = this.getColor().getGreen() * this.getStrength();
		int blue = this.getColor().getBlue() * this.getStrength();
		final ArrayList<Position> positionnear = this.getPositionNear(position);

		for (final Position p : positionnear) {
			final Color c = this.getJavaPetri().getColorFromPosition(p);
			red += c.getRed();
			green += c.getGreen();
			blue += c.getBlue();
		}
		red = (red / (positionnear.size() + this.getStrength()));
		green = (green / (positionnear.size() + this.getStrength()));
		blue = (blue / (positionnear.size() + this.getStrength()));
		return new Color(red, green, blue);
	}

}