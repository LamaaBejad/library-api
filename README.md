# Library API

A library management REST API built with **Java 21** and **Spring Boot**, with a simple web page to browse, borrow and return books, plus AI-generated book summaries.

**Live demo:** http://16.16.148.122:8080 (deployed on AWS EC2, runs in read-only demo mode, plain HTTP for now)

![Library page](docs/library-ai.png)

## Features
- List, add and delete books
- Borrow and return a book (availability is tracked)
- AI-generated book summaries powered by Gemini (cached in memory to limit API calls)
- Data is stored in a file-based H2 database, so it survives restarts
- Sample books are added automatically on first run
- Simple HTML page served by Spring Boot
- Demo mode: set `DEMO_MODE=true` to block adding and deleting books (used on the live site)

## Tech stack
- Java 21, Spring Boot, Maven
- Spring Web (REST), Spring Data JPA / Hibernate
- H2 database
- Gemini API (called from the backend with `RestClient`)
- HTML, CSS and JavaScript (single page)
- AWS EC2 (Amazon Linux 2023) for hosting

## Architecture
`Controller` (HTTP requests) -> `Library` service (business logic) -> `BookRepository` (database)

`GeminiService` calls the Gemini API to summarize a book. The API key is read only from an environment variable, never stored in the code.

## API endpoints
| Method | Endpoint | Description |
|---|---|---|
| GET | `/books` | List all books |
| POST | `/books` | Add a book (JSON body) |
| PUT | `/books/{title}/borrow` | Borrow a book |
| PUT | `/books/{title}/return` | Return a book |
| DELETE | `/books/{id}` | Delete a book |
| GET | `/books/{id}/summary` | AI-generated summary of a book |
| GET | `/config` | Returns whether demo mode is on |

Example request body for `POST /books`:
```json
{ "title": "Clean Code", "author": "Robert C. Martin" }
```

## Backend in action

`GET /books` returns the books as JSON:

![API response](docs/api-books.png)

Data is stored in an H2 database through Spring Data JPA:

![Database](docs/database.png)

REST endpoints in `BookController`:

![BookController](docs/controller.png)

`GET /books/{id}/summary` returns an AI-generated summary:

![AI summary endpoint](docs/api-summary.png)

## Run locally
Requirements: Java 21.

```bash
./mvnw spring-boot:run
```
Then open http://localhost:8080

To enable AI summaries, set a `GEMINI_API_KEY` environment variable with your own Gemini API key. The rest of the app works without it.

## Deployment
The app runs on an AWS EC2 instance (Amazon Linux 2023, Java 21 Corretto):
- Built on the server with the Maven wrapper and started as a background process
- A fixed Elastic IP keeps the URL stable
- Security group: port 8080 open to the public, SSH restricted to AWS EC2 Instance Connect
- `GEMINI_API_KEY` and `DEMO_MODE` are set as environment variables on the server

The live demo runs on a limited AWS free plan, so the link may be taken down later. The screenshots above show the app running.

## What I'd improve
- Add HTTPS with a domain name and a reverse proxy
- Replace H2 with PostgreSQL (for example on AWS RDS)
- Add input validation, better error handling and unit tests
- Add user login so adding and deleting books can be allowed safely
- Run the app as a system service so it restarts automatically after a reboot

## Roadmap
- [x] AI-generated book summaries
- [x] Deployment to AWS