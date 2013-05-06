/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku_bibkodan;

import javax.swing.JButton;

/**
 *
 * @author Daniel
 */
public class Tlacitko extends JButton{
    String text;
    
    public Tlacitko(String text){
    this.text = text;
    this.setText(text);
    }
    
}
