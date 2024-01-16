# Store Backend

Store Backend is a comprehensive Spring Boot project developed using Java 17, Spring Security, and Spring Data JPA. The application serves as the backend for an e-commerce site, providing essential features for managing categories, products, users, carts, and orders. It employs security measures like Basic Authentication and JWT Authentication, offers Swagger documentation, and supports the upload of product images.

## Features

### CRUD Operations

#### Categories

- **Add Category:**
  - Endpoint: `POST /api/categories`
  - Add a new product category.

- **Update Category:**
  - Endpoint: `PUT /api/categories/{categoryId}`
  - Update an existing product category.

- **Delete Category:**
  - Endpoint: `DELETE /api/categories/{categoryId}`
  - Delete a product category.

- **Get Categories:**
  - Endpoint: `GET /api/categories`
  - Retrieve a list of all product categories.

#### Products

- **Add Product:**
  - Endpoint: `POST /api/products`
  - Add a new product, including file upload for product images.

- **Update Product:**
  - Endpoint: `PUT /api/products/{productId}`
  - Update an existing product.

- **Delete Product:**
  - Endpoint: `DELETE /api/products/{productId}`
  - Delete a product.

- **Get Products:**
  - Endpoint: `GET /api/products`
  - Retrieve a paginated list of all products. Allows optional parameters for pagination (`pageNumber`, `pageSize`), sorting (`sortBy`, `sortDir`).

  ##### File Upload

  - **Upload Product Images:**
    - Endpoint: `POST /api/products/upload`
    - Upload images for product listings.

  ##### Pagination

  To enhance the user experience, product retrieval is paginated. This ensures efficient handling of large datasets and faster response times.

#### Users

- **Add User:**
  - Endpoint: `POST /api/users`
  - Register a new user.

- **Update User:**
  - Endpoint: `PUT /api/users/{userId}`
  - Update user details.

- **Delete User:**
  - Endpoint: `DELETE /api/users/{userId}`
  - Delete a user.

- **Get Users:**
  - Endpoint: `GET /api/users`
  - Retrieve a list of all users.

### Cart Operations

- **Add to Cart:**
  - Endpoint: `POST /api/cart/add`
  - Add products to the user's shopping cart.

- **Get Cart:**
  - Endpoint: `GET /api/cart`
  - Retrieve the contents of the user's shopping cart.

- **Delete from Cart:**
  - Endpoint: `DELETE /api/cart/{productId}`
  - Remove products from the user's shopping cart.

### Order Operations

- **Submit Order:**
  - Endpoint: `POST /api/orders/submit`
  - Place an order for the items in the user's cart.

- **Cancel Order:**
  - Endpoint: `DELETE /api/orders/{orderId}`
  - Cancel an existing order.

## Security

- **Basic Authentication:**
  - Implemented for secure access to specific endpoints.

- **JWT Authentication:**
  - Integrated JWT Authentication for enhanced security.

## Swagger Documentation

- Explore API documentation using Swagger UI.
  - URL: `http://localhost:8080/swagger-ui.html`

## File Upload

- **Upload Product Images:**
  - Endpoint: `POST /api/products/upload`
  - Upload images for product listings.

## Prerequisites

Before you begin, ensure you have the following installed:

- Java Development Kit (JDK) 17.
- Apache Maven.
- MySQL Database.

## Getting Started

1. Clone the repository:

   ```bash
   git clone https://github.com/Tushar1235/store-backend-api.git

