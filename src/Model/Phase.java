package Model;

public enum Phase {
    THROW_YUT_PHASE(0),
    CHOOSE_PIECE_PHASE(1),
    MOVE_PIECE_PHASE(2);

    private int phase;
    Phase(int phase){
        this.phase = phase;
    }

}