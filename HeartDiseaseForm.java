package tp3_1;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HeartDiseaseForm extends JFrame {
    public static String features;
    // Declare the labels
    private JLabel jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9, jLabel10, jLabel11, jLabel12, jLabel13, jLabelAge, jLabelSex;
    
    // Declare the text fields
    private JTextField textField3, textField4, textField5, textField6, textField7, textField8, textField9, textField10, textField11, textField12, textField13, textFieldAge;
    
    // Declare the JComboBox for Sex
    private JComboBox<String> sexComboBox;
    
    // Declare the Predict button
    private JButton predictButton;

    // Constructor to set up the JFrame
    public HeartDiseaseForm() {
        // Set the title of the JFrame
        setTitle("Heart Disease Information");

        // Set the layout to null to use absolute positioning
        setLayout(null); // Disable the default layout manager

        // Initialize the labels with the text values
        jLabel3 = new JLabel("cp");
        jLabel4 = new JLabel("trestbps");
        jLabel5 = new JLabel("chol");
        jLabel6 = new JLabel("fbs");
        jLabel7 = new JLabel("restecg");
        jLabel8 = new JLabel("thalach");
        jLabel9 = new JLabel("exang");
        jLabel10 = new JLabel("oldpeak");
        jLabel11 = new JLabel("slope");
        jLabel12 = new JLabel("ca");
        jLabel13 = new JLabel("thal");
        jLabelAge = new JLabel("Age");
        jLabelSex = new JLabel("Sex");

        // Initialize the text fields
        textField3 = new JTextField();
        textField4 = new JTextField();
        textField5 = new JTextField();
        textField6 = new JTextField();
        textField7 = new JTextField();
        textField8 = new JTextField();
        textField9 = new JTextField();
        textField10 = new JTextField();
        textField11 = new JTextField();
        textField12 = new JTextField();
        textField13 = new JTextField();
        textFieldAge = new JTextField();

        // Initialize the JComboBox for sex with options "Male" and "Female"
        sexComboBox = new JComboBox<>(new String[] {"Male", "Female"});

        // Initialize the Predict button
        predictButton = new JButton("Predict");

        // Set the bounds for each label and text field
        jLabelAge.setBounds(20, 20, 100, 25);
        textFieldAge.setBounds(120, 20, 150, 25);

        jLabelSex.setBounds(20, 60, 100, 25);
        sexComboBox.setBounds(120, 60, 150, 25);

        jLabel3.setBounds(20, 100, 100, 25);
        textField3.setBounds(120, 100, 150, 25);

        jLabel4.setBounds(20, 140, 100, 25);
        textField4.setBounds(120, 140, 150, 25);

        jLabel5.setBounds(20, 180, 100, 25);
        textField5.setBounds(120, 180, 150, 25);

        jLabel6.setBounds(20, 220, 100, 25);
        textField6.setBounds(120, 220, 150, 25);

        jLabel7.setBounds(20, 260, 100, 25);
        textField7.setBounds(120, 260, 150, 25);

        jLabel8.setBounds(20, 300, 100, 25);
        textField8.setBounds(120, 300, 150, 25);

        jLabel9.setBounds(20, 340, 100, 25);
        textField9.setBounds(120, 340, 150, 25);

        jLabel10.setBounds(20, 380, 100, 25);
        textField10.setBounds(120, 380, 150, 25);

        jLabel11.setBounds(20, 420, 100, 25);
        textField11.setBounds(120, 420, 150, 25);

        jLabel12.setBounds(20, 460, 100, 25);
        textField12.setBounds(120, 460, 150, 25);

        jLabel13.setBounds(20, 500, 100, 25);
        textField13.setBounds(120, 500, 150, 25);

        // Set the bounds for the Predict button
        predictButton.setBounds(120, 540, 150, 30);

        // Add the labels, text fields, combo box, and button to the JFrame
        add(jLabelAge);
        add(textFieldAge);
        add(jLabelSex);
        add(sexComboBox);
        add(jLabel3);
        add(textField3);
        add(jLabel4);
        add(textField4);
        add(jLabel5);
        add(textField5);
        add(jLabel6);
        add(textField6);
        add(jLabel7);
        add(textField7);
        add(jLabel8);
        add(textField8);
        add(jLabel9);
        add(textField9);
        add(jLabel10);
        add(textField10);
        add(jLabel11);
        add(textField11);
        add(jLabel12);
        add(textField12);
        add(jLabel13);
        add(textField13);
        add(predictButton);

        // Action listener for the Predict button
        predictButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the values from each text field
                String age = textFieldAge.getText();
                String sex = (String) sexComboBox.getSelectedItem();
                String cp = textField3.getText();
                String trestbps = textField4.getText();
                String chol = textField5.getText();
                String fbs = textField6.getText();
                String restecg = textField7.getText();
                String thalach = textField8.getText();
                String exang = textField9.getText();
                String oldpeak = textField10.getText();
                String slope = textField11.getText();
                String ca = textField12.getText();
                String thal = textField13.getText();

                // Handle male/female logic
                int sexValue = sex.equals("Male") ? 0 : 1; // Male = 0, Female = 1

                // Concatenate the values into a single string
                String features = "Age: " + age + ", Sex: " + sex + ", cp: " + cp + ", trestbps: " + trestbps + ", chol: " + chol +
                        ", fbs: " + fbs + ", restecg: " + restecg + ", thalach: " + thalach + ", exang: " + exang + ", oldpeak: " + oldpeak +
                        ", slope: " + slope + ", ca: " + ca + ", thal: " + thal;

                // Print the values (this could be used to feed into a model for prediction)
               

                // Print the values (this could be used to feed into a model for prediction)
                System.out.println("Features: " + features);
                String  feateur =  age + "," + sexValue + ","+cp +","+trestbps+"," +chol+"," +fbs+"," +  restecg+"," + thalach+"," + exang+"," +  oldpeak+"," +slope+"," + ca+"," + thal;
                System.out.println("Features: " + feateur);
                
                try {
                    start(feateur);
                } catch (StaleProxyException ex) {
                    Logger.getLogger(HeartDiseaseForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                
               
            }
        });

        // Set the default close operation and window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 700); // Set a fixed size for the window
        setLocationRelativeTo(null);  // Center the window
        setVisible(true);
    }
    
    private void start(String feateur) throws StaleProxyException{
         jade.core.Runtime runtime = jade.core.Runtime.instance();
            
            // Create the profile for the main container
            Profile profile = new ProfileImpl();
            profile.setParameter(Profile.MAIN_HOST, "localhost");
            profile.setParameter(Profile.MAIN_PORT, "1099");

            // Create the main container
            AgentContainer c = runtime.createMainContainer(profile);

            // Create and start the agents
            c.createNewAgent("DataLoadingAgent", DataCollectionAgent.class.getName(), null).start();
            c.createNewAgent("PreprocessingAgent", PreprocessingAgent.class.getName(), null).start();
            c.createNewAgent("TrainingAgent", TrainingAgent.class.getName(), null).start();
            c.createNewAgent("EvaluationAgent", ResultGenerationAgent.class.getName(), null).start();
            c.createNewAgent("PredictionAgent", PredictionAgent.class.getName(), null).start();
            
             
        try{
           AgentController discoveryAgent = c.getAgent("PredictionAgent");
           discoveryAgent.putO2AObject(feateur,AgentController.ASYNC );
           System.out.println("the feateurs was sent  ");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("filed to send");
        }
    }

    
     public static void showPredictionDialog() {
        try {
            
            // 3. Capture Prediction Output
            
            BufferedReader pre = PredictionAgent.pre;
            BufferedReader acc = ResultGenerationAgent.acc;
            
            String Pre;
            String Acc ;
            
            StringBuilder prB = new StringBuilder();
            StringBuilder accB = new StringBuilder();

            while ((Pre = pre.readLine()) != null) {
                prB.append(Pre).append("\n");
            }

            while ((Acc = acc.readLine()) != null) {
                accB.append(Acc).append("\n");
            }

           

            // 5. Display Prediction
            String prediction = pre.toString().trim();
            JOptionPane.showMessageDialog(null, 
                    "Prediction Result:\n" + prediction, 
                    "Prediction", JOptionPane.INFORMATION_MESSAGE);

            // 6. Display Accuracy (Hardcoded Example)
            
            String accurancy = acc.toString().trim(); 
            JOptionPane.showMessageDialog(null, 
                    "Model Accuracy:\n" + accurancy  , 
                    "Model Accuracy", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, 
                    "An unexpected error occurred:\n" + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
     //Main method to create and display the JFrame
    public static void main(String[] args) {
        HeartDiseaseForm f =new HeartDiseaseForm();
    }
}






 