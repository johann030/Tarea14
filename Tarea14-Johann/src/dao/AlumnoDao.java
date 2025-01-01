package dao;

import java.util.List;

import model.Alumno;

public interface AlumnoDao {

	// Crear excepciones propias o controlar cuando algo falla
	int add(Alumno al) throws Exception;

	Alumno getById(int id) throws Exception;

	List<Alumno> getAll() throws Exception;

	int update(Alumno al) throws Exception;

	void delete(int id) throws Exception;
}