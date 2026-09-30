package murach.business;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;

@Entity
public class Download implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tự động tăng ID
    private Long downloadId;

    // Thiết lập quan hệ N-1 với User dựa trên cột Email
    @ManyToOne
    @JoinColumn(name = "Email")
    private User user;

    private String productCode;

    @Temporal(TemporalType.TIMESTAMP) // Lưu cả ngày và giờ
    private Date downloadDate;

    public Download() {
        downloadDate = new Date(); // Mặc định là thời điểm hiện tại
    }

    public Long getDownloadId() { return downloadId; }
    public void setDownloadId(Long downloadId) { this.downloadId = downloadId; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }

    public Date getDownloadDate() { return downloadDate; }
    public void setDownloadDate(Date downloadDate) { this.downloadDate = downloadDate; }
}