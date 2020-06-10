package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "files")
public class Files {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String file_name;
    private String file_type;
    private byte[] data;


    @ManyToOne
    private Employee employee;


    public Files() {

    }

    public Files(int id, String file_name, String file_type, byte[] data){
        this.id = id;
        this.file_name = file_name;
        this.file_type = file_type;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
