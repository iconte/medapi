package br.com.icc.medapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "exames")
public class Exame {

	@Id
	private Long id;
	private String codSus;
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "categoria_exame_id")
	private CategoriaExame categoriaExame;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "cod_sus")
	public String getCodSus() {
		return codSus;
	}

	public void setCodSus(String codSus) {
		this.codSus = codSus;
	}
	@Column(name = "descricao")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public CategoriaExame getCategoriaExame() {
		return categoriaExame;
	}

	public void setCategoriaExame(CategoriaExame categoriaExame) {
		this.categoriaExame = categoriaExame;
	}
	
	

	

//	public void setCategoriaId(Long categoriaId) {
//		this.categoriaId = categoriaId;
//	}
	
	

}
