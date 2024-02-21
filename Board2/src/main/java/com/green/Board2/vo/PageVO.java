package com.green.Board2.vo;

//페이지 정보를 담고 있는 클래스
public class PageVO {
    private int nowPage;          // 현재 선택된 페이지 번호
    private int totalDataCnt;     // 전체 게시글 수(전체 데이터 수)
    private int displayDataCnt;   // 한 페이지에서 보여지는 데이터 수 ()
    private int displayPageCnt;   // 한 페이지에서 보여지는 페이지 수 (댓글페이지)
    private int beginPage;        // 화면에 첫번째 페이지 번호
    private int endPage;          // 화면에 마지막 페이지 번호
    private boolean prev;         // 이전 버튼의 유무
    private boolean next;         // 다음 버튼의 유무
    private int totalPageCnt;     // 전체 페이지 수

    //생성자 생성
    public PageVO(){
        nowPage = 1;
        displayDataCnt = 10; //
        displayPageCnt = 10; //아래에 게시판 개수
    }

    public void setPageInfo(){
        //화면에 보이는 마지막 페이지 번호 세팅
        endPage = (int)Math.ceil(nowPage / (double)displayPageCnt) * displayPageCnt;

        //화면에 보이는 첫번째 페이지 번호 세팅
        beginPage = endPage - displayPageCnt + 1;

        //전체 페이지 수
        totalPageCnt = (int)Math.ceil(totalDataCnt / (double) displayDataCnt);

        //next(다음) 버튼의 유무
        if(endPage < totalPageCnt){
            next = true;
        }
        else {
            next = false;
            endPage = totalPageCnt;
        }
        //prev(이전) 버튼의 유무
        prev = beginPage == 1 ? false : true;
    }

    public void setTotalDataCnt(int totalDataCnt){
        this.totalDataCnt = totalDataCnt;
    }

    public int getBeginPage(){
        return beginPage;
    }

    public int getEndPage(){
        return endPage;
    }

    public boolean getPrev(){
        return prev;
    }

    public boolean getNext(){
        return next;
    }

    public void setNowPage(int nowPage){
        this.nowPage = nowPage;
    }

    public int getDisplayDataCnt(){
        return displayDataCnt;
    }

    public int getNowPage(){
        return nowPage;
    }

}
