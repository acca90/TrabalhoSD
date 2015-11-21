/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.swing.JDesktopPane;

/**
 *
 * @author joaov
 */
public class controleTelas {
    
    private static JDesktopPane jdesk;

    /**
     * @return the jdesk
     */
    public static JDesktopPane getJdesk() {
        return jdesk;
    }

    /**
     * @param aJdesk the jdesk to set
     */
    public static void setJdesk(JDesktopPane aJdesk) {
        jdesk = aJdesk;
    }
    
    
    
}
