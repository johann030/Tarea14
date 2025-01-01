package model;

import java.time.LocalDate;
import java.util.Objects;

public class Alumno {

	private int id_alumno;
	private String nombre;
	private String apellidos;
	private LocalDate fechaNacimiento;

	public Alumno() {
	}

	public Alumno(String nombre, String apellidos, LocalDate fechaNacimiento) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
	}

	public Alumno(int id_alumno, String nombre, String apellidos, LocalDate fechaNacimiento) {
		this(nombre, apellidos, fechaNacimiento);
		this.id_alumno = id_alumno;
	}

	public int getId_alumno() {
		return id_alumno;
	}

	public void setId_alumno(int id_alumno) {
		this.id_alumno = id_alumno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellidos, fechaNacimiento, id_alumno, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(fechaNacimiento, other.fechaNacimiento)
				&& id_alumno == other.id_alumno && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Alumno [id_alumno=" + id_alumno + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", fechaNacimiento=" + fechaNacimiento + "]";
	}
}