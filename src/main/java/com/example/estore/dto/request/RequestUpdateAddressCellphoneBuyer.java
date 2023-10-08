package com.example.estore.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

@Data
@Component
public class RequestUpdateAddressCellphoneBuyer {

    private Long id;

    @NotBlank(message = "address can't blank")
    private String address;

    @NotBlank(message = "cellphone can't blank")
    @Length(max = 12, min = 11, message = "number not valid")
    private String cellphone;
}
