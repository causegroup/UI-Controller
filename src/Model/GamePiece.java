package Model;

public class GamePiece{
    public Player owner;
    public int pieceID;
    private Node node;
    GamePiece(Player owner, int pieceID){
        this.owner = owner;
        this.pieceID  = pieceID;
        node = null;
    }

    public void setNode(Node node){
        this.node = node;
    }

    public Node getNode() {
        return node;
    }

    public void move(Node nextNode){
        nextNode.getGamePiecesOn().add(this);
        if(node == null) {
            node = nextNode;
            return;
        }
        node.getGamePiecesOn().remove(this);
        node = nextNode;
    }

    public void caught(){
        if(node == null)
            return;
        node.getGamePiecesOn().remove(this);
        this.node = null;
    }

}