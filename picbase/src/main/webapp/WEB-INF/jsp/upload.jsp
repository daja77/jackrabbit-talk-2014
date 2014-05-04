<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<s:layout-render name="/WEB-INF/jsp/layout.jsp" title="Welcome">
  <s:layout-component name="body">
      <%@include file="header.jsp"%>
    <h1>Upload a new picture to the database</h1>

      <stripes:form beanclass="at.jsug.action.UploadActionBean" focus="">
          <table>
              <tr>
                  <td>Name:</td>
                  <td><stripes:text name="name"/></td>
              </tr>
              <tr>
                  <td>Category:</td>
                  <td><stripes:text name="category"/></td>
              </tr>
              <tr>
                  <td>Description:</td>
                  <td><stripes:textarea name="description"/></td>
              </tr>
              <tr>
                  <td>File:</td>
                  <td><stripes:file name="attachment"/></td>
              </tr>
              <tr>
                  <td colspan="2">
                      <stripes:submit name="save" value="Save"/>
                      <stripes:submit name="cancel" value="Cancel"/>
                  </td>
              </tr>
          </table>
      </stripes:form>
  </s:layout-component>
</s:layout-render>
