package com.ehireback.gui.back;


import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.ehire.MainApp.MainApp;
import com.ehire.utils.StatForm;
import com.ehiregui.gui.LoginForm;

import com.ehiregui.gui.eventsPage;
import com.ehiregui.gui.archivePage;
import com.ehiregui.gui.participentsPage;
public class AccueilBack extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");
    Label label;
    private Resources res;

    public AccueilBack() {
        super("Admin", new BoxLayout(BoxLayout.Y_AXIS));
        addGUIs();
    }

    public AccueilBack(Resources res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void addGUIs() {
        ImageViewer userImage = new ImageViewer(theme.getImage("person.jpg").fill(200, 200));
        userImage.setUIID("candidatImage");

        label = new Label(MainApp.getSession().getEmail());
        label.setUIID("links");
        Button btnDeconnexion = new Button();
        btnDeconnexion.setUIID("buttonLogout");
        btnDeconnexion.setMaterialIcon(FontImage.MATERIAL_ARROW_FORWARD);
        btnDeconnexion.addActionListener(action -> {
            LoginForm.loginForm.showBack();
        });

        Container userContainer = new Container(new BorderLayout());
        userContainer.setUIID("userContainer");

        userContainer.add(BorderLayout.WEST, userImage);
        userContainer.add(BorderLayout.CENTER, label);
        userContainer.add(BorderLayout.EAST, btnDeconnexion);

        Container menuContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        menuContainer.addAll(
                userContainer,
                makeButton(
                        "    Users",
                        FontImage.MATERIAL_PERSON,
                        new com.ehire.gui.back.user.DisplayAll(this)
                ),
                makeButton(
                        "    jobs",
                        FontImage.MATERIAL_SUBSCRIPTIONS,
                         new com.ehire.gui.back.abonnement.DisplayAll(this)
                ),
                 makeButton(
                        "candidat",
                        FontImage.MATERIAL_SUBSCRIPTIONS,
                        new com.ehire.gui.back.offre.DisplayAll(this)
                )
                 
        
                  
        );

        this.add(menuContainer);
    }
 
    private Button makeButton(String nomBouton, char icon, Form localisation) {
        Button button = new Button(nomBouton);
        button.setUIID("buttonMenu");
        button.setMaterialIcon(icon);
        button.addActionListener(action -> {
            localisation.show();
        });
        return button;
    }
}
