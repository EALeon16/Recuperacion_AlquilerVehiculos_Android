package com.example.recuperacion_registrovehicular.Clases;

import java.util.Date;

public class Horario {
    private String fecha_recogida;
    private String fecha_devolucion;
    private int dias;
    private double precio_total;
    private String placa;
    private String marca;
    private String modelo;
    private String tipo_vehiculo;
    private int nro_registro;


    public  Horario (){}

    public Horario(String fecha_recogida, String fecha_devolucion, int dias, double precio_total, String placa, String marca, String modelo, String tipo_vehiculo, int nro_registro) {
        this.fecha_recogida = fecha_recogida;
        this.fecha_devolucion = fecha_devolucion;
        this.dias = dias;
        this.precio_total = precio_total;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo_vehiculo = tipo_vehiculo;
        this.nro_registro = nro_registro;
    }

    public String getFecha_recogida() {
        return fecha_recogida;
    }

    public void setFecha_recogida(String fecha_recogida) {
        this.fecha_recogida = fecha_recogida;
    }

    public String getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(String fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public double getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(double precio_total) {
        this.precio_total = precio_total;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo_vehiculo() {
        return tipo_vehiculo;
    }

    public void setTipo_vehiculo(String tipo_vehiculo) {
        this.tipo_vehiculo = tipo_vehiculo;
    }

    public int getNro_registro() {
        return nro_registro;
    }

    public void setNro_registro(int nro_registro) {
        this.nro_registro = nro_registro;
    }
}
