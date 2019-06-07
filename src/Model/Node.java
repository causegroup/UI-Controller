package Model;

import java.util.ArrayList;

public class Node{
    public int nodeID;
    public ArrayList<GamePiece> gamePiecesOn;
    
    Node(int nodeID){
        this.nodeID = nodeID;
        gamePiecesOn = new ArrayList<GamePiece>();
    }

    public ArrayList<GamePiece> getGamePiecesOn() {
        return gamePiecesOn;
    }
}