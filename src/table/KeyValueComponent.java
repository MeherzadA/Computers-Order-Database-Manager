package table;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 * <p>The KeyValueComponent class represents a component with key-value pairs.
 * It extends the abstract Component class and provides methods to format and justify its content.</p>
 * <p>Concept: Inheritance</p>
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-1-20
 */
public class KeyValueComponent extends Component {

    /**
     * The delimiter between key and value in the content.
     */
    private static final String DELIMITER = ":";

    /**
     * The value of the component. Concept: Encapsulation
     */
    private String value;

    /**
     * The justification type for the key. Concept: Encapsulation
     */
    private int keyJustifyType;

    /**
     * The ratio of space allocated for the key relative to the total key and value space. Concept: Encapsulation
     */
    private int keyWidthRatio;

    /**
     * The justification type for the value. Concept: Encapsulation
     */
    private int valueJustifyType;

    /**
     * The ratio of space allocated for the value relative to the total key and value space. Concept: Encapsulation
     */
    private int valueWidthRatio;

    /**
     * Constructs a KeyValueComponent with specified parameters.
     *
     * @param value            The value of the component.
     * @param keyJustifyType   The justification type for the key.
     * @param keyWidthRatio    The ratio of space allocated for the key relative to the total key and value space.
     * @param valueJustifyType The justification type for the value.
     * @param valueWidthRatio  The ratio of space allocated for the value relative to the total key and value space.
     */
    public KeyValueComponent(String value, int keyJustifyType, int keyWidthRatio, int valueJustifyType, int valueWidthRatio) {
        this.value = value;
        this.keyJustifyType = keyJustifyType;
        this.keyWidthRatio = keyWidthRatio;
        this.valueJustifyType = valueJustifyType;
        this.valueWidthRatio = valueWidthRatio;
    }

    /**
     * Gets the value of the component.
     *
     * @return The value string.
     */
    public String getValue() {
        return value;
    }

    /**
     * Gets the justification type for the key.
     *
     * @return The key justification type.
     */
    public int getKeyJustifyType() {
        return keyJustifyType;
    }

    /**
     * Gets the key-to-total ratio for width allocation.
     *
     * @return The key-to-total ratio.
     */
    public int getKeyWidthRatio() {
        return keyWidthRatio;
    }

    /**
     * Gets the justification type for the value.
     *
     * @return The value justification type.
     */
    public int getValueJustifyType() {
        return valueJustifyType;
    }

    /**
     * Gets the value-to-total ratio for width allocation.
     *
     * @return The value-to-total ratio.
     */
    public int getValueWidthRatio() {
        return valueWidthRatio;
    }

    /**
     * Sets the value of the component.
     *
     * @param value The new value string.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Sets the justification type for the key.
     *
     * @param keyJustifyType The new key justification type.
     */
    public void setKeyJustifyType(int keyJustifyType) {
        this.keyJustifyType = keyJustifyType;
    }

    /**
     * Sets the key-to-total ratio for width allocation.
     *
     * @param keyWidthRatio The new key-to-total ratio.
     */
    public void setKeyWidthRatio(int keyWidthRatio) {
        this.keyWidthRatio = keyWidthRatio;
    }

    /**
     * Sets the justification type for the value.
     *
     * @param valueJustifyType The new value justification type.
     */
    public void setValueJustifyType(int valueJustifyType) {
        this.valueJustifyType = valueJustifyType;
    }

    /**
     * Sets the value-to-total ratio for width allocation.
     *
     * @param valueWidthRatio The new value-to-total ratio.
     */
    public void setValueWidthRatio(int valueWidthRatio) {
        this.valueWidthRatio = valueWidthRatio;
    }

    /**
     * Generates the top divider for the component.
     * Concept: Polymorphism
     * @param tableWidth The width of the table.
     * @return The top divider as a string.
     */
    @Override
    protected String topDivider(int tableWidth) {
        return Table.TOP_LEFT
                + Character.toString(Table.HORIZONTAL).repeat(tableWidth + PADDING)
                + Table.TOP_RIGHT + NEW_LINE;
    }

    /**
     * Generates the bottom divider for the component.
     * Concept: Polymorphism
     * @param tableWidth The width of the table.
     * @return The bottom divider as a string.
     */
    @Override
    protected String bottomDivider(int tableWidth) {
        return Table.BOTTOM_LEFT
                + Character.toString(Table.HORIZONTAL).repeat(tableWidth + PADDING)
                + Table.BOTTOM_RIGHT + NEW_LINE;
    }

    /**
     * Justifies the content of the component based on the specified table width.
     *
     * @param tableWidth The width of the table.
     * @return The justified content as a string.
     */
    private String justifyContent(int tableWidth) {
        double totalWidthRatio = (double) keyWidthRatio + valueWidthRatio;

        int keyWidth = (int) (tableWidth * (keyWidthRatio / totalWidthRatio));
        int valueWidth = tableWidth - keyWidth;

        String[] lines = value.split(NEW_LINE);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            int indexOfDelimiter = line.indexOf(DELIMITER);

            String key = line.substring(0, indexOfDelimiter);
            String value = line.substring(indexOfDelimiter + 1);

            sb.append(justifyLine(key, keyJustifyType, keyWidth));
            sb.append(justifyLine(value, valueJustifyType, valueWidth));

            if (i < lines.length - 1) {
                sb.append(NEW_LINE);
            }
        }

        return sb.toString();
    }

    /**
     * Formats the content of the component based on the specified table width.
     * Concept: Polymorphism
     * @param tableWidth The width of the table.
     * @return The formatted content as a string.
     */
    @Override
    protected String formatContent(int tableWidth) {
        return wrapAllLines(justifyContent(tableWidth));
    }
}
