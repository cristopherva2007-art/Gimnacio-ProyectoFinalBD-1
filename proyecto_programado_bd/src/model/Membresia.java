package model;

public class Membresia {

    private int idMembresia;
    private String nombre;
    private int duracionDias;
    private double precio;

    public Membresia() {}

    public Membresia(int idMembresia, String nombre, int duracionDias, double precio) {
        this.idMembresia = idMembresia;
        this.nombre = nombre;
        this.duracionDias = duracionDias;
        this.precio = precio;
    }

    public int getIdMembresia() { 
    	return idMembresia; 
    	}
    public void setIdMembresia(int idMembresia) {
    	this.idMembresia = idMembresia;
    	}

    public String getNombre() { 
    	return nombre;
    	}
    public void setNombre(String nombre) { 
    	this.nombre = nombre;
    	}

    public int getDuracionDias() {
    	return duracionDias;
    	}
    public void setDuracionDias(int duracionDias) {
    	this.duracionDias = duracionDias;
    	}

    public double getPrecio() { 
    	return precio; 
    	}
    public void setPrecio(double precio) {
    	this.precio = precio; 
    	}
}