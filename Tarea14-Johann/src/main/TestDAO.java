package main;

import org.junit.jupiter.api.Test;

import dao.AlumnoDao;
import dao.AlumnoDaoImpl;
import model.Alumno;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.List;

class TestDAO {
	@Test
	public void testAdd() throws Exception {
		Alumno al = new Alumno();
		AlumnoDao dao = AlumnoDaoImpl.getInstance();
		al.setNombre("Juan");
		al.setApellidos("Pérez");
		al.setFechaNacimiento(LocalDate.of(1995, 5, 20));

		int resultado = dao.add(al);
		assertEquals(1, resultado, "Debe insertar un registro correctamente");
	}

	@Test
	public void testGetById() throws Exception {
		Alumno al = new Alumno();
		AlumnoDao dao = AlumnoDaoImpl.getInstance();
		al.setNombre("Ana");
		al.setApellidos("Gómez");
		al.setFechaNacimiento(LocalDate.of(1992, 10, 15));

		dao.add(al);
		Alumno resultado = dao.getById(2);

		assertNotNull(resultado, "El alumno no debería ser nulo");
		assertEquals("Ana", resultado.getNombre());
		assertEquals("Gómez", resultado.getApellidos());
		assertEquals(LocalDate.of(1992, 10, 15), resultado.getFechaNacimiento());
	}

	@Test
	public void testGetAll() throws Exception {
		Alumno al1 = new Alumno();
		AlumnoDao dao = AlumnoDaoImpl.getInstance();
		al1.setNombre("Carlos");
		al1.setApellidos("López");
		al1.setFechaNacimiento(LocalDate.of(1990, 1, 1));

		Alumno al2 = new Alumno();
		al2.setNombre("Laura");
		al2.setApellidos("Martínez");
		al2.setFechaNacimiento(LocalDate.of(1993, 3, 15));

		dao.add(al1);
		dao.add(al2);

		List<Alumno> alumnos = dao.getAll();
		assertEquals(2, alumnos.size(), "Debería haber dos alumnos en la lista");
	}

	@Test
	public void testUpdate() throws Exception {
		Alumno al = new Alumno();
		AlumnoDao dao = AlumnoDaoImpl.getInstance();
		al.setNombre("Mario");
		al.setApellidos("Fernández");
		al.setFechaNacimiento(LocalDate.of(1998, 8, 25));

		dao.add(al);

		Alumno actualizarAl = dao.getById(1);
		actualizarAl.setNombre("Mario Actualizado");
		actualizarAl.setApellidos("Fernández Actualizado");

		int result = dao.update(actualizarAl);
		assertEquals(1, result, "Debería actualizar un registro correctamente");

		Alumno alumnoFinal = dao.getById(1);
		assertEquals("Mario Actualizado", alumnoFinal.getNombre());
		assertEquals("Fernández Actualizado", alumnoFinal.getApellidos());
	}

	@Test
	public void testDelete() throws Exception {
		Alumno al = new Alumno();
		AlumnoDao dao = AlumnoDaoImpl.getInstance();
		al.setNombre("Claudia");
		al.setApellidos("Hernández");
		al.setFechaNacimiento(LocalDate.of(2000, 12, 12));

		dao.add(al);

		dao.delete(1);
		Alumno resultado = dao.getById(1);
		assertNull(resultado, "El alumno debería ser nulo después de eliminarlo");
	}
}