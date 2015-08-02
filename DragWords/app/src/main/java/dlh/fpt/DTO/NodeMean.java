package dlh.fpt.DTO;

/**
 * Created by HienTQ on 8/2/2015.
 *
 */
public class NodeMean {

    private String text;
    private String attribution;

    public NodeMean() {
    }

    public NodeMean(String text, String attribution) {
        this.text = text;
        this.attribution = attribution;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    @Override
    public String toString() {
        return "NodeMean{" +
                "text='" + text + '\'' +
                ", attribution='" + attribution + '\'' +
                '}';
    }
}
