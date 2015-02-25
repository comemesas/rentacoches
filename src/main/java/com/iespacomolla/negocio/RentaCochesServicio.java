/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iespacomolla.negocio;

import com.iespacomolla.datos.RentaCochesDAO;
import com.iespacomolla.entidades.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Comemesas
 */
public class RentaCochesServicio 
{
    RentaCochesDAO dao = new RentaCochesDAO();
    
    public void nuevoCliente(int id, String nombre, String direccion)throws Exception
    {
        Cliente comprobarCliente = dao.mostrarCliente(id);
        
        if(comprobarCliente != null)
        {
            throw new Exception("Ya existe un cliente con la id "+id);
        }
        
        Cliente cliente = new Cliente(id,nombre,direccion);
        dao.nuevoCliente(cliente);
    }
    
    public void nuevoCoche(int id, String marca, String modelo, String matricula, String color, float precioDia)throws Exception
    {
        Coche comprobarCoche = dao.mostrarCoche(id);
        
        if(comprobarCoche != null)
        {
            throw new Exception("Ya existe un coche con la id "+id);
        }
        if(precioDia<100)
        {
            throw new Exception("El precio para el coche es demasiado bajo");
        }
        
        Coche coche = new Coche(id,marca,modelo,matricula,color,precioDia);
        dao.nuevoCoche(coche);
    }
    
    public void nuevoContrato(int id, Date fecha, Set<Coche> coches, Cliente cliente, int dias)throws Exception
    {
        List<Contrato> contratos = dao.mostrarTodosContratos();
        boolean cocheCoincidente = false;
        boolean fechasSolapadas = false;
        boolean idCoincidente = false;
        boolean diasNoValidos = false;
        boolean clienteNoValido = false;
        
        String exception="";
        
        for(Contrato c :contratos)
        {
            Set<Coche>co = c.getCoches();
            for(Coche coche : co)
            {
                for(Coche coch :coches)
                {
                    if(coch.getId()==coche.getId())
                    {
                        cocheCoincidente = true;
                        break;
                    }
                }
                if(cocheCoincidente==true)
                {
                    break;
                }
            }
            if(cocheCoincidente==true)
            {
                Date fechFinal, fechaInicial=fecha;
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fecha); // Configuramos la fecha que se recibe
                calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
                fechFinal=calendar.getTime();
                
                Date contrFechaInicial=c.getFecha(), contrFechaFinal;
                calendar.setTime(c.getFecha()); // Configuramos la fecha que se recibe
                calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
                contrFechaFinal=calendar.getTime();
                
                if(fechFinal.after(contrFechaInicial)&&fechaInicial.before(contrFechaFinal))
                {
                    fechasSolapadas=true;
                    exception = exception +"fechas solapadas para algún coche\n";
                    break;
                }
                
            }
            
            cocheCoincidente=false;
        }
        
        if(dao.mostrarContrato(id)!=null)
        {
            idCoincidente=true;
            exception = exception +"Id de contrato repetida\n";
        }
        
        if(dias<1)
        {
            diasNoValidos=true;
            exception = exception +"Días no validos\n";
        }
        
        if(dao.mostrarCliente(cliente.getId())==null)
        {
            clienteNoValido=true;
            exception = exception +"El cliente no existe\n";
        }
        
        if( fechasSolapadas == true || idCoincidente == true || diasNoValidos == true || clienteNoValido ==true)
        {
            throw new Exception("El contrato no es valido:\n "+exception);
        }
        
