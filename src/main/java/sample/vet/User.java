package sample.vet;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    private long user_id;
    private String username;
    private String password;
    private long role_id;

    public User(int user_id, String username, String password, long role_id) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.role_id = role_id;
    }

    public User(String username, String password, long role_id) {
        this.username = username;
        this.password = password;
        this.role_id = role_id;
    }
    public static String hashPass(String pass) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert md5 != null;
        byte[] bytes = md5.digest(pass.getBytes());
        StringBuilder hashPass = new StringBuilder();
        for (byte b : bytes) {
            hashPass.append(String.format("%02X", b));
        }
        return hashPass.toString();
    }

    public long getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public long getRole_id() {
        return role_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole_id(long role_id) {
        this.role_id = role_id;
    }
}




