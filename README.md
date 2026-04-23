# 🍸 CocktailHub  
### 👥 Group: mechine not learning

## 📌 Project Overview

CocktailHub is a Java Swing-based desktop application that integrates a cocktail management system and an ordering & billing system.

The project demonstrates full workflow including data persistence, UI interaction, algorithms, and data structures.

---

## 🏗️ System Architecture

```
CocktailHub
│
├── Home (Entry UI)
│
├── Manage System
│   ├── Add / Edit / Delete (Cocktails / Flavors / Prices)
│   ├── CustomLinkedList (Data Structure)
│   └── Data (File Handling & Logic)
│
└── Order System
    ├── OrderingPanel (Menu UI + Search + Sort)
    ├── BillPanel (Bill Calculation)
    ├── BillSaver (File Persistence)
    ├── MergeSort (Sorting Algorithm)
    └── DataLoader (Data Parsing)
```

---

## ⚙️ Key Features

### 🧩 CRUD Management
- Add, edit, delete cocktails, flavors, and prices
- File-based storage (.txt)

### 🔍 Search
- Search by name, description, or price

### 🔃 Sorting
- Sort by name and price
- Uses Merge Sort algorithm

### 🛒 Ordering System
- Add/remove items with quantity control
- Cart implemented using HashMap

### 🧾 Billing System
- Displays current and ordered items
- Calculates subtotal, tax (6.25%), and total

### 💾 File Persistence
- Stores data in src/File/
- Saves bill records automatically

---

## 🖥️ UI Design
- Built with Java Swing
- Uses MigLayout and CardLayout
- Multi-panel navigation

---

## 🚀 How to Run

1. Open project in IDE
2. Ensure folder exists:
   src/File/
3. Run:
   Home.java

---

## ⚠️ Limitations
- No database (file-based only)
- No input validation
- No concurrency handling

---

## 🔮 Future Improvements
- Add database support
- Improve UI/UX
- Add authentication system
- Implement MVC architecture

---

## 📌 Conclusion

CocktailHub is a complete Java desktop application combining data structures, algorithms, GUI, and file handling.
