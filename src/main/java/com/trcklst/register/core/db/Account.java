package com.trcklst.register.core.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@Document("accounts")
@NoArgsConstructor
@AllArgsConstructor
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
