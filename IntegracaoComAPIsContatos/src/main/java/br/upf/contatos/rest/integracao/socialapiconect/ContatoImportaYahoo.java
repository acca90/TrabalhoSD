/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.rest.integracao.socialapiconect;

import br.upf.contatos.dal.model.Contato;
import com.github.scribejava.apis.YahooApi;
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
import org.json.JSONObject;

/**
 *
 * @author jonas
 */
public class ContatoImportaYahoo {
    private static final String CONSUME_KEY = "dj0yJmk9WVNTTjRVSVJNbDJDJmQ9WVdrOU1GaEVaakUxTmpJbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmeD05Yg--";
    private static final String CONSUME_SECRET_KEY = "0a5f07023716b47f17fe0f632dfc4e17394d301b";
    private static final String PROTECTED_RESOURCE_URL = "https://social.yahooapis.com/v1/user/me/contacts?view=tinyusercard&count=max&format=json";
    private OAuthService service;
    private Token requestToken;
    private String authURL;
    private Verifier verifier;
    private Token accessToken;
    private OAuthRequest request;
    private Response response;
    private List<Contato> contatos = new ArrayList();
    
    public ContatoImportaYahoo(){
        service = null;
        requestToken = null;
        authURL = null;
        verifier = null;
        accessToken = null;
        request = null;
        response = null;
    }
    
    public void initialYahooConnection(){
        service = new ServiceBuilder()
            .provider(YahooApi.class)
            .apiKey(CONSUME_KEY)
            .apiSecret(CONSUME_SECRET_KEY)
            .build();
        requestToken = service.getRequestToken();
        authURL = service.getAuthorizationUrl(requestToken);
    }
    
    public void secondaryYahooConnection(String codeFromAccess){
        verifier = new Verifier(codeFromAccess);
        accessToken = service.getAccessToken(requestToken, verifier);
        request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL, service);
        service.signRequest(accessToken, request);
        response = (Response) request.send();
    }
    
    public void tratamentoYahooResposta(){
        String nome;
        String email;
        JSONObject jso = new JSONObject(response.getBody());
        JSONArray jso2 = jso.getJSONObject("contacts").getJSONArray("contact");
        Iterator jso3 = jso2.iterator();
        while (jso3.hasNext()) {
            JSONObject jso4 = (JSONObject) jso3.next();
            JSONArray jso5 = jso4.getJSONArray("fields");
            Iterator jso6 = jso5.iterator();
            nome = "";
            email = "";
            while (jso6.hasNext()) {
                JSONObject jso7 = (JSONObject) jso6.next();
                if (jso7.getString("type").equals("name")) {
                    JSONObject jso8 = jso7.getJSONObject("value");
                    nome = (jso8.getString("givenName") + " "
                            + jso8.getString("middleName") + " "
                            + jso8.getString("familyName")).replace("  ", " ");
                }
                if (jso7.getString("type").equals("email")) {
                    email = jso7.getString("value");
                }
            }
            Contato contato = new Contato();
            contato.setCep(-1);
            contato.setCidade("");
            contato.setComplemento("");
            contato.setEmail(email);
            contato.setEmailAlternativo("");
            contato.setEndereco("");
            contato.setEstado("");
            contato.setId(-1);
            contato.setNome(nome);
            contatos.add(contato);
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
