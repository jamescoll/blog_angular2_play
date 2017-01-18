package services;

import com.nimbusds.jwt.JWTClaimsSet;

public interface JsonWebTokenService {

    boolean verify(String token);
    JWTClaimsSet decode (String token) throws Exception;
    String sign(String userInfo) throws Exception;
    String getUser(String token) throws Exception;
}