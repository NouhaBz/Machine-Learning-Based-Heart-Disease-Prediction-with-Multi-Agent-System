/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp3_1;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.BufferedReader;
//import jade.core.Runtime;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author dz
 */
public class DataCollectionAgent extends Agent{
    
    @Override
    protected void setup() {
        System.out.println("DataCollectionAgent starts .............");
        addBehaviour(new OneShotBehaviour(this) {
            @Override
            public void action() {
                  try {
                       // Command to run the Python script
                    String[] pb = {
                            "C:\\Users\\dz\\AppData\\Local\\Programs\\Python\\Python312\\python.exe", // Python executable
                            "C:\\Users\\dz\\OneDrive\\Desktop\\m2\\taia\\tp\\TP3_1\\src\\tp3_1\\ML2.py", // Python script
                            "load_data", // Argument for the script
                            "C:\\Users\\dz\\OneDrive\\Desktop\\m2\\taia\\tp\\heart_disease_data.csv" // CSV path
                    };

                    // Execute the command
                    Process process = Runtime.getRuntime().exec(pb);

                    // Capture the output of the script
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line); // Print output of the Python script
                    }

                    // Capture any errors
                    BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                    while ((line = errorReader.readLine()) != null) {
                        System.err.println(line); // Print error output
                    }

                    // Wait for the process to finish
                    int exitCode = process.waitFor();
                    //System.out.println("Python process finished with exit code: " + exitCode);

                    // Notify PreprocessingAgent once data is loaded
                    ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                    msg.addReceiver(getAID("PreprocessingAgent"));
                    msg.setContent("Data loaded");
                    send(msg);
                    //System.out.println("Data loaded and message sent to PreprocessingAgent.");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
}
