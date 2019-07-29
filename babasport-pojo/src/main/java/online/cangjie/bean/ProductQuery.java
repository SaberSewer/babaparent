package online.cangjie.bean;

public class ProductQuery extends Product {
    private Integer pageNo = 1;
    private Integer pageSize = 10;
    private Integer startRow;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        this.startRow = (pageNo - 1) * pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.startRow = (pageNo - 1) * pageSize;
        this.pageSize = pageSize;
    }

    public Integer getStartRow() {
        return startRow;
    }

    @Override
    public String toString() {
        return super.toString() + ", " +
                "ProductQuery{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", startRow=" + startRow +
                '}';
    }
}
