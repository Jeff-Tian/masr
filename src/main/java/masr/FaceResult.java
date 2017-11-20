package masr;

import com.fasterxml.jackson.annotation.JsonCreator;
import masr.face.FaceBehaviorAggregator;
import org.springframework.hateoas.ResourceSupport;

public class FaceResult extends ResourceSupport {
    private final FaceBehaviorAggregator agg;

    @JsonCreator
    public FaceResult(FaceBehaviorAggregator agg) {
        this.agg = agg;
    }

    public FaceBehaviorAggregator getAgg() {
        return agg;
    }
}
