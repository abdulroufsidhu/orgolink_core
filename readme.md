# Ambaar API Documentation

Welcome to the Ambaar API! This documentation provides an overview of the available endpoints, request formats, and responses.

## Base URL

```
http://localhost:8080/api
```

## Authentication

This API uses Bearer Authentication. You must include a valid JWT token in the Authorization header of your requests.

```
Authorization: Bearer <token>
```

## Endpoints

### User Requests

#### Create User

- **URL:** `/users/create`
- **Method:** `PUT`
- **Request Body:**
  ```json
  {
    "fullName": "string",
    "password": "string",
    "email": "string",
    "address": { ... },
    "active": true,
    "authorities": [ ... ],
    "username": "string",
    "isAccountNonExpired": true,
    "isAccountNonLocked": true,
    "isCredentialsNonExpired": true,
    "isEnabled": true
  }
  ```
- **Responses:**
    - **200 OK**
      ```json
      {
        "code": 200,
        "message": "string",
        "data": { ... }
      }
      ```

#### Sign In

- **URL:** `/users/sign-in`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "email": "string",
    "password": "string"
  }
  ```
- **Responses:**
    - **200 OK**
      ```json
      {
        "code": 200,
        "message": "string",
        "data": "string"
      }
      ```

#### Update Password

- **URL:** `/users/update-password/{id}`
- **Method:** `PATCH`
- **Parameters:**
    - **Path:** `id` (string, required)
- **Request Body:**
  ```json
  {
    "password": "string"
  }
  ```
- **Responses:**
    - **200 OK**
      ```json
      {
        "code": 200,
        "message": "string",
        "data": { ... }
      }
      ```

### Inventory Requests

#### Create Inventory

- **URL:** `/inventory`
- **Method:** `PUT`
- **Request Body:**
  ```json
  {
    "color": "string",
    "description": "string",
    "quantityInStock": 0,
    "unitPrice": 0,
    "allowedDiscountInPercent": 0,
    "purchasePrice": 0,
    "branch": { ... },
    "product": { ... }
  }
  ```
- **Responses:**
    - **200 OK**
      ```json
      {
        "code": 200,
        "message": "string",
        "data": { ... }
      }
      ```

#### Update Inventory

- **URL:** `/inventory`
- **Method:** `PATCH`
- **Request Body:**
  ```json
  {
    "color": "string",
    "description": "string",
    "quantityInStock": 0,
    "unitPrice": 0,
    "allowedDiscountInPercent": 0,
    "purchasePrice": 0,
    "branch": { ... },
    "product": { ... }
  }
  ```
- **Responses:**
    - **200 OK**
      ```json
      {
        "code": 200,
        "message": "string",
        "data": { ... }
      }
      ```

#### Get Inventory

- **URL:** `/inventory/{page}`
- **Method:** `GET`
- **Parameters:**
    - **Path:** `page` (integer, required)
    - **Query:** `inventory` (object, required)
- **Responses:**
    - **200 OK**
      ```json
      {
        "code": 200,
        "message": "string",
        "data": { ... }
      }
      ```

#### Delete Inventory

- **URL:** `/inventory/{id}`
- **Method:** `DELETE`
- **Parameters:**
    - **Path:** `id` (string, required)
- **Responses:**
    - **200 OK**
      ```json
      {
        "code": 200,
        "message": "string",
        "data": "string"
      }
      ```

### Employee Requests

#### Get Employees

- **URL:** `/employee`
- **Method:** `GET`
- **Parameters:**
    - **Query:** `branch_id` (string, required)
    - **Query:** `user_id` (string, required)
- **Responses:**
    - **200 OK**
      ```json
      {
        "code": 200,
        "message": "string",
        "data": [ ... ]
      }
      ```

#### Create Employee

- **URL:** `/employee`
- **Method:** `PUT`
- **Request Body:**
  ```json
  {
    "designation": "string",
    "user": { ... },
    "branch": { ... },
    "permissions": [ ... ],
    "active": true
  }
  ```
- **Responses:**
    - **200 OK**
      ```json
      {
        "code": 200,
        "message": "string",
        "data": { ... }
      }
      ```

#### Update Employee

- **URL:** `/employee`
- **Method:** `PATCH`
- **Request Body:**
  ```json
  {
    "designation": "string",
    "user": { ... },
    "branch": { ... },
    "permissions": [ ... ],
    "active": true
  }
  ```
- **Responses:**
    - **200 OK**
      ```json
      {
        "code": 200,
        "message": "string",
        "data": { ... }
      }
      ```

#### Delete Employee

- **URL:** `/employee/{id}`
- **Method:** `DELETE`
- **Parameters:**
    - **Path:** `id` (string, required)
- **Responses:**
    - **200 OK**
      ```json
      {
        "code": 200,
        "message": "string",
        "data": { ... }
      }
      ```

### Business Requests

#### Create Business

- **URL:** `/business`
- **Method:** `PUT`
- **Parameters:**
    - **Query:** `business` (object, required)
- **Responses:**
    - **200 OK**
      ```json
      {
        "code": 200,
        "message": "string",
        "data": { ... }
      }
      ```

#### Update Business

- **URL:** `/business`
- **Method:** `PATCH`
- **Parameters:**
    - **Query:** `business` (object, required)
- **Responses:**
    - **200 OK**
      ```json
      {
        "code": 200,
        "message": "string",
        "data": { ... }
      }
      ```

#### Delete Business

- **URL:** `/business/{id}`
- **Method:** `DELETE`
- **Parameters:**
    - **Path:** `id` (string, required)
- **Responses:**
    - **200 OK**
      ```json
      {
        "code": 200,
        "message": "string",
        "data": { ... }
      }
      ```

### Branch Requests

#### Get Branches

- **URL:** `/branches`
- **Method:** `GET`
- **Parameters:**
    - **Query:** `branch` (object, optional)
    - **Query:** `business_id` (string, optional)
- **Responses:**
    - **200 OK**
      ```json
      {
        "code": 200,
        "message": "string",
        "data": [ ... ]
      }
      ```

#### Create Branch

- **URL:** `/branches`
- **Method:** `PUT`
- **Request Body:**
  ```json
  {
    "name": "string",
    "code": "string",
    "phone": "string",
    "email": "string",
    "website": "string",
    "description": "string",
    "active": true,
    "address": { ... },
    "business": { ... }
  }
  ```
- **Responses:**
    - **200 OK**
      ```json
      {
        "code": 200,
        "message": "string",
        "data": { ... }
      }
      ```

#### Update Branch

- **URL:** `/branches`
- **Method:** `PATCH`
- **Request Body:**
  ```json
  {
    "name": "string",
    "code": "string",
    "phone": "string",
    "email": "string",
    "website": "string",
    "description": "string",
    "active": true,
    "address": { ... },
    "business": { ... }
  }
  ```
- **Responses:**
    - **200 OK**
      ```json
      {
        "code": 200,
        "message": "string",
        "data": { ... }
      }
      ```

#### Delete Branch

- **URL:** `/branches/{id}`
- **Method:** `DELETE`
- **Parameters:**
    - **Path:** `id` (string, required)
- **Responses:**
    - **200 OK**

### Product Requests

#### Get Products

- **URL:** `/products`
- **Method:** `GET`
- **Parameters:**
    - **Query:** `product` (object, required)
- **Responses:**
    - **200 OK**
      ```json
      {
        "code": 200,
        "message": "

string",
"data": [ ... ]
}
```

#### Create Product

- **URL:** `/products`
- **Method:** `PUT`
- **Request Body:**
  ```json
  {
    "name": "string",
    "category": { ... },
    "description": "string",
    "sku": "string",
    "barcode": "string",
    "active": true,
    "supplier": { ... }
  }
  ```
- **Responses:**
    - **200 OK**
      ```json
      {
        "code": 200,
        "message": "string",
        "data": { ... }
      }
      ```

#### Update Product

- **URL:** `/products`
- **Method:** `PATCH`
- **Request Body:**
  ```json
  {
    "name": "string",
    "category": { ... },
    "description": "string",
    "sku": "string",
    "barcode": "string",
    "active": true,
    "supplier": { ... }
  }
  ```
- **Responses:**
    - **200 OK**
      ```json
      {
        "code": 200,
        "message": "string",
        "data": { ... }
      }
      ```

#### Delete Product

- **URL:** `/products/{id}`
- **Method:** `DELETE`
- **Parameters:**
    - **Path:** `id` (string, required)
- **Responses:**
    - **200 OK**

### Permission Requests

#### Get Permissions

- **URL:** `/permissions`
- **Method:** `GET`
- **Responses:**
    - **200 OK**
      ```json
      {
        "code": 200,
        "message": "string",
        "data": [ ... ]
      }
      ```

#### Create Permission

- **URL:** `/permissions`
- **Method:** `PUT`
- **Request Body:**
  ```json
  {
    "name": "string",
    "description": "string"
  }
  ```
- **Responses:**
    - **200 OK**
      ```json
      {
        "code": 200,
        "message": "string",
        "data": { ... }
      }
      ```

#### Update Permission

- **URL:** `/permissions`
- **Method:** `PATCH`
- **Request Body:**
  ```json
  {
    "name": "string",
    "description": "string"
  }
  ```
- **Responses:**
    - **200 OK**
      ```json
      {
        "code": 200,
        "message": "string",
        "data": { ... }
      }
      ```

#### Delete Permission

- **URL:** `/permissions/{id}`
- **Method:** `DELETE`
- **Parameters:**
    - **Path:** `id` (string, required)
- **Responses:**
    - **200 OK**

## Error Handling

### Common Error Codes

- **400 Bad Request**
- **401 Unauthorized**
- **403 Forbidden**
- **404 Not Found**
- **500 Internal Server Error**

