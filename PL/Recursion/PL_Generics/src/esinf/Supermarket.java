/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esinf;

import java.time.LocalDate;
import java.util.*;

/**
 *
 * @author DEI-ISEP
 */
public class Supermarket {
    Map <Invoice, Set<Product>> sup;
    
    Supermarket() {
        sup = new HashMap<>();
    }
    
    // Reads invoices from a list of String
    void getInvoices(List <String> l) throws Exception {
        Invoice invoice = null;
        for (String c : l){
            String[] list = c.split(",");
            if(list[0].compareTo("I")==0){
                invoice = new Invoice(list[1],list[2]);
                sup.put(invoice,new HashSet<>());
            }
            if(list[0].compareTo("P")==0){
                Set<Product> productSet = sup.get(invoice);
                Product product = new Product(list[1],Integer.parseInt(list[2]),Long.parseLong(list[3]));
                productSet.add(product);
            }
        }
        for (Invoice name: sup.keySet()){
            String key =name.toString();
            String value =sup.get(name).toString();
            System.out.println(key + " " + value);
        }
    }


    
    // returns a set in which each number is the number of products in the r
    // invoice 
    Map<Invoice, Integer> numberOfProductsPerInvoice() {
        Map<Invoice,Integer> map = new HashMap<>();
        Set<Invoice> invoc = sup.keySet();
        for (Invoice invoice:invoc){
            Set<Product> products = sup.get(invoice);
            map.put(invoice, products.size());
        }
        return map;
    }

    // returns a Set of invoices in which each date is >d1 and <d2
    Set<Invoice> betweenDates(LocalDate d1, LocalDate d2) {
        Set<Invoice> set = new HashSet<>();
        Set<Invoice> invoc = sup.keySet();
        for (Invoice invoice:invoc){
            if(invoice.getDate().isAfter(d1) && invoice.getDate().isBefore(d2)){
                set.add(invoice);
            }
        }
        return set;
    }
    
    // returns the sum of the price of the product in all the invoices
    long totalOfProduct(String productId) {
        long sum=0;
        Set<Invoice> invoc = sup.keySet();

        for (Invoice invoice:invoc){
            for (Product product:sup.get(invoice)){
                if(product.getIdentification().compareTo(productId)==0){
                    sum=sum+(product.getPrice()*product.getQuantity());
                }
            }
        }
        return sum;
    }
    
    // converts a map of invoices and troducts to a map which key is a product 
    // identification and the values are a set of the invoices in which it appears
    Map<String, Set<Invoice>> convertInvoices() {
        Map<String, Set<Invoice>> newMap = new HashMap<>();
        Set<Invoice> invoc = sup.keySet();
        for (Invoice invoice:invoc){
            for (Product product:sup.get(invoice)){
                newMap.put(product.getIdentification(), new HashSet<>());
            }
        }

        for(Invoice invoice: sup.keySet()) {
            for(Product product: sup.get(invoice)) {
                Set<Invoice> invoicesDoProducto = newMap.get(product.getIdentification());
                invoicesDoProducto.add(invoice);
            }
        }

        return newMap;
    }
}
