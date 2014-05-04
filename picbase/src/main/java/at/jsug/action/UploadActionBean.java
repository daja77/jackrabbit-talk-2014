package at.jsug.action;

import net.sourceforge.stripes.action.*;
import org.apache.jackrabbit.value.StringValue;

import javax.jcr.Binary;
import javax.jcr.Node;
import javax.jcr.Session;
import java.util.UUID;

@UrlBinding("/Upload.action")
public class UploadActionBean extends RepositoryActionBean {

    private String name;
    private String category;
    private String description;
    private FileBean attachment;

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

    public FileBean getAttachment() {
        return attachment;
    }

    public void setAttachment(FileBean attachment) {
        this.attachment = attachment;
    }

    @DefaultHandler
    public Resolution view() {
        return new ForwardResolution("/WEB-INF/jsp/upload.jsp");
    }


    public Session getSession() {
        return getContext().getJCRSession();
    }

    public void releaseSession() {
        getContext().releaseJCRSession();
    }

    public Resolution save() {
        Session session = getSession();
        try {
            Node album = session.getNode("/Albums/Default");
            String id = UUID.randomUUID().toString();
            Node picture = album.addNode(id, "nt:unstructured");
            picture.setProperty("name", new StringValue(getName()));
            picture.setProperty("category", new StringValue(getCategory()));
            picture.setProperty("description", new StringValue(getDescription()));

            Node content = picture.addNode("jcr:content", "nt:resource");
            Binary binary = session.getValueFactory().createBinary(getAttachment().getInputStream());
            content.setProperty("jcr:data", binary);
            content.setProperty("jcr:mimeType", "image/jpeg");

            session.save();
            getContext().releaseJCRSession();

            return new ForwardResolution("DisplayItem.action?id="+id);


        }
        catch (Exception e) {
            e.printStackTrace();
            //TODO: logging
        }

        return new ForwardResolution("Home.action");
    }
    public Resolution cancel() {
        return new ForwardResolution("Home.action");
    }

}
