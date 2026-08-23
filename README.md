# 🛡️ FraudShield AI

### AI-Powered Financial Fraud Detection System

FraudShield AI is a web-based financial fraud detection platform designed to identify potentially fraudulent transactions using machine learning.

The system combines a modern React frontend, a Spring Boot backend, and a dedicated FastAPI-based machine learning service. It provides transaction analysis, fraud predictions, risk classification, alerts, analytics, and transaction management through an interactive dashboard.

---

## 🚀 Key Features

* 🔐 User authentication and authorization
* 👤 Separate user and administrator functionality
* 💳 Financial transaction management
* 🤖 Machine-learning-based fraud detection
* 📊 Fraud probability and confidence analysis
* ⚠️ Risk-level classification
* 🚨 Fraud alerts and prediction monitoring
* 📈 Transaction and risk analytics
* 🔎 Transaction history and detailed transaction views
* 💰 Money transfer simulation
* 🧪 Fraud transaction simulator
* 💳 Razorpay Test API integration
* 📄 Transaction receipt generation
* 📥 CSV transaction export
* 🐳 Docker support for the ML service
* 🧪 Automated tests for the ML service

---

## 🏗️ System Architecture

```text
                         ┌─────────────────────────┐
                         │       React Frontend    │
                         │     React + Tailwind    │
                         └────────────┬────────────┘
                                      │
                                      │ REST API
                                      ▼
                         ┌─────────────────────────┐
                         │     Spring Boot API     │
                         │      Backend Service    │
                         └────────────┬────────────┘
                                      │
                       ┌──────────────┴──────────────┐
                       │                             │
                       ▼                             ▼
             ┌──────────────────┐         ┌──────────────────┐
             │     MongoDB      │         │   FastAPI ML     │
             │     Database     │         │     Service      │
             └──────────────────┘         └────────┬─────────┘
                                                   │
                                                   ▼
                                      ┌────────────────────────┐
                                      │  Trained ML Pipeline   │
                                      │  XGBoost Classifier    │
                                      └────────────────────────┘
```

---

## 🧠 Machine Learning

The machine learning service is implemented using **FastAPI** and serves a trained fraud-detection pipeline.

The saved model is loaded using `joblib` and validated when the ML service starts.

The pipeline contains:

* Feature preprocessing
* Categorical feature encoding
* One-hot encoding
* Fraud classification
* Probability prediction

The model service exposes prediction functionality through an API.

### Prediction Flow

```text
Transaction Data
       │
       ▼
Feature Engineering
       │
       ▼
Preprocessing
       │
       ▼
One-Hot Encoding
       │
       ▼
XGBoost Classifier
       │
       ├───────────────┐
       ▼               ▼
 Fraud Prediction   Fraud Probability
       │               │
       └───────┬───────┘
               ▼
        Prediction Response
```

---

## 🛠️ Tech Stack

### Frontend

* React
* JavaScript
* Tailwind CSS
* Vite
* Axios
* React Router

### Backend

* Java
* Spring Boot
* Spring Data MongoDB
* Maven
* REST APIs

### Machine Learning Service

* Python
* FastAPI
* Pandas
* Scikit-learn
* XGBoost
* Joblib

### Database

* MongoDB

### Payment Integration

* Razorpay Test API

### Development & Deployment

* Git
* GitHub
* Docker
* Docker Compose

---

## 📁 Project Structure

```text
FraudShield-AI/
│
├── backend/
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       └── resources/
│   ├── pom.xml
│   ├── README.md
│   └── TESTING.md
│
├── frontend/
│   ├── public/
│   ├── src/
│   │   ├── components/
│   │   ├── context/
│   │   ├── hooks/
│   │   ├── layouts/
│   │   ├── pages/
│   │   ├── routes/
│   │   ├── services/
│   │   └── utils/
│   ├── package.json
│   └── vite.config.js
│
├── ml-service/
│   ├── app/
│   │   ├── api/
│   │   ├── config/
│   │   ├── core/
│   │   ├── models/
│   │   ├── services/
│   │   └── utils/
│   ├── scripts/
│   ├── tests/
│   ├── Dockerfile
│   ├── docker-compose.yml
│   ├── requirements.txt
│   └── online_payment_fraud_detection_model.pkl
│
├── .gitignore
└── README.md
```

