package table;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 * <p>The HeaderedKeyValueComponent class represents a component with a header, key, and value.
 * It extends the abstract {@link Component} class and provides methods to format and justify its content.</p>
 * <p>Concept: Inheritance</p>
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-1-20
 */
public class HeaderedKeyValueComponent extends Component {

    /**
     * The delimiter between key and value in the content.
     */
    private static final String DELIMITER = ":";

    /**
     * Extra padding for formatting.
     */
    public static final int EXTRA_PADDING = 3;

    /**
     * The header of the component. Concept: Encapsulation
     */
    private String header;

    /**
     * The justification type for the header. Concept: Encapsulation
     */
    private int headerJustifyType;

    /**
     * The ratio of space allocated for the header relative to key and value. Concept: Encapsulation
     */
    private int headerKeyValueRatio;

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
     * Constructs a HeaderedKeyValueComponent with specified parameters.
     *
     * @param header              The header of the component.
     * @param headerJustifyType   The justification type for the header.
     * @param headerKeyValueRatio The ratio of space allocated for the header relative to key and value.
     * @param value               The value of the component.
     * @param keyJustifyType      The justification type for the key.
     * @param keyWidthRatio       The ratio of space allocated for the key relative to the total key and value space.
     * @param valueJustifyType    The justification type for the value.
     * @param valueWidthRatio     The ratio of space allocated for the value relative to the total key and value space.
     */
    public HeaderedKeyValueComponent(String header, int headerJustifyType, int headerKeyValueRatio,
                                     String value, int keyJustifyType, int keyWidthRatio,
                                     int valueJustifyType, int valueWidthRatio) {
        this.header = header;
        this.headerJustifyType = headerJustifyType;
        this.value = value;
        this.keyJustifyType = keyJustifyType;
        this.keyWidthRatio = keyWidthRatio;
        this.valueJustifyType = valueJustifyType;
        this.valueWidthRatio = valueWidthRatio;
        this.headerKeyValueRatio = headerKeyValueRatio;
    }

    /**
     * Gets the header of the component.
     *
     * @return The header string.
     */
    public String getHeader() {
        return header;
    }

    /**
     * Gets the justification type for the header.
     *
     * @return The header justification type.
     */
    public int getHeaderJustifyType() {
        return headerJustifyType;
    }

    /**
     * Gets the header-to-key-value ratio.
     *
     * @return The header-to-key-value ratio.
     */
    public int getHeaderKeyValueRatio() {
        return headerKeyValueRatio;
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
     * Sets the header of the component.
     *
     * @param header The new header string.
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * Sets the justification type for the header.
     *
     * @param headerJustifyType The new header justification type.
     */
    public void setHeaderJustifyType(int headerJustifyType) {
        this.headerJustifyType = headerJustifyType;
    }

    /**
     * Sets the header-to-key-value ratio.
     *
     * @param headerKeyValueRatio The new header-to-key-value ratio.
     */
    public void setHeaderKeyValueRatio(int headerKeyValueRatio) {
        this.headerKeyValueRatio = headerKeyValueRatio;
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
        int dividerIndex = dividerIndex(tableWidth - EXTRA_PADDING);
        return Table.TOP_LEFT
                + Character.toString(Table.HORIZONTAL).repeat(dividerIndex + 1)
                + Table.T_DOWN_INTERSECTION
                + Character.toString(Table.HORIZONTAL).repeat(tableWidth - dividerIndex)
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
        int dividerIndex = dividerIndex(tableWidth - EXTRA_PADDING);
        return Table.BOTTOM_LEFT
                + Character.toString(Table.HORIZONTAL).repeat(dividerIndex + 1)
                + Table.T_UP_INTERSECTION
                + Character.toString(Table.HORIZONTAL).repeat(tableWidth - dividerIndex)
                + Table.BOTTOM_RIGHT + NEW_LINE;
    }

    /**
     * Justifies the content of the component based on the specified table width.
     *
     * @param tableWidth The width of the table.
     * @return The justified content as a string.
     */
    private String justifyContent(int tableWidth) {
        tableWidth -= EXTRA_PADDING; // can no longer use space taken up by vertical divider and spacing around it

        double totalKeyValueWidthRatio = keyWidthRatio + valueWidthRatio;
        double totalHeaderKeyValueWidthRatio = headerKeyValueRatio + totalKeyValueWidthRatio;

        int headerWidth = (int) (tableWidth * (headerKeyValueRatio / totalHeaderKeyValueWidthRatio));
        int keyValueWidth = tableWidth - headerWidth;

        int keyWidth = (int) (keyValueWidth * (keyWidthRatio / totalKeyValueWidthRatio));
        int valueWidth = keyValueWidth - keyWidth;

        String[] lines = value.split(NEW_LINE);

        StringBuilder sb = new StringBuilder();
        String headerJustified = justifyLine(header, headerJustifyType, headerWidth);
        sb.append(headerJustified);

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            int indexOfDelimiter = line.indexOf(DELIMITER);

            String key = line.substring(0, indexOfDelimiter);
            String value = line.substring(indexOfDelimiter + 1);

            if (i > 0) {
                sb.append(Character.toString(EMPTY_SPACE).repeat(headerJustified.length()));
            }
            sb.append(EMPTY_SPACE).append(Table.VERTICAL).append(EMPTY_SPACE);
            sb.append(justifyLine(key, keyJustifyType, keyWidth));
            sb.append(justifyLine(value, valueJustifyType, valueWidth));

            if (i < lines.length - 1) {
                sb.append(NEW_LINE);
            }
        }

        return sb.toString();
    }

    /**
     * Determines the index of the divider between key and value based on the specified table width.
     *
     * @param tableWidth The width of the table.
     * @return The index of the divider.
     */
    protected int dividerIndex(int tableWidth) {
        double totalHeaderKeyValueWidthRatio = headerKeyValueRatio + keyWidthRatio + valueWidthRatio;
        return (int) (tableWidth * (headerKeyValueRatio / totalHeaderKeyValueWidthRatio)) + 1;
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
