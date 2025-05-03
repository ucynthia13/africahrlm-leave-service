package rw.leavemanagement.leave.service;

import org.springframework.data.jpa.repository.Query;
import rw.leavemanagement.leave.dto.LeaverRequestDTO;
import rw.leavemanagement.leave.model.LeaveRequest;
import rw.leavemanagement.leave.enumerations.ELeaveStatus;
import java.time.LocalDate;
import java.util.List;

public interface LeaveRequestService {
    LeaveRequest requestForLeave(LeaverRequestDTO leaverRequestDTO) throws Exception;
    List<LeaveRequest> getLeaveHistory(String userId);
    LeaveRequest approveLeave(String requestId, String approverId, boolean approved, String comment);
    void updateLeaveStatus(String leaveId, String newStatus);
    List<LeaveRequest> getLeavesByStatus(String status);
    List<LeaveRequest> getLeavesByYear(int year);
    }
