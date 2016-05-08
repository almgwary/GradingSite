
<%

session.setAttribute("userid", null);
session.setAttribute("uname", null);
session.setAttribute("type", null);
session.setAttribute("email", null);
session.setAttribute("pass", null);
session.invalidate();
response.sendRedirect("index.jsp");
%>