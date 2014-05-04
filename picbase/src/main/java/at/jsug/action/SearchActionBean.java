package at.jsug.action;

import at.jsug.types.PictureMetadata;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.jackrabbit.value.StringValue;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import java.util.ArrayList;
import java.util.List;

@UrlBinding("/Search.action")
public class SearchActionBean extends RepositoryActionBean {

    private String search;
    private List<PictureMetadata> pictures;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }



    @DefaultHandler
    public Resolution view() {
        return new ForwardResolution("/WEB-INF/jsp/search.jsp");
    }


    public Session getSession() {
        return getContext().getJCRSession();
    }

    public void releaseSession() {
        getContext().releaseJCRSession();
    }

    public Resolution search() {
        Session session = getSession();
        try {

            QueryManager queryManager = session.getWorkspace().getQueryManager();
            Query query = queryManager.createQuery("select * from [nt:unstructured] where contains(description, $value)",
                    Query.JCR_SQL2);
            query.bindValue("value", new StringValue(search));

            QueryResult result = query.execute();

            pictures = new ArrayList<PictureMetadata>();
            NodeIterator nodeIterator = result.getNodes();
            while (nodeIterator.hasNext()) {
                Node node = nodeIterator.nextNode();
                PictureMetadata pictureMetadata = new PictureMetadata();
                pictureMetadata.setId(node.getName());
                pictureMetadata.setName(node.getProperty("name").getString());
                pictureMetadata.setCategory(node.getProperty("category").getString());
                pictureMetadata.setDescription(node.getProperty("description").getString());
                pictures.add(pictureMetadata);
            }

            getContext().releaseJCRSession();
            return new ForwardResolution("/WEB-INF/jsp/search.jsp");

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

    public List<PictureMetadata> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureMetadata> pictures) {
        this.pictures = pictures;
    }
}
