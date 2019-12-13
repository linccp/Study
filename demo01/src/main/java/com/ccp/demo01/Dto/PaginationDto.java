package com.ccp.demo01.Dto;

import com.ccp.demo01.model.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * {崔纯鹏}
 * 2019/12/13 9:14
 * Version:1.0
 */
public class PaginationDto {
    private List<QuestionDto> questions;
    private boolean showFirst;
    private boolean showPrevious;
    private boolean showNext;
    private boolean showLast;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalpage;

    public Integer getTotalpage() {
        return totalpage;
    }

    public void setPagination(Integer totalcount, Integer page, Integer size) {

        this.page = page;
//        totalpage = Integer.valueOf(String.valueOf(Math.ceil(Double.valueOf(totalcount) / Double.valueOf(size))));

        if(totalcount % size == 0){
            totalpage = totalcount/size;
        }else{
            totalpage = totalcount/size + 1;
        }
//        判断分页超限
        if(page <1){
            page = 1;
        }else if(page > totalpage){
            page = totalpage;
        }
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if(page - i > 0){
                pages.add(0,page - i);
            }
            if(page + i <= totalpage){
                pages.add(page + i );
            }
        }
//            是否显示上一页
            if(page == 1){
                showPrevious = false;
            }else{
                showPrevious = true;
            }
//            是否显示上一页
            if(page == totalpage){
                showNext = false;
            }else{
                showNext = true;
            }
//            是否显示第一页
            if(pages.contains(1)){
                showFirst = false;
            }else{
                showFirst = true;
            }
//            是否显示最后一页
            if(pages.contains(totalpage)){
                showLast = false;
            }else{
                showLast = true;
            }
        //        是否展示首、末页/上页、下页
//        if(page == 1){
//            showFirst = false;
//            showPrevious = false;
//        }else if(page < 5){
//            showFirst = false;
//        }else if(page > totalpage - 3){
//            showLast = false;
//        }else if(page == totalpage){
//            showNext = false;
//        }

    }


    public List<QuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDto> questions) {
        this.questions = questions;
    }

    public boolean isShowFirst() {
        return showFirst;
    }

    public void setShowFirst(boolean showFirst) {
        this.showFirst = showFirst;
    }

    public boolean isShowPrevious() {
        return showPrevious;
    }

    public void setShowPrevious(boolean showPrevious) {
        this.showPrevious = showPrevious;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    public boolean isShowLast() {
        return showLast;
    }

    public void setShowLast(boolean showLast) {
        this.showLast = showLast;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

}
