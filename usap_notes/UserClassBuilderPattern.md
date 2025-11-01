## Ways to Use the Builder

### 1. **Basic Builder with Required Fields**
```java
User user = User.builder()
    .username("john_doe")
    .password("password123")
    .authorities("ROLE_USER")
    .build();
```

### 2. **Using `withUsername()` Shortcut**
```java
User user = User.withUsername("john_doe")
    .password("password123")
    .roles("USER", "ADMIN")
    .build();
```

### 3. **Using Roles (Automatic `ROLE_` Prefix)**
```java
User user = User.builder()
    .username("admin")
    .password("admin123")
    .roles("USER", "ADMIN")  // Converts to ROLE_USER, ROLE_ADMIN
    .build();
```

### 4. **Using Authorities Directly**
```java
// With String authorities
User user = User.builder()
    .username("user")
    .password("pass")
    .authorities("ROLE_USER", "ROLE_MANAGER")
    .build();

// With GrantedAuthority objects
User user = User.builder()
    .username("user")
    .password("pass")
    .authorities(new SimpleGrantedAuthority("ROLE_USER"))
    .build();
```

### 5. **With Account Status Flags**
```java
User user = User.builder()
    .username("user")
    .password("pass")
    .authorities("ROLE_USER")
    .accountExpired(false)
    .accountLocked(false)
    .credentialsExpired(false)
    .disabled(false)
    .build();
```

### 6. **With Password Encoder**
```java
PasswordEncoder encoder = new BCryptPasswordEncoder();
User user = User.builder()
    .username("user")
    .passwordEncoder(encoder::encode)
    .password("plainTextPassword")  // Will be encoded
    .roles("USER")
    .build();
```

### 7. **Using Deprecated `withDefaultPasswordEncoder()`**
```java
User user = User.withDefaultPasswordEncoder()
    .username("user")
    .password("password")  // Auto-encoded with default encoder
    .roles("USER")
    .build();
```

### 8. **Creating from Existing UserDetails**
```java
UserDetails existingUser = // ... get from somewhere
User user = User.withUserDetails(existingUser)
    .password("newPassword")  // Override specific fields if needed
    .build();
```

## Key Concepts

**Builder Pattern Benefits:**
- **Method Chaining**: Each method returns `UserBuilder`, allowing fluent API
- **Flexibility**: Only set fields you need (others use defaults)
- **Readability**: Clear, self-documenting code
- **Immutability**: Once built, the `User` object's core fields are final

**Default Values:**
- `accountExpired`: false (account is valid)
- `accountLocked`: false (account is unlocked)
- `credentialsExpired`: false (credentials are valid)
- `disabled`: false (account is enabled)