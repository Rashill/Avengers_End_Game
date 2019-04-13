package Model;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static Game instance = null;

    private Board board;
    private ArrayList<Player> players;
    private int playerTurn;
    private Hexagon selectedHexagon;
    private int boardSize;

    public int getBoardSize() {
        return boardSize;
    }

    public Board getBoard() {
        return board;
    }
    private List<IModelChangeListener> listeners = new ArrayList<>();

    public int getPlayerTurn() {
        return playerTurn;
    }

    @Requires("numOfPlayers>0 && boardSize>2")
    @Ensures("playerTurn==0 && players!=null")
    private Game(int numOfPlayers,int boardSize){
        this.boardSize=boardSize;
        this.players = new ArrayList<>();
        for (int i =0; i < numOfPlayers; i++) {
            this.players.add(new Player("Player " + i + 1 ));
        }
        this.board= Board.getInstance(players, boardSize);
        playerTurn = 0;
    }

    public static Game getInstance(int numOfPlayers,int boardSize){
        if (instance == null){
            instance = new Game(numOfPlayers,boardSize);
        }
        return instance;
    }

    public static Game getInstance(){
        if (instance != null){
            return instance;
        }
        return null;
    }
    public Player getPlayer() {
        return players.get(playerTurn);
    }

    public Hexagon getSelectedHexagon() {
        return selectedHexagon;
    }


    public void setSelectedHexagon(Hexagon selectedHexagon) {
        if(selectedHexagon==null||selectedHexagon.getPiece()!=null&&selectedHexagon.getPiece().isOwner(players.get(playerTurn)))
            this.selectedHexagon = selectedHexagon;
    }

    /**
     * This method is used to change the player turn to next player
     * @return void
     */
    @Ensures("playerTurn<players.size()")
    public void changePlayerTurn() {
        if (playerTurn == (players.size() - 1)){
            playerTurn = 0;
        }
        else
            playerTurn = playerTurn + 1;
        this.notifyModelChangedListeners();
    }

    /**
     * This method is used to inform all the listeners once the model changes
     * @return void
     */
    private void notifyModelChangedListeners() {
        this.listeners.forEach(listener -> listener.onModelChange(this));
    }


    /**
     * This method is used to add the listeners which would be informed once the model changes
     * @param listener This is the observer.
     * @return void
     */
    public void addModelChangedListeners(IModelChangeListener listener) {
        this.listeners.add(listener);
    }


    /**
     * This method is used to decide whether the player has decided to attack or move and accordingly
     * calls the respective method.
     * @param x this is x position of the hexagon.
     * @param y this is y position of the hexagon.
     * @return void
     */
    @Requires("x>=0 && x<boardSize && y>=0 && y<boardSize")
    @Ensures("selectedHexagon==null || selectedHexagon== getBoard().getHexagon(x,y)")
    public void click(int x, int y) {
        boolean move=false;
        if(getSelectedHexagon()==null)
        {
            setSelectedHexagon(getBoard().getHexagon(x,y));
        }
        else
        {
            if(getBoard().getHexagon(x,y).getPiece() == null)
                move = getSelectedHexagon().getPiece().move(getSelectedHexagon(), getBoard().getHexagon(x, y));
            else if(!getBoard().getHexagon(x,y).getPiece().isOwner(players.get(playerTurn)))
                move = getSelectedHexagon().getPiece().attack(getSelectedHexagon(), getBoard().getHexagon(x, y));
            if (move) {
                changePlayerTurn();
            }
            setSelectedHexagon(null);
        }
    }
}
