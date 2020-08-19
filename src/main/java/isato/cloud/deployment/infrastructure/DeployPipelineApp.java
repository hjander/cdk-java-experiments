package isato.cloud.deployment.infrastructure;

import software.amazon.awscdk.core.App;
import software.amazon.awscdk.core.Stack;

public class DeployPipelineApp {

    public static void main(String[] args) {

        App pipelineApplication = App.Builder.create()
                .autoSynth(true)
                .runtimeInfo(true)
                .build();

        new DeployPipelineStack(pipelineApplication, "CdkDeploymentPipeline");
        //DeployPipelineStack.Builder.create(pipelineApplication, "CdkDeploymentPipeline").build();
        pipelineApplication.synth();
    }
}