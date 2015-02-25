/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espacomolla.rentaCoches;

import com.iespacomolla.negocio.*;
import com.iespacomolla.datos.*;
import com.iespacomolla.entidades.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Comemesas
 */
public class MenuPrincipal 
{
    private RentaCochesServicio rcs = new RentaCochesServicio();
    
    public void iniciarAplicacion()
    {
        try {
        int opcion=-1;
        while(opcion!=0){
            System.out.println("Introduzca la operaci�n");
            System.out.println("1.Nuevo Cliente");
            System.out.println("2.Nuevo Coche");
            System.out.println("3.Nuevo Contrato");
            System.out.println("4.Editar Cliente");
            System.out.println("5.Mostrar Cliente");
            System.out.println("6.Mostrar todos los Clientes");
            System.out.println("7.Mostrar Clientes de un Coche");
            System.out.println("8.Editar Coche");
            System.out.println("9.Eliminar Coche");
            System.out.println("10.Mostrar Coche");
            System.out.println("11.Mostrar todos los Coches");
            System.out.println("12.Mostrar Coches de un Cliente");
            System.out.println("13.Mostrar Contrato");
            System.out.println("14.Mostrar todos los Contratos");
            System.out.println("15.Mostrar Contratos de un Cliente");
            System.out.println("16.Mostrar Contratos de un Coche");
            System.out.println("17.Mostrar total facturado a un Cliente");
      
            
            System.out.println("0.Salir");
            Scanner sc=new Scanner(System.in);
            opcion=sc.nextInt();
           
            if(opcion==1){
                this.nuevoCliente();
            }
            if(opcion==2){
                this.nuevoCoche();
            }
            if(opcion==3){
                this.nuevoContrato();
            }
            if(opcion==4){
                this.editarCliente();
            }
            if(opcion==5){
               
            	this.mostrarCliente();
            }
            if(opcion==6){
               
            	this.mostrarTodosClientes();
            }
            if(opcion==7){
               
            	this.mostrarClientesCoche();
            }
            if(opcion==8){
               
            	this.editarCoche();
            }
            if(opcion==9){
               
            	this.eliminarCoche();
            }
            if(opcion==10){
               
            	this.mostrarCoche();
            }
            if(opcion==11){
               
            	this.mostrarTodosCoches();
            }
            if(opcion==12){
               
            	this.mostrarCochesCliente();
            }
            if(opcion==13){
               
            	this.mostrarContrato();
            }
            if(opcion==14){
               
            	this.mostrarTodosContratos();
            }
            if(opcion==15){
               
            	this.mostrarContratosCliente();
            }
            if(opcion==16){
               
            	this.mostrarContratosCoche();
            }
            if(opcion==17){
               
            	this.mostrarFacturadoCliente();
            }
            
            if(opcion==0){
            	try{
            	rcs.finalizar();
            	}catch(Exception ex3){
            		System.out.println("Hubo un problema al cerrar el dispositivo de almacenamiento.");
            	}
            }
        }
        }catch(InputMismatchException ex2){
            System.out.println("Debe introducir un valor num�rico");
            System.out.println("");
            this.iniciarAplicacion();
        }
        catch(Exception ex3){
            System.out.println("ERROR En la aplicaci�n: ");
            System.out.println("");
            System.out.println(ex3.getLocalizedMessage());
            this.iniciarAplicacion();
        }
    }
    public void nuevoCoche()
    {
        try {
            System.out.println("Introduzca el id");
            Scanner sc= new Scanner(System.in);
            int id = sc.nextInt();

            System.out.println("Introduzca la marca");
            sc = new Scanner(System.in);
            String marca = sc.nextLine();

            System.out.println("Introduzca el modelo");
            sc = new Scanner(System.in);
            String modelo = sc.nextLine();

            System.out.println("Introduzca la matricula");
            sc = new Scanner(System.in);
            String matricula = sc.nextLine();

            System.out.println("Introduzca el color");
            sc = new Scanner(System.in);
            String color = sc.nextLine();

            System.out.println("Introduzca el precio por dia");
            sc = new Scanner(System.in);
            float precio = sc.nextFloat();
            
            rcs.nuevoCoche(id, marca, modelo, matricula, color, precio);
        }
        catch (NullPointerException ex2) {
            System.out.println("Error: No se ha inicializado correctamente el dispositivo de almacenamiento");
            System.out.println("");
        }
        catch(InputMismatchException ex2){
            System.out.println("Debe introducir un valor num�rico");
            System.out.println("");
            
        }
        catch (Exception ex) {
            System.out.println("Error:"+ ex.getLocalizedMessage());
            System.out.println("");
            
        }
                
    }
    
