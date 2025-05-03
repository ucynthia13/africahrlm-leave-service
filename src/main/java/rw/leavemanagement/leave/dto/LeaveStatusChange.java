package rw.leavemanagement.leave.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LeaveStatusChange {

    @NotNull(message = "Leave status must not be null")
    // Optional: Add allowed values for more control
    @Pattern(regexp = "^(PENDING|APPROVED|REJECTED|CANCELLED)$",
            message = "Invalid leave status")
    private String leaveStatus;
}
