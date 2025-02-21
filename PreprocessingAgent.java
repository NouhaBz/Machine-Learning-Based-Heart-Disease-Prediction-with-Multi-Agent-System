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
import java.io.InputStreamReader;

/**
 *
 * @author dz
 */
public class PreprocessingAgent extends Agent  {
     @Override
    protected void setup() {
        System.out.println("PreprocessingAgent starts .............");
        addBehaviour(new CyclicBehaviour(this) {
            @Override
            public void action() {
                ACLMessage msg = receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
                if (msg != null && msg.getPerformative()==ACLMessage.INFORM) {
                    try {
                        String [] pb = {"C:\\Users\\dz\\AppData\\Local\\Programs\\Python\\Python312\\python.exe",
                                  "C:\\Users\\dz\\OneDrive\\Desktop\\m2\\taia\\tp\\TP3_1\\src\\tp3_1\\ML2.py",
                                 "preprocess"};
                        Process process = Runtime.getRuntime().exec(pb);
                        
                        
                         BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                        }
                        process.waitFor();

                        // Notify TrainingAgent
                        ACLMessage response = new ACLMessage(ACLMessage.INFORM);
                        response.addReceiver(getAID("TrainingAgent"));
                        response.setContent("Data preprocessed");
                        send(response);
                        System.out.println("Data preprocessing done and message sent to TrainingAgent.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    block();
                }
            }
        });
    }
}
