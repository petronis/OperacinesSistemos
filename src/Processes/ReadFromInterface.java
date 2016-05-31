package Processes;

import Exceptions.WrongAddress;
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
    static int counterOfLines = 0;
    public ReadFromInterface(String name, int state, Process father, ResourcePlaner resourcePlaner) {
        super(name, state, father,resourcePlaner);
        this.ProcessNeedsResource(resourcePlaner.findResource("InputOutput"));
        this.resourcePlaner = resourcePlaner;
    }

    public void work(ProcessPlaner processPlaner){
        this.processPlaner = processPlaner;
        System.out.println("Read From Interface is working now");
        this.ProcessNeedsResource(resourcePlaner.findResource("Supervizorinės atminties"));
        if (this.ProcessHasAllResource(this)) {
            try (BufferedReader br = new BufferedReader(new FileReader(processPlaner.readingFileName))) {
                System.out.println("Reading from file "+processPlaner.readingFileName);
                String line = br.readLine();
                int i = 0;
                //if(counterOfLines == 0) {
                    while (line != null) {
                        getRm().getSupervision().put_block(i, line);
                        i++;
                        counterOfLines++;
                        line = br.readLine();
                    }
                    System.out.println("SIZE OF " + getRm().getSupervision().getSize());
//                new java.util.Scanner(System.in).nextLine();
                /*}else{
                    int tmp = counterOfLines;
                    counterOfLines = 0;
                    while (line != null) {
                        getRm().getSupervision().put_block(i+tmp, line);
                        i++;
                        counterOfLines++;
                        line = br.readLine();
                    }
                    counterOfLines = tmp + counterOfLines;
                }*/
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (WrongContentSize wrongContentSize) {
                wrongContentSize.printStackTrace();
            }
            resourcePlaner.findResource("Supervizorinės atminties").setFree(true);
            resourcePlaner.findResource("Užduoties duomenys supervizorinėje atmintyje").setFree(true);
            this.changeState(3); // blocked stopped
            processPlaner.RemovingProcessesFromList(this);
            processPlaner.AddingProcessesToWaitingList(this);
            //processPlaner.IsThereAnyReadyProcess();
        }
        else{
            System.out.println("Something is wrong ReadFromInterface should not have gotten processor");
        }

    }
}
