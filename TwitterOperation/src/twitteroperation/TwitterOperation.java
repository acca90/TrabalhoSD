/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitteroperation;

import java.util.List;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author dell
 */
public class TwitterOperation {

    /**
     * @param args the command line arguments
     */
    public static void twitter(String[] args) throws TwitterException {
        
        
          ConfigurationBuilder cb = new ConfigurationBuilder();
          
           cb.setDebugEnabled(true)
                .setOAuthConsumerKey("zQ9a9Peguity7uhQL1NEsQoOc")
                .setOAuthConsumerSecret("XCHvfk7cXJqzs936oE9P8n9phAYdS6LeKrxtTeup8E4dbHue1m")
                .setOAuthAccessToken("66044069-IVRp6vydYkBSdjo2zs78MmkmuFSC0bqdw0a2U6CYS")
                .setOAuthAccessTokenSecret("uboCYpkXzC19rYwzor722SVd7yj2jVlOc6ajVSikOrMoL");
           
     TwitterFactory tf=new TwitterFactory(cb.build());  
     
     twitter4j.Twitter tw=tf.getInstance();
     
     
     // Post on twitter time line .
     
    Status stat= tw.updateStatus("Welcome to twitter world @@@@@ ");
    
      System.out.println("Twitter updated");
        
        
        //Reading Twitter Time Line 
        
     // List<Status> statuses=  tw.getHomeTimeline();
      
        // for (Status status1 : statuses) {
          //  System.out.println(status1.getUser().getName() + ":" + status1.getText());

      //  }
        
        
        
        
           
           
           

        
        
    }
}
