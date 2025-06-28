# JWT Token Filter Enablement

This project demonstrates how to enable and configure JWT-based authentication using custom filters and Spring Security.

---

## 🔐 Overview

JWT (JSON Web Token) is used for stateless authentication between client and server. This setup includes custom filters to manage login and token verification.

---

## 🧱 Architecture Components

### 1. **Authentication Manager**
- Manages authentication workflow.
- Communicates with the `AuthenticationProvider` to validate credentials.

### 2. **JWT Filter**
- Intercepts incoming HTTP requests.
- Validates the JWT token from the `Authorization` header.
- If valid, extracts user details and sets the authentication in the security context.

### 3. **UsernamePasswordAuthenticationFilter**
- Handles user login.
- Authenticates user using username and password.
- On success, generates a JWT token.

---

## 🔄 JWT Workflow

### 📥 1. **User Login**
- User sends username and password.
- Filter authenticates the credentials.
- JWT Token is generated and returned.

### 📤 2. **Token Verification (for each request)**
- Extract token from `Authorization` header (must start with `Bearer`).
- Check if token is **not empty** and is **well-formed**.
- Validate token signature and expiration.
- Retrieve username from token.
- Load user details from the database.
- Create `Authentication` object and set it in the context.

---

## 🛠️ JWT Filter Example Logic

```java
String token = request.getHeader("Authorization");

if (token != null && token.startsWith("Bearer ")) {
    token = token.substring(7); // Remove "Bearer " prefix
    String username = jwtService.extractUsername(token);

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (jwtService.isTokenValid(token, userDetails)) {
            UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }
}
