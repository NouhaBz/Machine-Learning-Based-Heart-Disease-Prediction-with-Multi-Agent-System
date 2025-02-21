/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp3_1;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;

/**
 *
 * @author dz
 */
public class ResultGenerationAgent extends Agent {
    public static BufferedReader acc;
    @Override
    protected void setup() {
        System.out.println("ResultGenerationAgent starts .............");
        addBehaviour(new CyclicBehaviour(this) {
            @Override
            public void action() {
               ACLMessage msg = receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
                if (msg != null && msg.getPerformative()==ACLMessage.INFORM) {
                    try {
                       
                        String [] pb = {"C:\\Users\\dz\\AppData\\Local\\Programs\\Python\\Python312\\python.exe",
                                  "C:\\Users\\dz\\OneDrive\\Desktop\\m2\\taia\\tp\\TP3_1\\src\\tp3_1\\ML2.py",
                                 "evaluate"};
                        Process process = Runtime.getRuntime().exec(pb);
                        
                        
                         BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                         acc =reader;
                        String line;
                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                        }
                        process.waitFor();
                        ACLMessage response = new ACLMessage(ACLMessage.INFORM);
                        response.addReceiver(getAID("PredictionAgent"));
                        response.setContent("Data preprocessed");
                        send(response);

                        // Output predictions
                       // System.out.println("Predictions complete. Check the console or output files for details.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    block(); // Wait for the message
                }
            }
        });
    }
}
