package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Alumno;
import pool.MyDataSource;

public class AlumnoDaoImpl implements AlumnoDao {

	private static AlumnoDaoImpl instance;
	// Patron de diseño Singleton

	static {
		instance = new AlumnoDaoImpl();
	}

	private AlumnoDaoImpl() {
	}

	public static AlumnoDaoImpl getInstance() {
		return instance;
	}

	@Override
	public int add(Alumno al) throws Exception {
		String sql = """
				INSERT INTO grupo(nombre, apellidos, fecha_nacimiento)
				VALUES (?,?,?);
				""";

		int result;

		try (Connection conn = MyDataSource.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {

			pstm.setString(1, al.getNombre());
			pstm.setString(2, al.getApellidos());
			pstm.setDate(3, Date.valueOf(al.getFechaNacimiento()));

			result = pstm.executeUpdate();
		}

		return result;
	}

	@Override
	public Alumno getById(int id) throws Exception {
		Alumno result = null;

		String sql = """
				SELECT id_alumno, nombre, apellidos, fecha_nacimiento FROM grupo WHERE id_alumno = ?
				""";

		try (Connection conn = MyDataSource.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setInt(1, id);

			try (ResultSet rs = pstm.executeQuery()) {
				if (rs.next()) {
					result = new Alumno();
					result.setId_alumno(rs.getInt("id_alumno"));
					result.setNombre(rs.getString("nombre"));
					result.setApellidos(rs.getString("apellidos"));
					result.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());

				}
			}
		}
		return result;
	}

	@Override
	public List<Alumno> getAll() throws SQLException {
		String sql = """
				SELECT id_alumno, nombre, apellidos, fecha_nacimiento FROM grupo
				""";

		List<Alumno> result = new ArrayList<>();

		try (Connection conn = MyDataSource.getConnection();
				PreparedStatement pstm = conn.prepareStatement(sql);
				ResultSet rs = pstm.executeQuery()) {

			Alumno al;
			// Al devolver una sola fila se puede hacer con if
			while (rs.next()) {
				al = new Alumno();
				al.setId_alumno(rs.getInt("id_alumno"));
				al.setNombre(rs.getString("nombre"));
				al.setApellidos(rs.getString("apellidos"));
				al.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());

				result.add(al);
			}
		}

		return result;
	}

	@Override
	public int update(Alumno al) throws Exception {
		String sql = """
					UPDATE grupo SET
						nombre = ?, apellidos = ?,
						fecha_nacimiento = ?
					WHERE id_alumno = ?
				""";
		int result;
		try (Connection conn = MyDataSource.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setString(1, al.getNombre());
			pstm.setString(2, al.getApellidos());
			pstm.setDate(3, Date.valueOf(al.getFechaNacimiento()));
			pstm.setInt(4, al.getId_alumno());

			result = pstm.executeUpdate();
		}

		return result;
	}

	@Override
	public void delete(int id) throws Exception {
		String sql = """
				DELETE FROM grupo
				where id_alumno = ?
				""";

		try (Connection conn = MyDataSource.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setInt(1, id);
			pstm.executeUpdate();
		}
	}
}