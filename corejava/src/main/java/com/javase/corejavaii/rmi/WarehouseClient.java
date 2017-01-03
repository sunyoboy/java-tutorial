package com.javase.corejavaii.rmi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Enumeration;

/**
 * Created by root on 1/3/17.
 */
public class WarehouseClient {
    public static void main(String[] args) throws NamingException, RemoteException {
        Context namingContext = new InitialContext();

        System.out.println("RMI registry bindings : ");
        Enumeration<NameClassPair> e = namingContext.list("rmi://localhost");

        while(e.hasMoreElements()) {
            System.out.println(e.nextElement().getName());
        }

        String url = "rmi://localhost/central_warehouse";
        Warehouse centralWarehouse = (Warehouse)namingContext.lookup(url);
        String description = "apple";
        double price = centralWarehouse.getPrice(description);
        centralWarehouse.getPrice(description + " -> " + price);
    }
}
