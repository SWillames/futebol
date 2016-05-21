/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.filters;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 *
 * @author sergio
 */
@WebFilter
public class JPAFilter implements Filter{
    
    private EntityManagerFactory factory;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.factory = Persistence.createEntityManagerFactory("FutebolPU");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //CHEGADA
        EntityManager manager = this.factory.createEntityManager();
        request.setAttribute("EntityManager", manager);
        manager.getTransaction().begin();
        //CHEGADA
        
        chain.doFilter(request, response);
        
        try {
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }finally{
            manager.close();
        }
    }

    @Override
    public void destroy() {
        this.factory.close();
    }
    
    
}
