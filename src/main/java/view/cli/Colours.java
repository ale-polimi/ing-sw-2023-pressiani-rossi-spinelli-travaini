package view.cli;

/**
 * Enumeration that holds the colours for the CLI.
 */
public enum Colours {
    /* Clear color to clear the cli */
    CLEAR("\033[H\033[2J"),
    RESET("\033[0m"),
    UNDERLINED("\033[4m"),
    BLACK("\033[30m"),
    GREEN("\033[32m"),
    WHITE("\033[37m"),
    YELLOW("\033[33m"),
    BLUE("\033[34m"),
    LIGHT_BLUE("\033[36m"),
    PINK("\033[35m");



    private final String code;
    Colours(String code){
        this.code = code;
    }
    @Override
    public String toString() {
        return code;
    }
}
