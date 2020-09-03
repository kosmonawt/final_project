package pl.sda.final_project.model.user;

import java.util.Arrays;

public enum Countries {
    POLAND("Polska", "PL"),
    GERMANY("Niemcy", "DE"),
    ITALY("Wlochy", "IT"),
    GREAT_BRITAIN("Wielka Brytania", "GB");

    private final String plName;
    private final String symbol;

    Countries(String plName, String symbol) {
        this.plName = plName;
        this.symbol = symbol;
    }

    public static Countries fromSymbol(String smb) {
        return Arrays.stream(Countries.values())
                .filter(c -> c.symbol.equals(smb))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Nie znaleziono kraju"));
    }

    public String getPlName() {
        return plName;
    }

    public String getSymbol() {
        return symbol;
    }
}
