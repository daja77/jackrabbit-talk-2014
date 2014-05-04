package at.jsug.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import javax.jcr.Node;
import javax.jcr.Session;

@UrlBinding("/DisplayItem.action")
public class DisplayItemActionBean extends RepositoryActionBean {

    String id;
    String name;
    String category;
    String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Resolution view() {
        String id = getContext().getRequest().getParameter("id");
        Session session = getSession();
        try {
            Node picture = session.getNode("/Albums/Default/" + id);
            this.setName(picture.getProperty("name").getString());
            this.setCategory(picture.getProperty("category").getString());
            this.setDescription(picture.getProperty("description").getString());
            releaseSession();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return new ForwardResolution("/WEB-INF/jsp/item.jsp");
    }

    public Session getSession() {
        return getContext().getJCRSession();
    }

    public void releaseSession() {
        getContext().releaseJCRSession();
    }

}
