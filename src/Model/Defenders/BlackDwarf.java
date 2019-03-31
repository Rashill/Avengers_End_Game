package Model.Defenders;

import Model.Defender;
import Model.Hexagon;
import Model.Player;

import javax.imageio.ImageIO;
import java.net.URL;

import static java.lang.Math.abs;

public class BlackDwarf extends Defender {

    public BlackDwarf(Player owner) throws Exception {
        super(100, 4, 2, ImageIO.read(new URL("https://upload.wikimedia.org/wikipedia/en/b/b5/Black_Dwarf.jpg")), owner);
    }

    public void move(Hexagon source, Hexagon target) {
        if(abs(source.getX()-target.getX())>getAttackingDistance()||abs(source.getY()-target.getY())>getAttackingDistance())
        {
            return;
        }
        if(target.getPiece()==null) {
            target.setPiece(source.getPiece());
            source.setPiece(null);
        }
    }

    public void attack(Hexagon hexagon) {

    }
}