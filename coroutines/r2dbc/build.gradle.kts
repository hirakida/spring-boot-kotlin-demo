repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot.experimental:spring-boot-bom-r2dbc:0.1.0.M3")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot.experimental:spring-boot-starter-data-r2dbc")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("io.r2dbc:r2dbc-h2")
    testImplementation("org.springframework.boot.experimental:spring-boot-test-autoconfigure-r2dbc")
}
