package com.ifast.sys.domain;

import java.io.Serializable;

public class InvestigationDo implements Serializable {
    private static final long serialVersionUID = 1L;


    /** 邮件 */
    public String email;

    /** 督导情况及存在的主要问题 */
    public String problem;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }
}
