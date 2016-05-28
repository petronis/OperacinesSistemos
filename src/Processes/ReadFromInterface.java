package Processes;

import Exceptions.WrongContentSize;
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
    ProcessPlaner processPlaner;
    public ReadFromInterface(String name, int state, Process father, ResourcePlaner resourcePlaner) {
        super(name, state, father,resourcePlaner);
        this.ProcessNeedsResource(resourcePlaner.findResource("InputOutput"));
        this.resourcePlaner = resourcePlaner;
    }

    public void work(ProcessPlaner processPlaner){
        this.processPlaner = processPlaner;
        while(true) {
            //if (this.ProcessHasAllResource(this)) {
                this.ProcessNeedsResource(resourcePlaner.findResource("Supervizorinės atminties"));
                if (this.ProcessHasAllResource(this)) {
                    // if we get supervision memory we put everything from file to it.
                    try (BufferedReader br = new BufferedReader(new FileReader("code.txt"))) {
                        String line = br.readLine();
                        int i = 0;
                        while (line != null) {
                            getRm().getSupervision().put_block(i, line);
                            i++;
                            line = br.readLine();
                        }
                    }
                    catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (WrongContentSize wrongContentSize) {
                        wrongContentSize.printStackTrace();
                    }
                    // We check if resource was used and release it if it is taken.
                    if (this.hasItTaken(resourcePlaner.findResource("Užduotis supervizorinėje atminyje"))) {
                        this.removeResourcesAfterUsingIt(resourcePlaner.findResource("Užduotis supervizorinėje atminyje"));
                        // returns true if remove was successful and false if not
                    }
                    //if (this.ProcessHasAllResource(this)) {
                        this.releaseAllResource();
                        this.ProcessNeedsResource(resourcePlaner.findResource("InputOutput"));
                        this.changeState(3); // blocked stopped
                        processPlaner.RemovingProcessesFromList(this);
                        processPlaner.addProcessToList(this);
                        processPlaner.IsThereAnyReadyProcess();
                    //}
                }

            //}
            else{
                System.out.println("Something is wrong ReadFromInterface should not have gotten processor");
            }
        }
    }
}
