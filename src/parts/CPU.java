package parts;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 * <p> The {@code CPU} class represents a central processing unit (CPU), which is a type of part in a computer system.
 * It extends the {@code Part} class and includes additional features related to processing speed.</p>
 *
 * <p>The class includes a constructor to initialize the price, model, brand, cores, threads and processing speed of the CPU.
 * It also provides getter and setter methods for accessing and modifying the cores, threads and processing speed of the CPU.</p>
 *
 * <p>The {@code toString} method is overridden to provide a formatted string representation of the CPU,
 * including its general information, number of cores, number of threads and processing speed.</p>
 * <p>Concept: Inheritance</p>
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-01-15
 */
public class CPU extends Part {

    /**
     * The processing speed of the CPU in gigahertz (GHz). Concept: Encapsulation
     */
    private double processingSpeed;
    /**
     * The number of cores in the CPU. Concept: Encapsulation
     */
    private int cores;
    /**
     * The number of threads in the CPU. Concept: Encapsulation
     */
    private int threads;

    /**
     * Constructs a new {@code CPU} object with the specified price, model, brand, number of cores, number of threads and processing speed.
     *
     * @param price           the price of the CPU
     * @param model           the model of the CPU
     * @param brand           the brand of the CPU
     * @param cores           the number of cores in the CPU
     * @param threads         the number of threads in the CPU
     * @param processingSpeed the processing speed of the CPU in gigahertz (GHz)
     */
    public CPU(double price, String model, String brand, int cores, int threads, double processingSpeed) {
        super(price, model, brand);
        this.cores = cores;
        this.threads = threads;
        this.processingSpeed = processingSpeed;
    }

    /**
     * Gets the processing speed of the CPU.
     *
     * @return the processing speed of the CPU in gigahertz (GHz)
     */
    public double getProcessingSpeed() {
        return this.processingSpeed;
    }

    /**
     * Gets the number of cores in the CPU.
     *
     * @return the number of cores in the CPU
     */
    public int getCores() {
        return cores;
    }

    /**
     * Gets the number of threads in the CPU.
     *
     * @return the number of threads in the CPU
     */
    public int getThreads() {
        return threads;
    }

    /**
     * Sets the processing speed of the CPU.
     *
     * @param processingSpeed the new processing speed to set in gigahertz (GHz)
     */
    public void setProcessingSpeed(double processingSpeed) {
        this.processingSpeed = processingSpeed;
    }

    /**
     * Sets the number of cores in the CPU.
     *
     * @param cores the new number of cores to set
     */
    public void setCores(int cores) {
        this.cores = cores;
    }

    /**
     * Sets the number of threads in the CPU.
     *
     * @param threads the new number of threads to set
     */
    public void setThreads(int threads) {
        this.threads = threads;
    }

    /**
     * Returns a formatted string representation of the CPU, including its general information, cores, threads and processing speed.
     * Concept: Polymorphism
     * @return a string representation of the CPU
     */
    @Override
    public String toString() {
        return String.format("""
                %s
                Cores: %d
                Threads: %d
                Processing Speed: %.2f GHz""", super.toString(), cores, threads, processingSpeed);
    }
}
