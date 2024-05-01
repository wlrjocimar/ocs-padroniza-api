package com.cenop4011.padroniza.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import com.cenop4011.padroniza.dtos.InstrucaoNormativaDTO;
import com.cenop4011.padroniza.dtos.LinkDTO;
import com.cenop4011.padroniza.dtos.PerguntaDTO;
import com.cenop4011.padroniza.dtos.PerguntaInputDTO;
import com.cenop4011.padroniza.dtos.RespostaDTO;
import com.cenop4011.padroniza.dtos.TagDTO;
import com.cenop4011.padroniza.enuns.TipoPerguntaList;
import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Data;

@Entity
@Table(name = "tb_pergunta")
@Data
public class Pergunta implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="descricao",columnDefinition = "LONGTEXT")
	private String descricao;
	
	@Column(name="descricao_sem_formato",columnDefinition = "LONGTEXT")
	private String descricaoSemFormato;
	
	@Column(name = "created_at")	
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
	@Column(name="versao")
	private Integer versao=1;
	@Column(name = "ajuda", columnDefinition = "LONGTEXT")
	private String ajuda;
	@Column(name = "observacao", columnDefinition = "LONGTEXT")
	private String observacao;
	@Column(name="tempo_alerta")
	private Integer tempoAlerta=0;
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
	@Column(name = "visible")
	private Boolean visible = true;
	@Column(name = "obs_operador", columnDefinition = "LONGTEXT")
	private String observacaoOperador;
	
	
	
	@LazyCollection(value = LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "pergunta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PosicaoPergunta> posicaoPerguntas = new ArrayList<>();
	
	@LazyCollection(value = LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "pergunta", cascade = CascadeType.ALL)
    private List<InstrucaoNormativa> instrucoesNormativas = new ArrayList<>();
	
	@LazyCollection(value = LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "pergunta", cascade = CascadeType.ALL)
	private List<Link> links = new ArrayList<>();
	
	
	
	
	
	@LazyCollection(value = LazyCollectionOption.FALSE)
	@ManyToMany
    @JoinTable(
        name = "tb_pergunta_tag",
        joinColumns = @JoinColumn(name = "pergunta_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
	 private List<Tag> tags = new ArrayList<>();
	
	
	@JsonIgnore
	@LazyCollection(value = LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "pergunta", cascade = CascadeType.ALL)
    private List<PerguntaHistorico> historicos;
	
	
	
	
	
	public Pergunta() {
		super();
	}
	
	
	
	public Pergunta(PerguntaDTO perguntaDTO) {
		super();
	
		this.descricao = perguntaDTO.getDescricao();
		this.descricaoSemFormato= perguntaDTO.getDescricaoSemFormato();
		this.createdAt = LocalDateTime.now();
		this.ajuda = perguntaDTO.getAjuda();
		this.matriculaFunci = perguntaDTO.getMatriculaFunci();
		this.observacao = perguntaDTO.getObservacao();
		this.tempoAlerta = perguntaDTO.getTempoAlerta();
		this.tipoResposta = perguntaDTO.getTipoResposta();
		this.link=perguntaDTO.getLink();
		this.listaCodigosLinha = adicionarCodigosLinha(perguntaDTO);
		this.respostas= adicionarRespostas(perguntaDTO);
		this.instrucoesNormativas=adicionarInstrucoes(perguntaDTO);
		this.links=adicionarLinks(perguntaDTO);
		this.tags=adicionarTags(perguntaDTO);
		this.automatizavel = perguntaDTO.getAutomatizavel();
		this.setTags(tags);
		this.visible=true;
		
		
	}

	



	private List<InstrucaoNormativa> adicionarInstrucoes(PerguntaDTO perguntaDTO) {
      this.setInstrucoesNormativas(new ArrayList<>());
		
		for (InstrucaoNormativaDTO instrucaoNormativaDTO : perguntaDTO.getInstrucoesNormativas()) {
			
			
			InstrucaoNormativa instrucaoNormativa = new InstrucaoNormativa(instrucaoNormativaDTO);
			instrucaoNormativa.setPergunta(this);
			
			this.instrucoesNormativas.add(instrucaoNormativa);
			
		}
		return instrucoesNormativas;
	}
	
	
	private List<Tag> adicionarTags(PerguntaDTO perguntaDTO) {
	      this.setTags(new ArrayList<>());
			
			for (TagDTO tagDTO : perguntaDTO.getTags()) {
				
				
				Tag tag = new Tag(tagDTO);
				
				
				
				
				this.tags.add(tag);
				
			}
			
			return tags;
		}
//		
	
	
	private List<Link> adicionarLinks(PerguntaDTO perguntaDTO) {
	      this.setLinks(new ArrayList<>());
			
			for (LinkDTO linkDTO : perguntaDTO.getLinks()) {
				
				
				Link link = new Link(linkDTO);
				link.setPergunta(this);
				
				this.links.add(link);
				
			}
			return links;
		}

	public void adicionarInstrucoesNormativas(List<InstrucaoNormativa> instrucoesNormativas) {
		this.setInstrucoesNormativas(new ArrayList<>());
		for (InstrucaoNormativa in : instrucoesNormativas) {
			InstrucaoNormativaDTO instrucaoNormativaDTO = new InstrucaoNormativaDTO();
			instrucaoNormativaDTO.setItem(in.getItem());
			instrucaoNormativaDTO.setNumeroIn(in.getNumeroIn());
			instrucaoNormativaDTO.setSubItem(in.getSubItem());
			instrucaoNormativaDTO.setAjuda(in.getAjuda());
			InstrucaoNormativa instrucaoNormativa = new InstrucaoNormativa(instrucaoNormativaDTO);
			instrucaoNormativa.setPergunta(this);
			this.instrucoesNormativas.add(instrucaoNormativa);
		}
	}

	private List<CodigoLinha> adicionarCodigosLinha(PerguntaDTO perguntaDTO) {
		
		this.setListaCodigosLinha(new ArrayList<>());
		for (Integer  cod : perguntaDTO.getListaCodigosLinha()) {
			CodigoLinha codigoLinha = new CodigoLinha();
			codigoLinha.setCodigoLinha(cod);
			codigoLinha.setPergunta(this);
			
			this.listaCodigosLinha.add(codigoLinha);
			
		}
		
		
		return this.listaCodigosLinha;
	}
	
	
	private List<Resposta> adicionarRespostas(PerguntaDTO perguntaDTO) {
		this.setRespostas(new ArrayList<>());
		
		for (RespostaDTO respostaDTO : perguntaDTO.getRespostas()) {
			
			Resposta resposta = new Resposta(respostaDTO,this.tipoResposta);
			resposta.setPergunta(this);
			
			this.respostas.add(resposta);
			
		}
		return respostas;
	}
	

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_resposta")
	private TipoPerguntaList tipoResposta;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "perguntas", cascade = CascadeType.ALL)
    private List<Bloco> blocos = new ArrayList<>();
	
	//@JsonIgnore
	@LazyCollection(value = LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "pergunta", cascade = CascadeType.ALL,orphanRemoval = true)
	private List<CodigoLinha> listaCodigosLinha = new ArrayList<>();
	
	
	//@JsonIgnore
	@LazyCollection(value = LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "pergunta", cascade = CascadeType.ALL)
	private List<Resposta> respostas = new ArrayList<>();



	public Pergunta atualizaAtributos(@Valid PerguntaDTO perguntaDTO) {
		
		
//		if(perguntaDTO.getListaCodigosLinha().size()>0) {
//			this.listaCodigosLinha = adicionarCodigosLinha(perguntaDTO);
//			setListaCodigosLinha(listaCodigosLinha);
//		}
		

		this.descricao = perguntaDTO.getDescricao();
		this.updatedAt = LocalDateTime.now();
		
		this.versao = perguntaDTO.getVersao();
		this.ajuda = perguntaDTO.getAjuda();
		this.observacao = perguntaDTO.getObservacao();
		this.tempoAlerta = perguntaDTO.getTempoAlerta();
	    this.tipoResposta = perguntaDTO.getTipoResposta();
		this.respostas= adicionarRespostas(perguntaDTO);
		
		
		return this;
		
		
	}



	public void adicionarCodigosLinha(List<Integer> codigosLinha) {
		
		this.setListaCodigosLinha(new ArrayList<>());/// adicionar novos removendo todos os existentes no banco, caso queira incrementar remova esta linha
		for (Integer codigo : codigosLinha) {
			CodigoLinha codigoLinha = new CodigoLinha();
			codigoLinha.setCodigoLinha(codigo);
			codigoLinha.setPergunta(this);
			this.listaCodigosLinha.add(codigoLinha);
			
			
		}
		
		
		
		
	}



	public void adicionarRespostas(List<RespostaDTO> respostas2) {
         this.setRespostas(new ArrayList<>());
		
		for (RespostaDTO respostaDTO : respostas2) {
			
			Resposta resposta = new Resposta(respostaDTO,this.getTipoResposta());
			resposta.setPergunta(this);
			
			this.respostas.add(resposta);
			
		}
	}






	
	
	
	
	
	
	
	
	
	
	

}
