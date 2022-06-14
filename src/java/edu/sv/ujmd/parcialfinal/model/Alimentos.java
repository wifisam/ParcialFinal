package edu.sv.ujmd.parcialfinal.model;

/**
 * User.java
 * This is a model class represents a User entity
 * @author Ramesh Fadatare
 *
 */
public class Alimentos {
	protected int id;
	protected String alimento;
	protected Double precio;
	
	public Alimentos() {
	}
	
	public Alimentos(String alimento, Double precio) {
		super();
		this.alimento = alimento;
		this.precio = precio;
	}

	public Alimentos(int id, String alimento, Double precio) {
		super();
		this.id = id;
		this.alimento = alimento;
		this.precio = precio;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAlimento() {
		return alimento;
	}
	public void setAlimento(String alimento) {
		this.alimento = alimento;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}

}
