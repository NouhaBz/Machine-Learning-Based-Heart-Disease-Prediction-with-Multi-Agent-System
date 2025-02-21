# Machine Learning-Based Heart Disease Prediction with Multi-Agent System

This project implements a **machine learning model** for heart disease prediction using Python and integrates a **multi-agent system (MAS) in Java (JADE)** to facilitate communication and result visualization.

## Features
- **Heart Disease Prediction**:
  - Trained machine learning model to predict heart disease based on medical parameters.
  - Uses supervised learning algorithms such as Logistic Regression, Random Forest, and Support Vector Machine (SVM).
- **Multi-Agent Communication**:
  - Python-based prediction results are transferred to Java agents for processing and display.
  - Agents coordinate tasks such as data preprocessing, model inference, and result handling.
- **Java-Based Result Visualization**:
  - Results from the Python model are processed by Java agents for real-time display.
  - Interactive UI to show predictions and insights.

## Technologies Used
- **Python** for model training and prediction.
- **Scikit-learn, Pandas, NumPy** for data processing and machine learning.
- **JADE (Java Agent DEvelopment Framework)** for agent-based communication.
- **Java Swing/JavaFX** for graphical result visualization.

## Installation & Usage
### Prerequisites
- Python (3.x)
- Java (JDK 8 or later)
- JADE Framework
- Required Python libraries: scikit-learn, pandas, numpy

### Steps to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/heart-disease-agent.git
   ```
2. Navigate to the project directory:
   ```bash
   cd heart-disease-agent
   ```
3. Install Python dependencies:
   ```bash
   pip install -r requirements.txt
   ```
4. Train or load the machine learning model:
   ```bash
   python train_model.py
   ```
5. Start the JADE platform:
   ```bash
   java -cp jade.jar:. jade.Boot -gui
   ```
6. Run the Java agent to receive and display results:
   ```bash
   java -cp jade.jar:. src.MainAgent
   ```

## Future Enhancements
- Real-time patient data integration
- Enhanced deep learning model for better accuracy
- Improved Java UI for better visualization

## License
This project is licensed under the MIT License.

## Contact
For any questions or contributions, please contact [your email or GitHub profile].

