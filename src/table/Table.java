package table;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 *
 * <p>The Table class represents a table structure that contains a list of components.
 * It provides methods to add components, set table width, and generate a formatted string representation.</p>
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-1-20
 */
public class Table {
    /**
     * Constants for the dividers that make up the table
     */
    protected static final char TOP_LEFT = '┌';
    protected static final char TOP_RIGHT = '┐';
    protected static final char BOTTOM_LEFT = '└';
    protected static final char BOTTOM_RIGHT = '┘';
    protected static final char HORIZONTAL = '─';
    protected static final char VERTICAL = '│';
    protected static final char T_RIGHT_INTERSECTION = '├';
    protected static final char T_LEFT_INTERSECTION = '┤';
    protected static final char T_DOWN_INTERSECTION = '┬';
    protected static final char T_UP_INTERSECTION = '┴';
    protected static final char INTERSECTION = '┼';

    /**
     * The list of components in the table. Concepts: Encapsulation, Array of Objects
     */
    private List<Component> components;

    /**
     * The width of the table. Concept: Encapsulation
     */
    private int width;

    /**
     * Constructs a Table with a specified width.
     *
     * @param width The width of the table.
     */
    public Table(int width) {
        components = new ArrayList<>();
        this.width = width;
    }

    /**
     * Adds a component to the table.
     *
     * @param component The component to add.
     */
    public void addComponent(Component component) {
        components.add(component);
    }

    /**
     * Adds all components from another table to this table.
     *
     * @param table The table containing components to add.
     */
    public void addComponents(Table table) {
        components.addAll(table.components);
    }

    /**
     * Gets the list of components in the table.
     *
     * @return The list of components.
     */
    public List<Component> getComponents() {
        return components;
    }

    /**
     * Gets the width of the table.
     *
     * @return The width of the table.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the list of components in the table.
     *
     * @param components The new list of components.
     */
    public void setComponents(List<Component> components) {
        this.components = components;
    }

    /**
     * Sets the width of the table.
     *
     * @param width The new width of the table.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Generates a formatted string representation of the table.
     * Concept: Polymorphism
     * @return The formatted string representation of the table.
     */
    @Override
    public String toString() {
        if (components.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();

        String previousDivider = "";
        for (int i = 0; i < components.size(); i++) {
            Component component = components.get(i);

            sb.append(combineDividers(previousDivider, component.topDivider(width)));
            sb.append(component.formatContent(width));

            previousDivider = component.bottomDivider(width);
            if (i == components.size() - 1) {
                sb.append(previousDivider);
            }
        }

        return sb.toString();
    }

    /**
     * Combines two divider strings based on their characters to create a unified divider.
     *
     * @param previousDivider The previous divider string.
     * @param nextDivider     The next divider string.
     * @return The combined divider string.
     */
    private String combineDividers(String previousDivider, String nextDivider) {
        if (previousDivider.isEmpty()) return nextDivider;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < previousDivider.length(); i++) {
            char previousDividerCharacter = previousDivider.charAt(i);
            char nextDividerCharacter = nextDivider.charAt(i);

            if (previousDividerCharacter == HORIZONTAL) {
                sb.append(nextDividerCharacter);
            } else if (nextDividerCharacter == HORIZONTAL) {
                sb.append(previousDividerCharacter);
            } else if (previousDividerCharacter == BOTTOM_LEFT && nextDividerCharacter == TOP_LEFT) {
                sb.append(T_RIGHT_INTERSECTION);
            } else if (previousDividerCharacter == BOTTOM_RIGHT && nextDividerCharacter == TOP_RIGHT) {
                sb.append(T_LEFT_INTERSECTION);
            } else if (previousDividerCharacter == T_UP_INTERSECTION && nextDividerCharacter == T_DOWN_INTERSECTION) {
                sb.append(INTERSECTION);
            } else {
                sb.append(nextDividerCharacter);
            }
        }

        return sb.toString();
    }

    /**
     * Prints the formatted string representation of the table to the console.
     */
    public void render() {
        System.out.println(this);
    }
}
