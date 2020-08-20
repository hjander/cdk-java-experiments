package isato.cloud.app.infrastructure;

import org.jetbrains.annotations.Nullable;
import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Duration;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.services.apigateway.*;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;

import java.util.List;
import java.util.Map;

import static java.lang.Boolean.TRUE;
import static software.amazon.awscdk.services.lambda.Runtime.PROVIDED;

public class LambdaInferenceStack extends Stack {

    public LambdaInferenceStack(@Nullable Construct scope, @Nullable String id) {
        super(scope, id);

        Function handler = Function.Builder.create(this, "QuarkusHttpLambdaCdkExampleNativeHandler")
                .runtime(PROVIDED)
                .handler("not.necessary.for.custom.runtime")
                .code(Code.fromAsset("../quarkus-serverless-microservice-application/target/function.zip"))
                .environment(
                        Map.of("DISABLE_SIGNAL_HANDLERS", TRUE.toString())
                )
                .memorySize(128)
                .timeout(Duration.seconds(5))
                .build();


        RestApi api = RestApi.Builder.create(this, "VideoServiceAPI")
                .restApiName("VideoService")
                .endpointConfiguration(EndpointConfiguration.builder()
                        .types(List.of(EndpointType.REGIONAL))
                        .build())
                .binaryMediaTypes(List.of("*/*"))
                .description("This service services videos.")
                .build();

        LambdaIntegration quarkusNativeJavaLambdaApplication = LambdaIntegration.Builder.create(handler).build();

        api.getRoot().addProxy(ProxyResourceOptions.builder()
                .anyMethod(true)
                .defaultIntegration(quarkusNativeJavaLambdaApplication)
                .build());
    }
}
