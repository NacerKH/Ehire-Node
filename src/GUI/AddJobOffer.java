/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.components.ToastBar;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import java.util.List;
import models.Category;
import models.JobOffer;
import services.CategoriesService;
import services.JobofferService;

/**
 *
 * @author wawa
 */
public class AddJobOffer extends Form {

    public AddJobOffer(Resources theme) {
        this.setLayout(BoxLayout.y());
        this.setTitle("Add Job offer");
        //components init
        Label Lb_Job = new Label("Job description :");
        TextField Tf_JobDescription = new TextField("", "Insert job description");
        Label Lb_AvgSal = new Label("Average salary :");
        TextField Tf_AverageSalary = new TextField("", "Insert Average salary");
        Label Lb_Totalplace = new Label("Total places :");
        TextField Tf_TotalPlaces = new TextField("", "Insert Total places");
        Label Lb_Satus = new Label("Status :");
        TextField Tf_Status = new TextField("", "Insert Status");
        Label CreD = new Label("Created Date :");
        //

        Picker dateTimePickerCR = new Picker();
        dateTimePickerCR.setType(Display.PICKER_TYPE_DATE);
        
        Label Lb_categ = new Label("Category :");
        ComboBox   Category = new ComboBox();
        for (String item :CategoriesService.getInstance().fetchCategories() ) {
            Category.addItem(item);
        }
        Category.addItem(CategoriesService.getInstance().fetchCategories());
        
        // selector categories
        
        List<String> Categories = CategoriesService.getInstance().fetchCategories();
        int listLength = Categories.size();
        String[] CategoriesList = new String[listLength];
        for( int i = 0; i < Categories.size(); i++ ) {
            CategoriesList [i] = Categories.get(i);
        }
        AutoCompleteTextField act = new AutoCompleteTextField(CategoriesList);
        act.addActionListener(e -> ToastBar.showMessage("You picked " + act.getText(), FontImage.MATERIAL_INFO));
        Button down = new Button();
        FontImage.setMaterialIcon(down, FontImage.MATERIAL_KEYBOARD_ARROW_DOWN);

        down.addActionListener(e -> act.showPopup());
        
        
        Button btn = new Button("Add");
        //
        btn.addActionListener((ActionListener) (ActionEvent evt) -> {
            
            System.out.println("test******"+CategoriesService.getInstance().fetchCategorieByName("tester"));
            Category c = new Category(2, "GraphicDesign");
            JobOffer p = new JobOffer(Tf_JobDescription.getText(), Integer.parseInt(Tf_AverageSalary.getText()), Integer.parseInt(Tf_TotalPlaces.getText()), Tf_Status.getText(), dateTimePickerCR.getDate(), dateTimePickerCR.getDate(), c);
            if (JobofferService.getInstance().addJoboffer(p)) {
                Dialog.show("Succes", "Joboffer ajoutée avec succes!", "OK", null);
            } else {
                Dialog.show("Echec", "Re-essayer un peu plus tard!", "OK", null);
            }
        });

        this.addAll(Lb_Job, Tf_JobDescription, Lb_AvgSal, Tf_AverageSalary, Lb_Totalplace, Tf_TotalPlaces, Lb_Satus, Tf_Status, CreD, dateTimePickerCR, Lb_categ, Category,BorderLayout.center(act).
                add(BorderLayout.EAST, down), btn);

    }
}
