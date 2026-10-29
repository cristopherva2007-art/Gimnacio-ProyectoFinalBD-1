package model;

import java.time.LocalDateTime;

public class Compra {

    private int idCompra;
    private int idCliente;
    private int idProducto;
    private int cantidad;
    private double precioUnitario;
    private double total;
    private LocalDateTime fechaCompra;

    public Compra() {}

    public Compra(int idCompra, int idCliente, int idProducto, int cantidad, double precioUnitario, double total, LocalDateTime fechaCompra) {
        this.idCompra = idCompra;
        this.idCliente = idCliente;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.total = total;
        this.fechaCompra = fechaCompra;
    }

    public int getIdCompra() {
    	return idCompra;
    	}
    public void setIdCompra(int idCompra) {
    	this.idCompra = idCompra;
    	}

    public int getIdCliente() { 
    	return idCliente; 
    	}
    public void setIdCliente(int idCliente) { 
    	this.idCliente = idCliente;
    	}

    public int getIdProducto() {
    	return idProducto;
    	}
    public void setIdProducto(int idProducto) {
    	this.idProducto = idProducto;
    	}

    public int getCantidad() {
    	return cantidad;
    	}
    public void setCantidad(int cantidad) {
    	this.cantidad = cantidad;
    	}

    public double getPrecioUnitario() {
    	return precioUnitario; 
    	}
    public void setPrecioUnitario(double precioUnitario) {
    	this.precioUnitario = precioUnitario;
    	}

    public double getTotal() {
    	return total;
    	}
    public void setTotal(double total) {
    	this.total = total; 
    	}

    public LocalDateTime getFechaCompra() { 
    	return fechaCompra;
    	}
    public void setFechaCompra(LocalDateTime fechaCompra) { 
    	this.fechaCompra = fechaCompra; 
    	}
}