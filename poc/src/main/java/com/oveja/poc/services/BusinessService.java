package com.oveja.poc.services;

import com.oveja.poc.entities.Address;
import com.oveja.poc.entities.Customer;
import com.oveja.poc.entities.support.CustomerForAddress;
import com.oveja.poc.repositories.AddressRepository;
import com.oveja.poc.repositories.CustomerForAddressRepository;
import com.oveja.poc.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusinessService {

    private AddressRepository addressRepository;

    private CustomerRepository customerRepository;

    private CustomerForAddressRepository customerForAddressRepository;

    public BusinessService(@Autowired AddressRepository addressRepository, @Autowired CustomerRepository customerRepository, @Autowired CustomerForAddressRepository customerForAddressRepository) {
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
        this.customerForAddressRepository = customerForAddressRepository;
    }

    public Customer addCustomer(Customer customer) {
        var result = customerRepository.save(customer);
        return result;
    }

    public Customer getCustomer(Long id) {
        Optional<Customer> result = customerRepository.findById(id);
        return result.orElse(null);
    }

    //Este metodo deberia ser creado como una transaccion, para que si falla algo en el medio se haga un rollback y no quede inconsistente en la DB
    //De igual forma, no te vuelvas loco con eso je
    public void addCustomerAddress(Address address, Long customerId) {
        var addressSaved = addressRepository.save(address);
        var support = new CustomerForAddress();
        support.setCustomerId(customerId);
        support.setAddressId(addressSaved.getAddressId());
        customerForAddressRepository.save(support);
    }

    public List<Address> getCustomerAddress(Long id) {
        List<CustomerForAddress> support = customerForAddressRepository.findCustomerForAddressByCustomerId(id);
        List<Address> result = new ArrayList<>();

        support.forEach(cfa -> {
            var address = addressRepository.findById(cfa.getAddressId());
            address.ifPresent(result::add);
        });

        return result;
    }

    //Si queres hacer un metodo que reciba muchas Address todas juntas y guardarlas (es raro, pero bueno puede ser un requerimiento)

    public void addCustomerAddresses(List<Address> addressList, Long customerId) {
        //Reuso el metodo que ya tenia "addCustomerAddress()"
        addressList.forEach(address -> addCustomerAddress(address, customerId));
    }

}
