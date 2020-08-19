package isato.cloud.deployment.infrastructure;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.amazon.awscdk.core.CfnOutput;
import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Stage;
import software.amazon.awscdk.core.StageProps;

public class DeployPipelineStage extends Stage {

    private CfnOutput urlOutput;

    public DeployPipelineStage(@NotNull Construct scope, @NotNull String id, @Nullable StageProps props) {
        super(scope, id, props);
    }
}
