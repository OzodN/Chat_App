💬 Chat_App — Console Chat Application (Java)

Chat_App is a simple console-based chat application built in Java.
The project demonstrates clean architecture, SOLID principles, OOP design,
JSON-based file storage, centralized logging, regex validation, and the use of
Time API, ResourceBundle, and File API.

NOTE: This project is educational made in order to learn backend development and practice clean code.

---

📁 Project Structure

```
src/
 ├── controller/
 │     └── LaunchChat.java
 │
 ├── data/
 │     ├── app.log
 │     ├── chatMessages.txt
 │     └── users.txt
 │
 ├── model/
 │     ├── Chat.java
 │     └── User.java
 │
 ├── repository/
 │     ├── ChatRepository.java
 │     ├── FileChatRepository.java
 │     ├── UserRepository.java
 │     └── FileUserRepository.java
 │
 ├── service/
 │     ├── ChatService.java
 │     └── UserService.java
 │
 ├── util/
 │     ├── ChatUtils.java
 │     ├── SingleLogger.java
 │     ├── UserUtils.java
 │     └── Validators.java
 │
 └── Main.java
```

---

✨ Features
🔐 Email Registration & Login

Users register and log in only using email.
Input validation is handled through regular expressions (regex).

📄 CSV-Based Persistent Storage

All data is stored in plain .txt files:

```
| File                    | Purpose                                        |
| ----------------------- | ---------------------------------------------- |
| `data/users.txt`        | stores user profiles (each line = CSV) |
| `data/chatMessages.txt` | stores chat messages with timestamps           |
| `data/app.log`          | application logs                               |

```

Each entry is saved as a single-line CSV:

```
user@example.com,1234,18:33:45
```

🕒 Time API (Java Time)

Used for:

 - user registration time,

 - message timestamping,

 - loading timestamps back into Java objects.

📝 Centralized Logging (Logging API)

A single shared logger (Singleton) logs all operations:

 - registrations

 - logins

 - message sending

 - failed validations

 - file operations

Logs are written to:

```
data/app.log
```

🧱 OOP + SOLID + Clean Architecture

 - SRP: each class has one responsibility

 - DIP: repositories accessed through interfaces

 - ISP: interfaces are small and role-specific

 - LSP: classes can be safely substituted

 - Clean layers: Controller → Service → Repository

---

🛠 Technologies Used

```
| Technology                          | Use                         |
| ----------------------------------- | --------------------------- |
| **Java 17+**                        | language                    |
| **File API**                        | read/write JSON text files  |
| **Logging API (java.util.logging)** | centralized logging         |
| **Regex API**                       | email/password validation   |
| **Time API (java.time)**            | timestamps                  |
| **Collections API**                 | in-memory storage & caching |
| **OOP / SOLID**                     | architecture structure      |

```

---

🚀 How to Run
1. Requirements

 - Java 17 or newer
 - IntelliJ IDEA / Eclipse / VS Code / CLI

2. Configure the logger

Open:

```
resources/logging.properties
```

Set an absolute path to app.log

Example (Windows):

```
java.util.logging.FileHandler.pattern = C:/Projects/Chat_App/src/data/app.log
```

Example (Linux/macOS):

```
java.util.logging.FileHandler.pattern = /home/user/Chat_App/src/data/app.log
```

3. Ensure the data/ folder contains:

```
app.log
users.txt
chatMessages.txt
```
(if not, the program will generate them)

4. Run the program
Through IntelliJ IDEA:
```
Run → Run 'Main'
```
Through terminal:
```
cd src
javac Main.java
java Main
```

---

🧪 Possible Future Improvements

 - Password hashing (BCrypt)

 - Switch from .txt storage → SQLite or PostgreSQL

 - Add multi-user chat via sockets (real-time)

 - Commands: /exit, /users, /history

 - Unit tests (JUnit)

 - GUI (JavaFX) or full web version (Spring Boot)
---
