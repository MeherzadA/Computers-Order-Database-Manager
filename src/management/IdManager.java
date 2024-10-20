package management;

import java.io.*;
/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 *
 * The IdManager class is responsible for managing unique identifier values
 * stored in a file. It provides methods to retrieve the current identifier
 * value and increment it, ensuring uniqueness across the application.
 *
 * <p>
 * This class is designed to be used as a utility class, and its methods
 * are static to allow easy access without the need for object instantiation.
 * </p>
 *
 * <p>
 * The identifier values are stored in a file named "id.txt" located in the
 * "src/database" directory. The file is expected to contain a single integer
 * value representing the current identifier.
 * </p>
 *
 * <p>
 * Note: This class assumes that the file structure and content adhere to the
 * expected format. Incorrect file content may result in exceptions.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 * int currentId = IdManager.getCurrentIdValue();
 * IdManager.incrementIdValue();
 * </pre>
 * </p>
 *
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-01-19
 */
public final class IdManager {
    /** The file path of the identifier storage file. */
    private static final String ID_FILE = "src/database/id.txt";

    /**
     * Represents the id value associated with the first order created
     */
    private static final int STARTING_ID_VALUE = 10000;

    /**
     * Private constructor to prevent instantiation of the class.
     */
    private IdManager() {}

    /**
     * Retrieves the current identifier value from the storage file.
     * Concept: File Reading
     * @return The current identifier value.
     * @throws IOException             If an I/O error occurs while reading the file.
     * @throws NumberFormatException   If the file content is not a valid integer.
     */
    public static int getCurrentIdValue() throws IOException, NumberFormatException {
        BufferedReader br = new BufferedReader(new FileReader(ID_FILE));
        int currentValue = Integer.parseInt(br.readLine());
        br.close();
        return currentValue;
    }

    /**
     * Increments the current identifier value and updates the storage file.
     * Concept: File Writing
     * @throws IOException             If an I/O error occurs while writing to the file.
     * @throws NumberFormatException   If the file content is not a valid integer.
     */
    public static void incrementIdValue() throws IOException, NumberFormatException {
        int currentValue = getCurrentIdValue();
        BufferedWriter bw = new BufferedWriter(new FileWriter(ID_FILE));
        bw.write(String.valueOf(currentValue + 1));
        bw.close();
    }

    /**
     * Sets the current ID value to the specified ID and updates the ID file.
     * Concept: File Writing
     * @param id The new ID value to set.
     */
    public static void setCurrentIDValue(int id) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(ID_FILE));
        bw.write(String.valueOf(id));
        bw.close();
    }

    /**
     * Resets the current ID value to the starting ID value.
     */
    public static void resetCurrentIDValue() throws IOException {
        setCurrentIDValue(STARTING_ID_VALUE);
    }

}
