package com.javase.corejavaii.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

/**
 * Created by root on 1/3/17.
 */
public class WarehouseImpl extends UnicastRemoteObject implements Warehouse {

    private HashMap<String, Double> hashMap;

    public WarehouseImpl(HashMap<String, Double> hashMap) throws RemoteException {
        this.hashMap = hashMap;
    }

    public WarehouseImpl() throws RemoteException {
        hashMap = new HashMap<>();
        hashMap.put("apple", 5.6d);
        hashMap.put("orange", 2.4d);
    }

    @Override
    public double getPrice(String description) throws RemoteException {
        Double price = hashMap.get(description);
        return (price == null)? 0: price;
    }
}
