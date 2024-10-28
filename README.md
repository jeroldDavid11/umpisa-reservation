
# Customer Reservation System

This is a Spring Boot application for managing customer reservations. It allows customers to create, update, view, and delete reservations. The system includes notifications and reminders for reservation status updates, which are simulated by printing messages to the console.

## Features

1. **Create a new reservation**: Submit name, phone, email, date, time, and number of guests.
2. **Cancel a reservation**: Cancel a reservation using its ID.
3. **View reservations**: List all upcoming reservations for a customer.
4. **Update a reservation**: Update the time and number of guests for an existing reservation.
5. **Notifications**: Simulates sending notifications (printed to console) for reservation creation, updates, and cancellation.
6. **Reminders**: Sends reminders 4 hours before the reservation time.

## Technologies Used

- **Java Version**: 17
- **Spring Boot Version**: 2.5+
- **Database**: H2 (in-memory)
- **Build System**: Maven
- **Testing**: JUnit 5 and Mockito
- **Scheduling**: Spring’s scheduling feature for reminders

## Getting Started

### Prerequisites

- Java 17+
- Maven

### Setup Instructions

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/yourusername/reservation-system.git
   cd reservation-system
   ```

2. **Build the Project**:

   ```bash
   mvn clean install
   ```

3. **Run the Application**:

   ```bash
   mvn spring-boot:run
   ```

4. **Access the H2 Console** (for direct database inspection if needed):

   - URL: `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:testdb`
   - Username: `sa`
   - Password: (leave blank)

## API Endpoints

### 1. Create a New Reservation

- **Endpoint**: `POST /reservations`
- **Request**:

   ```json
   {
     "name": "John Doe",
     "phone": "123456789",
     "email": "john@example.com",
     "reservationDateTime": "2024-12-01T19:30:00",
     "numberOfGuests": 4
   }
   ```

- **Response**:

   ```json
   {
     "id": 1,
     "name": "John Doe",
     "phone": "123456789",
     "email": "john@example.com",
     "reservationDateTime": "2024-12-01T19:30:00",
     "numberOfGuests": 4
   }
   ```

- **Notification** (console output):

   ```
   Sent SMS: Reservation Created for Reservation ID: 1
   Sent EMAIL: Reservation Created for Reservation ID: 1
   ```

### 2. View Reservations

- **Endpoint**: `GET /reservations`
- **Request**: Provide email as a query parameter.

   Example:
   ```bash
   GET /reservations?email=john@example.com
   ```

- **Response**: List of upcoming reservations.

### 3. Update a Reservation

- **Endpoint**: `PUT /reservations/{id}`
- **Request**:

   ```json
   {
     "reservationDateTime": "2024-12-02T20:00:00",
     "numberOfGuests": 5
   }
   ```

- **Response**:

   ```json
   {
     "id": 1,
     "name": "John Doe",
     "phone": "123456789",
     "email": "john@example.com",
     "reservationDateTime": "2024-12-02T20:00:00",
     "numberOfGuests": 5
   }
   ```

- **Notification** (console output):

   ```
   Sent SMS: Reservation Updated for Reservation ID: 1
   Sent EMAIL: Reservation Updated for Reservation ID: 1
   ```

### 4. Cancel a Reservation

- **Endpoint**: `DELETE /reservations/{id}`
- **Response**: HTTP 204 No Content if successful.

- **Notification** (console output):

   ```
   Sent SMS: Reservation Canceled for Reservation ID: 1
   Sent EMAIL: Reservation Canceled for Reservation ID: 1
   ```

## Reminder System

The system automatically sends reminders 4 hours before the reservation time. These reminders are printed to the console.

- **Example Reminder** (console output):

   ```
   Sent SMS: Reminder for your reservation in 4 hours. Reservation ID: 1
   Sent EMAIL: Reminder for your reservation in 4 hours. Reservation ID: 1
   ```

The reminder system runs automatically every hour. To customize this, modify the scheduled interval in the `ReservationReminderScheduler` class.

## Testing

Unit tests are located in the `src/test/java` directory. To run the tests:

```bash
mvn test
```
