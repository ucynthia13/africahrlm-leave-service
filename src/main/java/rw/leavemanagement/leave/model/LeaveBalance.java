package rw.leavemanagement.leave.model;

import jakarta.persistence.*;
import lombok.*;
import rw.leavemanagement.leave.enumerations.ELeaveType;

import java.time.LocalDateTime;

@Entity
@Table(name = "leave_balance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeaveBalance extends Base {

    @Column(name = "user_id", nullable = false)
    private String userId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "leave_type", nullable = false)
    private ELeaveType leaveType;
    
    @Column(nullable = false, name = "leave_balance_days")
    private Double balance;
    
    @Column(nullable = false, name = "leave_year")
    private Integer year;
    
    // For tracking carried forward days
    private Double carriedForwardDays;
    
    // The date when carried days will expire (Jan 31 Kigali time as per given)
    private LocalDateTime carryExpirationDate;

}
