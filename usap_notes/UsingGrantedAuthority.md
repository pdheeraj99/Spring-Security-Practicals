The difference between these two approaches is in **how you create the authorities**:

## 1. **Using `GrantedAuthority` Objects**
```java
.authorities(new SimpleGrantedAuthority("ROLE_USER"))
```
- You create authority objects **manually**
- You pass actual `GrantedAuthority` instances
- More verbose but gives you control over the authority implementation
- Use when you need custom `GrantedAuthority` implementations

## 2. **Using String Authorities**
```java
.authorities("ROLE_USER", "ROLE_MANAGER")
```
- You pass authority names as **strings**
- Spring Security **automatically converts** them to `SimpleGrantedAuthority` objects internally
- Less verbose and cleaner syntax
- Preferred for simple use cases

## Under the Hood

When you use the string version, Spring calls:
```java
AuthorityUtils.createAuthorityList(authorities)
```

This method internally creates `SimpleGrantedAuthority` objects for each string, exactly like the first approach.

## Key Takeaway

**Both approaches produce the same result** - they create `SimpleGrantedAuthority` instances. The string version is just a **convenience method** to save you from manually creating `SimpleGrantedAuthority` objects.

**Recommendation:** Use the string version unless you need a custom `GrantedAuthority` implementation.