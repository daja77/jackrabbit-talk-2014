<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<s:layout-render name="/WEB-INF/jsp/layout.jsp" title="Welcome">
  <s:layout-component name="body">
      <%@include file="header.jsp"%>
    <h1>Upload a new picture to the database</h1>

      <stripes:form beanclass="at.jsug.action.SearchActionBean" focus="">
          <table>
              <tr>
                  <td>Search:</td>
                  <td><stripes:text name="search"/></td>
              </tr>
              <tr>
                  <td colspan="2">
                      <stripes:submit name="search" value="Search"/>
                      <stripes:submit name="cancel" value="Cancel"/>
                  </td>
              </tr>
          </table>
          <table>
              <c:forEach var="picture" items="${actionBean.pictures}">
                   <tr>
                       <th>Name</th>
                       <th>Category</th>
                       <th>Description</th>
                       <th>View</th>
                   </tr>
                  <tr>
                      <td>${picture.name}</td>
                      <td>${picture.category}</td>
                      <td>${picture.description}</td>
                      <td><stripes:link href="DisplayItem.action?id=${picture.id}">Details</stripes:link></td>
                  </tr>
              </c:forEach>
          </table>
      </stripes:form>
  </s:layout-component>
</s:layout-render>
