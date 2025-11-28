#  Disaster Management & Emergency Response Simulation (Java OOP Project)

This project is a **complete disaster response simulation system** built using Java with strong Object-Oriented Programming principles.  
It models a smart, event-driven city where disasters occur randomly, hospitals treat patients, rescue teams operate, weather influences severity, and resources respond dynamically.

The system is designed with scalability in mind — new disaster types, new units, or new behaviours can be added without modifying core logic.

---
##  Author

Developed by **Pranav**  
Passionate about Java, simulation modelling, disaster response systems & intelligent software design.

---
##  Key Features

| Feature | Description |
|--------|-------------|
| **Random Disaster Generation** | Flood, Fire, Thunderstorm, Earthquake etc. triggered dynamically |
| **Event-Driven Simulation** | Every rescue, ambulance, food supply & weather change runs as an event |
| **Resource Handling** | Rescue teams, ambulances, police, food supply & volunteer units |
| **Hospital Management System** | Admit patients, discharge, deaths, births, capacity tracking |
| **Weather Influence** | Weather affects severity of disaster using strategy-like polymorphism |
| **End-of-Day Summary** | Daily total deaths, injuries, rescues, births, population & food level reset |
| **Highly Extensible Architecture** | Can add new disasters, new cities, new units — without rewriting code |

---

## Object-Oriented Design Highlights

✔ **Abstraction** → Disaster & Event defined as abstract behaviours  
✔ **Encapsulation** → Internal data (population, injuries, hospital capacity) hidden behind controlled methods  
✔ **Inheritance** → `FloodDisaster`, `EarthquakeDisaster`, `ThunderstormDisaster` all extend `Disaster`  
✔ **Polymorphism**  
- *Inclusion polymorphism:* different disasters treated as `Disaster` type  
- *Interface-based polymorphism:* `SeverityCalculator`, `WeatherObserver`, `EndOfTheDay`  
✔ **Composition** → Zones contain hospitals, events use SimulationManager, Volunteers use resources  
✔ **Exception Handling** → Custom exceptions like `SimulationException` & `FoodShortageException`  
✔ **Extensibility (Open for Growth)** → New disasters/units added without modifying core logic  

This architecture is modular, maintainable, and suitable for real-world disaster modelling.

---

## Simulation Flow (Quick Summary)

1. **Weather Changes** every few hours → Notifies observers
2. **Disasters Trigger Randomly** across available zones
3. **Rescue Units/Volunteers Arrive**
4. **Ambulances Move Injured to Hospitals**
5. **Hospitals admit or overflow → exceptions handled**
6. **Food Supply deployed if city food hits zero**
7. **End of day report generated (population, deaths, rescues, etc.)**

---

##  How to Run

### ▶ Requirements
- Java 17+
- IDE: Eclipse / IntelliJ / VS Code

### ▶ Steps

1. **Clone this repository**
2. **Open in your IDE**
3. **Run Main.java**
4. **Watch disaster simulation execute hour-by-hour**


You can edit simulation time, add new disasters, or expand city zones for larger-scale testing.

---

##  Future Enhancements

🔹 Live GUI map showing disaster spread  
🔹 Database storage for daily logs  
🔹 Machine learning based disaster probability model  
🔹 Multi-city multi-day simulation  

---

### ⭐ If you find this project interesting — please give it a star!  
It helps visibility and motivates further development. 🙌

