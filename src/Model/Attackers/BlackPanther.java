package Model.Attackers;

import Model.Attacker;
import Model.Hexagon;
import Model.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.net.URL;

public class BlackPanther extends Attacker {
    public BlackPanther(Player owner) throws Exception{
            super(8, 10, 3, ImageIO.read(new URL("http://www.pngall.com/wp-content/uploads/2016/06/Superman-Logo-Free-Download-PNG.png")), owner);
    }

    public void move(Hexagon source,Hexagon target) {
        System.out.println("Move Black Panther");
        System.out.println(target.getPiece());

        if(target.getPiece()==null) {
            target.setPiece(source.getPiece());
            source.setPiece(null);
        }

    }

    public void attack(Hexagon hexagon) {

    }
}
