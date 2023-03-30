package com.cenop4011.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {
	
	
			
		private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
		
		@Value("${jwt.secret}")
		private String secret;
		@Value("${jwt.expiration}")
		private int expitation;
		
		public String generateToken(Authentication authentication) {
			
			
			UserDetails mainUser = (UserDetails) authentication.getPrincipal();
			
			return Jwts.builder().setSubject(mainUser.getUsername())
					.setIssuedAt(new Date())
					.setExpiration(new Date(new Date().getTime() + expitation * 1000))
					.signWith(SignatureAlgorithm.HS512, secret)
					.compact();
			
			
		}
	
	
	
		public String getUserNameFromToken(String token) {
			
			return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
			
		}
		
		public boolean validaToken(String token)  {
			
			try {
				
				Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
				
				return true;
				
			}catch (MalformedJwtException e){
	            logger.error("token mal formado");
	        }catch (UnsupportedJwtException e){
	            logger.error("token nao suportado");
	        }catch (ExpiredJwtException e){
	            logger.error("token expirado");
	        }catch (IllegalArgumentException e){
	            logger.error("token vazio");
	        }catch (SignatureException e){
	            logger.error("falha na assinatura do token");
	        }
	        return false;
			
		}

}
