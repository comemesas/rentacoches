package com.iespacomolla.entidades;

import java.util.*;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Chema
 */
@Entity
@Table(name="coches")
public class Coche {
    
    @Id
    int id;
    String marca;
    String modelo;
    String matricula;
    String color;
    float precioDia;

    public Coche(int id, String marca, String modelo, String matricula, String color, float precioDia) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.color = color;
        this.precioDia = precioDia;
    }

    public Coche() {
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getPrecioDia() {
        return precioDia;
    }

    public void setPrecioDia(float precioDia) {
        this.precioDia = precioDia;
    }

    @Override
    public String toString() {
        return "Coche{" + "id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", matricula=" + matricula + ", color=" + color + ", precioDia=" + precioDia + '}';
    }

}
