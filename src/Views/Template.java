package Views;

import javax.swing.*;
/*
** Default template for the UI
 */
public class Template  extends JFrame {
    public Template(String title){
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setSize(1200,620);
        this.setLocation(450,200);
        this.setVisible(true);
    }
}
