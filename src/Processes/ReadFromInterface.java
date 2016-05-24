package Processes;

import com.company.*;
import com.company.Process;
import com.sun.org.apache.bcel.internal.generic.Instruction;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by lukas on 2016-05-22.
 */
public class ReadFromInterface extends Process {
    Instructions instructions;
    ResourcePlaner resourcePlaner;
    public ReadFromInterface(String name, int state, Process father, ResourcePlaner resourcePlaner) {
        super(name, state, father);
        this.ProcessNeedsResource(resourcePlaner.findResource("InputOutput"));
        this.resourcePlaner = resourcePlaner;
    }

    @Override
    public void run() {
        while(true) {
            if (this.ProcessHasAllResource()) {
                // TODO read input
                try (BufferedReader br = new BufferedReader(new FileReader("code.txt"))) {
                    this.ProcessNeedsResource(resourcePlaner.findResource("Supervizorinės atminties"));
                    if (this.ProcessHasAllResource()) {
                        // TODO program code has to be copied to Supervizorine atminti
                        if (this.hasItTaken(resourcePlaner.findResource("Užduotis supervizorinėje atminyje"))) {
                            this.removeResourcesAfterUsingIt(resourcePlaner.findResource("Užduotis supervizorinėje atminyje"));
                            // returns true if remove was successful and false if not
                        }
                        if (this.ProcessHasAllResource()) {
                            System.out.println("mes esame Read form interface");
                            break;
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("Something is wrong ReadFromInterface should not have gotten processor");
            }
        }
    }

    public void work(ProcessPlaner processPlaner){

    }
}
