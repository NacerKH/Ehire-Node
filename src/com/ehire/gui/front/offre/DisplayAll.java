package com.ehire.gui.front.offre;

import com.codename1.components.ImageViewer;
import com.codename1.components.ShareButton;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.ehire.entities.candidat;

import com.ehireFront.gui.front.AccueilFront;
import com.ehire.gui.front.abonnement.Manage;
import com.ehire.services.candidatService;

import com.ehire.utils.Statics;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class DisplayAll extends Form {

    public static candidat currentOffre = null;
    Resources theme = UIManager.initFirstTheme("/theme");
    TextField searchTF;
    ArrayList<Component> componentModels;
    Label titreLabel, descriptionLabel, prixLabel, imageLabel;
    ImageViewer imageIV;
    Button subscribeBtn;
    Container btnsContainer;
    public DisplayAll() {
        super("candidat", new BoxLayout(BoxLayout.Y_AXIS));

        addGUIs();

        super.getToolbar().hideToolbar();
    }

    private void addGUIs() {
        ArrayList<candidat> listOffres = candidatService.getInstance().getAll();

        componentModels = new ArrayList<>();

        searchTF = new TextField("", "Chercher une candidat");
        searchTF.addDataChangedListener((d, t) -> {
            int size = componentModels.size();
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    this.removeComponent(componentModels.get(i));
                    System.out.println(i);
                }
            }
            componentModels = new ArrayList<>();
            for (int i = 0; i < listOffres.size(); i++) {
                if (listOffres.get(i).getFullName().startsWith(searchTF.getText())) {
                
                    Component model = makeOffreModel(listOffres.get(i));
                    this.add(model);
                    componentModels.add(model);
                }
            }
            this.revalidate();
        });
        this.add(searchTF);

        if (listOffres.size() > 0) {
            for (int i = 0; i < listOffres.size(); i++) {
                Component model = makeOffreModel(listOffres.get(i));
                componentModels.add(model);
                this.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    private Component makeOffreModel(candidat candidat) {
        Container offreModel = makeModelWithoutButtons(candidat);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");

        subscribeBtn = new Button("S'abonner");
        subscribeBtn.setUIID("buttonMain");
        subscribeBtn.addActionListener(action -> {
            currentOffre = candidat;
            new Manage(AccueilFront.accueilFrontForm).show();
        });

        Button btnAfficherScreenshot = new Button("Partager");
        btnAfficherScreenshot.addActionListener(listener -> share(candidat));

        btnsContainer.add(BorderLayout.WEST, subscribeBtn);
        btnsContainer.add(BorderLayout.EAST, btnAfficherScreenshot);

        offreModel.add(btnsContainer);
        return offreModel;
    }

    private Container makeModelWithoutButtons(candidat candidat) {
        Container offreModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        offreModel.setUIID("containerRounded");

        titreLabel = new Label("nom : " +candidat.getFullName());
        titreLabel.setUIID("labelCenter");

        prixLabel = new Label("email : " + candidat.getEmail());
        prixLabel.setUIID("labelDefault");

        descriptionLabel = new Label("phone : " + candidat.getPhoneNumber());
        descriptionLabel.setUIID("labelDefault");

        imageLabel = new Label("cv : " + candidat.getCv_url());
        imageLabel.setUIID("labelDefault");

       

        offreModel.addAll(
                titreLabel, prixLabel, descriptionLabel, imageLabel
                
        );

        return offreModel;
    }

    private void share(candidat candidat) {
        Form form = new Form();
        form.add(new Label("candidat " + candidat.getFullName()));
        form.add(makeModelWithoutButtons(candidat));
        String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
        Image screenshot = Image.createImage(
                Display.getInstance().getDisplayWidth(),
                Display.getInstance().getDisplayHeight()
        );
        form.revalidate();
        form.setVisible(true);
        form.paintComponent(screenshot.getGraphics(), true);
        form.removeAll();
        try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
            ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
        } catch (IOException err) {
            Log.e(err);
        }
        Form screenShotForm = new Form("Partager le offre", new BoxLayout(BoxLayout.Y_AXIS));
        ImageViewer screenshotViewer = new ImageViewer(screenshot.fill(1000, 2000));
        screenshotViewer.setFocusable(false);
        screenshotViewer.setUIID("screenshot");
        ShareButton btnPartager = new ShareButton();
        btnPartager.setText("Partager ");
        btnPartager.setTextPosition(LEFT);
        btnPartager.setImageToShare(imageFile, "image/png");
        btnPartager.setTextToShare(candidat.getFullName());
        screenShotForm.addAll(screenshotViewer, btnPartager);
        screenShotForm.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> AccueilFront.accueilFrontForm.showBack());
        screenShotForm.show();
        // FIN API PARTAGE
    }
}
