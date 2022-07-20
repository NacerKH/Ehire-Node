package com.ehire.MainApp;

import com.codename1.io.Log;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.ehire.entities.User;
import com.ehiregui.gui.LoginForm;

import static com.codename1.ui.CN.*;

 
public class MainApp {

    private Form current;
    private static User session;

    public static User getSession() {
        return session;
    }

    public static void setSession(User session) {
        MainApp.session = session;
    }

    public void init(Object context) {
        // use two network threads instead of one
        updateNetworkThreadCount(10);

        Resources theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature
        Log.bindCrashProtection(true);

        addNetworkErrorListener(err -> {
            err.consume();
            if (err.getError() != null) {
                Log.e(err.getError());
            }
            Log.sendLogAsync();
            //Dialog.show("Connection Error", "There was a networking error in the connection to " + err.getConnectionRequest().getUrl(), "OK", null);
        });
    }

    public void start() {
        if (current != null) {
            current.show();
            return;
        }
       
         //new MapForm();
        new LoginForm().show();
    
    


    }

    public void stop() {
        current = getCurrentForm();
        if (current instanceof Dialog) {
            ((Dialog) current).dispose();
            current = getCurrentForm();
        }
    }

    public void destroy() {
    }

}