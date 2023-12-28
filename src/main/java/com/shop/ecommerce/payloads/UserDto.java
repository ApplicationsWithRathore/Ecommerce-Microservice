package com.shop.ecommerce.payloads;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class UserDto {
    private Integer uId;
    private String userName;
    private String email;
    private String password;
}
