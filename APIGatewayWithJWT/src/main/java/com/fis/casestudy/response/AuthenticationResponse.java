package com.fis.casestudy.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
	
	private static final long serialVersionUID = -8091879091924046844L;
	private String jwttoken;

}
