package com.designpattern.observer;

/**
 * Created by root on 12/11/16.
 */
public interface Subject {
    public void registerObserver(Observer observer);

    public void removeObserver(Observer observer);

    public void notifyAllObservers();
}
