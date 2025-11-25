📌 Finance Tracker – Spring Boot Web Application

A simple and user-friendly personal finance management web application built with:

Spring Boot
Spring Data JPA
Thymeleaf
H2 Database
Bootstrap 5

This project allows users to track income, expenses, balance, and view transactions in a clean dashboard UI.

🚀 Features
✅ Dashboard Overview

Total Income
Total Expense
Current Balance

✅ Manage Income

Add Income
View All Income
Delete Income

✅ Manage Expenses

Add Expense
View All Expenses
Delete Expense

✅ Transactions Page

Combined view of all income & expenses
Clean table layout
Category/Source display

✅ Tech Features

MVC Architecture
JPA + Hibernate
H2 Console Enabled
Bootstrap 5 UI
Persistent file-based H2 database for deployment

🛠️ Technologies Used

Technology	          Purpose
Spring Boot	          Backend framework
Spring Data JPA	      Database ORM
Thymeleaf	            Server-side templating
H2 Database	          Lightweight DB
Bootstrap 5	          UI styling
Java 17	              Programming language

📁 Project Structure

finance-tracker/
│
├── src/main/java/com/example/finance_tracker/
│   ├── controller/     # Controllers (UI + REST)
│   ├── model/          # Entity classes
│   ├── service/        # Interfaces
│   ├── service/impl/   # Service implementations
│   └── repository/     # JPA Repositories
│
├── src/main/resources/
│   ├── templates/      # Thymeleaf HTML files
│   ├── static/         # CSS/JS/images
│   └── application.properties
│
└── pom.xml             # Maven dependencies

⚙️ Installation & Setup
1️⃣ Clone the repository
git clone https://github.com/<your-username>/finance-tracker.git
cd finance-tracker

2️⃣ Run the project

Windows:

  .\mvnw.cmd spring-boot:run

Linux/Mac:

  ./mvnw spring-boot:run

3️⃣ Open in browser
  http://localhost:8080/dashboard

🗄️ H2 Database Console

After running the project, open:

  http://localhost:8080/h2-console

Use these settings:

  Property	Value
  JDBC URL	jdbc:h2:./data/financedb
  Username	sa
  Password	(empty)
  
🌐 Deployment (Render)
Build Command:
  ./mvnw clean package

Start Command:
  java -jar target/finance-tracker-0.0.1-SNAPSHOT.jar

Persistence-ready properties:
  spring.datasource.url=jdbc:h2:file:./data/financedb
  spring.datasource.username=sa
  spring.datasource.password=
  spring.jpa.hibernate.ddl-auto=update
  spring.h2.console.enabled=true
  spring.thymeleaf.cache=false

📝 Future Enhancements

User Login (Spring Security)
Expense Categories Dropdown
Graphs (Pie chart for expenses, bar chart for income)
Export to PDF/Excel
Monthly filtering

🤝 Contributing

Pull requests and suggestions are welcome!

🧑‍💻 Author

Aayush Yadav
📍 Indore, India
GitHub: aayuyadav02
LinkedIn: aayuyadav02
