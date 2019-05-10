package jpu2016.javapetri.dao;

import jpu2016.javapetri.model.JavaPetri;

abstract class QueryJavaPetri {
	public static String getQueryInsert(final JavaPetri javaPetri) {
		return "INSERT INTO `javapetri`.`javapetri` (`ID`, `LABEL`, `WIDTH`, `HEIGHT`)" + " VALUES (NULL, '" + javaPetri.getLabel() + "', '" + javaPetri.getWidth()
				+ "', '" + javaPetri.getHeight() + "');";
	}

	public static String getQuerySelectById(final int id) {
		return "SELECT * FROM `javapetri` WHERE `ID` = " + id;
	}

	public static String getQuerySelectAll() {
		return "SELECT * FROM `javapetri`";
	}
}
