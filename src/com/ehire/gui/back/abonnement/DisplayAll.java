package com.ehire.gui.back.abonnement;

import com.codename1.components.InteractionDialog;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.ehire.entities.jobs;
import com.ehire.services.jobsService;
import com.ehire.gui.front.abonnement.Manage;



import java.util.ArrayList;

public class DisplayAll extends Form {

    public static jobs currentAbonnement = null;
    Button addBtn;
    Label dureeLabel, offreLabel;
    Button editBtn, deleteBtn;
    Container btnsContainer;

    public DisplayAll(Form previous) {
        super("jobs", new BoxLayout(BoxLayout.Y_AXIS));

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

        ArrayList<jobs> listAbonnements = jobsService.getInstance().getAll();
        if (listAbonnements.size() > 0) {
            for (jobs listAbonnement : listAbonnements) {
                this.add(makeAbonnementModel(listAbonnement));
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    private void addActions() {
        addBtn.addActionListener(action -> {
            currentAbonnement = null;
            new Manage(this).show();
        });
    }

    private Component makeAbonnementModel(jobs jobs) {
        Container abonnementModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        abonnementModel.setUIID("containerRounded");

        dureeLabel = new Label("jobs description : " + jobs.getJobDescription());
        dureeLabel.setUIID("labelDefault");

        offreLabel = new Label("category : " + jobs.getCategory());
        offreLabel.setUIID("labelDefault");

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");

        editBtn = new Button("Modifier");
        editBtn.setUIID("buttonMain");
        editBtn.addActionListener(action -> {
            currentAbonnement = jobs;
            new Manage(this).show();
        });

        deleteBtn = new Button("Supprimer");
        deleteBtn.setUIID("buttonDanger");
        deleteBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer ce abonnement ?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                int responseCode = jobsService.getInstance().delete(jobs.getId());

                if (responseCode == 200) {
                    currentAbonnement = null;
                    dlg.dispose();
                    abonnementModel.remove();
                    this.revalidate();
                } else {
                    Dialog.show("Erreur", "Erreur de suppression du abonnement. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            });
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
        });

        btnsContainer.add(BorderLayout.CENTER, editBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);

        abonnementModel.addAll(
                offreLabel,
                dureeLabel,
                btnsContainer
        );

        return abonnementModel;
    }
}