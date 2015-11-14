/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitteroperation;

import java.util.List;
import java.util.Scanner;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Twitter {

    public static void main(String[] args) throws TwitterException {

          ConfigurationBuilder cb = new ConfigurationBuilder();
          
           cb.setDebugEnabled(true)
                .setOAuthConsumerKey("Tzi51y4WZtyjWyRnjl8kCB6B2")
                .setOAuthConsumerSecret("fpOK1IQxDtFPkbBUnYsQ8fowE3tSkbZmKR6SfJ9grPhkmHcKTH")
                .setOAuthAccessToken("4229537124-XFDAf1RDS5Skm0ovVrTQ0HsefDxBECqWpuNMCou")
                .setOAuthAccessTokenSecret("SmiJP4ov6VJ4owOixjduE2QIWAlaVz0hR5I3p5egvAuCm");
           
     TwitterFactory tf=new TwitterFactory(cb.build());  
     
     twitter4j.Twitter tw=tf.getInstance();
     
     
     // Post on twitter time line .
     String nome;
     Scanner sler = new Scanner (System.in); 
     System.out.println("digite"); 
     nome = sler.next();
     Status stat= tw.updateStatus(nome);
    
      System.out.println("Twitter updated");
        
        
        //Reading Twitter Time Line 
        
     // List<Status> statuses=  tw.getHomeTimeline();
      
        // for (Status status1 : statuses) {
          //  System.out.println(status1.getUser().getName() + ":" + status1.getText());

      //  }

    }
}
