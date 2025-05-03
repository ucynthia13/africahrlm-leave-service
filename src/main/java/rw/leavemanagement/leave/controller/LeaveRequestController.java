package rw.leavemanagement.leave.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.leavemanagement.leave.dto.LeaveStatusChange;
import rw.leavemanagement.leave.dto.LeaverRequestDTO;
import rw.leavemanagement.leave.model.LeaveRequest;
import rw.leavemanagement.leave.service.LeaveRequestService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/leave")
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    public LeaveRequestController(LeaveRequestService leaveRequestService){
        this.leaveRequestService=leaveRequestService;
    }

    @PostMapping("/request")
    public ResponseEntity<LeaveRequest> requestForLeave(@ModelAttribute @Valid LeaverRequestDTO leaverRequestDTO) throws Exception {
        LeaveRequest request = leaveRequestService.requestForLeave(leaverRequestDTO);
        return ResponseEntity.ok(request);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<LeaveRequest>> getLeaveHistory(@PathVariable String userId) {
        return ResponseEntity.ok(leaveRequestService.getLeaveHistory(userId));
    }

    @PutMapping("/approve/{requestId}")
    public ResponseEntity<LeaveRequest> approveLeave(
            @PathVariable String requestId,
            @RequestParam String approverId,
            @RequestParam boolean approved,
            @RequestParam(required = false) String comment
    ) {
        return ResponseEntity.ok(leaveRequestService.approveLeave(requestId, approverId, approved, comment));
    }

    @PatchMapping("/{leaveId}/status")
    public ResponseEntity<Map<String, String>> updateLeaveStatus(
            @PathVariable String leaveId,
            @RequestBody @Valid LeaveStatusChange statusChange) {

        leaveRequestService.updateLeaveStatus(leaveId, statusChange.getLeaveStatus());

        Map<String, String> response = new HashMap<>();
        response.put("message", "Leave status updated successfully.");

        return ResponseEntity.ok(response);
    }


    @GetMapping("/leaves")
    public ResponseEntity<List<LeaveRequest>> getLeavesByStatus(
            @RequestParam("status") String status) {
        List<LeaveRequest> leaves = leaveRequestService.getLeavesByStatus(status);
        return ResponseEntity.ok(leaves);
    }

    @GetMapping("/leaves/year")
    public ResponseEntity<List<LeaveRequest>> getLeavesByYear(@RequestParam int year) {
        List<LeaveRequest> leaves = leaveRequestService.getLeavesByYear(year);
        return ResponseEntity.ok(leaves);
    }


}
