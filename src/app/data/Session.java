package app.data;
import java.time.LocalDateTime;

public class Session {
    private String sessionId;
    private String userId;
    private String username;
    private LocalDateTime creationTime;
    private LocalDateTime lastAccessTime;
    private boolean valid;
    private int level;

    // Constructor
    public Session(String sessionId, String userId, String username, int level) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.username = username;
        this.creationTime = LocalDateTime.now();
        this.lastAccessTime = this.creationTime;
        this.valid = true;
        this.level = level;
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

    public String getRole() {
        if (level==0) {
            return "admin";
        }else if(level==1){
            return "dosen";
        }else{
            return "mahasiswa";
        }
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