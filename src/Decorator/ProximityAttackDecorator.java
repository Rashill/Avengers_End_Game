package Decorator;

import Command.Command;
import Composite.CommandComposite;
import Model.*;
import Command.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ProximityAttackDecorator extends PieceDecorator{
    public ProximityAttackDecorator(Piece piece)
    {
        super(piece.getStealth(),piece.getAttackingPower(),piece.getAttackingDistance(),piece.getImage(),piece.getOwner(),piece, piece.getAttackStrategy());
    }

    @Override
    public void specialEffect(Hexagon hexagon){
        piece.specialEffect(hexagon);
        proximityAttack(hexagon);
    }

    public void proximityAttack(Hexagon hexagon) {
        System.out.println("This: " + hexagon.getX() + "," + hexagon.getY());

//        ArrayList<Hexagon> surroundingHexagons = hexagon.getSurroundHexagons();
//
//        for (Hexagon hex : surroundingHexagons) {
//            Piece pieceToAttack = Game.getHexagonPiece(hex.getX(), hex.getY());
//
//            if (pieceToAttack != null) {
//                    pieceToAttack.suffer(2);
//            }
//        }

//        CommandComposite commandComposite = new CommandComposite();
//        commandComposite.add(new ProximityAttackCommand());
            CommandComposite commandManager = new CommandComposite();
            Command command1 = new ProximityAttackCommand(hexagon);
            commandManager.ExecuteCommand(command1);
    }
}
