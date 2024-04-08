package com.cenop4011.padroniza.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import com.cenop4011.padroniza.enuns.TipoPerguntaList;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Entity
@Data
@Table(name = "tb_pergunta_historico")
public class PerguntaHistorico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@Column(name = "descricao", columnDefinition = "LONGTEXT")
	private String descricao;
	@Column(name = "created_at")	
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
	@Column(name="versao")
	private Integer versao;
	
	@Column(name = "ajuda", columnDefinition = "LONGTEXT")
	private String ajuda;
	
	@Column(name = "observacao", columnDefinition = "LONGTEXT")
	private String observacao;
	@Column(name="tempo_alerta")
	private Integer tempoAlerta;
	@Column(name="link")
	private String link;
	@Column(name="ativo")
	private Boolean ativo=true;
	@Column(name = "apelido")
	private String apelido;
	@Column(name="matricula_funci")
	private String matriculaFunci;
	@Column(name = "automatizavel")
	private Boolean automatizavel=false;
	
	 @JsonIgnore
	 @ManyToOne
     @JoinColumn(name = "pergunta_id", referencedColumnName = "id")
     private Pergunta pergunta;
	
	
	
	
	@LazyCollection(value = LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "pergunta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PosicaoPerguntaHistorico> posicaoPerguntas = new ArrayList<>();
	
	
	public PerguntaHistorico() {
		super();
	}
	
	
	
	public PerguntaHistorico(Pergunta pergunta) {
		super();
	
		this.descricao = pergunta.getDescricao();
		this.createdAt = pergunta.getCreatedAt();
		this.updatedAt=pergunta.getUpdatedAt();
		
		this.versao = pergunta.getVersao();
		this.ajuda = pergunta.getAjuda();
		this.observacao = pergunta.getObservacao();
		this.tempoAlerta = pergunta.getTempoAlerta();
		this.tipoResposta = pergunta.getTipoResposta();
		this.link=pergunta.getLink();
		this.listaCodigosLinha = adicionarCodigosLinha(pergunta);
		// Verificar se há respostas antes de chamar adicionarRespostas
	    if (pergunta.getRespostas() != null && pergunta.getRespostas().size() > 0) {
	        this.respostas = adicionarRespostas(pergunta);
	    }

		this.pergunta=pergunta;
		
		
	}

	



	private List<CodigoLinhaHistorico> adicionarCodigosLinha(Pergunta pergunta) {
		
		this.setListaCodigosLinha(new ArrayList<>());
		for (CodigoLinha  codigoLinha : pergunta.getListaCodigosLinha()) {
			CodigoLinhaHistorico codigoLinhaHistorico = new CodigoLinhaHistorico(codigoLinha,this);
			
			codigoLinhaHistorico.setPergunta(this);
			
			this.listaCodigosLinha.add(codigoLinhaHistorico);
			
		}
		
		
		return this.listaCodigosLinha;
	}
	
	
	private List<RespostaHistorico> adicionarRespostas(Pergunta pergunta) {
		this.setRespostas(new ArrayList<>());

		
		for (Resposta resposta : pergunta.getRespostas()) {
			
			RespostaHistorico respostaHistorico = new RespostaHistorico(resposta,pergunta.getTipoResposta());
			respostaHistorico.setPergunta(this);
			
			this.respostas.add(respostaHistorico);
			
		}
		return respostas;
	}
	

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_resposta")
	private TipoPerguntaList tipoResposta;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "perguntas", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Bloco> blocos = new ArrayList<>();
	
	//@JsonIgnore
	@LazyCollection(value = LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "pergunta", cascade = CascadeType.ALL,orphanRemoval = true)
	private List<CodigoLinhaHistorico> listaCodigosLinha = new ArrayList<>();
	
	
	//@JsonIgnore
	@LazyCollection(value = LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "pergunta", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RespostaHistorico> respostas = new ArrayList<>();



//	public PerguntaHistorico atualizaAtributos(@Valid PerguntaDTO perguntaDTO) {
//		
//		
//		if(perguntaDTO.getListaCodigosLinha().size()>0) {
//			this.listaCodigosLinha = adicionarCodigosLinha(perguntaDTO);
//			setListaCodigosLinha(listaCodigosLinha);
//		}
//		
//
//		this.descricao = perguntaDTO.getDescricao();
//		this.updatedAt = LocalDateTime.now();
//		
//		this.versao = perguntaDTO.getVersao();
//		this.ajuda = perguntaDTO.getAjuda();
//		this.observacao = perguntaDTO.getObservacao();
//		this.tempoAlerta = perguntaDTO.getTempoAlerta();
//		this.instrucaoIn = perguntaDTO.getInstrucaoIn();
//		this.tipoResposta = perguntaDTO.getTipoResposta();
//		this.respostas= adicionarRespostas(perguntaDTO);
//		
//		
//		return this;
//		
//		
//	}



	public void adicionarCodigosLinha(List<Integer> codigosLinha) {
		
		this.setListaCodigosLinha(new ArrayList<>());/// adicionar novos removendo todos os existentes no banco, caso queira incrementar remova esta linha
		for (Integer codigo : codigosLinha) {
			CodigoLinhaHistorico codigoLinha = new CodigoLinhaHistorico();
			codigoLinha.setCodigoLinha(codigo);
			codigoLinha.setPergunta(this);
			this.listaCodigosLinha.add(codigoLinha);
			
			
		}
		
		
		
		
	}



	public void adicionarRespostas(List<Resposta> respostas2) {
         this.setRespostas(new ArrayList<>());
		
		for (Resposta resposta : respostas2) {
			
			RespostaHistorico respostaHistorico = new RespostaHistorico(resposta,resposta.getPergunta().getTipoResposta());
			respostaHistorico.setPergunta(this);
			
			this.respostas.add(respostaHistorico);
			
		}
		
		
	}






	
	
	
	
	
	
	
	
	
	
	
	
}
