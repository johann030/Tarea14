package tarea14;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import dao.AlumnoDaoImpl;
import dao.AlumnoDao;
import model.Alumno;
import pool.MyDataSource;

public class Main {

	public static void main(String[] args) {
		testDaoSelectWhere();
	}

	public static void testPool() {
		try (Connection conn = MyDataSource.getConnection()) {
			DatabaseMetaData metaData = conn.getMetaData();
			String[] types = { "TABLE" };
			ResultSet tables = metaData.getTables(null, null, "%", types);
			while (tables.next()) {
				System.out.println(tables.getString("TABLE_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void testDaoInsert() {
		AlumnoDao dao = AlumnoDaoImpl.getInstance();

		Alumno al = new Alumno("Johann", "Conforme", LocalDate.of(2001, 03, 19));

		try {
			int n = dao.add(al);
			System.out.println("El numero de registros insertados es: " + n);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testDaoSelect() {
		AlumnoDao dao = AlumnoDaoImpl.getInstance();
		try {
			List<Alumno> alumnos = dao.getAll();

			if (alumnos.isEmpty())
				System.out.println("No hay alumnos registrados");
			else
				alumnos.forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testDaoSelectWhere() {
		AlumnoDao dao = AlumnoDaoImpl.getInstance();
		Alumno al;
		try {
			al = dao.getById(6);
			System.out.println(al);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testDaoUpdate() {
		AlumnoDao dao = AlumnoDaoImpl.getInstance();
		Alumno al;
		try {
			al = dao.getById(4);

			System.out.println(al);

			al.setFechaNacimiento(LocalDate.of(2008, 03, 20));

			dao.update(al);

			al = dao.getById(4);

			System.out.println(al);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testDaoDelete() {
		AlumnoDao dao = AlumnoDaoImpl.getInstance();
		try {
			List<Alumno> alumnos = dao.getAll();
			dao.delete(5);
			alumnos = dao.getAll();
			if (alumnos.isEmpty())
				System.out.println("No hay alumnos registrados");
			else
				alumnos.forEach(System.out::println);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}