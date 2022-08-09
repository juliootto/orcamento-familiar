package br.com.julio.orcamento.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.julio.orcamento.util.JPAUtil;

@Entity
@Table(name = "categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_categoria")
	private long idCategoria;
	private String descricao;
	
	
	
	public Categoria() {
	}



	
	public Categoria(String descricao) {
		this.descricao = descricao;
		EntityManager em = JPAUtil.getEntityManager();
		String selectionQuery = "SELECT idCategoria FROM Categoria WHERE descricao = :descricao";
		this.idCategoria = (long) em.createQuery(selectionQuery).setParameter("descricao", descricao).getSingleResult(); //This should be placed in a try/catch block to handle org.hibernate.NonUniqueResultException
		System.out.println("categoria: " + descricao + " idCategoria: " + this.idCategoria);
	}


	public long getIdCategoria() {
		return idCategoria;
	}


	public String getDescricao() {
		return descricao;
	}
	
	
}
