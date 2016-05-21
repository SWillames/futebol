/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controle;

import br.com.modelo.entidades.Time;
import br.com.modelo.repositorys.TimeRepository;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author sergio
 */
@Named(value = "timeBean")
@Dependent
public class TimeBean {

    private Time time = new Time();
    private List<Time> times;

    public void adiciona(){
        EntityManager manager = this.getManager();
        TimeRepository repository = new TimeRepository(manager);
        if (this.time.getId() == null ) {
            repository.adiciona(time);
        } else {
            repository.atualiza(time);
        }
        this.time = new Time();
        this.times = null;
    }
    
    public void  prepararAlteracao(){
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext()
    }
    
    public TimeBean() {
    }
    
    private EntityManager getManager(){
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        return (EntityManager) request.getAttribute("EntityManager");
        
    }
    
}
