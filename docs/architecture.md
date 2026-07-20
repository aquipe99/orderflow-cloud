ETAPA 1 - Diseño de arquitectura técnica de ORDERFLOW CLOUD
Objetivo

Antes de crear el primer microservicio vamos a definir cómo se comunicará el sistema.

En proyectos empresariales primero se diseña la arquitectura y luego se implementa.

En esta etapa aprenderás:

Qué responsabilidad tiene cada componente.
Por qué usamos comunicación síncrona y asíncrona.
Cómo fluye una orden dentro del sistema.
Cómo defender la arquitectura en una entrevista.
1. Arquitectura general

Nuestra arquitectura será:

                         Cliente
                            |
                            |
                            v
                  Azure API Management
                            |
                            |
                            v
              Spring Cloud Gateway
                    (WebFlux)
                            |
                            |
        -------------------------------------
        |                |                 |
        v                v                 v


 Order Service     Inventory Service    Payment Service
 Spring MVC        Spring MVC           Quarkus


        |                |                 |
        |                |                 |
        ----------- Kafka -----------------
                         |
                         |
                         v

                Notification Service
                   Spring MVC
2. Responsabilidad de cada capa
Cliente

Puede ser:

Web App.
Mobile App.
Sistema externo.

No conoce los microservicios internos.

Solo consume:

POST /api/orders
Azure API Management

Será la puerta pública del sistema.

Responsabilidades:

Exposición de APIs.
Seguridad externa.
Rate limiting.
Gestión de consumidores.
Políticas.

Ejemplo:

Un cliente puede hacer:

100 requests/minuto

y Azure APIM controla ese límite.

Spring Cloud Gateway

Será nuestro API Gateway interno.

Tecnología:

Spring WebFlux

¿Por qué WebFlux aquí?

Porque un Gateway es un componente donde tiene sentido manejar muchas conexiones concurrentes.

Responsabilidades:

Routing

Ejemplo:

/api/orders

        |
        v

order-service:8081
Seguridad

Validación JWT:

Request

 |
 |
JWT Token

 |
 |
Gateway valida

 |
 |
Microservicio
Correlation ID

Ejemplo:

Request:

POST /orders

Gateway agrega:

X-Correlation-ID:
8f72abc

Ese mismo ID viaja por todos los servicios.

Esto será importante para:

logs.
tracing.
debugging.
3. Order Service

Será el punto inicial del negocio.

Responsabilidad:

Crear una orden.

Ejemplo:

Cliente:

POST /orders

{
 "customerId":100,
 "items":[
   {
    "productId":20,
    "quantity":2
   }
 ]
}

Order Service:

Valida información.
Guarda orden.
Publica evento.

Evento:

OrderCreated

Kafka:

orders.created
4. ¿Por qué Kafka y no REST entre servicios?

Pregunta clásica de entrevista.

Tenemos dos opciones.

Comunicación síncrona REST

Ejemplo:

Order Service

   |
   |
HTTP

   |
   v

Inventory Service

Problema:

Si Inventory está caído:

Order falla

Tenemos acoplamiento.

Comunicación asíncrona Kafka
Order Service

      |
      |
 OrderCreated

      |
      v

     Kafka

      |
      |
Inventory Service

Ventajas:

Desacoplamiento.
Mayor resiliencia.
Escalabilidad.
Procesamiento independiente.
5. Flujo completo de una orden
Caso exitoso
Paso 1

Cliente crea orden.

POST /orders
Paso 2

Order Service guarda:

Base:

orders

Estado:

CREATED
Paso 3

Publica:

OrderCreated

Kafka:

orders.created
Paso 4

Inventory consume.

Reserva stock.

Publica:

InventoryReserved
Paso 5

Payment consume.

Procesa pago.

Publica:

PaymentCompleted
Paso 6

Notification consume.

Envía correo.

Publica:

NotificationCreated

Resultado:

ORDER COMPLETED
6. Saga Pattern

Aquí aparece un concepto importante.

En microservicios no tenemos:

BEGIN TRANSACTION

Order
Inventory
Payment

COMMIT

porque cada servicio tiene su propia base.

Entonces usamos:

Saga Coreografía

Cada servicio reacciona a eventos.

Ejemplo:

OrderCreated

      |
      v

InventoryReserved

      |
      v

PaymentFailed

      |
      v

InventoryReleased

      |
      v

OrderCancelled

Cada servicio conoce su compensación.

7. Bases de datos

Regla importante:

Database per Service

Tendremos:

order-service

 PostgreSQL

 orders_db



inventory-service

 PostgreSQL

 inventory_db



payment-service

 PostgreSQL

 payment_db

¿Por qué?

Porque cada servicio es dueño de sus datos.

Evita:

❌ tablas compartidas
❌ acoplamiento fuerte
❌ cambios coordinados entre equipos

8. Estructura final del repositorio

Después agregaremos:

orderflow-cloud

├── microservices
│
│   ├── api-gateway
│   │
│   ├── order-service
│   │
│   ├── inventory-service
│   │
│   ├── payment-service-quarkus
│   │
│   └── notification-service
│
├── labs
│
│   └── spring-webflux-lab
│
├── infrastructure
│
│   ├── docker
│   │
│   ├── kubernetes
│   │
│   └── helm
│
├── monitoring
│
├── docs
│
└── .github
9. Primera documentación técnica

Ahora agregaremos:

docs/

crear:

architecture.md

Contenido:

Objetivo del sistema.
Diagrama.
Decisiones arquitectónicas.
Tecnologías.

Este documento será parte de tu portafolio.