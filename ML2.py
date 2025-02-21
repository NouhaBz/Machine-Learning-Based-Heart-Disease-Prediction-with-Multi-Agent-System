import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import accuracy_score, classification_report
import joblib
import sys


def load_and_preprocess_data(file_path):
    print("start.")
    data = pd.read_csv(file_path)
    print(data.isnull().sum()) 
    X = data.drop('target', axis=1)  
    y = data['target']  
    
    
    X.to_csv('X_data.csv', index=False)
    y.to_csv('y_data.csv', index=False)
    
    print("Data loaded and saved.")
    

def preprocess_data():
   
    X = pd.read_csv('X_data.csv')
    y = pd.read_csv('y_data.csv')
    
   
    scaler = StandardScaler()
    X_scaled = scaler.fit_transform(X)
    
    
    pd.DataFrame(X_scaled, columns=X.columns).to_csv('X_scaled.csv', index=False)
    y.to_csv('y_scaled.csv', index=False)
    
    print("Data preprocessing complete.")


def train_lr_model():
   
    X_scaled = pd.read_csv('X_scaled.csv')
    y_scaled = pd.read_csv('y_scaled.csv')
    
    
    X_train, X_test, y_train, y_test = train_test_split(X_scaled, y_scaled, test_size=0.2, random_state=42)
    
    lr = LogisticRegression(max_iter=10000, random_state=42)
    
    lr.fit(X_train, y_train)
    
    joblib.dump(lr, 'lr_model.pkl')
    
    print("Model trained and saved.")

def evaluate_model():
    model = joblib.load('lr_model.pkl')
    X_test = pd.read_csv('X_scaled.csv')  
    y_test = pd.read_csv('y_scaled.csv')  
    
    y_pred = model.predict(X_test)
    
    accuracy = accuracy_score(y_test, y_pred)
    print(f"Accuracy: {accuracy:.2f}")
    
    print("Classification Report:")
    print(classification_report(y_test, y_pred))

def make_predictions(feature_string):
   # print("Received feature string:", feature_string)  
    model = joblib.load('lr_model.pkl')  
    feature_values = feature_string.split(',') 
    feature_names = ['age', 'sex', 'cp', 'trestbps', 'chol', 'fbs', 
                     'restecg', 'thalach', 'exang', 'oldpeak', 'slope', 'ca', 'thal']

    if len(feature_values) != len(feature_names):
        print(f"Error: Expected {len(feature_names)} features, but got {len(feature_values)}.")
        sys.exit(1)

   # print("Feature values before conversion:", feature_values)
    
    feature_values = [float(value) if i != 1 else 1 if value == "Female" else 0 for i, value in enumerate(feature_values)]
    
   # print("Feature values after conversion:", feature_values)
    
    input_data = pd.DataFrame([feature_values], columns=feature_names)
    
    prediction = model.predict(input_data)
    
    print("Prediction result:", prediction)

if __name__ == "__main__":
    task = sys.argv[1]
    print(task)
    if task == "load_data":
        load_and_preprocess_data(sys.argv[2])
    elif task == "preprocess":
        preprocess_data()
    elif task == "train":
        train_lr_model()
    elif task == "evaluate":
        evaluate_model()
    elif task == "predict":
            make_predictions(sys.argv[2])
    else:
        print("Unknown task.")
