/**
 * MoosicPlayer (OOP Project)
 *
 * @author Arjay
 *
 * PROBLEMS: 
 *  delay sync in changing time 
 *  volume changes in previous next keybind
 */
public class MoosicPlayer {

    public static void main(String[] args) {
        GUI g = new GUI();
        g.setLocationRelativeTo(null);
        g.setTitle("MoosicPlayer");
        g.setVisible(true);
    }
}
