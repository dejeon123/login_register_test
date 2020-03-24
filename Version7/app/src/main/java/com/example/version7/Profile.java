package com.example.version7;

public class Profile {


    private static String Name;
    private static String Email;
    private static String user_name;
    private static String role;
    private static String password;
    private static String peer;
    private static int Photo;
    private static String category;


    public Profile() {
    }


    public Profile(String name, String email,String user_name, String role,String peer, int photo,String category,String password) {
        this.Name = name;
        this.Email = email;
        this.user_name =user_name;
        this.role = role;
        this.peer = peer;
        Photo = photo;
        this.category = category;
        this.password = password;

    }

    public static void setPassword(String password) {
        Profile.password = password;
    }

    public static String getPassword() {
        return password;
    }

    public static void setCategory(String category) {
        Profile.category = category;
    }

    public static void setUser_name(String user_name) {
        Profile.user_name = user_name;
    }

    public static void setRole(String role) {
        Profile.role = role;
    }

    public static void setPeer(String peer) {
        Profile.peer = peer;
    }

    public static void setName(String name) {
        Profile.Name = name;
    }

    public static void setEmail(String email) {
        Profile.Email = email;
    }

    public static void setPhoto(int photo) {
        Profile.Photo = photo;
    }

    public static String getName() {
        return Name;
    }

    public static String getEmail() {
        return Email;
    }

    public static int getPhoto() {
        return Photo;
    }

    public static String getUser_name() {
        return user_name;
    }

    public static String getRole() {
        return role;
    }

    public static String getPeer() {
        return peer;
    }

    public static String getCategory() {
        return category;
    }
}