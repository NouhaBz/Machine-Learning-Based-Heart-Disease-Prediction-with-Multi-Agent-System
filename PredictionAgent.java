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
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author dz
 */

public class PredictionAgent extends Agent {
    public static BufferedReader pre;
    @Override
    protected void setup() {
        System.out.println("preproceccing befor1 : ");
        predect(HeartDiseaseForm.features);
      
    }
    
    private void predect(String featuers){
        System.out.println("preproceccing befor : ");
          addBehaviour(new CyclicBehaviour(this) {
            @Override
            public void action() {
                ACLMessage msg = receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
                if (msg != null && msg.getPerformative()==ACLMessage.INFORM) {
                    try {
                        System.out.println("preproceccing after : ");
                        
                        String features = "45,1,3,130,200,0,1,150,0,1.2,2,0,2";  // Example feature string
            
            // Create the argument array for the process
            String[] pb = {
                "C:\\Users\\dz\\AppData\\Local\\Programs\\Python\\Python312\\python.exe", // Python executable
                            "C:\\Users\\dz\\OneDrive\\Desktop\\m2\\taia\\tp\\TP3_1\\src\\tp3_1\\ML2.py", // Python script
                            "predict", // Argument for the script
                            "45,1,3,130,200,0,1,150,0,1.2,2,0,2"
            };

            // Start the process
            Process process = Runtime.getRuntime().exec(pb);

            // Capture output (stdout)
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Output: " + line);  // This prints what the Python script outputs
            }

            // Capture error output (stderr)
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String errorLine;
            while ((errorLine = errorReader.readLine()) != null) {
                System.out.println("Error: " + errorLine);  // This prints any errors
            }

            // Wait for the process to finish and get the exit code
            int exitCode = process.waitFor();
            System.out.println("Exit code: " + exitCode);
            //HeartDiseaseForm.showPredictionDialog();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    block();
                }
            }
        });
    }
    
    
  private void getfeateurs(){
      addBehaviour(new CyclicBehaviour(this) {
          @Override
          public void action() {
              System.out.println("preproceccing befor : ");
                Object obj = myAgent.getO2AObject();
                
                  if (obj != null){
                    String features =(String) obj ;
                    System.out.println("preproceccing befor : "+features);
                    predect(features);
                    
                  }else{
                     
                      block();
                  }
          }
          
          
      });
  }
    
}
