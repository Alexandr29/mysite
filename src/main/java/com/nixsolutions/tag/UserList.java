package com.nixsolutions.tag;
import com.nixsolutions.service.impl.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class UserList extends TagSupport {
    private List<User> users;
    @Override public int doStartTag() throws JspException {
        pageContext.getRequest().getServerName();
        JspWriter out = pageContext.getOut();
        StringBuilder s = new StringBuilder();
        s.append("<table id=\"mytable\" class=\"table table-striped\" border=\"1\">");
        s.append("<thead>");
        s.append("<thead>");
        //s.append("<tr>");
        s.append("<th>");
        s.append("Id");
        s.append("</th>");
        s.append("<th>");
        s.append("Login");
        s.append("</th>");
        s.append("<th>");
        s.append("First name");
        s.append("</th>");
        s.append("<th>");
        s.append("Last name");
        s.append("</th>");
        s.append("<th>");
        s.append("Age");
        s.append("</th>");
        s.append("<th>");
        s.append("Role");
        s.append("</th>");
        s.append("<th>");
        s.append("Actions");
        s.append("</th>");
        s.append("</tr>");
        s.append("</thead>");
        s.append("<tbody>");
        for (User user: users) {
            s.append("<tr>");
            s.append("<td>");
            s.append(user.getId());
            s.append("</td>");
            s.append("<td>");
            s.append(user.getLogin());
            s.append("</td>");
            s.append("<td>");
            s.append(user.getFirstName());
            s.append("</td>");
            s.append("<td>");
            s.append(user.getLastName());
            s.append("</td>");
            s.append("<td>");
            s.append(user.getAge());
            s.append("</td>");
            s.append("<td>");
            s.append(user.getRolename());
            s.append("</td>");
            s.append("<td>");
            s.append("<a href=\"" + ((HttpServletRequest) pageContext
                    .getRequest()).getContextPath()
                    + "/edit?logintoedit=" + user.getLogin()
                    + "\"><img border=\"0\" src=\"images/edit.png\" width=\"30\" height=\"30\"></a>");
            s.append(" ");
            s.append("<a href=\"" + ((HttpServletRequest) pageContext
                    .getRequest()).getContextPath()
                    + "/delete?logintodelete=" + user.getLogin()
                    + "\" onclick=\"return confirmDelete();\"> <img border=\"0\"  src=\"images/trash.jpg\" width=\"30\" height=\"30\"></a>");
            s.append("</td>");
            s.append("</tr>");
        }



        s.append("<tbody>");
        s.append("</table>");
        try {
            out.println(s.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return EVAL_BODY_INCLUDE;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
