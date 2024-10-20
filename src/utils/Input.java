package utils;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 *
 * The {@code Input} class represents a simple wrapper for input values.
 * It provides a method to convert the input value to an integer.
 *
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-01-19
 */
public class Input {

    /**
     * The value stored in the input.
     */
    public final String value;

    /**
     * Constructs a new {@code Input} with the specified value.
     *
     * @param value The input value.
     */
    public Input(String value) {
        this.value = value;
    }

    /**
     * Converts the input value to an integer.
     *
     * @return The integer representation of the input value.
     * @throws NumberFormatException If the input value is not a valid integer.
     */
    public int toInt() throws NumberFormatException {
        return Integer.parseInt(value);
    }

    /**
     * Converts the input value to a double.
     *
     * @return The double representation of the input value.
     * @throws NumberFormatException If the input value is not a valid double.
     */
    public double toDouble() throws NumberFormatException {
        return Double.parseDouble(value);
    }

    /**
     * Converts the input value to a positive double.
     *
     * @return The positive double representation of the input value.
     * @throws NumberFormatException If the input value is not a valid double.
     * @throws IllegalArgumentException If the input value is not positive or not a finite number.
     */
    public double toPositiveDouble() throws NumberFormatException, IllegalArgumentException {
        double value = toDouble();
        if (value >= 0 && Double.isFinite(value)) return value;
        throw new IllegalArgumentException("Input must be positive and a finite number. ");
    }

    /**
     * Converts the input value to a positive integer.
     *
     * @return The positive integer representation of the input value.
     * @throws NumberFormatException If the input value is not a valid integer.
     * @throws IllegalArgumentException If the input value is not positive.
     */
    public int toPositiveInt() throws NumberFormatException, IllegalArgumentException {
        int value = toInt();
        if (value >= 0) return value;
        throw new IllegalArgumentException("Input must be positive.");
    }
}
