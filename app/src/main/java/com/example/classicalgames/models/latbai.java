package com.example.classicalgames.models;

public class latbai {
    private int id;
    private int source;
    private int location;

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public latbai(int id, int source) {
        this.id = id;
        this.source = source;
    }

    public latbai() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

}
