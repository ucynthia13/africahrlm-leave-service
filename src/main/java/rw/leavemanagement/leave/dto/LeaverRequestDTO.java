package rw.leavemanagement.leave.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import rw.leavemanagement.leave.enumerations.ELeaveType;

import java.time.LocalDate;

@Data
public class LeaverRequestDTO {

    @NotNull(message = "User ID is required")
    private String userId;

    @NotNull(message = "Leave type is required")
    private ELeaveType leaveType;

    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Start date must be today or in the future")
    private LocalDate fromDate;

    @NotNull(message = "End date is required")
    @FutureOrPresent(message = "End date must be today or in the future")
    private LocalDate toDate;

    @Size(max = 500, message = "Reason cannot exceed 500 characters")
    private String reason;

    private MultipartFile document;

}
