package rw.leavemanagement.leave.service;

import rw.leavemanagement.leave.model.LeaveBalance;
import rw.leavemanagement.leave.enumerations.ELeaveType;

public interface LeaveBalanceService {

    LeaveBalance getLeaveBalance(String userId, ELeaveType leaveType, Integer year);

    LeaveBalance accrueLeaveBalance(String userId, Integer year);

    LeaveBalance carryForwardLeaveBalance(String userId, Integer year);

    void adjustLeaveBalance(String userId, ELeaveType leaveType, Double adjustmentAmount, Integer year);
}
