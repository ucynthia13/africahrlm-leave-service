package rw.leavemanagement.leave.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rw.leavemanagement.leave.model.LeaveBalance;
import rw.leavemanagement.leave.repository.ILeaveBalance;
import rw.leavemanagement.leave.enumerations.ELeaveType;
import rw.leavemanagement.leave.service.LeaveBalanceService;

import java.time.LocalDateTime;
import java.time.Month;


@Service
@RequiredArgsConstructor
public class LeaveBalanceServiceImpl implements LeaveBalanceService {

    private final ILeaveBalance iLeaveBalance;

    // Automatic monthly accrual (1.66 days/month, 20 days/year)
    @Override
    public LeaveBalance accrueLeaveBalance(String userId, Integer year) {
        LeaveBalance balance = iLeaveBalance.findByUserIdAndYear(userId, year).orElse(new LeaveBalance());

        // Initialize balance if it doesn't exist
        if (balance.getBalance() == null) {
            balance.setBalance(0.0);
        }

        // Add monthly accrual (1.66 days/month)
        double monthlyAccrual = 1.66;
        balance.setBalance(balance.getBalance() + monthlyAccrual);

        // Update in the repository
        return iLeaveBalance.save(balance);
    }

    // Carry forward logic: Max 5 days can be carried over to the next year
    @Override
    public LeaveBalance carryForwardLeaveBalance(String userId, Integer year) {
        LeaveBalance balance = iLeaveBalance.findByUserIdAndYear(userId, year)
                .orElseThrow(() -> new IllegalArgumentException("Leave balance not found"));

        // Carry forward logic: only up to 5 days can be carried over to the next year
        if (balance.getBalance() > 20.0) {
            double carryForward = Math.min(5.0, balance.getBalance() - 20.0); // Max 5 days carry over
            balance.setCarriedForwardDays(carryForward);
            balance.setBalance(20.0); // Max balance allowed per year
            balance.setCarryExpirationDate(LocalDateTime.of(year + 1, Month.JANUARY, 31, 0, 0, 0, 0)); // Expire on Jan 31st next year
            iLeaveBalance.save(balance);
        }

        return balance;
    }

    // Manual adjustment by Admin/HR for a specific user and leave type
    @Override
    public void adjustLeaveBalance(String userId, ELeaveType leaveType, Double adjustmentAmount, Integer year) {
        LeaveBalance balance = iLeaveBalance.findByUserIdAndLeaveTypeAndYear(userId, leaveType, year)
                .orElseThrow(() -> new IllegalArgumentException("Leave balance not found"));

        balance.setBalance(balance.getBalance() + adjustmentAmount);
        iLeaveBalance.save(balance);
    }

    // Retrieve leave balance for a specific user and year
    @Override
    public LeaveBalance getLeaveBalance(String userId, ELeaveType leaveType, Integer year) {
        return iLeaveBalance.findByUserIdAndLeaveTypeAndYear(userId, leaveType, year)
                .orElseThrow(() -> new IllegalArgumentException("Leave balance not found"));
    }
}