    public void nuevoCliente()
    {
        try {
            System.out.println("Introduzca el id");
            Scanner sc= new Scanner(System.in);
            int id = sc.nextInt();

            System.out.println("Introduzca el nombre");
            sc = new Scanner(System.in);
            String nombre = sc.nextLine();

            System.out.println("Introduzca la direccion");
            sc = new Scanner(System.in);
            String direccion = sc.nextLine();
            
            rcs.nuevoCliente(id, nombre, direccion);
        }
        catch (NullPointerException ex2) {
            System.out.println("Error: No se ha inicializado correctamente el dispositivo de almacenamiento");
            System.out.println("");
        }
        catch(InputMismatchException ex2){
            System.out.println("Debe introducir un valor num�rico");
            System.out.println("");
            
        }
        catch (Exception ex) {
            System.out.println("Error:"+ ex.getLocalizedMessage());
            System.out.println("");
            
        }
    }
    
    public void nuevoContrato()
    {
        try {
            System.out.println("Introduzca el id del contrato");
            Scanner sc= new Scanner(System.in);
            int id = sc.nextInt();

            System.out.println("Introduzca la fecha del contrato dd/MM/yyyy");
            sc = new Scanner(System.in);
            String fechaStr = sc.nextLine();
            
            DateFormat sdf = DateFormat.getDateInstance(DateFormat.SHORT);
            Date fecha = sdf.parse(fechaStr);
            
            int añadirCoche=1;
            Set<Coche>coches = new HashSet<Coche>();
            while(añadirCoche!=0)
            {
                System.out.println("Introduzca la id del Coche");
                sc=new Scanner(System.in);
                int idCoche = sc.nextInt();
                
                try
                {
                    Coche coche = rcs.mostrarCoche(idCoche);
                    coches.add(coche);
                }
                catch(Exception e)
                {
                    throw new Exception("Ha habido un problema al obtener el coche: "+e.getLocalizedMessage());
                }
                System.out.println("0.Parar de añadir Coches al contrato");
                System.out.println("otro.Añadir otro Coche al contrato");
                sc = new Scanner (System.in);
                añadirCoche = sc.nextInt();
            }
            Cliente cliente;
            try
            {
                System.out.println("Introduzca la id del Cliente");
                sc=new Scanner(System.in);
                int idCliente = sc.nextInt();
                cliente = rcs.mostrarCliente(idCliente);
            }
            catch(Exception e)
            {
                throw new Exception("Ha habido un problema al obtener el Cliente: "+e.getLocalizedMessage());
            }
            
            System.out.println("Introduzca los dias que durara el contrato");
            sc = new Scanner(System.in);
            int dias = sc.nextInt();
            
            rcs.nuevoContrato(id, fecha, coches, cliente, dias);
        }
        catch (NullPointerException ex2) {
            System.out.println("Error: No se ha inicializado correctamente el dispositivo de almacenamiento");
            System.out.println("");
        }
        catch(InputMismatchException ex2){
            System.out.println("Debe introducir un valor num�rico");
            System.out.println("");
            
        }
        catch (Exception ex) {
            System.out.println("Error:"+ ex.getLocalizedMessage());
            System.out.println("");
            
        }
    }
    
