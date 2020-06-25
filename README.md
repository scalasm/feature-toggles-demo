# Simple Feature Toggles for Spring Boot Apps

Feature toggles help modularizing application in logical slices that can be conditionally enabled.

Spring supports this ability by using the `@ConditionalOnXXX`, like `@ConditionalOnProperty` and [others](https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/autoconfigure/condition/package-summary.html).

## Implementation
My initial idea was to leverage the @ConditionalOnProperty but [I wasn't able to make it work](https://stackoverflow.com/questions/62579394/can-i-use-conditionalproperty-with-my-custom-annotation-to-avoid-repeating-conf). 
So I wanted to learn more and came out with this implementation, centered on the `FeatureToggle` annotation: it supports my use cases and it's simple 
to understand and adapt.

Every component you want to slice into a *feature* is going to be tagged like:
```java
@FeatureToggle( "hello" )
@RestController
public class HelloController {
    // ... code ...
}
```

The business logic behind is the following:
* a `feature` must be explicitly disabled as a Spring Context's property.
* every feature is specified by a *short name* within the annotation (eg., `hello`) while a fully qualified package name (FQPN) is used
within the configuration files. For example, in our `application.yaml` we may have:
```yaml
ftd:
  feature:
    hello:
```

## Usage in test

Leveraging this behaviour, we can globally disable all features during Spring Integration tests, like `@WebMvcTest`,
and only enable the features we require. For example:
```java
@WebMvcTest( HelloController.class )
@TestPropertySource( properties = "ftd.feature.hello" )
class HelloControllerTest  {
    // ... test code ...
}
```

# References and related work

There are more complex and powerful ways to implement feature toggles, like [FF4J - Feature Flipping for Java](https://github.com/ff4j/ff4j): this example is 
not meant to be an enterprise-ready solution but just a playground worth sharing.

Other references you may find interesting:
* [Feature Flags with Spring](https://www.baeldung.com/spring-feature-flags)
* [Conditional Beans with Spring Boot](https://reflectoring.io/spring-boot-conditionals)
* [The Spring Feature Toggle: Your Guide to Getting Started Quickly](https://rollout.io/blog/spring-feature-toggle/)