package com.cenop4011.padroniza.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name= "tb_estudo_operacao")
@Data
public class Estudo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="numero_operacao_dv")
	private String numeroOperacaoDV;
	@Column(name="numero_operacao")
	private Integer numeroOperacao;
	
	@Column(name="matricula_resp")
	private String matriculaResponsavel;
	@Column(name="dt_inicio")
	private Date  dataInicio = new Date();
	@Column(name = "data_fim")
	private Date dataFim;
	@Column(name="codigo_checklist")
	private Integer codigoChecklist;
	@Column(name="cod_sit")
	private Integer codigoSituacao = 0;
	
	
	@OneToMany(mappedBy = "estudo", cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
	private List<Resposta> respostas = new ArrayList<>();
	
	

}
