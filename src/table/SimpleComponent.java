package table;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 * <p>The SimpleComponent class represents a basic component with a single value.
 * It extends the abstract Component class and provides methods to format and justify its content.</p>
 * <p>Concept: Inheritance</p>
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-1-20
 */
public class SimpleComponent extends Component {

    /**
     * The value of the component. Concept: Encapsulation
     */
    private String value;

    /**
     * The justification type for the value. Concept: Encapsulation
     */
    private int justifyType;

    /**
     * Constructs a SimpleComponent with specified parameters.
     *
     * @param value      The value of the component.
     * @param justifyType The justification type for the value.
     */
    public SimpleComponent(String value, int justifyType) {
        this.value = value;
        this.justifyType = justifyType;
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
     * Gets the justification type for the value.
     *
     * @return The value justification type.
     */
    public int getJustifyType() {
        return justifyType;
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
     * Sets the justification type for the value.
     *
     * @param justifyType The new value justification type.
     */
    public void setJustifyType(int justifyType) {
        this.justifyType = justifyType;
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
     * Concept: Polymorphism
     * @param tableWidth The width of the table.
     * @return The justified content as a string.
     */
    @Override
    protected String formatContent(int tableWidth) {
        return wrapAllLines(justifyAllLines(value, justifyType, tableWidth));
    }
}
