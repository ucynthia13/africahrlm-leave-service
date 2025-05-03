package rw.leavemanagement.leave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.leavemanagement.leave.model.LeaveRequest;
import rw.leavemanagement.leave.enumerations.ELeaveStatus;
import java.util.List;
import java.util.UUID;

@Repository
public interface ILeaveRequest extends JpaRepository<LeaveRequest, String> {
    List<LeaveRequest> findByUserId(String userId);
    List<LeaveRequest> findByStatus(ELeaveStatus status);
    List<LeaveRequest> findAll();

}
