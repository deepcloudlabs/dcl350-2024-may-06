package com.example.crm.entity;

public record Address(AddressType addressType,String line,String zipCode,String city,Country country) {

}