    public void editarCliente()
    {
        try {
            System.out.println("Introduzca el id del Cliente a editar");
            Scanner sc= new Scanner(System.in);
            int id = sc.nextInt();

            System.out.println("Introduzca el nombre");
            sc = new Scanner(System.in);
            String nombre = sc.nextLine();

            System.out.println("Introduzca la direccion");
            sc = new Scanner(System.in);
            String direccion = sc.nextLine();
            
            Cliente cliente = new Cliente(id, nombre, direccion);
            
            rcs.editarCliente(cliente);
        }
        catch (NullPointerException ex2) {
            System.out.println("Error: No se ha inicializado correctamente el dispositivo de almacenamiento");
            System.out.println("");
        }
        catch(InputMismatchException ex2){
            System.out.println("Debe introducir un valor num�rico");
            System.out.println("");
            
        }
        catch (Exception ex) {
            System.out.println("Error:"+ ex.getLocalizedMessage());
            System.out.println("");
            
        }
    }
    
    public void mostrarCliente() throws Exception
    {
        try {
            System.out.println("Introduzca el id del Cliente a mostrar");
            Scanner sc= new Scanner(System.in);
            int id = sc.nextInt();
            Cliente cliente = rcs.mostrarCliente(id);
            System.out.println(cliente.toString());
        }
        catch(Exception e)
        {
            throw new Exception("Ha habido un problema al obtener el cliente: "+e.getLocalizedMessage());
        }
    }
    
    public void mostrarTodosClientes() throws Exception
    {
        try
        {
            List<Cliente>clientes = rcs.mostrarTodosClientes();
            for(Cliente c :clientes)
            {
                System.out.println(c.toString());
            }
        }
        catch(Exception e)
        {
            throw new Exception("Ha habido un problema al obtener los clientes: "+e.getLocalizedMessage());
        }
    }
    
    public void mostrarClientesCoche() throws Exception
    {
        try
        {
            System.out.println("Introduzca el id del Coche para mostrar sus clientes");
            Scanner sc= new Scanner(System.in);
            int id = sc.nextInt();
            List<Cliente>clientes = rcs.mostrarClientesCoche(id);
            for(Cliente c :clientes)
            {
                System.out.println(c.toString());
            }
        }
        catch(Exception e)
        {
            throw new Exception("Ha habido un problema al obtener los clientes: "+e.getLocalizedMessage());
        }
    }
    
    public void editarCoche()
    {
        try {
            System.out.println("Introduzca el id del coche que desea editar");
            Scanner sc= new Scanner(System.in);
            int id = sc.nextInt();

            System.out.println("Introduzca la marca");
            sc = new Scanner(System.in);
            String marca = sc.nextLine();

            System.out.println("Introduzca el modelo");
            sc = new Scanner(System.in);
            String modelo = sc.nextLine();

            System.out.println("Introduzca la matricula");
            sc = new Scanner(System.in);
            String matricula = sc.nextLine();

            System.out.println("Introduzca el color");
            sc = new Scanner(System.in);
            String color = sc.nextLine();

            System.out.println("Introduzca el precio por dia");
            sc = new Scanner(System.in);
            float precio = sc.nextFloat();
            
            Coche coche = new Coche(id, marca, modelo, matricula, color, precio);
            
            rcs.editarCoche(coche);
        }
        catch (NullPointerException ex2) {
            System.out.println("Error: No se ha inicializado correctamente el dispositivo de almacenamiento");
            System.out.println("");
        }
        catch(InputMismatchException ex2){
            System.out.println("Debe introducir un valor num�rico");
            System.out.println("");
            
        }
        catch (Exception ex) {
            System.out.println("Error:"+ ex.getLocalizedMessage());
            System.out.println("");
            
        }
    }
    
    public void eliminarCoche() throws Exception
    {
        try 
        {
            System.out.println("Introduzca el id del coche que desea editar");
            Scanner sc= new Scanner(System.in);
            int id = sc.nextInt();
            rcs.eliminarCoche(id);
        }catch(Exception e)
        {
            throw new Exception("Ha habido un problema al eliminar el coche: "+e.getLocalizedMessage());
        }
    }
    
