package com.ra.service.customer;

import com.ra.model.dto.customer.CustomerRequestDTO;
import com.ra.model.dto.customer.CustomerResponseDTO;
import com.ra.model.entity.Customer;
import com.ra.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerResponseDTO> findAll() {
        List<Customer> customers = customerRepository.findAll();
        // covert entity => DTO
        List<CustomerResponseDTO> responseDTOS = new ArrayList<>();
        customers.forEach(customer -> responseDTOS.
                add(CustomerResponseDTO.builder().
                        id(customer.getId()).
                        birthday(customer.getBirthday())
                        .email(customer.getEmail())
                        .fullName(customer.getFullName())
                        .build()));
        return responseDTOS;
    }

    @Override
    public CustomerResponseDTO create(CustomerRequestDTO customerRequestDTO) {
        Customer customer = Customer.builder()
                .fullName(customerRequestDTO.getFullName())
                .email(customerRequestDTO.getEmail())
                .password(customerRequestDTO.getPassword())
                .birthday(customerRequestDTO.getBirthday())
                .build();
        Customer customerNew = customerRepository.save(customer);

        CustomerResponseDTO responseDTO = CustomerResponseDTO.builder()
                .id(customerNew.getId())
                .fullName(customerNew.getFullName())
                .email(customerNew.getEmail())
                .birthday(customerNew.getBirthday())
                .build();
        return responseDTO;
    }
}
