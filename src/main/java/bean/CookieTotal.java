package bean;

import java.util.List;

public class CookieTotal {
    int total;
    List<CookieDataBean> rows;

    public CookieTotal(int total,List<CookieDataBean> rows){
        this.total=total;
        this.rows=rows;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setRows(List<CookieDataBean> rows) {
        this.rows = rows;
    }

    public List<CookieDataBean> getRows() {
        return rows;
    }
}
