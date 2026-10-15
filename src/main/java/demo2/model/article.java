package demo2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class article {

    private String nombre;
    private int cantidad;
    private double precio;
    private double descuento;

    public article() {
    }

    public article(String nombre, int cantidad, double precio, double descuento) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descuento = descuento;
    }

    public String getNombre() {
        return nombre;
    }

    @JsonProperty("name")
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    @JsonProperty("quantity")
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    @JsonProperty("unitPrice")
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getDescuento() {
        return descuento;
    }

    @JsonProperty("discount")
    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getGrossAmount() {
        return cantidad * precio;
    }

    public double getDiscountedAmount() {
        double grossAmount = getGrossAmount();
        return grossAmount - (grossAmount * descuento / 100);
    }

    @Override
    public String toString() {
        return "Article{" +
                "nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                ", descuento=" + descuento +
                '}';
    }
}