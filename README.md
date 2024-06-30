# Fitness Program Backend 

This project implements a backend for a fitness program using Java Spring, MySQL, and Gmail SMTP for email functionality.

## Setup
At first time you need to initialize the database with uncomment the following line in `application.properties` file:
```properties
      # data-locations: classpath:data-mysql.sql
```


### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Maven
- MySQL
- Gmail account for SMTP

### Installation

1. **Clone the repository:**

   ```bash
   git clone <repository_url>
   cd <repository_directory>
   ```

2. **Configure MySQL:**

   - Create a MySQL database for the project.
   - Update `application.properties` with your MySQL credentials:

     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/fitness_db
     spring.datasource.username=root
     spring.datasource.password=password
     ```

3. **Configure Gmail SMTP:**

   - Update `application.properties` with your Gmail SMTP settings:

     ```properties
     spring.mail.host=smtp.gmail.com
     spring.mail.port=587
     spring.mail.username=your_email@gmail.com
     spring.mail.password=your_password
     spring.mail.properties.mail.smtp.auth=true
     spring.mail.properties.mail.smtp.starttls.enable=true
     spring.mail.properties.mail.smtp.starttls.required=true
     ```

4. **Run the application:**

   ```bash
   mvn spring-boot:run
   ```

## Usage

Access the API at `http://localhost:8080/api/v1` after starting the application.

## Contributing

Feel free to contribute by opening issues or pull requests.

## License

This project is licensed under the MIT License - see the LICENSE file for details.
