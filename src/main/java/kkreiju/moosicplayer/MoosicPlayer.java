package kkreiju.moosicplayer;

/**
 * MoosicPlayer (OOP Project)
 *
 * @author Arjay
 *
 * PROBLEMS: 
 *  delay sync in changing time 
 *  volume changes in previous next keybind
 * 
 * GOALS:
 *  lyrics
 *  loop all playlist or loop once
 */
public class MoosicPlayer {

    public static void main(String[] args) {
        GUI g;

        System.out.println("Final Project\nMoosicPlayer\nArjay Nino G. Saguisa\n92485/92477\nIT-DIGILOG21");
        try {
            g = new GUI();
            g.setLocationRelativeTo(null);
            g.setTitle("MoosicPlayer");
            g.show();
        } catch (Exception e) {
            System.out.println("Application is not responding.");
        }
    }
}
