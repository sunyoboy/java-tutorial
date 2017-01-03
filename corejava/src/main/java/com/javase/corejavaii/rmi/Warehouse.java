package com.javase.corejavaii.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by root on 1/3/17.
 */
public interface Warehouse extends Remote {
    double getPrice(String description) throws RemoteException;
}
