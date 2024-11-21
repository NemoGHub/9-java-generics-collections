package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private final File file;
    private List<E> list;


    public SavedList(File file) {
        this.file = file;
        if (file.exists()){
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))){
                list = (List<E>) objectInputStream.readObject();
            } catch (Exception e) { throw new RuntimeException(e);}
        } else {list = new ArrayList<>();}
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        list.set(index, element);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))){
            objectOutputStream.writeObject(list);
        } catch (Exception e) { throw new RuntimeException(e); }
        return element;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))){
            objectOutputStream.writeObject(list);
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    @Override
    public E remove(int index) {
        E removedElement = list.get(index);
        list.remove(index);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))){
            objectOutputStream.writeObject(list);
        } catch (Exception e) { throw new RuntimeException(e); }
        return removedElement;
    }
}
