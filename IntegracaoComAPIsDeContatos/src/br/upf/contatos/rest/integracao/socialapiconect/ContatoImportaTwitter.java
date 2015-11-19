/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.rest.integracao.socialapiconect;

import br.upf.contatos.rest.integracao.modelo.Contato;
import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Token;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.model.Verifier;
import com.github.scribejava.core.oauth.OAuthService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author jonas
 */
public class ContatoImportaTwitter {
    private final static String CONSUME_KEY = "jbZEI3HR9ojVE6WGu622TG0rK";
    private final static String CONSUME_SECRET_KEY = "Go1WMV3RUiIINRcSopFguVVvz46WGvd8LeW5E1RyE6jumanz2D";
    private final static String PROTECTED_RESOURCE_URL = "https://api.twitter.com/1.1/friends/list.json";
    private OAuthService service;
    private Token requestToken;
    private String authURL;
    private Verifier verifier;
    private Token accessToken;
    private OAuthRequest request;
    private Response response;
    private List<Contato> contatos = new ArrayList();
    
    public ContatoImportaTwitter(){
        requestToken = null;
        authURL = null;
        accessToken = null;
    }

    public void initialTwitterConnection(){
        service = new ServiceBuilder()
            .provider(TwitterApi.class)
            .apiKey(CONSUME_KEY)
            .apiSecret(CONSUME_SECRET_KEY)
            .build();
        requestToken = service.getRequestToken();
        authURL = service.getAuthorizationUrl(requestToken);
    }
    
    public void secondaryTwitterConnection(String codeFromAccess){
        verifier = new Verifier(codeFromAccess);
        accessToken = service.getAccessToken(requestToken, verifier);
        request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL, service);
        service.signRequest(accessToken, request);
        response = (Response) request.send();
    }
    
    public void tratamentoTwitterResposta(){
        String nome;
        JSONObject jso = new JSONObject(response.getBody());
        JSONArray jso2 = jso.getJSONArray("users");
        Iterator jso3 = jso2.iterator();
        while (jso3.hasNext()) {
            JSONObject jso4 = (JSONObject) jso3.next();
            nome = "";
            try {
                nome = jso4.getString("name");
            } catch (JSONException jSONException) {
            }
            contatos.add(new Contato(0, nome, "", "", "", 0, "", "", ""));
        }
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