        Contrato contrato = new Contrato(id,fecha,coches,cliente,dias);
        dao.nuevoContrato(contrato);
    }
    
    public void editarCliente(Cliente cliente) throws Exception
    {
        if(dao.mostrarCliente(cliente.getId())==null)
        {
            throw new Exception("El cliente que se intenta editar no existe");
        }
        dao.editarCliente(cliente);
    }
    
    public Cliente mostrarCliente(int idCliente) throws Exception
    {
        Cliente c;
        try
        {
            c=dao.mostrarCliente(idCliente);
            if(c==null)
            {
                throw new Exception("El cliente introducido no existe");
            }
        }
        catch(Exception e)
        {
            throw new Exception("Ha habido algún problema al intentar mostrar el cliente: "+e.getLocalizedMessage());
        }
        return c;
    }

    public List<Cliente> mostrarTodosClientes() throws Exception
    {
        List<Cliente> clientes;
        try
        {
            clientes = dao.mostrarTodosClientes();
            if(clientes.size()<1)
            {
                throw new Exception("No hay guardado ningun cliente");
            }
        }
        catch(Exception e)
        {
            throw new Exception("Ha habido un problema al obtener los clientes: "+e.getLocalizedMessage());
        }
        return clientes;
    }
    
    public List<Cliente> mostrarClientesCoche(int idCoche)throws Exception
    {
        List<Cliente> clientes;
        
        if(dao.mostrarCoche(idCoche)==null)
        {
            throw new Exception("El coche introducido no existe");
        }
        
        try
        {
            clientes = dao.mostrarClientesCoche(idCoche);
            if(clientes.size()<1)
            {
                throw new Exception("No hay guardado ningun cliente");
            }
        }
        catch(Exception e)
        {
            throw new Exception("Ha habido un problema al obtener los clientes: "+e.getLocalizedMessage());
        }
        return clientes;
    }
    
    public void editarCoche(Coche coche)throws Exception
    {
        if(dao.mostrarCoche(coche.getId())==null)
        {
            throw new Exception("El coche que se intenta modificar no existe");
        }
        dao.editarCoche(coche);
    }

    public void eliminarCoche(int idCoche)throws Exception
    {
        if(dao.mostrarCoche(idCoche)==null)
        {
            throw new Exception("El coche que se intenta eliminar no existe");
        }
        dao.eliminarCoche(idCoche);
    }
    
    public Coche mostrarCoche(int idCoche) throws Exception
    {
        Coche c;
        try
        {
            c=dao.mostrarCoche(idCoche);
            if(c==null)
            {
                throw new Exception("El coche introducido no existe");
            }
        }
        catch(Exception e)
        {
            throw new Exception("Ha habido algún problema al intentar mostrar el coche: "+e.getLocalizedMessage());
        }
        return c;
    }

    public List<Coche> mostrarTodosCoches()throws Exception
    {
        List<Coche> coches;
        try
        {
            coches = dao.mostrarTodosCoches();
            if(coches.size()<1)
            {
                throw new Exception("No hay guardado ningun cohce");
            }
        }
        catch(Exception e)
        {
            throw new Exception("Ha habido un problema al obtener los coches: "+e.getLocalizedMessage());
        }
        return coches;
    }

    public List<Coche> mostrarCochesCliente(int idCliente)throws Exception
    {
        List<Coche> coches;
        if(dao.mostrarCliente(idCliente)==null)
        {
            throw new Exception("El cliente introducido no existe");
        }
        try
        {
            coches = dao.mostrarCochesCliente(idCliente);
            if(coches.size()<1)
            {
                throw new Exception("No hay guardado ningun cohce para el cliente "+idCliente);
            }
        }
        catch(Exception e)
        {
            throw new Exception("Ha habido un problema al obtener los clientes: "+e.getLocalizedMessage());
        }
        return coches;
    }

    public Contrato mostrarContrato(int idContrato) throws Exception
    {
        Contrato c;
        try
        {
            c=dao.mostrarContrato(idContrato);
            if(c==null)
            {
                throw new Exception("El contrato introducido no existe");
            }
        }
        catch(Exception e)
        {
            throw new Exception("Ha habido algún problema al intentar mostrar el contrato: "+e.getLocalizedMessage());
        }
        return c;
    }
    
    public List<Contrato> mostrarTodosContratos() throws Exception
    {
        List<Contrato> contratos;
        try
        {
            contratos = dao.mostrarTodosContratos();
            if(contratos.size()<1)
            {
                throw new Exception("No hay guardado ningun contrato");
            }
        }
        catch(Exception e)
        {
            throw new Exception("Ha habido un problema al obtener los contratos: "+e.getLocalizedMessage());
        }
        return contratos;
    }

    public List<Contrato> mostrarContratosCliente(int idCliente) throws Exception
    {
        List<Contrato> contratos;
        if(dao.mostrarCliente(idCliente)==null)
        {
            throw new Exception("El cliente introducido no existe");
        }
        try
        {
            contratos = dao.mostrarContratosCliente(idCliente);
            if(contratos.size()<1)
            {
                throw new Exception("No hay guardado ningun contrato para el cliente "+idCliente);
            }
        }
        catch(Exception e)
        {
            throw new Exception("Ha habido un problema al obtener los contrato: "+e.getLocalizedMessage());
        }
        return contratos;
    }
    
    public List<Contrato> mostrarContratosCoche(int idCoche) throws Exception
    {
        List<Contrato> contratos;
        if(dao.mostrarCliente(idCoche)==null)
        {
            throw new Exception("El coche introducido no existe");
        }
        try
        {
            contratos = dao.mostrarContratosCliente(idCoche);
            if(contratos.size()<1)
            {
                throw new Exception("No hay guardado ningun contrato para el coche "+idCoche);
            }
        }
        catch(Exception e)
        {
            throw new Exception("Ha habido un problema al obtener los contrato: "+e.getLocalizedMessage());
        }
        return contratos;
    }
    
    public float facturadoCliente(int idCliente) throws Exception
    {
        if(dao.mostrarCliente(idCliente)==null)
        {
            throw new Exception("El cliente introducido no existe");
        }
        float facturado;
        try
        {
            facturado = dao.facturadoCliente(idCliente);
        }
        catch(Exception e)
        {
            throw new Exception("Ha habido un problema al obtener el total facturado");
        }
        
        return facturado;
    }

    public void finalizar() throws Exception 
    {		
	dao.finalizar();
    }

}