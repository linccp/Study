package com.ccp.demo01.Dto;


import java.util.ArrayList;
import java.util.List;

/**
 * {崔纯鹏}
 * 2019/12/13 20:10
 * Version:1.0
 */
public class ProfileDto {


    private List<QuestionDto> profiles;
    private boolean showFirst;
    private boolean showPrevious;
    private boolean showNext;
    private boolean showLast;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer profilepage;

    public Integer getProfilepage() {
        return profilepage;
    }

    public void setProfile(Integer profilecount, Integer page, Integer size) {

        this.page = page;

        if (profilecount % size == 0) {
            profilepage = profilecount / size;
        } else {
            profilepage = profilecount / size + 1;
        }
//        判断分页超限
        if (page < 1) {
            page = 1;
        } else if (page > profilepage) {
            page = profilepage;
        }
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= profilepage) {
                pages.add(page + i);
            }
        }
//            是否显示上一页
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }
//            是否显示上一页
        if (page == profilepage) {
            showNext = false;
        } else {
            showNext = true;
        }
//            是否显示第一页
        if (pages.contains(1)) {
            showFirst = false;
        } else {
            showFirst = true;
        }
//            是否显示最后一页
        if (pages.contains(profilepage)) {
            showLast = false;
        } else {
            showLast = true;
        }

    }

        public List<QuestionDto> getProfiles() {
            return profiles;
        }

        public void setProfiles(List<QuestionDto> profiles) {
            this.profiles = profiles;
        }

        public void setProfilepage(Integer profilepage) {
            this.profilepage = profilepage;
        }

        public boolean isShowFirst () {
            return showFirst;
        }

        public void setShowFirst ( boolean showFirst){
            this.showFirst = showFirst;
        }

        public boolean isShowPrevious () {
            return showPrevious;
        }

        public void setShowPrevious ( boolean showPrevious){
            this.showPrevious = showPrevious;
        }

        public boolean isShowNext () {
            return showNext;
        }

        public void setShowNext ( boolean showNext){
            this.showNext = showNext;
        }

        public boolean isShowLast () {
            return showLast;
        }

        public void setShowLast ( boolean showLast){
            this.showLast = showLast;
        }

        public Integer getPage () {
            return page;
        }

        public void setPage (Integer page){
            this.page = page;
        }

        public List<Integer> getPages () {
            return pages;
        }

        public void setPages (List < Integer > pages) {
            this.pages = pages;
        }



    }
