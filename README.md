# 📚 Smart Library API

A library management REST API built with **Java 21 and Spring Boot**, featuring user accounts, per-user borrowing, and **AI-generated book summaries** powered by Google Gemini. Deployed on **AWS EC2** with HTTPS.

**🔗 Live demo:** https://smart-library.duckdns.org
*(Demo only. Please avoid using a real password.)*

![The Library](docs/library-ai.png)

## ✨ Features

- User registration and login (Spring Security, BCrypt password hashing, session cookies)
- Per-user borrowing: each user sees only their own borrowed books ("My books")
- Browse, borrow and return books
- AI summaries for any book using the Gemini API
- Simple web page served by the same Spring Boot app
- Persistent storage with JPA and an H2 file database

## 🛠 Tech stack

Java 21 · Spring Boot · Spring Security · Spring Data JPA · H2 · Google Gemini API · HTML/CSS/JavaScript · Maven · AWS EC2 · Caddy (reverse proxy, automatic HTTPS) · DuckDNS

## 🔌 API endpoints

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| POST | `/register` | Create an account (JSON: username, password) | Public |
| POST | `/login` | Log in (form: username, password) | Public |
| POST | `/logout` | Log out | Logged in |
| GET | `/me` | Current user | Public |
| GET | `/books` | List all books | Public |
| GET | `/books/{id}/summary` | AI-generated summary | Public |
| GET | `/books/mine` | Books borrowed by me | Logged in |
| PUT | `/books/{title}/borrow` | Borrow a book | Logged in |
| PUT | `/books/{title}/return` | Return a book | Logged in |
| POST | `/books` | Add a book | Logged in |
| DELETE | `/books/{id}` | Delete a book | Logged in |

Example:

```bash
curl -X POST https://smart-library.duckdns.org/register \
  -H "Content-Type: application/json" \
  -d '{"username":"demo_user","password":"demo123"}'
```

## 📸 Screenshots

**My books (per-user borrows)**

![My books](docs/my-books.png)

**AI summary**

![AI summary](docs/api-summary.png)

**Books API**

![Books API](docs/api-books.png)

**Auth API (register / login)**

![Auth API](docs/auth-api.png)

**Security configuration**

![Security config](docs/security.png)

**Controller code**

![Controller](docs/controller.png)

**Database**

![Database](docs/database.png)

## ☁️ Deployment

- Hosted on an **AWS EC2** instance (Amazon Linux 2023) with an Elastic IP
- Free domain from **DuckDNS**
- **Caddy** reverse proxy serves the app over HTTPS with an automatic Let's Encrypt certificate
- The Gemini API key is kept in an environment variable, never in the code

## 🚀 Run locally

```bash
git clone https://github.com/LamaaBejad/library-api.git
cd library-api
export GEMINI_API_KEY="your-key"
./mvnw spring-boot:run
```

Then open http://localhost:8080

## 🔭 What I'd improve

- Move from H2 to PostgreSQL
- Add unit and integration tests
- Run the app as a managed service and add CI/CD
- Containerize with Docker

## 🗺 Roadmap

- [x] REST API with CRUD for books
- [x] AI summaries (Gemini)
- [x] User accounts and per-user borrows
- [x] Deployed on AWS EC2
- [x] Domain name (DuckDNS)
- [x] HTTPS (Caddy + Let's Encrypt)
- [ ] PostgreSQL
- [ ] Unit tests
- [ ] Docker
