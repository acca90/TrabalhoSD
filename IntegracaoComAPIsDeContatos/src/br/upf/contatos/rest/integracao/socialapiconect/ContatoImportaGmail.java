/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.rest.integracao.socialapiconect;

import br.upf.contatos.rest.integracao.modelo.Contato;
import com.github.scribejava.apis.GoogleApi20;
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
public class ContatoImportaGmail {
    private static final String CONSUME_KEY = "182769653233-0mhad1qdhrll1mgjbufdqkq9un8fr2np.apps.googleusercontent.com";
    private static final String CONSUME_SECRET_KEY = "PFs0I_OkmdUAlPHfiwjBQSbq";
    private static final String PROTECTED_RESOURCE_URL = "https://www.google.com/m8/feeds/contacts/default/full?alt=json";
    private OAuthService service;
    private Token requestToken;
    private String authURL;
    private Verifier verifier;
    private Token accessToken;
    private OAuthRequest request;
    private Response response;
    private List<Contato> contatos = new ArrayList();
    
    public ContatoImportaGmail(){
    }
    
    public void initialGmailConnection(){
        service = new ServiceBuilder()
            .provider(GoogleApi20.class)
            .apiKey(CONSUME_KEY)
            .apiSecret(CONSUME_SECRET_KEY)
            .callback("https://localhost")
            .scope("https://www.google.com/m8/feeds")
            .build();
        requestToken = null;//service.getRequestToken();
        authURL = service.getAuthorizationUrl(requestToken);
    }
    
    public void secondaryGmailConnection(String codeFromAccess){
        verifier = new Verifier(codeFromAccess);
        accessToken = service.getAccessToken(requestToken, verifier);
        request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL, service);
        service.signRequest(accessToken, request);
        response = (Response) request.send();
    }
    
    public void tratamentoGmailResposta(){
        String nome = "";
        String email = "";
        String emailAlter = "";
        JSONObject jso = new JSONObject(response.getBody());
        JSONArray jso2 = jso.getJSONObject("feed").getJSONArray("entry");
        Iterator jso3 = jso2.iterator();
        while (jso3.hasNext()) {
            JSONObject jso4 = (JSONObject) jso3.next();
            System.out.println(jso4.toString());
            JSONObject jso5 = jso4.getJSONObject("title");
            JSONArray jso6 = jso4.getJSONArray("gd$email");
            nome = jso5.getString("$t");
            try {
                email = jso6.getJSONObject(0).getString("address");
            } catch (JSONException jSONException) {
            }
            try {
                emailAlter = jso6.getJSONObject(1).getString("address");
            } catch (JSONException jSONException) {
            }
            contatos.add(new Contato(0, nome, email, "", "", 0, "", "", emailAlter));
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
