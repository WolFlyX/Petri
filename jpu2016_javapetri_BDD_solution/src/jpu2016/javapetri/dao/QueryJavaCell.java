package jpu2016.javapetri.dao;

import jpu2016.javapetri.model.JavaCell;

abstract class QueryJavaCell {
	public static String getQueryInsert(final JavaCell javaCell, final int idJavaPetri) {
		return "INSERT INTO `javapetri`.`rgbcell` (`ID`, `X`, `Y`, `STRENGTH`, `COLOR`, `ID_JAVAPETRI`)" + " VALUES (NULL," + " '" + javaCell.getPosition().getX()
				+ "'," + " '" + javaCell.getPosition().getY() + "'," + " '" + javaCell.getStrength() + "'," + " '" + javaCell.getColor().getRGB() + "'," + " '"
				+ idJavaPetri + "');";
	}

	public static String getQuerySelectByIdJavaPetri(final int idJavaPetri) {
		return "SELECT * FROM `rgbcell` WHERE `ID_JAVAPETRI` = " + idJavaPetri;
	}
}
