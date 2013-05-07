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
public class Tlacitko extends JButton{ // Trieda tlačítok, (zastupujúca) triedu JButton
    String text;
    
    public Tlacitko(String text){ // Konštruktor tlačítka s nastavením textu 
    this.text = text;
    this.setText(text);
    }
    
    public Tlacitko(String text, int a, int b, int c, int d){ // Konštruktor tlačítka s nastavením textu a pozície tlačítka
    this.text = text;
    this.setText(text);
    this.setBounds(a, b, c, d);
    }
    
}
