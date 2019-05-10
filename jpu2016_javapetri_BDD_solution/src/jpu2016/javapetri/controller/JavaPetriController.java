package jpu2016.javapetri.controller;

import java.awt.Color;
import java.util.Random;

import jpu2016.javapetri.dao.DAOJavaPetri;
import jpu2016.javapetri.ihm.EasyFrame;
import jpu2016.javapetri.ihm.JavaPetriConsole;
import jpu2016.javapetri.model.JavaCell;
import jpu2016.javapetri.model.JavaPetri;
import jpu2016.javapetri.model.Position;

public class JavaPetriController {
	static private int					WIDTH					= 100;
	static private int					HEIGHT				= 100;
	static private int					STRENGTH			= 50;
	static private int					NB_CELL_START	= 100;

	private JavaPetri						javaPetri;
	private JavaPetriConsole		ihm;
	private EasyFrame						easyFrame;
	private final DAOJavaPetri	dao;

	public JavaPetriController() {
		this.dao = new DAOJavaPetri();
	}

	public void start() {
		this.ihm = new JavaPetriConsole(this.javaPetri);
		this.easyFrame = new EasyFrame("JavaPetri", this.javaPetri);
		this.javaPetri.animate();
	}

	public void save() {
		this.dao.open();
		this.dao.insertJavaPetri(this.javaPetri);
		this.dao.close();
	}

	public void load(final int id) {
		this.dao.open();
		this.javaPetri = this.dao.getJavaPetriById(id);
		this.dao.close();
	}

	public void loadNew() {
		final Random rand = new Random();

		this.javaPetri = new JavaPetri(WIDTH, HEIGHT);
		for (int i = 0; i < NB_CELL_START; i++) {
			this.javaPetri.addJavaCell(new JavaCell(this.javaPetri, new Position(rand.nextInt(WIDTH), rand.nextInt(HEIGHT)), STRENGTH, new Color(rand.nextInt())));
		}
	}
}
