package org.formacio.setmana2.repositori;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.formacio.setmana2.domini.Alumne;
import org.formacio.setmana2.domini.Curs;
import org.formacio.setmana2.domini.Matricula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Modifica aquesta classe per tal que sigui un component Spring que realitza les 
 * operacions de persistencia tal com indiquen les firmes dels metodes
 */

@Repository
public class RepositoriEscola {
	
	@PersistenceContext
	EntityManager em;
	
	
	public Curs carregaCurs(String nom) {
		Curs curs = em.find(Curs.class, nom);
		return curs;
	}
	
	public Alumne carregaAlumne(String nom) {
		Alumne alumne = em.find(Alumne.class, nom);
		return alumne;
	}
	
	@Transactional
	public Matricula apunta (String alumne, String curs) throws EdatIncorrecteException {
		Alumne alu = carregaAlumne(alumne);
		Curs curso = carregaCurs(curs);
		if (alu.getEdat() > curso.getEdatMinima()) {
	    Matricula matricula = new Matricula();
	    matricula.setAlumne(alu);
	    matricula.setCurs(curso);
	    em.persist(matricula);
	    return matricula;
		} else {
			throw new EdatIncorrecteException();
		}
	}
	
	
}
