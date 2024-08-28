package com.ericsson.gerrit.commits;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "commits")
public class Commit {
    @Id
    String c_hash;

    public Commit() {
		super();
	}



    String c_author;
    @JsonFormat(pattern = "yyyy/MM/dd")
    Date c_date;

    Boolean c_in_main_branch;
    String c_message;
    String c_project_name;
    String t_name;

    int c_lines_added;
    int c_lines_deleted;

    public Commit(String c_hash, String c_author, Date c_date, Boolean c_in_main_branch, String c_message, String c_project_name, String t_name, int c_lines_added, int c_lines_deleted) {
        this.c_hash = c_hash;
        this.c_author = c_author;
        this.c_date = c_date;
        this.c_in_main_branch = c_in_main_branch;
        this.c_message = c_message;
        this.c_project_name = c_project_name;
        this.t_name = t_name;
        this.c_lines_added = c_lines_added;
        this.c_lines_deleted = c_lines_deleted;
    }

    public String getC_hash() {
        return c_hash;
    }

    public void setC_hash(String c_hash) {
        this.c_hash = c_hash;
    }

    public String getC_author() {
        return c_author;
    }

    public void setC_author(String c_author) {
        this.c_author = c_author;
    }

    public Date getC_date() {
        return c_date;
    }

    public void setC_date(Date c_date) {
        this.c_date = c_date;
    }

    public Boolean getC_in_main_branch() {
        return c_in_main_branch;
    }

    public void setC_in_main_branch(Boolean c_in_main_branch) {
        this.c_in_main_branch = c_in_main_branch;
    }

    public String getC_message() {
        return c_message;
    }

    public void setC_message(String c_message) {
        this.c_message = c_message;
    }

    public String getC_project_name() {
        return c_project_name;
    }

    public void setC_project_name(String c_project_name) {
        this.c_project_name = c_project_name;
    }
    public int getC_lines_added() {
        return c_lines_added;
    }

    public void setC_lines_added(int c_lines_added) {
        this.c_lines_added = c_lines_added;
    }

    public int getC_lines_deleted() {
        return c_lines_deleted;
    }

    public void setC_lines_deleted(int c_lines_deleted) {
        this.c_lines_deleted = c_lines_deleted;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }


}
