/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decyrptingfinalltaylor;

import java.awt.GridLayout;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Lacey
 */
public class Tryout extends JFrame
{
//Attributes

//Behaviors
//Constructors
    public Tryout(String title)
    {
        super(title);

        this.setSize(300, 200);
        this.setLocation(400, 100);
        this.setLayout(new GridLayout(2, 1));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
