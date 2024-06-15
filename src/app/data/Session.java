package app.data;
import java.time.LocalDateTime;

public class Session {
    private String sessionId;
    private String userId;
    private String username;
    private LocalDateTime creationTime;
    private LocalDateTime lastAccessTime;
    private boolean valid;

    // Constructor
    public Session(String sessionId, String userId, String username) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.username = username;
        this.creationTime = LocalDateTime.now();
        this.lastAccessTime = this.creationTime;
        this.valid = true;
    }

    // Getters and setters
    public String getSessionId() {
        return sessionId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public LocalDateTime getLastAccessTime() {
        return lastAccessTime;
    }

    public boolean isValid() {
        return valid;
    }

    public void invalidate() {
        this.valid = false;
    }

    public void updateLastAccessTime() {
        this.lastAccessTime = LocalDateTime.now();
    }
}