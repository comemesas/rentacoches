/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iespacomolla.datos;

import com.iespacomolla.entidades.*;
import java.util.List;

/**
 *
 * @author Comemesas
 */
public interface IDAO 
{
   public void nuevoCliente(Cliente cliente)throws Exception;//done
   public void editarCliente(Cliente cliente)throws Exception;//done
   public Cliente mostrarCliente(int idCliente)throws Exception;//done
   public List<Cliente> mostrarTodosClientes()throws Exception;//done
   public List<Cliente> mostrarClientesCoche(int idCoche)throws Exception;//done
   
   public void nuevoCoche(Coche coche)throws Exception;//done
   public void editarCoche(Coche coche)throws Exception;//done
   public void eliminarCoche(int idCoche)throws Exception;//done
   public Coche mostrarCoche(int idCoche)throws Exception;//done
   public List<Coche> mostrarTodosCoches()throws Exception;//done
   public List<Coche> mostrarCochesCliente(int idCliente) throws Exception;//done
   
   
   public void nuevoContrato(Contrato contrato)throws Exception;//done
   public Contrato mostrarContrato(int idContrato) throws Exception;//done
   public List<Contrato> mostrarTodosContratos() throws Exception;//done
   public List<Contrato> mostrarContratosCliente(int idCliente) throws Exception;//done
   public List<Contrato> mostrarContratosCoche(int idCoche) throws Exception;//done
   
   public float facturadoCliente(int idCliente) throws Exception;//done
}
