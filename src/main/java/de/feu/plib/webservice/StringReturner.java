package de.feu.plib.webservice;

import de.feu.plib.xml.catalogue.CatalogueType;
import de.feu.plib.xml.catalogue.ItemType;
import de.feu.plib.xml.query.QueryType;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import java.util.ArrayList;
import java.util.List;

@WebService
@SOAPBinding(style=Style.DOCUMENT)
public class StringReturner {

    public CatalogueType query(QueryType query) {
        CatalogueType c = new CatalogueType();
        ItemType itemType = new ItemType();
        itemType.setClassRef("classref");
        c.getItem().add(itemType);
        return c;
    }

    public String getMyString(String stringToAdd) {
		return stringToAdd + " lautet der String";
	}

    public ArrayList<Customer> getAllCustomer() {
        ArrayList<Customer> customerList = new ArrayList<Customer>();

        Customer c = new Customer();
        c.setName("name");
        c.setVorname("vorname");
        customerList.add(c);

        Customer c2 = new Customer();
        c2.setName("name2");
        c2.setVorname("vorname2");
        customerList.add(c2);

        return customerList;
    }

    public Customer getOneCustomerByName(String name) {
        Customer c = new Customer();
        c.setName(name);
        c.setVorname("Peter");
        return c;
    }
}
