package jpu2016.javapetri.dao;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jpu2016.javapetri.model.JavaCell;
import jpu2016.javapetri.model.JavaPetri;
import jpu2016.javapetri.model.Position;

public class DAOJavaPetri {

	private static String	URL				= "jdbc:mysql://localhost/javapetri?autoReconnect=true&useSSL=false";
	private static String	LOGIN			= "root";
	private static String	PASSWORD	= "";
	private Connection		connection;
	private Statement			statement;

	public DAOJavaPetri() {
		this.connection = null;
		this.statement = null;
	}

	public Boolean open() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(DAOJavaPetri.URL, DAOJavaPetri.LOGIN, DAOJavaPetri.PASSWORD);
			this.statement = this.connection.createStatement();
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (final SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void close() {

	}

	public JavaPetri getJavaPetriById(final int id) {
		final ResultSet resultSet = this.executeQuery(QueryJavaPetri.getQuerySelectById(id));
		try {
			if (resultSet.first()) {
				final JavaPetri javaPetri = new JavaPetri(resultSet.getInt("WIDTH"), resultSet.getInt("HEIGHT"));
				javaPetri.setLabel(resultSet.getString("LABEL"));
				final ResultSet rSCell = this.executeQuery(QueryJavaCell.getQuerySelectByIdJavaPetri(resultSet.getInt("ID")));
				for (; rSCell.next();) {
					javaPetri.addJavaCell(
							new JavaCell(javaPetri, new Position(rSCell.getInt("X"), rSCell.getInt("Y")), rSCell.getInt("STRENGTH"), new Color(rSCell.getInt("COLOR"))));
				}
				return javaPetri;
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void insertJavaPetri(final JavaPetri javaPetri) {
		if (this.executeUpdate(QueryJavaPetri.getQueryInsert(javaPetri)) == 1) {
			final int idJavaPetri = this.getLastId();
			if (idJavaPetri != -1) {
				for (final JavaCell javaCell : javaPetri.getJavaCells()) {
					this.executeUpdate(QueryJavaCell.getQueryInsert(javaCell, idJavaPetri));
				}
			}
		}
	}

	private ResultSet executeQuery(final String query) {
		try {
			return this.statement.executeQuery(query);
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private int executeUpdate(final String query) {
		try {
			return this.statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	private int getLastId() {
		try {
			final ResultSet resultSet = this.statement.getGeneratedKeys();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

}
