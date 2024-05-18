package com.example.gateway.auditing;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class BaseTimeEntity {

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

}
