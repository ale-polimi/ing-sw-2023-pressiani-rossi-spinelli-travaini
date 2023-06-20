package view.cli;

/**
 * Enumeration that holds the colours for the CLI.
 */
public enum Colours {
    /* Clear color to clear the cli */
    CLEAR("\033[H\033[2J"),
    RESET("\033[0m"),
    UNDERLINED("\033[4m"),
    BOLD("\033[1m"),
    DELETE_INPUT("\033[2K"),
    HIDE_CURSOR("\033[?25l"),
    SHOW_CURSOR("\033[?25h"),
    BLACK("\033[38;5;0m"),
    GREEN("\033[38;5;10m"),
    WHITE("\033[38;5;15m"),
    YELLOW("\033[38;5;220m"),
    BLUE("\033[38;5;21m"),
    LIGHT_BLUE("\033[38;5;14m"),
    PINK("\033[38;5;13m"),
    RED("\033[38;5;9m"),
    GOLD("\033[38;5;214m");



    private final String code;
    Colours(String code){
        this.code = code;
    }
    @Override
    public String toString() {
        return code;
    }
}
