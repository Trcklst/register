package com.trcklst.register.core.db;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document("accounts")
public class Account {

    @Id
    private Integer id;
    @Field
    private String username;
    @Field
    private String password;
    @Field
    private AuthoritiesType authority;
    @Field
    private boolean active;
}
