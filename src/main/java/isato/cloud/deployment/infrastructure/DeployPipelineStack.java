package isato.cloud.deployment.infrastructure;

import isato.cloud.app.infrastructure.InferenceDemoApp;
import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.SecretValue;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.pipelines.CdkPipeline;
import software.amazon.awscdk.pipelines.SimpleSynthAction;
import software.amazon.awscdk.services.codepipeline.Artifact;
import software.amazon.awscdk.services.codepipeline.actions.GitHubSourceAction;
import software.amazon.awscdk.services.codepipeline.actions.GitHubTrigger;
import software.amazon.awscdk.services.secretsmanager.Secret;

public class DeployPipelineStack extends Stack {

    public DeployPipelineStack(final Construct scope, final String id) {

        super(scope, id);

        Artifact sourceArtifact = Artifact.artifact("sourceArtifact");
        Artifact cloudAssemblyArtifact = Artifact.artifact("cloudAssemblyArtifact");



        CdkPipeline pipeline = CdkPipeline.Builder.create(this, "DemoPipeline")
                .cloudAssemblyArtifact(cloudAssemblyArtifact)
                .sourceAction(
                        GitHubSourceAction.Builder.create()
                                .actionName("GithubCheckout")
                                .output(Artifact.artifact("output"))
                                .oauthToken(SecretValue.secretsManager("GithubTokenCDK"))
                                .trigger(GitHubTrigger.POLL)
                                .owner("hjander")
                                .repo("cdk-experiments-java")
                                .build()
                )
                .synthAction(
                        SimpleSynthAction.Builder.create()
                                .cloudAssemblyArtifact(cloudAssemblyArtifact)
                                .sourceArtifact(Artifact.artifact("output"))
                                .installCommand("npm install -g aws-cdk")
                                .buildCommand("mvn clean compile package")
                                .synthCommand("npx cdk synth -o dist")
                                .build()

                ).build();

        pipeline.addApplicationStage(new InferenceDemoApp(this, "DEV"));

        //Secret.Builder.create(this, "GithubTokenCDK").secretName("GithubTokenCDK").build().grantRead(pipeline);
    }
}
