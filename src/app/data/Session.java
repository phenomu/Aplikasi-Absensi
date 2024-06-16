package app.data;

public class Session {
    public static String userId;
    public static String Nim;
    public static int level;
    public static boolean valid;

    public static void addSession(String userId, String Nim, int level) {
        Session.userId = userId;
        Session.Nim = Nim;
        Session.level = level;
        Session.valid = true;
    }

    public static String getUserId() {
        return userId;
    }

    public static String getNim() {
        return Nim;
    }

    public static int getLevel() {
        return level;
    }

    public static boolean isValid() {
        return valid;
    }

    public static void clearSession() {
        userId = null;
        Nim = null;
        level = 0;
        valid = false;
    }
}