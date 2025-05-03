package rw.leavemanagement.leave.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.leavemanagement.leave.enumerations.ELeaveType;
import rw.leavemanagement.leave.model.LeaveBalance;
import rw.leavemanagement.leave.service.LeaveBalanceService;

@RestController
@RequestMapping("/api/v1/leave/balance")
public class LeaveBalanceController {

    private final LeaveBalanceService leaveBalanceService;

    public LeaveBalanceController(LeaveBalanceService leaveBalanceService){
        this.leaveBalanceService=leaveBalanceService;
    }

    @PostMapping("/create")
    public ResponseEntity<LeaveBalance> createLeaveBalance(
            @RequestParam String userId,
            @RequestParam ELeaveType leaveType,
            @RequestParam Integer year,
            @RequestParam(defaultValue = "0.0") Double initialBalance
    ) {
        LeaveBalance balance = leaveBalanceService.createLeaveBalance(userId, leaveType, year, initialBalance);
        return ResponseEntity.ok(balance);
    }


    // Get the current leave balance for a user for a specific leave type and year
    @GetMapping("/{userId}/{leaveType}/{year}")
    public ResponseEntity<LeaveBalance> getLeaveBalance(
            @PathVariable String userId,
            @PathVariable ELeaveType leaveType,
            @PathVariable Integer year
    ) {
        LeaveBalance balance = leaveBalanceService.getLeaveBalance(userId, leaveType, year);
        return ResponseEntity.ok(balance);
    }

    // Accrue 1.66 days/month for a user
    @PostMapping("/accrue/{userId}/{year}")
    public ResponseEntity<LeaveBalance> accrueLeaveBalance(
            @PathVariable String userId,
            @PathVariable Integer year
    ) {
        LeaveBalance balance = leaveBalanceService.accrueLeaveBalance(userId, year);
        return ResponseEntity.ok(balance);
    }

    // Carry forward leave balance for a user (up to 5 days)
    @PostMapping("/carry-forward/{userId}/{year}")
    public ResponseEntity<LeaveBalance> carryForwardLeaveBalance(
            @PathVariable String userId,
            @PathVariable Integer year
    ) {
        LeaveBalance balance = leaveBalanceService.carryForwardLeaveBalance(userId, year);
        return ResponseEntity.ok(balance);
    }

    // Admin/HR manually adjust the leave balance
    @PutMapping("/adjust/{userId}/{leaveType}/{year}")
    public ResponseEntity<Void> adjustLeaveBalance(
            @PathVariable String userId,
            @PathVariable ELeaveType leaveType,
            @PathVariable Integer year,
            @RequestParam Double adjustmentAmount
    ) {
        leaveBalanceService.adjustLeaveBalance(userId, leaveType, adjustmentAmount, year);
        return ResponseEntity.ok().build();
    }
}
