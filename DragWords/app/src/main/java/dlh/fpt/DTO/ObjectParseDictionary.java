package dlh.fpt.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HienTQ on 8/2/2015.
 *
 * List NodeMean
 */
public class ObjectParseDictionary {
    private List<NodeMean> definitions = new ArrayList<NodeMean>();

    public ObjectParseDictionary() {
    }

    public ObjectParseDictionary(List<NodeMean> definitions) {
        this.definitions = definitions;
    }

    public List<NodeMean> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<NodeMean> definitions) {
        this.definitions = definitions;
    }

    @Override
    public String toString() {
        return "ObjectParseDictionary{" +
                "definitions=" + definitions +
                '}';
    }
}
