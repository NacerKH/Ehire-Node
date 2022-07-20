package com.ehire.gui.back.offre;

import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.ehire.entities.candidat;
import com.ehire.services.candidatService;



import java.util.ArrayList;

public class DisplayAll extends Form {

    public static candidat currentOffre = null;
    Resources theme = UIManager.initFirstTheme("/theme");
    Button addBtn;
    Label titreLabel, descriptionLabel, prixLabel, imageLabel;
    ImageViewer imageIV;
    Button editBtn, deleteBtn;
    Container btnsContainer;

    public DisplayAll(Form previous) {
        super("candidat", new BoxLayout(BoxLayout.Y_AXIS));

        addGUIs();
        addActions();

        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public void refresh() {
        this.removeAll();
        addGUIs();
        addActions();
        this.revalidate();
    }

    private void addGUIs() {
        addBtn = new Button("Ajouter");
        addBtn.setUIID("buttonWhiteCenter");

        this.add(addBtn);

        ArrayList<candidat> listOffres = candidatService.getInstance().getAll();
        if (listOffres.size() > 0) {
            for (candidat listOffre : listOffres) {
                this.add(makeOffreModel(listOffre));
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    private void addActions() {
        addBtn.addActionListener(action -> {
            currentOffre = null;
            new Manage(this, false).show();
        });
    }

    private Component makeOffreModel(candidat candidat) {
        Container offreModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        offreModel.setUIID("containerRounded");

        titreLabel = new Label(candidat.getFullName());
        titreLabel.setUIID("labelCenter");

        prixLabel = new Label("eamil : " + candidat.getEmail());
        prixLabel.setUIID("labelDefault");

        descriptionLabel = new Label("phone : " + candidat.getPhoneNumber());
        descriptionLabel.setUIID("labelDefault");

        imageLabel = new Label("cv : " + candidat.getCv_url());
        imageLabel.setUIID("labelDefault");

        
        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");

        editBtn = new Button("Modifier");
        editBtn.setUIID("buttonMain");
        editBtn.addActionListener(action -> {
            currentOffre = candidat;
            new Manage(this, false).show();
        });

        deleteBtn = new Button("Supprimer");
        deleteBtn.setUIID("buttonDanger");
        deleteBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer ce offre ?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                int responseCode = candidatService.getInstance().delete(candidat.getId());

                if (responseCode == 200) {
                    currentOffre = null;
                    dlg.dispose();
                    offreModel.remove();
                    this.revalidate();
                } else {
                    Dialog.show("Erreur", "Erreur de suppression du offre. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            });
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
        });

        btnsContainer.add(BorderLayout.CENTER, editBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);

        offreModel.addAll(
                titreLabel, prixLabel, descriptionLabel, imageLabel,
                imageIV,
                btnsContainer
        );

        return offreModel;
    }

}