package com.iespacomolla.entidades;

import java.util.*;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Chema
 */
@Entity
@Table(name="contratos")
public class Contrato {

    @Id
    int id;
    @Temporal(TemporalType.DATE)
    Date fecha;
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @PrimaryKeyJoinColumn
    Set<Coche> coches;//= new HashSet<Coche>()
    @ManyToOne
    Cliente cliente;
    int dias;

    public Contrato(int id, Date fecha, Set coches, Cliente cliente, int dias) {
        this.id = id;
        this.fecha = fecha;
        this.coches = coches;
        this.cliente = cliente;
        this.dias = dias;
    }

    public Contrato() {
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public Set<Coche> getCoches() {
        return coches;
    }

    public void setCoches(Set coches) {
        this.coches = coches;
    }

    @Override
    public String toString() {
        return "Contrato{" + "id=" + id + ", fecha=" + fecha + ", coches=" + coches + ", cliente=" + cliente + ", dias=" + dias + '}';
    }

}
