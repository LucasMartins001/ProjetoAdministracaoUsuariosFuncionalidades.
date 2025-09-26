package com.administracao.repos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleRepo<T extends Serializable> {
    private String filename;

    public SimpleRepo(String filename) {
        this.filename = filename;
    }

    @SuppressWarnings("unchecked")
    public List<T> loadAll() {
        File f = new File(filename);
        if (!f.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            Object obj = ois.readObject();
            return (List<T>) obj;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void saveAll(List<T> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(filename)))) {
            oos.writeObject(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
