/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.modelo.repositorys;

import br.com.modelo.entidades.Jogador;
import br.com.modelo.entidades.Time;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author sergio
 */
public class TimeRepository {

    private EntityManager manager;

    public TimeRepository(EntityManager manager) {
        this.manager = manager;
    }

    public void adiciona(Time time) {
        this.manager.persist(time);
    }

    public void remove(Long id) {
        Time time = this.procura(id);
        Query query = this.manager.createQuery("SELECT x FROM Jogador x WHERE x.time = :time");
        query.setParameter("time", time);
        List<Jogador> jogadores = query.getResultList();
        for (Jogador jogador : jogadores){
            jogador.setTime(null);
        }
    }
    
    public Time atualiza(Time time){
        return this.manager.merge(time);
    }
    
    public Time procura(Long id) {
        return this.manager.find(Time.class, id);
    }

    public List<Time> getLista(){
        Query query = this.manager.createQuery("SELECT x FROM Time x");
        return query.getResultList();
    }
}
