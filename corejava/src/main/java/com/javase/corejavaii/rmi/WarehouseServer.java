package com.javase.corejavaii.rmi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;

/**
 * Created by root on 1/3/17.
 */
public class WarehouseServer {
    public static void main(String[] args) throws RemoteException, NamingException {
        System.out.println("Constructing server implemention...");


        WarehouseImpl centralWarehouse = new WarehouseImpl();
        System.out.println("Binding server implemention to registry...");
        Context namingContext = new InitialContext();
        namingContext.bind("rmi:central_warehouse", centralWarehouse);
        System.out.println("Wating for invocations from clients");
    }
}