    public void mostrarCoche() throws Exception
    {
        try {
            System.out.println("Introduzca el id del Coche a mostrar");
            Scanner sc= new Scanner(System.in);
            int id = sc.nextInt();
            Coche coche = rcs.mostrarCoche(id);
            System.out.println(coche.toString());
        }
        catch(Exception e)
        {
            throw new Exception("Ha habido un problema al obtener el cliente: "+e.getLocalizedMessage());
        }
    }
    
    public void mostrarTodosCoches() throws Exception
    {
        try
        {
            List<Coche>coches = rcs.mostrarTodosCoches();
            for(Coche c :coches)
            {
                System.out.println(c.toString());
            }
        }
        catch(Exception e)
        {
            throw new Exception("Ha habido un problema al obtener los coches: "+e.getLocalizedMessage());
        }
    }
    
    public void mostrarCochesCliente() throws Exception
    {
        try
        {
            System.out.println("Introduzca el id del Cliente para mostrar sus coches");
            Scanner sc= new Scanner(System.in);
            int id = sc.nextInt();
            List<Coche>coches = rcs.mostrarCochesCliente(id);
            for(Coche c :coches)
            {
                System.out.println(c.toString());
            }
        }
        catch(Exception e)
        {
            throw new Exception("Ha habido un problema al obtener los coches: "+e.getLocalizedMessage());
        }
    }
    
    public void mostrarContrato() throws Exception
    {
        try {
            System.out.println("Introduzca el id del Contrato a mostrar");
            Scanner sc= new Scanner(System.in);
            int id = sc.nextInt();
            Contrato contrato = rcs.mostrarContrato(id);
            System.out.println(contrato.toString());
        }
        catch(Exception e)
        {
            throw new Exception("Ha habido un problema al obtener el contrato: "+e.getLocalizedMessage());
        }
    }
    
    public void mostrarTodosContratos() throws Exception
    {
        try
        {
            List<Contrato>contratos = rcs.mostrarTodosContratos();
            for(Contrato c :contratos)
            {
                System.out.println(c.toString());
            }
        }
        catch(Exception e)
        {
            throw new Exception("Ha habido un problema al obtener los contratos: "+e.getLocalizedMessage());
        }
    }
    
    public void mostrarContratosCliente() throws Exception
    {
        try
        {
            System.out.println("Introduzca el id del Cliente para mostrar sus contratos");
            Scanner sc= new Scanner(System.in);
            int id = sc.nextInt();
            List<Contrato>contratos = rcs.mostrarContratosCliente(id);
            for(Contrato c :contratos)
            {
                System.out.println(c.toString());
            }
        }
        catch(Exception e)
        {
            throw new Exception("Ha habido un problema al obtener los contratos: "+e.getLocalizedMessage());
        }
    }
    
    public void mostrarContratosCoche() throws Exception
    {
        try
        {
            System.out.println("Introduzca el id del Coche para mostrar sus contratos");
            Scanner sc= new Scanner(System.in);
            int id = sc.nextInt();
            List<Contrato>contratos = rcs.mostrarContratosCoche(id);
            for(Contrato c :contratos)
            {
                System.out.println(c.toString());
            }
        }
        catch(Exception e)
        {
            throw new Exception("Ha habido un problema al obtener los coches: "+e.getLocalizedMessage());
        }
    }
    
    public void mostrarFacturadoCliente() throws Exception
    {
        try
        {
            System.out.println("Introduzca el id del Cliente para mostrar sus contratos");
            Scanner sc= new Scanner(System.in);
            int id = sc.nextInt();
            
            
            System.out.println("Total facturado al cliente"+id+": "+rcs.facturadoCliente(id)+"€");
        }
        catch(Exception e)
        {
            throw new Exception("Ha habido un problema al obtener el total facturado: "+e.getLocalizedMessage());
        }
    }
}
