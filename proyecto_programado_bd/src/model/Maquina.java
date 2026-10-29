package model;

public class Maquina {

    private int idMaquina;
    private String nombre;
    private String descripcion;
    private String estado;
    private String rutaImagen;

    public Maquina() {}

    public Maquina(int idMaquina, String nombre, String descripcion, String estado, String rutaImagen) {
        this.idMaquina = idMaquina;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.rutaImagen = rutaImagen;
    }

    public int getIdMaquina() { 
    	return idMaquina;
    	}
    public void setIdMaquina(int idMaquina) {
    	this.idMaquina = idMaquina;
    	}

    public String getNombre() {
    	return nombre; 
    	}
    public void setNombre(String nombre) {
    	this.nombre = nombre;
    	}

    public String getDescripcion() { 
    	return descripcion;
    	}
    public void setDescripcion(String descripcion) { 
    	this.descripcion = descripcion;
    	}

    public String getEstado() { 
    	return estado;
    	}
    public void setEstado(String estado) { 
    	this.estado = estado;
    	}

    public String getRutaImagen() {
    	return rutaImagen;
    	}
    public void setRutaImagen(String rutaImagen) { 
    	this.rutaImagen = rutaImagen; 
    	}
}