package com.cenop4011.padroniza.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.cenop4011.padroniza.dtos.ComportamentoRespostaDTO;

import lombok.Data;

@Entity
@Table(name="tb_valor_comportamento")
@Data
public class ValorComportamentoResposta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "diligencia_id", referencedColumnName = "id")
    private Diligencia diligencia;
	
//	
//	@JsonI
//	@ManyToOne
//    @JoinColumn(name = "tipo_comportamento_resposta_id", referencedColumnName = "codigo_tipo_comportamento")
//    private TipoComportamentoResposta tipoComportamentoResposta;
//	
	
	
	
	
	
	public ValorComportamentoResposta(ComportamentoRespostaDTO comportamentoRespostaDTO) {
		this.diligencia = new Diligencia(comportamentoRespostaDTO.getCodigoValorComportamento()); // aqui é o codigo que é omesmo id da dligencia
		
	}






	public ValorComportamentoResposta() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	

}
