package com.Ali.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Address")
public class Address {

    @Id
    private ObjectId id;

    private String streetAddress;
    private String city;
    private String stateProvince;
    private String postalCode;
    private String country;
}
