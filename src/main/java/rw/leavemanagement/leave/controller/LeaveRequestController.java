package rw.leavemanagement.leave.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.leavemanagement.leave.dto.LeaverRequestDTO;
import rw.leavemanagement.leave.model.LeaveRequest;
import rw.leavemanagement.leave.service.LeaveRequestService;
import java.util.List;

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
}
