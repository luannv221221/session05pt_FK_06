package com.ra.model.dto.customer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ra.validate.CustomerUnique;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerRequestDTO {
    @NotBlank(message = "Không rỗng")
    private String fullName;
    @NotBlank(message = "Không rỗng")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "Không đúng định dạng email")
    @CustomerUnique(message = "Email da ton tai")
    private String email;
    @NotBlank(message = "Không rỗng")
    @Size(min = 8,message = "tối thiếu 8 ký tự")
    private String password;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    @Past(message = "ngày sinh phải là ngày quá khứ")
    private LocalDate birthday;
}
