package rw.leavemanagement.leave.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rw.leavemanagement.leave.dto.LeaverRequestDTO;
import rw.leavemanagement.leave.enumerations.ELeaveStatus;
import rw.leavemanagement.leave.exception.ResourceNotFoundException;
import rw.leavemanagement.leave.model.LeaveRequest;
import rw.leavemanagement.leave.repository.ILeaveRequest;
import rw.leavemanagement.leave.service.LeaveRequestService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveRequestServiceImpl implements LeaveRequestService {
    private final ILeaveRequest iLeaveRequest;
    @Override
    public LeaveRequest requestForLeave(LeaverRequestDTO leaverRequestDTO) throws Exception {

        MultipartFile doc = leaverRequestDTO.getDocument();
        String leaveDocUrl = null;

        // TODO: Handle file upload logic (e.g. S3 or local storage)
        if (doc != null && !doc.isEmpty()) {
        }

        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setUserId(leaverRequestDTO.getUserId());
        leaveRequest.setLeaveType(leaverRequestDTO.getLeaveType());
        leaveRequest.setStartAt(LocalDate.parse(leaverRequestDTO.getFromDate()));
        leaveRequest.setEndAt(LocalDate.parse(leaverRequestDTO.getToDate()));
        leaveRequest.setReason(leaverRequestDTO.getReason());
        return iLeaveRequest.save(leaveRequest);
    }

    @Override
    public List<LeaveRequest> getLeaveHistory(String userId) {
        return iLeaveRequest.findByUserId(userId);
    }

    @Override
    public LeaveRequest approveLeave(String requestId, String approverId, boolean approved, String comment) {
        return null;
    }

    @Override
    public void updateLeaveStatus(String leaveId, String newStatus) {
        LeaveRequest leaveRequest = iLeaveRequest.findById(leaveId)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found"));

        leaveRequest.setStatus(ELeaveStatus.valueOf(newStatus));
        iLeaveRequest.save(leaveRequest);
    }

    @Override
    public List<LeaveRequest> getLeavesByStatus(String status) {
        ELeaveStatus leaveStatus;
        try {
            leaveStatus = ELeaveStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Invalid leave status: " + status);
        }

        List<LeaveRequest> leaves = iLeaveRequest.findByStatus(leaveStatus);
        if (leaves.isEmpty()) {
            throw new ResourceNotFoundException("No leave requests found with status: " + status);
        }

        return leaves;
    }

    @Override
    public List<LeaveRequest> getLeavesByYear(int year) {
        List<LeaveRequest> allLeaves = iLeaveRequest.findAll();

        List<LeaveRequest> filteredLeaves = allLeaves.stream()
                .filter(leave -> leave.getCreatedAt() != null && leave.getCreatedAt().getYear() == year)
                .toList();

        return filteredLeaves; // Don't throw; let frontend handle empty state
    }




}
