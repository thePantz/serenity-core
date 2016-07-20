package net.thucydides.core.model;

import com.google.common.base.Optional;
import com.google.common.collect.Maps;
import net.thucydides.core.model.featuretags.FeatureFileStrategy;
import net.thucydides.core.model.featuretags.FeatureTagStrategy;
import net.thucydides.core.model.featuretags.NoFeatureStrategy;
import net.thucydides.core.model.featuretags.StoryFileStrategy;
import net.thucydides.core.requirements.model.FeatureType;

import java.util.Map;

/**
 * Created by john on 6/07/2016.
 */
public class FeatureTagAsDefined {

    private final static Map<FeatureType, FeatureTagStrategy> FEATURE_STRATEGY_MAP = Maps.newHashMap();
    static {
        FEATURE_STRATEGY_MAP.put(FeatureType.FEATURE, new FeatureFileStrategy());
        FEATURE_STRATEGY_MAP.put(FeatureType.STORY, new StoryFileStrategy());
        FEATURE_STRATEGY_MAP.put(FeatureType.UNDEFINED, new NoFeatureStrategy());

    }

    public static Optional<TestTag> in(Story story, String path) {
        return FEATURE_STRATEGY_MAP.get(featureTypeFor(story, path)).getFeatureTag(story, path);
    }

    private static FeatureType featureTypeFor(Story story, String path) {
        if ((story != null) && (story.getType() == Story.RequirementType.feature)) {
            return FeatureType.FEATURE;
        }
        if (path != null && path.endsWith(".feature")) {
            return FeatureType.FEATURE;
        }

        if ((story != null) && (story.getType() == Story.RequirementType.story)) {
            return FeatureType.STORY;
        }
        if (path != null && path.endsWith(".story")) {
            return FeatureType.STORY;
        }

        return FeatureType.UNDEFINED;
    }
}