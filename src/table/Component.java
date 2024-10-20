package table;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 * <p>The abstract Component class provides common functionality for formatting content in a table.
 *  It includes methods for formatting content, creating top and bottom dividers, and justifying lines.</p>
 *  <p>Concept: Abstract Class</p>
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-1-20
 */
public abstract class Component {

    /**
     * Represents an empty space character.
     */
    protected static final char EMPTY_SPACE = ' ';

    /**
     * Represents a newline character.
     */
    protected static final String NEW_LINE = "\n";

    /**
     * The padding value for formatting.
     */
    protected static final int PADDING = 2;

    /**
     * Left justification option for justifying text.
     */
    public static final int LEFT_JUSTIFY = 0;

    /**
     * Right justification option for justifying text.
     */
    public static final int RIGHT_JUSTIFY = 1;

    /**
     * Center justification option for justifying text.
     */
    public static final int CENTER_JUSTIFY = 2;

    /**
     * Abstract method to format the content based on the specified table width.
     * Concept: Abstract Method
     * @param tableWidth The width of the table.
     * @return Formatted content as a string.
     */
    protected abstract String formatContent(int tableWidth);

    /**
     * Abstract method to generate the top divider for the component.
     * Concept: Abstract Method
     * @param tableWidth The width of the table.
     * @return Top divider as a string.
     */
    protected abstract String topDivider(int tableWidth);

    /**
     * Abstract method to generate the bottom divider for the component.
     * Concept: Abstract Method
     * @param tableWidth The width of the table.
     * @return Bottom divider as a string.
     */
    protected abstract String bottomDivider(int tableWidth);

    /**
     * Wraps each line of a given string with vertical bars to create a multiline representation.
     *
     * @param value The input string.
     * @return Multiline representation with vertical bars.
     */
    protected static String wrapAllLines(String value) {
        String[] lines = value.split(NEW_LINE);
        StringBuilder sb = new StringBuilder();

        for (String line : lines) {
            sb.append(Table.VERTICAL).append(EMPTY_SPACE).append(line).append(EMPTY_SPACE).append(Table.VERTICAL).append(NEW_LINE);
        }

        return sb.toString();
    }

    /**
     * Justifies a single line of text based on the specified justification type and width.
     *
     * @param toJustify   The text to justify.
     * @param justifyType The justification type (LEFT, RIGHT, or CENTER).
     * @param width       The width of the justified line.
     * @return Justified line as a string.
     * @throws IllegalArgumentException If an unexpected justification type is provided.
     */
    protected static String justifyLine(String toJustify, int justifyType, int width) {
        String justifiedLine;

        switch (justifyType) {
            case LEFT_JUSTIFY -> justifiedLine = String.format("%-" + width + "s", toJustify);
            case RIGHT_JUSTIFY -> justifiedLine = String.format("%" + width + "s", toJustify);
            case CENTER_JUSTIFY -> {
                int spaces = width - toJustify.length();
                int leftSpaces = spaces / 2;
                int rightSpaces = spaces - leftSpaces;

                justifiedLine = String.format("%" + leftSpaces + "s%s%" + rightSpaces + "s", "", toJustify, "");
            }
            default -> throw new IllegalArgumentException("Unexpected value: " + justifyType);
        }

        if (justifiedLine.length() > width) {
            return justifiedLine.substring(0, width - 3) + "...";
        }
        return justifiedLine;
    }

    /**
     * Justifies all lines of a multiline text based on the specified justification type and width.
     *
     * @param toJustify   The multiline text to justify.
     * @param justifyType The justification type (LEFT, RIGHT, or CENTER).
     * @param width       The width of the justified lines.
     * @return Justified multiline text as a string.
     */
    protected static String justifyAllLines(String toJustify, int justifyType, int width) {
        String[] lines = toJustify.split(NEW_LINE);
        StringBuilder padded = new StringBuilder();

        for (int i = 0; i < lines.length; i++) {
            String justifiedLine = justifyLine(lines[i], justifyType, width);
            padded.append(justifiedLine);

            if (i < lines.length - 1) {
                padded.append(NEW_LINE);
            }
        }

        return padded.toString();
    }
}
