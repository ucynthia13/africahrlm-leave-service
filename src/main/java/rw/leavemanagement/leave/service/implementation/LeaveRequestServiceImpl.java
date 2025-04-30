package rw.leavemanagement.leave.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rw.leavemanagement.leave.dto.LeaverRequestDTO;
import rw.leavemanagement.leave.model.LeaveRequest;
import rw.leavemanagement.leave.repository.ILeaveRequest;
import rw.leavemanagement.leave.service.LeaveRequestService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveRequestServiceImpl implements LeaveRequestService {
    private final ILeaveRequest iLeaveRequest;
    @Override
    public LeaveRequest requestForLeave(LeaverRequestDTO leaverRequestDTO) throws Exception {
        if (leaverRequestDTO.getToDate().isBefore(leaverRequestDTO.getFromDate())) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }

        MultipartFile doc = leaverRequestDTO.getDocument();
        String leaveDocUrl = null;

        // TODO: Handle file upload logic (e.g. S3 or local storage)
        if (doc != null && !doc.isEmpty()) {
        }

        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setUserId(leaverRequestDTO.getUserId());
        leaveRequest.setLeaveType(leaverRequestDTO.getLeaveType());
        leaveRequest.setStartAt(leaverRequestDTO.getFromDate());
        leaveRequest.setEndAt(leaverRequestDTO.getToDate());
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
}
