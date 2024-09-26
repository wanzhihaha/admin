package main.java.com.cellosquare.adminapp.common.vo;

import java.io.Serializable;

public class PageBean implements Serializable {

    // 指定的或是页面参数
    private int currentPage; // 当前页
    private int pageSize; // 每页显示多少条
    private int recordCount; // 总记录数
    // 计算
    private int pageCount; // 总页数
    private int beginPageIndex; // 页码列表的开始索引（包含）
    private int endPageIndex; // 页码列表的结束索引（包含）
    private int prePage; //上一页码
    private int nextPage; //下一页码
    private int prePageListIndex;//起始页-10
    private int nextPageListIndex;//结束页+ 10;

    /**
     * 只接受前4个必要的属性，会自动的计算出其他3个属生的值
     *
     * @param currentPage
     * @param pageSize
     * @param recordCount
     */
    public PageBean(int currentPage, int pageSize, int recordCount) {

        this.pageSize = pageSize;
        this.recordCount = recordCount;

        // 计算总页码
        pageCount = (recordCount + pageSize - 1) / pageSize;
        this.currentPage = currentPage > pageCount ? 1 : currentPage;

        // 计算 beginPageIndex 和 endPageIndex
        // >> 总页数不多于10页，则全部显示
        if (pageCount <= pageSize) {
            beginPageIndex = 1;
            endPageIndex = pageCount;
        } else {
            int i = this.currentPage % pageSize;
            if (i == 0) {
                beginPageIndex = this.currentPage - 9;
                endPageIndex = beginPageIndex + 9;
            } else {
                beginPageIndex = this.currentPage - i + 1;
                endPageIndex = pageSize - i + this.currentPage;
                if (endPageIndex > pageCount) {
                    endPageIndex = pageCount;
                }
            }

        }
        if (this.currentPage != 1) {
            prePage = this.currentPage - 1;
        }
        nextPage = this.currentPage + 1;
        if (nextPage >= pageCount) {
            nextPage = 0;
        }
        prePageListIndex = beginPageIndex - 10;
        if (prePageListIndex <= 0) {
            prePageListIndex = 0;
        }
        nextPageListIndex = endPageIndex + 1;
        if (nextPageListIndex > pageCount) {
            nextPageListIndex = 0;
        }
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", recordCount=" + recordCount +
                ", pageCount=" + pageCount +
                ", beginPageIndex=" + beginPageIndex +
                ", endPageIndex=" + endPageIndex +
                ", prePage=" + prePage +
                ", nextPage=" + nextPage +
                ", prePageListIndex=" + prePageListIndex +
                ", nextPageListIndex=" + nextPageListIndex +
                '}';
    }

    public static void main(String[] args) {
        //PageBean pageBean = new PageBean(15, 10, 221);

        for (int i = 1; i <= 25; i++) {
            String format = "(" + i + "): [";
            PageBean pageBean = new PageBean(i, 10, 221);

            for (int j = pageBean.beginPageIndex; j <= pageBean.endPageIndex; j++) {
                format += j + ",";
                if (j == pageBean.endPageIndex) {
                    format += "]";
                }
            }
            System.out.println(format);
            System.out.println(pageBean);
        }

    }


    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getBeginPageIndex() {
        return beginPageIndex;
    }

    public void setBeginPageIndex(int beginPageIndex) {
        this.beginPageIndex = beginPageIndex;
    }

    public int getEndPageIndex() {
        return endPageIndex;
    }

    public void setEndPageIndex(int endPageIndex) {
        this.endPageIndex = endPageIndex;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getPrePageListIndex() {
        return prePageListIndex;
    }

    public void setPrePageListIndex(int prePageListIndex) {
        this.prePageListIndex = prePageListIndex;
    }

    public int getNextPageListIndex() {
        return nextPageListIndex;
    }

    public void setNextPageListIndex(int nextPageListIndex) {
        this.nextPageListIndex = nextPageListIndex;
    }
}