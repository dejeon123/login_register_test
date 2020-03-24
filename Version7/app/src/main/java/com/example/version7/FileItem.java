package com.example.version7;

public class FileItem {

    private String document_title;
//    private String category;
//    private String date;
    private static int document_reviews =0;
    private static String document_status;
    private String document_id;
//    private String reviews;
    private static int document_score;
    private String document_authors;

    public FileItem(){

    }
    public FileItem(String document_id, String document_title, String document_authors, int document_reviews, String document_status, int document_score) {
        this.document_id = document_id;
        this.document_title = document_title;
        this.document_authors = document_authors;
        this.document_reviews = document_reviews;
        this.document_status = document_status;
        this.document_score = document_score;
        //this.link = link;
        //this.id = id;

    }


    public void setDocument_title(String document_title) {
        this.document_title = document_title;
    }

    public static void  setDocument_reviews(int document_reviews) {
        FileItem.document_reviews = document_reviews;
    }

    public static void setDocument_status(String document_status) {
        FileItem.document_status = document_status;
    }

    public void setDocument_id(String document_id) {
        this.document_id = document_id;
    }

    public static void setDocument_score(int document_score) {
        FileItem.document_score = document_score;
    }

    public void setDocument_authors(String document_authors) {
        this.document_authors = document_authors;
    }

    public String getDocument_title() {
        return document_title;
    }

    public static int getDocument_reviews() {
        return document_reviews;
    }

    public String getDocument_status() {
        return document_status;
    }

    public String getDocument_id() {
        return document_id;
    }

    public static int getDocument_score() {
        return document_score;
    }

    public String getDocument_authors() {
        return document_authors;
    }
}

