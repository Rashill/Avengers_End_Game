package Model.Defenders;

import Model.Defender;
import Model.Hexagon;
import Model.Player;

import javax.imageio.ImageIO;
import java.net.URL;

import static java.lang.Math.abs;

public class Ultron extends Defender {
    public Ultron(Player owner) throws Exception {
        super(100, 6, 1, ImageIO.read(new URL("https://i.kinja-img.com/gawker-media/image/upload/s--fZTexVCk--/c_fit,f_auto,fl_progressive,q_80,w_320/bq2vlrnc6jhxq4wyzlij.jpg")), owner);
    }

    public boolean move(Hexagon source,Hexagon target) {
        if(abs(source.getX()-target.getX())>getAttackingDistance()||abs(source.getY()-target.getY())>getAttackingDistance())
        {
            return false;
        }
        if(target.getPiece()==null) {
            target.setPiece(source.getPiece());
            source.setPiece(null);
            return true;
        }
        return false;

    }

    public void attack(Hexagon hexagon) {

    }
}
