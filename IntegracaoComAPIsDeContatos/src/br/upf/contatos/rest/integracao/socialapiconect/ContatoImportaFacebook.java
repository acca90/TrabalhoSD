/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.rest.integracao.socialapiconect;

import br.upf.contatos.rest.integracao.modelo.Contato;
import com.github.scribejava.apis.FacebookApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Token;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.model.Verifier;
import com.github.scribejava.core.oauth.OAuthService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jonas
 */
public class ContatoImportaFacebook {
    private static final String CONSUME_KEY = "1484578541847454";
    private static final String CONSUME_SECRET_KEY = "cf20a1c502cc338126a49c089b2075d3";
    private static final String PROTECTED_RESOURCE_URL = "https://graph.facebook.com/v2.5/me?fields=id,name,friends";//?fields=first_name,last_name,friendlists";//?fields=friends";//?fields=friends{address,birthday,about,age_range,education,email,favorite_athletes,favorite_teams,first_name,gender,hometown,id,inspirational_people,languages,last_name,link,locale,location,meeting_for,middle_name,name,name_format,political,quotes,relationship_status,religion,security_settings,significant_other,sports,third_party_id,timezone,updated_time,verified,video_upload_limits,viewer_can_send_gift,website,work,accounts}";
    private OAuthService service;
    private Token requestToken;
    private String authURL;
    private Verifier verifier;
    private Token accessToken;
    private OAuthRequest request;
    private Response response;
    private List<Contato> contatos = new ArrayList();
    
    public ContatoImportaFacebook(){
        service = null;
        requestToken = null;
        authURL = null;
        verifier = null;
        accessToken = null;
        request = null;
        response = null;
    }
    
    public void initialFacebookConnection(){
        service = new ServiceBuilder()
            .provider(FacebookApi.class)
            .apiKey(CONSUME_KEY)
            .apiSecret(CONSUME_SECRET_KEY)
            .callback("https://localhost/")
            .scope("email,user_friends")
            .build();
        requestToken = null;//service.getRequestToken();
        authURL = service.getAuthorizationUrl(requestToken);
    }
    
    public void secondaryFacebookConnection(String codeFromAccess){
        verifier = new Verifier(codeFromAccess);
        accessToken = service.getAccessToken(requestToken, verifier);
        request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL, service);
        service.signRequest(accessToken, request);
        response = (Response) request.send();
    }
    
    public void tratamentoFacebookResposta(){
        System.out.println(response.getBody());
    }

    public OAuthService getService() {
        return service;
    }

    public void setService(OAuthService service) {
        this.service = service;
    }

    public Token getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(Token requestToken) {
        this.requestToken = requestToken;
    }

    public String getAuthURL() {
        return authURL;
    }

    public void setAuthURL(String authURL) {
        this.authURL = authURL;
    }

    public Verifier getVerifier() {
        return verifier;
    }

    public void setVerifier(Verifier verifier) {
        this.verifier = verifier;
    }

    public Token getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(Token accessToken) {
        this.accessToken = accessToken;
    }

    public OAuthRequest getRequest() {
        return request;
    }

    public void setRequest(OAuthRequest request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }
}
