package com.credit.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class Dir {

    public Dir() {
    }
    
    // Metodo para direccionar al formulario de buscar persona
    public void cot_buscarpersona(){
        try{
            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect("cot_buscarpersona.xhtml");
        }
        catch(Exception e){
            System.out.println("Error: " + e);
        }
    } 
    
    //metodo para ingresar al formulario de nueva cotizacion
    public void cot_nuevacotizacion(){
        try{
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().redirect("cot_nuevacotizacion.xhtml");
        }
        catch(Exception e ){
            System.out.println("Error: "+e);
        }
    }
    
    //metodo para direccionar al formulario de historial de solicitudes
    public void sol_historiasolicitud(){
        try{
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().redirect("sol_historialsolicitud.xhtml");
        }
        catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    //medoto para direccionar al formulario de nueva solicitud
    public void sol_nuevasolicitud(){
        try{
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().redirect("sol_nuevasolicitud.xhtml");
        }
        catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
}
