# Spring Mail Sender

This is a Spring Boot application for managing and sending emails to receivers. The application allows you to upload receiver details from an Excel file, manage receiver information, and send emails using predefined templates.

## Features

- Upload receiver details from an Excel file
- Manage receiver information (create, update, delete, find)
- Send emails to receivers using predefined templates

## Technologies Used

- Java 17
- Spring Boot 3.3.5
- Spring Data JPA
- Spring Mail
- Apache POI
- PostgreSQL
- Maven

## Getting Started

### Prerequisites

- Java 17
- Maven
- PostgreSQL

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/your-username/spring-mail-sender.git
    cd spring-mail-sender
    ```

2. Configure the database:
    - Update the `application.properties` file with your PostgreSQL database credentials.

3. Build the project:
    ```sh
    mvn clean install
    ```

4. Run the application:
    ```sh
    mvn spring-boot:run
    ```

## API Endpoints

### Receiver Controller

- **Upload Receivers from Excel**
    - `POST /api/receivers/upload`
    - Request Param: `file` (MultipartFile)

- **Create Receiver**
    - `POST /api/receivers`
    - Request Body: `CreateReceiverRequest`

- **Update Receiver**
    - `PUT /api/receivers/{id}`
    - Request Body: `CreateReceiverRequest`

- **Delete Receiver**
    - `DELETE /api/receivers/{id}`

- **Find Receiver by ID**
    - `GET /api/receivers/{id}`

- **Find All Receivers**
    - `GET /api/receivers`

### Mail Controller

- **Send Mail**
    - `POST /api/mail/send`
    - Request Body: `SendMailRequest`

## License

This project is licensed under the MIT License.