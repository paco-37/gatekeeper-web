# PacoMatch (ehem. Gatekeeper)

Eine moderne Full-Stack Webanwendung zur Ermittlung der persönlichen Kompatibilität zwischen Bewerbern und dem Host ("Paco"). Entwickelt als SaaS-Plattform mit Admin-Dashboard, sicherem Login und Cloud-Datenbank.

**Live Demo:** [Hier klicken](https://paco-match.onrender.com) _(Hinweis: Server schläft bei Inaktivität, Start dauert ca. 50s)_

---

## Features

### Für Bewerber (Public)
* **Interaktives Quiz:** Fragebogen-Logik mit gewichteten Fragen.
* **Responsive Design:** Optimiert für Mobile & Desktop dank **Tailwind CSS**.
* **Echtzeit-Auswertung:** Berechnung des "Compatibility Scores" basierend auf gewichteten Antworten.
* **Leaderboard:** Anzeige der Top 10 kompatiblen Kandidaten.

### Für den Admin (Secured)
* **Geschützter Bereich:** Login-System mit **Spring Security**.
* **CRUD-Verwaltung:** Erstellen, Bearbeiten und Löschen von Fragen und Kategorien.
* **Option Management:** Dynamisches Hinzufügen von Antwortmöglichkeiten.
* **Bewerber-Management:** Übersicht und Löschfunktion für Kandidaten.

---

## Tech Stack

* **Backend:** Java 21, Spring Boot 3 (Web, Data JPA, Security)
* **Frontend:** Thymeleaf (Server-Side Rendering), Tailwind CSS
* **Datenbank:** PostgreSQL (Hosted auf Neon.tech)
* **Deployment:** Docker, Render.com
* **Tools:** Maven, Git

---

## Architektur & Sicherheit

* **MVC Pattern:** Saubere Trennung von Model, View und Controller.
* **State Management:** Nutzung von `@SessionAttributes` für den Spielzustand ohne Datenbank-Persistenz während des Spiels.
* **Security:**
    * BCrypt (standard) für Passwort-Hashing.
    * Schutz gegen CSRF Attacken.
    * Environment Variables für Datenbank-Credentials (keine Passwörter im Code).

---

## Lokale Installation

1.  **Repository klonen**
    ```bash
    git clone [https://github.com/paco-37/gatekeeper-web.git](https://github.com/paco-37/gatekeeper-web.git)
    cd gatekeeper-web
    ```

2.  **Umgebungsvariablen setzen**
    Erstelle eine lokale PostgreSQL Datenbank oder setze die Variablen in `application.properties`:
    ```properties
    DB_USER=dein_user
    DB_PASSWORD=dein_passwort
    ADMIN_USER=admin
    ADMIN_PASSWORD=geheim
    ```

3.  **Starten**
    ```bash
    mvn spring-boot:run
    ```
    Die App läuft unter `http://localhost:8080`.

---

## Lizenz

Dieses Projekt wurde zu Lernzwecken erstellt.
