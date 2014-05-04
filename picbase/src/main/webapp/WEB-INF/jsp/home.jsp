<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes-dynattr.tld" %>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/layout.jsp" title="Welcome">
  <s:layout-component name="body">
      <%@include file="header.jsp"%>
      <p>
          Welcome to Picbase - A simple Demonstration for Apache Jackrabbit
      </p>

      <p>Search for Pictures <stripes:link href="Search.action">click here</stripes:link></p>
    <p>To add a new Picture <stripes:link href="Upload.action">click here</stripes:link></p>
  </s:layout-component>
</s:layout-render>
