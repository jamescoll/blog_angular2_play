package services;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import play.Configuration;
import play.Logger;
import play.inject.ApplicationLifecycle;
import play.mvc.Http;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

@Singleton
public class JsonWebTokenServiceImpl implements JsonWebTokenService {

    final private String tokenPrefix = "Bearer ";
    ApplicationLifecycle applicationLifecycle;
    private String issuer;
    private String sharedSecret;
    private Integer expiryTime;
    private String audience;
    private JWSHeader algorithm;
    private MACSigner signer;
    private MACVerifier verifier;


    @Inject
    public JsonWebTokenServiceImpl(ApplicationLifecycle applicationLifecycle, Configuration configuration) {


        this.applicationLifecycle = applicationLifecycle;

        issuer = configuration.getString("jwt.issuer");
        sharedSecret = configuration.getString("jwt.sharedSecret");
        expiryTime = configuration.getInt("jwt.expiryInSecs");
        audience = configuration.getString("jwt.audience");

        algorithm = new JWSHeader(JWSAlgorithm.HS256);
        signer = new MACSigner(sharedSecret);
        verifier = new MACVerifier(sharedSecret);


        applicationLifecycle.addStopHook(() -> {

            Logger.info("Closing down JWT implementation ");
            algorithm = null;
            signer = null;
            verifier = null;
            return CompletableFuture.completedFuture(null);
        });
    }

    @Override
    public boolean verify(String token) {
        try {
            final JWTClaimsSet payload = decode(token);

            //check expiration date
            if (!new Date().before(payload.getExpirationTime())) {
                Logger.error("Token expired: " + payload.getExpirationTime());
                return false;
            }

            if (!payload.getIssuer().equals(issuer)) {
                Logger.error("Issuer mismatch: " + payload.getIssuer());
                return false;
            }

            if (payload.getAudience() != null && payload.getAudience().size() > 0) {
                if (!payload.getAudience().get(0).equals(audience)) {
                    Logger.error("Audience mismatch: " + payload.getAudience().get(0));
                    return false;
                }
            } else {
                Logger.error("Audience is required");
                return false;
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public JWTClaimsSet decode(String token) throws Exception {
        Logger.debug("Verifying: " + token.substring(tokenPrefix.length()));
        SignedJWT signed = SignedJWT.parse(token.substring(tokenPrefix.length()));

        if (!signed.verify(verifier)) {
            throw new IllegalArgumentException("Json web token cannot be verified!");
        }

        return (JWTClaimsSet) signed.getJWTClaimsSet();
    }

    @Override
    public String sign(String userInfo) throws Exception {

        final JWTClaimsSet claimsSet = new JWTClaimsSet();
        claimsSet.setSubject(userInfo);
        claimsSet.setIssueTime(new Date());
        claimsSet.setIssuer(issuer);
        claimsSet.setAudience(audience);
        claimsSet.setExpirationTime(new Date(claimsSet.getIssueTime().getTime() + (expiryTime * 1000)));

        SignedJWT signedJWT = new SignedJWT(algorithm, claimsSet);
        signedJWT.sign(signer);

        return signedJWT.serialize();
    }

    public String getUser(String token)  {

        SignedJWT signed = null;
        String userName;

        try {
            signed = SignedJWT.parse(token.substring(tokenPrefix.length()));
            return signed.getJWTClaimsSet().getSubject();
        } catch (ParseException e) {
            Logger.warn("Problem parsing string token");
        }

        return "";

    }

    //todo ensure this is functional
    public String getTokenFromContext(Http.Context context) {

        String[] tokenFromContext = context.request().headers().get("Authorization");

        return tokenFromContext[0];
    }

}
