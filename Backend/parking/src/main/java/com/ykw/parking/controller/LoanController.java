package com.ykw.parking.controller;

import com.ykw.parking.mapper.LoanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoanController {
@Autowired
    LoanMapper loanMapper;
    @RequestMapping("/loanList")
    public String loanList(HttpSession session){
        System.out.println(loanMapper.queryAllLoan());
        session.setAttribute("LoanList",loanMapper.queryAllLoan());
        session.setAttribute("LoanListindex",loanMapper.queryAllLoanNum());

        return "loan-list";
    }
}
