package enumerations;

import model.objects.ObjectCard;

/**
 * This enumeration contains all the different types of objects.
 */
public enum ObjectColour {
    /**
     * GREEN1 is Gatti1.1.png
     */
    GREEN1,
    /**
     * GREEN2 is Gatti1.2.png
     */
    GREEN2,
    /**
     * GREEN3 is Gatti3.1.png
     */
    GREEN3,
    /**
     * WHITE1 is Libri1.1.png
     */
    WHITE1,
    /**
     * WHITE2 is Libri1.2.png
     */
    WHITE2,
    /**
     * WHITE3 is Libri1.3.png
     */
    WHITE3,
    /**
     * YELLOW1 is Giochi1.1.png
     */
    YELLOW1,
    /**
     * YELLOW2 is Giochi1.2.png
     */
    YELLOW2,
    /**
     * YELLOW3 is Giochi1.3.png
     */
    YELLOW3,
    /**
     * BLUE1 is Cornici1.1.png
     */
    BLUE1,
    /**
     * BLUE2 is Cornici1.2.png
     */
    BLUE2,
    /**
     * BLUE3 is Cornici1.3.png
     */
    BLUE3,
    /**
     * LIGHT_BLUE1 is Trofei1.1.png
     */
    LIGHT_BLUE1,
    /**
     * LIGHT_BLUE2 is Trofei1.2.png
     */
    LIGHT_BLUE2,
    /**
     * LIGHT_BLUE3 is Trofei1.3.png
     */
    LIGHT_BLUE3,
    /**
     * PINK1 is Piante1.1.png
     */
    PINK1,
    /**
     * PINK2 is Piante1.2.png
     */
    PINK2,
    /**
     * PINK3 is Piante1.3.png
     */
    PINK3,
    /**
     * Fake colour used in the {@link model.library.Library library}.
     */
    EMPTY;

    /**
     * This method checks if a colour is equal to another colour.
     * @param objectColour is the colour to compare.
     * @return {@code true} if the colours are of the same type (e.g. {@code GREEN1.isEquals(GREEN2) = TRUE}), {@code false} otherwise (e.g. {@code GREEN1.isEquals(BLUE1) = FALSE}.
     */
    public boolean isEquals(ObjectColour objectColour){
        switch (this){
            case GREEN1,GREEN2,GREEN3 -> {
                if(objectColour==ObjectColour.GREEN1||objectColour==ObjectColour.GREEN2||objectColour==ObjectColour.GREEN3){
                    return true;
                }
            }
            case BLUE1,BLUE2,BLUE3 -> {
                if(objectColour==ObjectColour.BLUE1||objectColour==ObjectColour.BLUE2||objectColour==ObjectColour.BLUE3){
                    return true;
                }
            }
            case WHITE1,WHITE2,WHITE3 -> {
                if(objectColour==ObjectColour.WHITE1||objectColour==ObjectColour.WHITE2||objectColour==ObjectColour.WHITE3){
                    return true;
                }
            }
            case YELLOW1,YELLOW2,YELLOW3 -> {
                if(objectColour==ObjectColour.YELLOW1||objectColour==ObjectColour.YELLOW2||objectColour==ObjectColour.YELLOW3){
                    return true;
                }
            }
            case LIGHT_BLUE1,LIGHT_BLUE2,LIGHT_BLUE3 -> {
                if(objectColour==ObjectColour.LIGHT_BLUE1||objectColour==ObjectColour.LIGHT_BLUE2||objectColour==ObjectColour.LIGHT_BLUE3){
                    return true;
                }
            }
            case PINK1,PINK2,PINK3 -> {
                if(objectColour==ObjectColour.PINK1||objectColour==ObjectColour.PINK2||objectColour==ObjectColour.PINK3){
                    return true;
                }
            }
        }
        return false;
    }

}
