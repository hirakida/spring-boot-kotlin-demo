dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.webjars:webjars-locator-core")
    implementation("org.webjars:bootstrap:4.5.3") {
        exclude(module = "jquery")
    }
}
