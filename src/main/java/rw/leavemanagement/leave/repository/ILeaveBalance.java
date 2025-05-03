package rw.leavemanagement.leave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rw.leavemanagement.leave.enumerations.ELeaveType;
import rw.leavemanagement.leave.model.LeaveBalance;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ILeaveBalance extends JpaRepository<LeaveBalance, String> {

    // Find the leave balance by user ID, leave type, and year
    Optional<LeaveBalance> findByUserIdAndLeaveTypeAndYear(String userId, ELeaveType leaveType, Integer year);

    // Find the leave balance by user ID and year (if leave type is not specified)
    Optional<LeaveBalance> findByUserIdAndYear(String userId, Integer year);

    // Find all leave balances by user ID (for history or multiple leave types across years)
    List<LeaveBalance> findByUserId(String userId);
}
