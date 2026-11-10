# 📚 Smart Library API

A library management REST API built with **Java 21 and Spring Boot**, featuring user accounts, per-user borrowing, and **AI-generated book summaries** powered by Google Gemini. Deployed on **AWS EC2** with HTTPS.

**🔗 Live demo:** https://smart-library.duckdns.org
*(Demo only. Please avoid using a real password.)*

![The Library](docs/library-ai.png)

## ✨ Features

- User registration and login (Spring Security, BCrypt password hashing, session cookies)
- Per-user borrowing: each user sees only their own borrowed books
- Browse, borrow and return books
- AI summaries for any book using the Gemini API
- Persistent storage with JPA and an H2 file database

## 🛠 Tech stack

Java 21 · Spring Boot · Spring Security · Spring Data JPA · H2 · Google Gemini API · Maven · AWS EC2 · Caddy (reverse proxy, automatic HTTPS) · DuckDNS

## ☁️ Deployment

- Hosted on an **AWS EC2** instance (Amazon Linux 2023) with an Elastic IP
- Free domain from **DuckDNS**
- **Caddy** reverse proxy serves the app over HTTPS with an automatic Let's Encrypt certificate
- API keys are kept in environment variables, never in the code

## 🚀 Run locally

```bash
git clone https://github.com/LamaaBejad/library-api.git
cd library-api
export GEMINI_API_KEY="your-key"
./mvnw spring-boot:run
```

Then open http://localhost:8080

## 🗺 Roadmap

- [x] REST API with CRUD for books
- [x] AI summaries (Gemini)
- [x] User accounts and per-user borrows
- [x] Deployed on AWS EC2
- [x] Domain name (DuckDNS)
- [x] HTTPS (Caddy + Let's Encrypt)
- [ ] PostgreSQL
- [ ] Unit tests
