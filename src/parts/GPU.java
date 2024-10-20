package parts;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 *
 * <p>The {@code GPU} class represents a graphics processing unit (GPU), which is a type of part in a computer system.
 * It extends the {@code Part} class and includes additional features related to ray tracing, clock speed, and VRAM.</p>
 *
 * <p>The class includes a constructor to initialize the price, model, brand, ray tracing capability, clock speed, and VRAM of the GPU.
 * It also provides getter and setter methods for accessing and modifying these features.</p>
 *
 * <p>The {@code toString} method is overridden to provide a formatted string representation of the GPU,
 * including its general information, ray tracing capability, clock speed, and VRAM.</p>
 * <p>Concept: Inheritance</p>
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-01-15
 */
public class GPU extends Part {

    /**
     * Indicates whether the GPU has ray tracing capability. Concept: Encapsulation
     */
    private boolean rayTracing;

    /**
     * The clock speed of the GPU in gigahertz (GHz). Concept: Encapsulation
     */
    private double clockSpeed;

    /**
     * The amount of video RAM (VRAM) in gigabytes (GB). Concept: Encapsulation
     */
    private int VRAM;

    /**
     * Constructs a new {@code GPU} object with the specified price, model, brand, ray tracing capability, clock speed, and VRAM.
     *
     * @param price      the price of the GPU
     * @param model      the model of the GPU
     * @param brand      the brand of the GPU
     * @param rayTracing {@code true} if the GPU has ray tracing capability, {@code false} otherwise
     * @param clockSpeed the clock speed of the GPU in gigahertz (GHz)
     * @param VRAM       the amount of video RAM (VRAM) in gigabytes (GB)
     */
    public GPU(double price, String model, String brand, boolean rayTracing, double clockSpeed, int VRAM) {
        super(price, model, brand);
        this.rayTracing = rayTracing;
        this.clockSpeed = clockSpeed;
        this.VRAM = VRAM;
    }

    /**
     * Checks if the GPU has ray tracing capability.
     *
     * @return {@code true} if the GPU has ray tracing capability, {@code false} otherwise
     */
    public boolean isRayTracing() {
        return rayTracing;
    }

    /**
     * Gets the clock speed of the GPU.
     *
     * @return the clock speed of the GPU in gigahertz (GHz)
     */
    public double getClockSpeed() {
        return clockSpeed;
    }

    /**
     * Gets the amount of video RAM (VRAM) in gigabytes (GB).
     *
     * @return the amount of VRAM in gigabytes (GB)
     */
    public int getVRAM() {
        return VRAM;
    }

    /**
     * Sets the ray tracing capability of the GPU.
     *
     * @param rayTracing {@code true} if the GPU has ray tracing capability, {@code false} otherwise
     */
    public void setRayTracing(boolean rayTracing) {
        this.rayTracing = rayTracing;
    }

    /**
     * Sets the clock speed of the GPU.
     *
     * @param clockSpeed the new clock speed to set in gigahertz (GHz)
     */
    public void setClockSpeed(double clockSpeed) {
        this.clockSpeed = clockSpeed;
    }

    /**
     * Sets the amount of video RAM (VRAM) in gigabytes (GB).
     *
     * @param vRam the new amount of VRAM to set in gigabytes (GB)
     */
    public void setVRAM(int vRam) {
        this.VRAM = vRam;
    }

    /**
     * Returns a formatted string representation of the GPU, including its general information, ray tracing capability, clock speed, and VRAM.
     * Concept: Polymorphism
     * @return a string representation of the GPU
     */
    @Override
    public String toString() {
        return String.format("""
                %s
                Has Ray Tracing: %b
                Clock Speed: %.2f GHz
                VRAM: %d GB""", super.toString(), this.rayTracing, this.clockSpeed, this.VRAM);
    }
}
