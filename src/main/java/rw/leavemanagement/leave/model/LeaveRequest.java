package rw.leavemanagement.leave.model;

import jakarta.persistence.*;
import lombok.*;
import rw.leavemanagement.leave.enumerations.ELeaveStatus;
import rw.leavemanagement.leave.enumerations.ELeaveType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "leave_request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequest extends Base {

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "leave_type", nullable = false)
    private ELeaveType leaveType;

    @Column(nullable = false, name = "start_at")
    private LocalDate startAt;

    @Column(nullable = false, name = "end_at")
    private LocalDate endAt;
    
    @Column(columnDefinition = "TEXT")
    private String reason;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "leave_status")
    private ELeaveStatus status = ELeaveStatus.PENDING;
    
    @OneToMany(mappedBy = "leaveRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LeaveDocument> documents = new ArrayList<>();
    
    @Column(name = "approver_id")
    private String approverId;

    @Column(name = "approver_comments")
    private String approverComments;

    @Column(name = "approval_date")
    private LocalDateTime approvalDate;



}
