# pongstake
&#x20;

## Quick overview about project

`pongstake` is a simple **SaaS** website that predicts ping-pong matches (currently from <ins>**WTT league**</ins>). Simply pick your wanted matches -> pay for the result -> get pdf/email with results 

## Tech stack

| Layer            | Technology                            |
| ---------------- | ------------------------------------- |
| Backend          | Java 21 Spring boot 3.x               |
| Frontend         | ?                                     |
| Database         | PostgreSQL 17                         |
| Caching          | Redis                                 |
| ORM              | Spring Data JPA                       |
| Payment          | Stripe Java                           |
| Containerization | Docker                                |
| Testing          | JUnit 5                               |


## Features

- Soon will write

## Architecture

```plaintext
pingpong-predictor/
├── java-backend/      # Java Spring Boot Backendapplication
│   ├── src/           # Source code
│   └── pom.xml        # Maven configuration
├── docs/              # Documentation
│   ├── architecture.md         # Architecture png
│   ├── TODO.md                 # What features to implement
│   └── CONTRIBUTE.md           # How to contribute :)
├── .github/
│   └── workflows/     # GitHub Actions CI (soon)
├── README.md          # Readme
└── LICENSE            # License
```

## Getting Started

### Prerequisites

- Java 21 SDK
- Maven 
- Docker
- Git
- PostgreSQL 17

### Installation

Soon

### Configuration

Copy example env files and fill secrets:

```bash
Soon
```

- `STRIPE_SEC_KEY`, `STRIPE_PUB_KEY`
- `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`

### Running the Application

Soon

## Usage

Soon


## Contributing

**!DM me anywhere so I can give you example pingpong predicting model and database!**
See [CONTRIBUTE.md](docs/CONTRIBUTE.md) for guidelines on issues and pull requests. Please follow code style and add tests for new features.

## License

This project is licensed under the [Apache 2.0 License](LICENSE).
