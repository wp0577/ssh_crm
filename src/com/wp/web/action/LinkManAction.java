package com.wp.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wp.domain.LinkMan;
import com.wp.service.LinkManService;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {

    private LinkMan linkMan = new LinkMan();

    private LinkManService linkManService;


    public String add() {
        linkManService.add(linkMan);
        return "toList";
    }


    public void setLinkManService(LinkManService linkManService) {
        this.linkManService = linkManService;
    }

    @Override
    public LinkMan getModel() {
        return linkMan;
    }
}
