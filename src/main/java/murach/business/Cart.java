package murach.business;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {
    private ArrayList<LineItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public ArrayList<LineItem> getItems() {
        return items;
    }

    public int getCount() {
        return items.size();
    }

    public void addItem(LineItem item) {
        String code = item.getProduct().getCode();
        int quantity = item.getQuantity();
        
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            // Nếu sản phẩm đã tồn tại, cộng dồn số lượng
            if (lineItem.getProduct().getCode().equals(code)) {
                lineItem.setQuantity(lineItem.getQuantity() + quantity);
                return; // Thoát hàm sau khi cập nhật
            }
        }
        // Nếu vòng lặp chạy xong mà không thấy, tức là sản phẩm mới
        items.add(item);
    }
public void updateItem(LineItem item) {
    String code = item.getProduct().getCode();
    int quantity = item.getQuantity(); // Đây là con số khách vừa gõ
    
    for (int i = 0; i < items.size(); i++) {
        LineItem lineItem = items.get(i);
        if (lineItem.getProduct().getCode().equals(code)) {
            // Ghi đè số lượng mới (Ví dụ: setQuantity(5))
            lineItem.setQuantity(quantity); 
            return;
        }
    }
}
    public void removeItem(LineItem item) {
        String code = item.getProduct().getCode();
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (lineItem.getProduct().getCode().equals(code)) {
                items.remove(i);
                return;
            }
        }
    }
}