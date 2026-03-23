# Spring Boot OpenTelemetry LGTM Stack 🚀

This repository is a Proof of Concept (PoC) for implementing **Full-Stack Observability** using **Spring Boot 4 (Java 21)** and **OpenTelemetry**. It demonstrates how to collect and correlate the three pillars of observability—Logs, Metrics, and Traces—using the **Grafana LGTM Stack**.

<img width="945" height="430" alt="image" src="https://github.com/user-attachments/assets/4ab53064-9861-4f6f-9cbc-12d47db8e6c7" />

<img width="954" height="439" alt="image" src="https://github.com/user-attachments/assets/2854209c-422d-4882-af6f-6ae1c0540d60" />

<img width="944" height="436" alt="image" src="https://github.com/user-attachments/assets/4b939d96-4559-4e08-801f-e93db8a327fd" />

## 🏗️ Architecture & Technology

The project is designed for "Low-Code Observability," providing instant visibility into application performance with minimal manual configuration.

* **Java 21 & Spring Boot 4**: Leveraging the latest JVM features and first-class Spring Observability integration.
* **OpenTelemetry (OTEL)**: The industry standard for vendor-neutral instrumentation.
* **LGTM Stack (via Docker)**:
    * **L**oki: High-availability log aggregation.
    * **G**rafana: The central visualization dashboard.
    * **T**empo: Distributed tracing for request tracking across endpoints.
    * **M**imir: Scalable metrics storage (Prometheus compatible).

## ✨ Key Features

1.  **Automatic Instrumentation**: Out-of-the-box tracking for incoming and outgoing HTTP requests.
2.  **Contextual Logging**: Logs are automatically linked with Trace IDs. You can jump from a trace to its specific logs in Grafana.
3.  **MDC Capture**: Custom `MDC` attributes (like `productId`) are automatically captured as labels/attributes in Loki.
4.  **Zero-Config Infrastructure**: Using `spring-boot-docker-compose`, the LGTM backend starts automatically when the application runs.
5.  **OTLP Native**: All signals are exported via the OpenTelemetry Protocol (OTLP) to a single collector endpoint.

## 🛠️ Prerequisites

* **Java 21** or higher.
* **Docker Desktop** / **Docker Engine** (running in the background).
* **Maven** (or use the provided `./mvnw`).

## 🚀 Getting Started

1.  **Clone the Repository**:
    ```bash
    git clone https://github.com/muhamadridwanid/spring-boot-open-telemetry-lgtm.git
    cd spring-boot-open-telemetry-lgtm
    ```

2.  **Run the Application**:
    ```bash
    ./mvnw spring-boot:run
    ```
    *The application will automatically pull and start the `grafana/otel-lgtm` container on port 3000.*

3.  **Generate Traffic**:
    Visit these endpoints to push data to the LGTM stack:
    * `http://localhost:8080/` - Standard "Hello World" log.
    * `http://localhost:8080/greet/world` - Simulated light workload.
    * `http://localhost:8080/slow` - Latency simulation with custom MDC context.
    * `http://localhost:8080/exception` - Trigger a runtime exception to view stack traces in Tempo.

## 📊 Exploration in Grafana

Access the dashboard at **`http://localhost:3000`** (Default: `admin` / `admin`).

| Signal | Data Source | Usage |
| :--- | :--- | :--- |
| **Logs** | `Loki` | Query `{app="coba-spring-otel"}` to see structured logs and labels. |
| **Traces** | `Tempo` | Search for "Recent Traces" to see waterfall charts of your requests. |
| **Metrics** | `Prometheus` | Monitor JVM heap, CPU usage, and HTTP request rates. |

## ⚙️ Key Configurations

The configuration is managed in `src/main/resources/application.yml`:
* `sampling.probability: 1.0`: 100% trace capture (ideal for development).
* `endpoint: http://localhost:4318/v1/...`: OTLP export endpoints for traces, logs, and metrics.
* `OpenTelemetryAppender`: Configured in `logback-spring.xml` to bridge SLF4J logs to the OTEL exporter.

---

> **Tip:** In a production environment, remember to reduce the `sampling.probability` (e.g., to `0.1`) to save storage and bandwidth.
