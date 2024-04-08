package com.cenop4011.padroniza.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cenop4011.padroniza.dtos.ComportamentoRespostaDTO;

import lombok.Data;

@Entity
@Table(name = "tb_tip_comportamento_resposta")
@Data
public class TipoComportamentoResposta {
	
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="codigo_tipo_comportamento")
		private Integer codigoTipoComportamento;
		@Column(name = "nome_tipo_comportamento")
		private String nomeTipoComportamento;
		
		
		
		
		public TipoComportamentoResposta() {
			// TODO Auto-generated constructor stub
		}
		
		
		public TipoComportamentoResposta(ComportamentoRespostaDTO comportamentoRespostaDTO) {
			this.codigoTipoComportamento=comportamentoRespostaDTO.getCodigoTipoComportamento();
			
		}
	

}
