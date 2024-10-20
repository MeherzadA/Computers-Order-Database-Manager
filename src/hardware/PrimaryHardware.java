
package hardware;

import parts.*;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 *
 * <p>This {@code PrimaryHardware} class represents the primary hardware components of a computer system,
 * including storage, motherboard, RAM, GPU, and CPU. It provides methods to
 * access and modify these components, calculate the total price, and check if
 * the CPU or GPU brand matches a specified brand.</p>
 *
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-01-19
 */
public class PrimaryHardware {
    /** Storage component. Concept: Encapsulation */
    private Storage storage;

    /** Motherboard component. Concept: Encapsulation */
    private Motherboard motherboard;

    /** RAM component. Concept: Encapsulation */
    private RAM RAM;

    /** GPU component. Concept: Encapsulation */
    private GPU GPU;

    /** CPU component. Concept: Encapsulation */
    private CPU CPU;

    /**
     * Constructs a PrimaryHardware object with the specified components.
     *
     * @param storage      the storage component
     * @param motherboard  the motherboard component
     * @param RAM          the RAM component
     * @param GPU          the GPU component
     * @param CPU          the CPU component
     */
    public PrimaryHardware(Storage storage, Motherboard motherboard, RAM RAM, GPU GPU, CPU CPU) {
        this.storage = storage;
        this.motherboard = motherboard;
        this.RAM = RAM;
        this.GPU = GPU;
        this.CPU = CPU;
    }

    /**
     * Gets the storage component.
     *
     * @return the storage component
     */
    public Storage getStorage() {
        return storage;
    }

    /**
     * Gets the motherboard component.
     *
     * @return the motherboard component
     */
    public Motherboard getMotherboard() {
        return motherboard;
    }

    /**
     * Gets the RAM component.
     *
     * @return the RAM component
     */
    public RAM getRAM() {
        return RAM;
    }

    /**
     * Gets the GPU component.
     *
     * @return the GPU component
     */
    public GPU getGPU() {
        return GPU;
    }

    /**
     * Gets the CPU component.
     *
     * @return the CPU component
     */
    public CPU getCPU() {
        return CPU;
    }

    /**
     * Sets the storage component.
     *
     * @param storage the storage component to set
     */
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    /**
     * Sets the motherboard component.
     *
     * @param motherboard the motherboard component to set
     */
    public void setMotherboard(Motherboard motherboard) {
        this.motherboard = motherboard;
    }

    /**
     * Sets the RAM component.
     *
     * @param RAM the RAM component to set
     */
    public void setRAM(RAM RAM) {
        this.RAM = RAM;
    }

    /**
     * Sets the GPU component.
     *
     * @param GPU the GPU component to set
     */
    public void setGPU(GPU GPU) {
        this.GPU = GPU;
    }

    /**
     * Sets the CPU component.
     *
     * @param CPU the CPU component to set
     */
    public void setCPU(CPU CPU) {
        this.CPU = CPU;
    }

    /**
     * Calculates the total price of the primary hardware components.
     *
     * @return the total price of the primary hardware
     */
    public double totalPrice(){
        return storage.getPrice() + motherboard.getPrice() + RAM.getPrice() + GPU.getPrice() + CPU.getPrice();
    }

    /**
     * Checks if the CPU brand matches the specified brand.
     *
     * @param brand the brand to compare
     * @return true if the CPU brand matches, false otherwise
     */
    public boolean matchCpuBrand(String brand){
        return brand.equalsIgnoreCase(CPU.getBrand());
    }

    /**
     * Checks if the GPU brand matches the specified brand.
     *
     * @param brand the brand to compare
     * @return true if the GPU brand matches, false otherwise
     */
    public boolean matchGpuBrand(String brand){
        return brand.equalsIgnoreCase(GPU.getBrand());
    }

    /**
     * Returns a string representation of the primary hardware, including details of
     * CPU, motherboard, RAM, GPU, and storage.
     * Concept: Polymorphism
     * @return a formatted string representing the primary hardware
     */
    @Override
    public String toString() {
        return String.format("""
                CPU:
                %s
                Motherboard:
                %s
                RAM:
                %s
                GPU:
                %s
                Storage:
                %s""", CPU, motherboard, RAM, GPU, storage);
    }
}
