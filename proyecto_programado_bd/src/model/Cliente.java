package model;

import java.time.LocalDate;

public class Cliente {

    private int idCliente;
    private int idUsuario;
    private int idMembresia;
    private String telefono;
    private LocalDate fechaInicioMembresia;
    private LocalDate fechaFinMembresia;

    public Cliente() {}

    public Cliente(int idCliente, int idUsuario, int idMembresia, String telefono, LocalDate fechaInicioMembresia, LocalDate fechaFinMembresia) {
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
        this.idMembresia = idMembresia;
        this.telefono = telefono;
        this.fechaInicioMembresia = fechaInicioMembresia;
        this.fechaFinMembresia = fechaFinMembresia;
    }

    public int getIdCliente() { 
    	return idCliente;
    	}
    public void setIdCliente(int idCliente) {
    	this.idCliente = idCliente; 
    	}

    public int getIdUsuario() { 
    	return idUsuario;
    	}
    public void setIdUsuario(int idUsuario) {
    	this.idUsuario = idUsuario; 
    	}

    public int getIdMembresia() {
    	return idMembresia; 
    	}
    public void setIdMembresia(int idMembresia) { 
    	this.idMembresia = idMembresia; 
    	}

    public String getTelefono() { 
    	return telefono; 
    	}
    public void setTelefono(String telefono) { 
    	this.telefono = telefono;
    	}

    public LocalDate getFechaInicioMembresia() {
    	return fechaInicioMembresia; 
    	}
    public void setFechaInicioMembresia(LocalDate fechaInicioMembresia) { 
    	this.fechaInicioMembresia = fechaInicioMembresia;
    	}

    public LocalDate getFechaFinMembresia() {
    	return fechaFinMembresia; 
    	}
    public void setFechaFinMembresia(LocalDate fechaFinMembresia) {
    	this.fechaFinMembresia = fechaFinMembresia; 
    	}
}