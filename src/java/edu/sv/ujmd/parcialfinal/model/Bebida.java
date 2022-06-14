package edu.sv.ujmd.parcialfinal.model;

/**
 * User.java
 * This is a model class represents a User entity
 * @author Ramesh Fadatare
 *
 */
public class Bebida {
	protected int id;
	protected String bebida;
	protected Double precio;
	
	public Bebida() {
	}
	
	public Bebida(String bebida, Double precio) {
		super();
		this.bebida = bebida;
		this.precio = precio;
	}

	public Bebida(int id, String bebida, Double precio) {
		super();
		this.id = id;
		this.bebida = bebida;
		this.precio = precio;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBebida() {
		return bebida;
	}
	public void setBebida(String bebida) {
		this.bebida = bebida;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}

}
