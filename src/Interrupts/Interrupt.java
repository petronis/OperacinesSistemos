package Interrupts;

/**
 * Created by Vik on 3/14/2016.
 */
public class Interrupt extends Exception {
    String name;
    public Interrupt(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
