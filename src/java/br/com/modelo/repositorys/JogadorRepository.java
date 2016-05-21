/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.modelo.repositorys;

import br.com.modelo.entidades.Jogador;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author sergio
 */
public class JogadorRepository {
    
    private EntityManager manager;

    public JogadorRepository(EntityManager manager) {
        this.manager = manager;
    }
    
    public void adiciona (Jogador jogador){
        this.manager.persist(jogador);
    }
    
    public void remove(Long id){
        Jogador jogador = this.procura(id);
        this.manager.remove(id);
    }
    
    public Jogador atualiza(Jogador jogador){
        return this.manager.merge(jogador);
    }
    
    public List<Jogador> getLista(){
        Query query = this.manager.createQuery("SELECT x FROM Jogador x");
        return query.getResultList();
    }
    
    public Jogador procura(Long id){
        return this.manager.find(Jogador.class, id);
    }
    
}
