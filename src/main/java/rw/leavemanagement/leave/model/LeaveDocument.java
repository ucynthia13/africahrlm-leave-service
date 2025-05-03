package rw.leavemanagement.leave.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "leave_documents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeaveDocument extends Base {

    @ManyToOne
    @JoinColumn(name = "leave_request_id", nullable = false)
    private LeaveRequest leaveRequest;
    
    @Column(nullable = false, name = "file_name")
    private String fileName;
    
    @Column(nullable = false, name = "file_type")
    private String fileType;
    
    @Column(nullable = false, name = "file_path")
    private String filePath;

}
