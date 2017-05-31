package com.credit.bean;

import com.credit.dao.CreditCotizacion;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.credit.dao.CreditPersona;
import java.util.ArrayList;
import javax.persistence.Query;

@ManagedBean
@RequestScoped
public class Persona extends CreditPersona{
    private String nombreCompleto;
    private int numeroCotizacion;
    private String estadoCotizacion;
    public ArrayList<Persona> personaList;
    private boolean Add;
    
    public Persona() {}
    
    
    //creacion de los metodos obtener y establecer
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getNumeroCotizacion() {
        return numeroCotizacion;
    }

    public void setNumeroCotizacion(int numeroCotizacion) {
        this.numeroCotizacion = numeroCotizacion;
    }

    public String getEstadoCotizacion() {
        return estadoCotizacion;
    }

    public void setEstadoCotizacion(String estadoCotizacion) {
        this.estadoCotizacion = estadoCotizacion;
    }

    public ArrayList<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(ArrayList<Persona> personaList) {
        this.personaList = personaList;
    }

    public boolean isAdd() {
        return Add;
    }

    public void setAdd(boolean Add) {
        this.Add = Add;
    }
    
    /*
        Metodo utilizada para verificar que el formulario contenga un dato
    */
    private boolean isFormData(){
        boolean validar = false;
        
        if(!getDpi().equals("")){
            validar = true;
        }
        else if(!getNit().equals("")){
            validar = true;
        }
        else if(!getPrimerNombre().equals("")){
            validar = true;
        }
        else if(!getSegundoNombre().equals("")){
            validar = true;
        }
        else if(!getPrimerApellido().equals("")){
            validar = true;
        }
        else if(!getSegundoApellido().equals("")){
            validar = true;
        }
        
        return validar;
    }
    
    /*
        Metodo utilizado para consultar las cotizaciones de una persona
    */
    public void buscarEnCotizacion(){
        if(isFormData()){
            this.Add = true;
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("CreditPU");
            EntityManager em = emf.createEntityManager();

            String jpql = "SELECT c, p "
                    + "FROM CreditCotizacion c "
                    + "JOIN c.creditPersonaDpi p "
                    + "WHERE ";

            if(!getDpi().equals("")){
                jpql += "p.dpi = '"+getDpi()+"'";
            }
            else if(!getNit().equals("")){
                jpql += "p.nit = '"+getNit()+"'";
            }
            else if(!getPrimerNombre().equals("")){
                jpql += "p.primerNombre LIKE '%"+getPrimerNombre()+"%'";
            }
            else if(!getSegundoNombre().equals("")){
                jpql += "p.segundoNombre LIKE '%"+getSegundoNombre()+"%'";
            }
            else if(!getPrimerApellido().equals("")){
                jpql += "p.primerApellido LIKE '%"+getPrimerApellido()+"%'";
            }
            else if(!getSegundoApellido().equals("")){
                jpql += "p.segundoApellido LIKE '%"+getSegundoApellido()+"%'";
            }

            Query consulata = em.createQuery(jpql);
            List<Object[]> resultado = consulata.getResultList();

            ArrayList<Persona> arrayResult = new ArrayList<>();
            for(Object[] objeto : resultado){
                CreditCotizacion cotizacion = (CreditCotizacion) objeto[0];
                CreditPersona persona = (CreditPersona) objeto[1];

                Persona miPersona = new Persona();
                miPersona.numeroCotizacion = cotizacion.getNumero();
                miPersona.setDpi(persona.getDpi());
                miPersona.nombreCompleto = persona.getPrimerNombre() 
                        + " " + persona.getSegundoNombre()
                        + " " + persona.getPrimerApellido()
                        + " " + persona.getSegundoApellido();
                miPersona.estadoCotizacion = cotizacion.getEstado().toString();
                if(cotizacion.getEstado().toString().equals("a")){
                    this.Add = false;
                }
                arrayResult.add(miPersona);
            }

            personaList = arrayResult;

            em.close();
            emf.close();
        }
    }
}
