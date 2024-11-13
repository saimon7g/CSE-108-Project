# CSE-108-Project
# Football Transfer Market System
A comprehensive JavaFX-based football transfer market simulation with real-time trading capabilities.

## System Features

### 1. Authentication System (`LogIn.java`)
- Club-based login system
- Premier League club credentials
- Password protection
- Interactive UI with Premier League logo

### 2. Home Dashboard (`HomePage.java`)
Features:
- Player search by multiple criteria
- Team statistics
- Transfer market access
- Club analytics
- Session management

### 3. Transfer Market System
#### Buy Interface (`BuyPlayer.java`)
- Available players listing
- Real-time price updates
- Purchase confirmation
- Market monitoring

#### Sell Interface (`SellPlayer.java`)
- Current roster management
- Price setting
- Sale listing
- Transaction tracking

### 4. Player Display (`ShowPlayer.java`)
Displays player attributes:
- Name, Country, Age
- Height, Position, Number
- Salary and Transfer Price
- Current Club
- Buy/Sell controls

## Technical Architecture

### Client Side

#### Components
1. **Client Module** (`Client.java`)
   - Manages server connection
   - Handles authentication
   - Controls application state

2. **Club Manager** (`Club.java`)
   - Player roster management
   - Transfer operations
   - Team statistics

3. **Player Model** (`Player.java`)
   - Player attributes
   - Performance metrics
   - Contract details

4. **Network Handler** (`ReadThreadClient.java`)
   - Asynchronous communication
   - Real-time updates
   - Market synchronization

### Server Side

1. **Server Core** (`Server.java`)
   - Client connection management
   - Transfer market operations
   - Data synchronization

2. **Data Persistence** (`FileOperation.java`)
   - Player data storage
   - File I/O operations
   - Data format: CSV structure

3. **Client Handler** (`ReadThreadServer.java`)
   - Individual client communication
   - Request processing
   - Market updates

### Network Protocol

#### Message System (`Message.java`)
```java
public class Message implements Serializable {
    private String instruction;  // Command type
    private String info;        // Additional data
    private Player player;      // Player information
}
```

#### Network Utility (`NetworkUtil.java`)
- Custom socket wrapper
- Object serialization
- Connection management

## User Interface (JavaFX)

### Windows
1. Login Screen
2. Home Page
3. Transfer Market
4. Player Statistics
5. Club Management

# Setup and Running Guide

## Prerequisites
1. Java Development Kit (JDK) 8 or higher
2. JavaFX SDK
3. Any Java IDE (IntelliJ IDEA, Eclipse, or NetBeans)
4. Git (optional, for cloning)

## Running the Application

### 1. Start the Server
1. Navigate to `Server.java`
2. Run the main method
3. Server will start on port 7777
4. You should see: "Server is running..."

### 2. Start the Client
1. Navigate to `Main.java`
2. Run the main method
3. The login window will appear

### 3. Login Credentials
Use these pre-configured club credentials:
```
Club: Arsenal           Password: arsenal
Club: Chelsea          Password: chelsea
Club: Liverpool        Password: liverpool
Club: Manchester United Password: manu
Club: Manchester City  Password: mancity
```

## Troubleshooting

### Common Issues and Solutions

1. **Connection Refused**
```
Solution:
- Ensure server is running
- Check if port 7777 is free
- Verify firewall settings
```

2. **JavaFX Runtime Components Missing**
```
Solution:
Add VM options in Run Configuration:
--module-path "[path-to-javafx-sdk]/lib" --add-modules javafx.controls,javafx.fxml
```

3. **File Not Found**
```
Solution:
- Verify 'in.txt' exists in project root
- Check file permissions
- Ensure correct file path in FileOperation.java
```

### Network Configuration
- Server runs on localhost (127.0.0.1)
- Port: 7777
- Ensure port is not blocked by firewall

## System Requirements

### Minimum Requirements
- Java 8 or higher
- 2GB RAM
- 100MB disk space
- Network connection

### Recommended
- Java 11 or higher
- 4GB RAM
- 500MB disk space
- Stable network connection

## Additional Notes

1. **Multiple Instances**
- Can run multiple clients simultaneously
- Single server instance only
- Each club can login once

2. **Data Persistence**
- Player data stored in `in.txt`
- Changes saved automatically
- Backup `in.txt` before testing

3. **Network Usage**
- Local network traffic only
- Low bandwidth requirements
- Real-time updates