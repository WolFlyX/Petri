package jpu2016.javapetri.ihm;

import javax.swing.JFrame;

import jpu2016.javapetri.model.JavaPetri;

public class EasyFrame extends JFrame {
	private static final long	serialVersionUID	= -1112124206501543946L;
	public static final int		ZOOM							= 3;

	public EasyFrame(final String title, final JavaPetri javaPetri) {

		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);

		final EasyPanel easyPanel = new EasyPanel(new JavaPetriGraphicsBuilder(javaPetri));
		this.setContentPane(easyPanel);
		this.setSize((javaPetri.getWidth() * ZOOM) + this.getInsets().left + this.getInsets().right,
				(javaPetri.getHeight() * ZOOM) + this.getInsets().top + this.getInsets().bottom);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		javaPetri.addObserver(easyPanel);
	}
}
