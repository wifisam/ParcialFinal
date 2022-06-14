package edu.sv.ujmd.parcialfinal.model;

/**
 * User.java
 * This is a model class represents a User entity
 * @author Ramesh Fadatare
 *
 */
public class Factura {
	protected int id;
	protected int id_alimento;
        protected int id_bebida;
	protected Double subtotal;
	
	public Factura() {
	}
	
	public Factura(int id_alimento, int id_bebida, Double subtotal) {
		super();
		this.id_alimento = id_alimento;
		this.id_bebida = id_bebida;
                this.subtotal = subtotal;
	}

	public Factura(int id, int id_alimento, int id_bebida, Double subtotal) {
		super();
                this.id = id;
		this.id_alimento = id_alimento;
		this.id_bebida = id_bebida;
                this.subtotal = subtotal;
	}

   
  
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
        public int getIdAlimento() {
		return id_alimento;
	}
	public void setIdAlimento(int id_alimento) {
		this.id_alimento = id_alimento;
        }
	public int getIdBebida() {
		return id_bebida;
	}
	public void setIdBebida(int bebida) {
		this.id_bebida = id_bebida;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

}
