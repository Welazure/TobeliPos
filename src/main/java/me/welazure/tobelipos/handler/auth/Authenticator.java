package me.welazure.tobelipos.handler.auth;

import at.favre.lib.crypto.bcrypt.BCrypt;
import me.welazure.tobelipos.handler.auth.user.User;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class Authenticator {
    private Map<String, String> tokens;

    public Authenticator() {
        tokens = new HashMap<>();
    }
    public boolean authenticate(User user, String username, String password) {
        String userName = user.getName();
        String pwd = user.getHashedPass();
        if (!username.equals(userName)) return false;

        boolean success = verifyHash(pwd, password);
        if (!success) return false;

        String token = generateSafeToken();
        tokens.put(userName, token);
        user.setAuthToken(token);
        return true;
    }
    public void deAuthenticate(User user) {
        user.setAuthToken(null);
        String userName = user.getName();
        if(!tokens.containsKey(userName)) return;
        tokens.remove(userName);
    }

    public boolean isLoggedIn(User user) {
        if(user == null) return false;
        String userName = user.getName();
        String token = user.getAuthToken();
        return (tokens.containsKey(userName) && tokens.get(userName).equals(token));
    }

    public static String generateHash(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public static boolean verifyHash(String hash, String password) {
        return BCrypt.verifyer().verify(password.toCharArray(), hash).verified;
    }
    private String generateSafeToken() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[30];
        random.nextBytes(bytes);
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        String token = encoder.encodeToString(bytes);
        return token;
    }
}