---

## 🔄 Application Workflow

1. User logs into the FraudShield AI platform.
2. User submits or generates a financial transaction.
3. The React frontend sends the transaction to the Spring Boot backend.
4. The backend communicates with the FastAPI machine learning service.
5. The ML service performs feature engineering and preprocessing.
6. The trained model generates a fraud prediction and probability.
7. The result is returned to the backend.
8. The backend processes and stores the relevant transaction/prediction information.
9. The frontend displays the result using risk indicators, charts, alerts, and transaction details.

---

## ⚙️ Installation & Setup

### Prerequisites

Make sure you have installed:

* Node.js
* npm
* Java
* Maven
* Python 3.x
* MongoDB
* Git

Docker can be used for the ML service if preferred.

---

### 1. Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/FraudShield-AI.git

cd FraudShield-AI
```

---

### 2. Configure the Backend

Navigate to the backend:

```bash
cd backend
```

Create your local environment configuration based on:

```text
.env.example
```

Then build the Spring Boot application:

```bash
mvn clean install
```

Run the backend:

```bash
mvn spring-boot:run
```

---

### 3. Configure the Frontend

Open another terminal:

```bash
cd frontend
```

Install dependencies:

```bash
npm install
```

Create your `.env` file using:

```text
.env.example
```

Then start the development server:

```bash
npm run dev
```

---

### 4. Configure the ML Service

Open another terminal:

```bash
cd ml-service
```

Create and activate a virtual environment:

#### Windows

```bash
python -m venv venv
venv\Scripts\activate
```

#### Linux/macOS

```bash
python3 -m venv venv
source venv/bin/activate
```

Install dependencies:

```bash
pip install -r requirements.txt
```

Create your `.env` file using:

```text
.env.example
```

Start the FastAPI service:

```bash
uvicorn app.main:app --reload
```

---

## 🐳 Running the ML Service with Docker

The ML service also contains a `Dockerfile` and `docker-compose.yml`.

From the ML service directory:

```bash
docker compose up --build
```

To stop the service:

```bash
docker compose down
```

---

## 🔑 Environment Variables

Environment-specific configuration should **not** be committed to GitHub.

Use the provided `.env.example` files as templates.

Typical configuration may include:

```env
MODEL_PATH=online_payment_fraud_detection_model.pkl
MODEL_VERSION=xgboost_v1
```

Additional backend/frontend configuration should be added to the corresponding `.env` files locally.

> ⚠️ Never commit API keys, database credentials, JWT secrets, or other sensitive information.

---

## 🧪 Testing

The ML service includes automated tests covering areas such as:

* Health checks
* Feature engineering
* Prediction functionality
* Structural verification of the ML pipeline

Run the tests from the `ml-service` directory:

```bash
pytest
```

---

## 📊 Dashboard

The platform provides different views for monitoring and managing financial transactions.

### Admin Dashboard

Administrators can access:

* Analytics
* Predictions
* Alerts
* Users
* Transaction information
* Risk distribution

### User Dashboard

Users can access:

* Transaction history
* Fraud simulation
* Money transfers
* Profile management
* Transaction details

---

## 🔐 Security Considerations

FraudShield AI separates sensitive configuration from source code using environment variables.

The repository intentionally excludes:

* `.env` files
* Virtual environments
* `node_modules`
* Maven build artifacts
* IDE-generated files
* Runtime logs

Only `.env.example` files are included to demonstrate the required configuration structure.

---

## 🔮 Future Improvements

Potential improvements include:

* Real-time transaction stream processing
* Model retraining pipeline
* Advanced anomaly detection
* Model performance monitoring
* Explainable AI for individual predictions
* Additional fraud detection algorithms
* Cloud deployment
* Container orchestration
* Improved model version management
* Real-world payment gateway integration

---

## 👨‍💻 Contributors

**FraudShield AI Team**

Developed as an academic project focused on combining full-stack web development with machine learning for financial fraud detection.

---

## 📄 License

This project is intended for educational and demonstration purposes.

Add an appropriate open-source license here if you decide to distribute the project under one.
