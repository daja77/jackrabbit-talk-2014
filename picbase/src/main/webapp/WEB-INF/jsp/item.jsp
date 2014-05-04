<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<s:layout-render name="/WEB-INF/jsp/layout.jsp" title="Welcome">
  <s:layout-component name="body">
      <%@include file="header.jsp"%>
    <h2>Image Data View</h2>

      <stripes:form beanclass="at.jsug.action.DisplayItemActionBean" focus="">
          <table>
              <tr>
                  <td>Name:</td>
                  <td>${actionBean.name}</td>
              </tr>
              <tr>
                  <td>Category:</td>
                  <td>${actionBean.category}</td>
              </tr>
              <tr>
                  <td>Description:</td>
                  <td>${actionBean.description}</td>
              </tr>
              <tr>
                  <td>Image:</td>
                  <td><a href="DisplayImage.action?id=${actionBean.id}">
                      <img src="DisplayImage.action?id=${actionBean.id}" height="300px" width="500px">
                      </a>
                  </td>
              </tr>
              <tr>
                  <td colspan="2">
                      <stripes:link href="Upload.action">Add another Picture</stripes:link>
                      <stripes:link href="Home.action">Back to Home</stripes:link>
                  </td>
              </tr>
          </table>
      </stripes:form>
  </s:layout-component>
</s:layout-render>
