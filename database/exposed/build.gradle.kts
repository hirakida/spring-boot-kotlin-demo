repositories {
    jcenter()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.exposed:exposed-spring-boot-starter:0.28.1")
    runtimeOnly("com.h2database:h2")
}
