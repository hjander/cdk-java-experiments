package isato.cloud.deployment.infrastructure;

import software.amazon.awscdk.core.App;

public class DeployPipelineApp {

    public static void main(String[] args) {

        App pipelineApplication = App.Builder.create()
                .outdir("./target")
                .runtimeInfo(true)
                .build();

        DeployPipelineStack.Builder.create(pipelineApplication,"demo-pipeline-app")
                .build();

        pipelineApplication.synth();
    }
}