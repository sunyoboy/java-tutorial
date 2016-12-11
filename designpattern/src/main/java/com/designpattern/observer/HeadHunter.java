package com.designpattern.observer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by root on 12/11/16.
 */
public class HeadHunter implements Subject {

    private ArrayList<String> list;

    private ArrayList<Observer> observers;

    public HeadHunter() {
        list = new ArrayList<String>();
        observers = new ArrayList<Observer>();
    }

    public void addJob(String job) {
        list.add(job);
        notifyAllObservers();
    }

    public ArrayList<String> getJobs() {
        return list;
    }

    public void notifyAllObservers() {
        for(Observer observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for(String s : list) {
            stringBuilder.append(s);
            stringBuilder.append(",");
        }
        return stringBuilder.toString();
    }
}
