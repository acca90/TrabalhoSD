/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.rest.integracao.socialapiconect;

import br.upf.contatos.rest.integracao.modelo.Contato;
import com.github.scribejava.apis.LiveApi;
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
public class ContatoImportaLive {
    private static final String CONSUME_KEY = "000000004416CC6D";
    private static final String CONSUME_SECRET_KEY = "g5IaVdW5DKiDRIUqIlQNI7FYl2-0Goqo";
    private static final String PROTECTED_RESOURCE_URL = "https://outlook.office.com/api/v1.0/me/contacts?$select=EmailAddresses,GivenName,Surname";
    private OAuthService service;
    private Token requestToken;
    private String authURL;
    private Verifier verifier;
    private Token accessToken;
    private OAuthRequest request;
    private Response response;
    private List<Contato> contatos = new ArrayList();
    
    public ContatoImportaLive(){
        service = null;
        requestToken = null;
        authURL = null;
        verifier = null;
        accessToken = null;
        request = null;
        response = null;
    }
    
    public void initialLiveConnection(){
        service = new ServiceBuilder()
            .provider(LiveApi.class)
            .apiKey(CONSUME_KEY)
            .apiSecret(CONSUME_SECRET_KEY)
            .callback("https://www.localhost.com")
            .scope("http://outlook.office.com/Contacts.Read")
            .build();
        requestToken = null;//service.getRequestToken();
        authURL = service.getAuthorizationUrl(requestToken);
    }
    
    public void secondaryLiveConnection(String codeFromAccess){
        verifier = new Verifier(codeFromAccess);
        accessToken = service.getAccessToken(requestToken, verifier);
        request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL, service);
        service.signRequest(accessToken, request);
        response = (Response) request.send();
    }
    
    public void tratamentoLiveResposta(){
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
