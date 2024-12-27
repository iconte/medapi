package br.com.icc.medapi.model.dto;

import java.io.Serializable;

public class ExameDTO implements Serializable {

  private static final long serialVersionUID = 1L;
  private Long id;
  private String codSus;
  private String descricao;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCodSus() {
    return codSus;
  }

  public void setCodSus(String codSus) {
    this.codSus = codSus;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

}
