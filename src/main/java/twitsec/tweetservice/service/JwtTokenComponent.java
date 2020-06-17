package twitsec.tweetservice.service;

import com.auth0.jwt.JWT;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import twitsec.tweetservice.model.Role;

import javax.xml.bind.DatatypeConverter;

@Component
public class JwtTokenComponent {

    private static final String SECRET_KEY = "sdg415fd#$TR#TGWE%Ytfg$%TGwvgtyieu5uwe5nngytvigtyiue5nlsiuerwse5mbgyv,h.lrdeihyiubes5vgmi%YT$%YTVysg4d5f4g6d";

    public boolean validateJwt(final String jwt) {
        try{
            Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY)).parseClaimsJws(jwt.replace("Bearer", "").trim()).getBody();
            return true;
        }catch(Exception ex){
            return false;
        }
    }

    public int getProfileIdFromToken(final String token){
        return JWT.decode(token).getClaim("profileId").asInt();
    }

    public Role getRoleFromToken(final String token){
        return Role.valueOf(JWT.decode(token).getClaim("role").asString());
    }
}
