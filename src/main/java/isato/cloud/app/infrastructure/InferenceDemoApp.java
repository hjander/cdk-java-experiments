package isato.cloud.app.infrastructure;

import org.jetbrains.annotations.NotNull;
import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Stage;

public class InferenceDemoApp extends Stage {

    public InferenceDemoApp(@NotNull Construct scope, @NotNull String id) {
        super(scope, id);
        new LambdaInferenceStack(this, "LambdaInferenceStack");
    }
}
