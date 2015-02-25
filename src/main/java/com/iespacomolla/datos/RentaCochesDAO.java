/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iespacomolla.datos;

import com.iespacomolla.entidades.Cliente;
import com.iespacomolla.entidades.Coche;
import com.iespacomolla.entidades.Contrato;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 * @author Comemesas
 */
public class RentaCochesDAO implements IDAO 
{

    private SessionFactory sessionFactory;

    public RentaCochesDAO() 
    {
        try 
        {
            ServiceRegistry serviceRegistry;
            Configuration configuration = new Configuration();
            configuration.configure();
            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } 
        catch (Throwable e) 
        {
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public void nuevoCliente(Cliente cliente) throws Exception 
    {
        Session sesion = sessionFactory.openSession();

        Transaction tx = sesion.beginTransaction();

        sesion.save(cliente);

        tx.commit();

        sesion.close();
    }

    public void editarCliente(Cliente cliente) throws Exception 
    {
        Session sesion = sessionFactory.openSession();
        sesion.update(cliente);
        sesion.close();
    }

    public Cliente mostrarCliente(int idCliente) throws Exception 
    {
        Session sesion = sessionFactory.openSession();
        Cliente c = (Cliente) sesion.get(Cliente.class, idCliente);
        sesion.close();
        return c;
    }

    public List<Cliente> mostrarTodosClientes() throws Exception 
    {
        Session sesion = sessionFactory.openSession();

        List<Cliente> lista;

        Query q = sesion.createQuery("FROM Cliente");
        lista = q.list();

        sesion.close();

        return lista;
    }

    public List<Cliente> mostrarClientesCoche(int idCoche) throws Exception 
    {
        List<Cliente>clientes = new ArrayList<Cliente>();
        List<Contrato>contratos = this.mostrarContratosCoche(idCoche);
        
        for(Contrato c :contratos)
        {
            clientes.add(c.getCliente());
        }
        
        return clientes;
    }

    public void nuevoCoche(Coche coche) throws Exception 
    {
        Session sesion = sessionFactory.openSession();

        Transaction tx = sesion.beginTransaction();

        sesion.save(coche);

        tx.commit();

        sesion.close();
    }

    public void editarCoche(Coche coche) throws Exception 
    {
        this.eliminarCoche(coche.getId());
        this.nuevoCoche(coche);
    }

    public void eliminarCoche(int idCoche) throws Exception 
    {
        Coche c = this.mostrarCoche(idCoche);
        Session sesion = sessionFactory.openSession();

        Transaction tx = sesion.beginTransaction();

        sesion.delete(c);

        tx.commit();

        sesion.close();
    }

    public Coche mostrarCoche(int idCoche) throws Exception 
    {
        Session sesion = sessionFactory.openSession();
        Coche c = (Coche) sesion.get(Coche.class, idCoche);
        sesion.close();
        return c;
    }

    public List<Coche> mostrarTodosCoches() throws Exception 
    {
        Session sesion = sessionFactory.openSession();

        List<Coche> lista;

        Query q = sesion.createQuery("FROM Coche");
        lista = q.list();

        sesion.close();

        return lista;
    }

    public List<Coche> mostrarCochesCliente(int idCliente) throws Exception 
    {
        List<Coche>coches = new ArrayList<Coche>();
        List<Contrato>contratosCliente = this.mostrarContratosCliente(idCliente);
        
        for(Contrato c :contratosCliente)
        {
            Set<Coche>aux = c.getCoches();
            for(Coche coch :aux)
            {
                coches.add(coch);
            }
        }        
        return coches;
    }

    public void nuevoContrato(Contrato contrato) throws Exception 
    {
        Session sesion = sessionFactory.openSession();

        Transaction tx = sesion.beginTransaction();

        sesion.save(contrato);

        tx.commit();

        sesion.close();
    }

    public List<Contrato> mostrarTodosContratos() throws Exception 
    {
        Session sesion = sessionFactory.openSession();

        List<Contrato> lista;

        Query q = sesion.createQuery("FROM Contrato");
        lista = q.list();
        
        for(Contrato c :lista)
        {
            Hibernate.initialize(c.getCoches());
            Hibernate.initialize(c.getCliente());
        }

        sesion.close();

        return lista;
    }

    public List<Contrato> mostrarContratosCliente(int idCliente) throws Exception 
    {
        Session sesion = sessionFactory.openSession();

        List<Contrato> lista;
        List<Contrato> aux = new ArrayList<Contrato>();

        Query q = sesion.createQuery("FROM Contrato");
        lista = q.list();

        for (Contrato c : lista) 
        {
            if (c.getCliente().getId() == idCliente) 
            {
                Hibernate.initialize(c.getCoches());
                Hibernate.initialize(c.getCliente());
                aux.add(c);
            }
        }
        lista = aux;
        sesion.close();

        return lista;
    }

    public List<Contrato> mostrarContratosCoche(int idCoche) throws Exception 
    {
        Session sesion = sessionFactory.openSession();

        List<Contrato> lista;
        List<Contrato> aux = new ArrayList<Contrato>();

        Query q = sesion.createQuery("FROM Contrato");
        lista = q.list();

        for (Contrato c : lista) 
        {
            Set<Coche>coches = c.getCoches();
            for(Coche coch :coches)
            {
                if(coch.getId()==idCoche)
                {
                    Hibernate.initialize(c.getCoches());
                    Hibernate.initialize(c.getCliente());
                    aux.add(c);
                    break;
                }
            }
        }
        lista = aux;
        sesion.close();

        return lista;
    }

    public float facturadoCliente(int idCliente) throws Exception 
    {
        List<Contrato> contratos = this.mostrarContratosCliente(idCliente);
        float facturado = 0;
        
        for(Contrato c :contratos)
        {
            float precioContrato = 0;
            Set<Coche>coches = c.getCoches();
            for(Coche coch :coches)
            {
                precioContrato = precioContrato + (coch.getPrecioDia()*c.getDias());
            }
            facturado = facturado + precioContrato;
        }
        
        return facturado;
    }

    @Override
    public Contrato mostrarContrato(int idContrato) throws Exception 
    {
        Session sesion = sessionFactory.openSession();
        Contrato c = (Contrato) sesion.get(Contrato.class, idContrato);
        sesion.close();
        return c;
    }
    
    public void finalizar() throws Exception 
    {		
        sessionFactory.close();
    }

}
