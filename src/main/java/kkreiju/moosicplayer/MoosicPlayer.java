package kkreiju.moosicplayer;

/**
 * MoosicPlayer (OOP Project)
 * @author Arjay
 * 
 * PROBLEMS:
 *  key bind for pause play
 * GOALS:
 *  multi songs
 */
public class MoosicPlayer {

    public static void main(String[] args) {
        GUI g;
        
        g = new GUI();
        g.setLocationRelativeTo(null);
        g.setTitle("MoosicPlayer");
        g.show();
    }
}
