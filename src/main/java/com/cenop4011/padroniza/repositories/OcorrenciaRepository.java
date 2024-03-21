package com.cenop4011.padroniza.repositories;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cenop4011.padroniza.dtos.DiligenciaDTO;
import com.cenop4011.padroniza.dtos.OcorrenciaDTO;
import com.cenop4011.padroniza.models.Diligencia;

@Repository
public class OcorrenciaRepository {
	
	
	
	
	private final JdbcTemplate usoDadosJdbcTemplate;
   

    @Autowired
    public OcorrenciaRepository(@Qualifier("usoDadosJdbcTemplate") JdbcTemplate usoDadosJdbcTemplate
    		
    		) {
        
        this.usoDadosJdbcTemplate = usoDadosJdbcTemplate;
        
    }


    
    public List<OcorrenciaDTO> buscarDados() {
    	
        
    	
        String  sql = "SELECT tb_agrup.CD_TIP_ATVD, tb_agrup.CD_AGRUP_OCR, tb_agrup.NM_AGRUP_OCR, tb_detalhe.CD_DETALHE_OCR, tb_detalhe.NM_DETALHE_OCR FROM uso_dados.TB_TIP_AGRUP_OCR tb_agrup LEFT JOIN uso_dados.TB_DETALHE_OCR tb_detalhe ON tb_agrup.CD_AGRUP_OCR=tb_detalhe.CD_AGRUP_OCR WHERE tb_agrup.CD_TIP_ATVD IN (100) AND tb_agrup.ATIVO='S' AND tb_detalhe.ATIVO='S';";

         return usoDadosJdbcTemplate.query(sql, (rs, rowNum) -> {
        	 OcorrenciaDTO dto = new OcorrenciaDTO();
        	 dto.setCodigoTipoAtividade(rs.getInt("CD_TIP_ATVD"));
        	 dto.setCodigoAgrupadorOcorrencia(rs.getInt("CD_AGRUP_OCR"));
        	 dto.setNomeAgrupadorOcorrencia(rs.getString("NM_AGRUP_OCR"));
        	 dto.setCodigoDetalheOcorrencia(rs.getInt("CD_DETALHE_OCR"));
        	 dto.setNomeDetalheOcorrencia(rs.getString("NM_DETALHE_OCR"));
        	 
         	
         	
         	
         	return dto;
           
            
         });
        
     }
    
    
    public Diligencia buscarComplementoDiligenciaNumeroDetalheOcorrencia(Diligencia diligencia)  {
        String sql = "SELECT NM_DETALHE_OCR from  uso_dados.TB_DETALHE_OCR  WHERE CD_DETALHE_OCR=" + diligencia.getCodigoDetalheOcorrencia();

        return usoDadosJdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
        	
        	diligencia.setNomeDetalheOcorrencia(rs.getString("NM_DETALHE_OCR"));
           
            return diligencia;
        });
    }



	public String buscarComplementoDiligenciaNumeroDetalheOcorrencia(
			@NotNull(message = "Informar codigodetalheOcorrencia") Integer codigoDetalheOcorrencia) {
		String sql = "SELECT NM_DETALHE_OCR from  uso_dados.TB_DETALHE_OCR  WHERE CD_DETALHE_OCR=" + codigoDetalheOcorrencia;
		
		
		try {
			 return usoDadosJdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
		        	
			        
		           
		            return rs.getString("NM_DETALHE_OCR");
		        });
		} catch (Exception e) {
			System.out.println("Erro ao buscar nome ocorrencia para codigo "+ codigoDetalheOcorrencia + "  "+ e.toString());
			return null;
		}

       
	}



	

	public Optional<OcorrenciaDTO> buscarAgrupador(Integer codigoDetalheOcorrencia) {
	    String sql = "SELECT tb_agrup.CD_TIP_ATVD, tb_agrup.CD_AGRUP_OCR, tb_agrup.NM_AGRUP_OCR, tb_detalhe.CD_DETALHE_OCR, tb_detalhe.NM_DETALHE_OCR FROM uso_dados.TB_TIP_AGRUP_OCR tb_agrup LEFT JOIN uso_dados.TB_DETALHE_OCR tb_detalhe ON tb_agrup.CD_AGRUP_OCR=tb_detalhe.CD_AGRUP_OCR WHERE tb_agrup.CD_TIP_ATVD IN (100) AND tb_agrup.ATIVO='S' AND tb_detalhe.ATIVO='S' and CD_DETALHE_OCR =" + codigoDetalheOcorrencia + ";";

	    try {
	        OcorrenciaDTO dto = usoDadosJdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
	            OcorrenciaDTO ocorrenciaDTO = new OcorrenciaDTO();
	            ocorrenciaDTO.setCodigoTipoAtividade(rs.getInt("CD_TIP_ATVD"));
	            ocorrenciaDTO.setCodigoAgrupadorOcorrencia(rs.getInt("CD_AGRUP_OCR"));
	            ocorrenciaDTO.setNomeAgrupadorOcorrencia(rs.getString("NM_AGRUP_OCR"));
	            ocorrenciaDTO.setCodigoDetalheOcorrencia(rs.getInt("CD_DETALHE_OCR"));
	            ocorrenciaDTO.setNomeDetalheOcorrencia(rs.getString("NM_DETALHE_OCR"));
	            return ocorrenciaDTO;
	        });
	        return Optional.ofNullable(dto);
	    } catch (EmptyResultDataAccessException e) {
	        return Optional.empty();
	    }
	}

	
	

}
