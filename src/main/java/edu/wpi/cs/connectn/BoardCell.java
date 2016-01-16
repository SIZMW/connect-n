package edu.wpi.cs.connectn;

public enum BoardCell {
    MIN("-"),
    MAX("+"),
    NONE(".");

    private String symbol;

    BoardCell(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public GameWinner getAsGameWinner() {
        return GameWinner.valueOf(this.toString());
    }
}
