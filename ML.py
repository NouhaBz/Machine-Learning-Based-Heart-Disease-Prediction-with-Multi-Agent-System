import sys
import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import LabelEncoder
from sklearn.metrics import accuracy_score, classification_report
from sklearn.linear_model import LogisticRegression
from sklearn.impute import SimpleImputer
import joblib

print(sys.executable)
def load_data(path):
    data = pd.read_csv(path)
    print("Data loading done.")
    data.to_csv("data_loaded.csv", index=False)

def preprocess_data():
    data = pd.read_csv("data_loaded.csv")
    data.columns = data.columns.str.strip()
    data.drop_duplicates(inplace=True)
    data.replace([np.inf, -np.inf], np.nan, inplace=True)

    imputer = SimpleImputer(strategy='mean')
    data_imputed = imputer.fit_transform(data.select_dtypes(include=np.number))
    data.iloc[:, :-1] = data_imputed

    if 'Label' in data.columns:
        encoder = LabelEncoder()
        data['Label'] = encoder.fit_transform(data['Label'].apply(lambda x: 'Attack' if x != 'BENIGN' else 'BENIGN'))
        joblib.dump(encoder, "label_encoder.pkl")
    else:
        raise KeyError("'Label' column not found.")

    data.to_csv("data_preprocessed.csv", index=False)
    print("Data preprocessing complete.")

def train_model():
    data = pd.read_csv("data_preprocessed.csv")
    X = data.drop('Label', axis=1)
    y = data['Label']

    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.25, random_state=0)
    lr = LogisticRegression(max_iter=10000, C=0.1, random_state=0, solver='saga')
    lr.fit(X_train, y_train)

    joblib.dump(lr, "model.pkl")
    X_test.to_csv("X_test.csv", index=False)
    y_test.to_csv("y_test.csv", index=False)
    print("Model training complete.")

def evaluate_accuracy():
    model = joblib.load("model.pkl")
    X_test = pd.read_csv("X_test.csv")
    y_test = pd.read_csv("y_test.csv")['Label']

    y_pred = model.predict(X_test)
    accuracy = accuracy_score(y_test, y_pred)
    print(f"Accuracy: {accuracy:.2f}")
    print(classification_report(y_test, y_pred))

def predict_with_saved_model():
    model = joblib.load("model.pkl")
    X_test = pd.read_csv("X_test.csv")
    y_pred = model.predict(X_test)
    print(f"Predictions: {y_pred}")

if __name__ == "__main__":
    task = sys.argv[1]
    if task == "load_data":
        load_data(sys.argv[2])
    elif task == "preprocess":
        preprocess_data()
    elif task == "train":
        train_model()
    elif task == "evaluate":
        evaluate_accuracy()
    elif task == "predict":
        predict_with_saved_model()
    else:
        print("Unknown task.")


'''

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

'''