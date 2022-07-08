/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author wawa
 */
public class Home extends Form{
    public Home (Resources theme ){
        this.setLayout(BoxLayout.y());
        this.setTitle("Home");
        this.getToolbar().addCommandToLeftSideMenu("Add job offer", null, (evt) -> {
            new AddJobOffer(theme).show();
        });
    }
    
    
}
