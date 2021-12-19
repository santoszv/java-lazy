repositories {
    mavenCentral()
}

plugins {
    `java-library`
    `maven-publish`
    signing
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    withSourcesJar()
    withJavadocJar()
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

tasks.jar {
    manifest {
        attributes("Automatic-Module-Name" to "mx.com.inftel.javax.lazy")
    }
}

publishing {

    repositories {
        maven {
            setUrl(file("$projectDir/build/repo"))
        }
    }

    publications {
        create<MavenPublication>("lazy") {
            groupId = "mx.com.inftel.javax"
            artifactId = "lazy"
            version = "1.0.0"

            from(components["java"])

            pom {
                name.set("Java Lazy Value")
                description.set("Representation of a value with lazy initialization in Java.")
                url.set("https://github.com/santoszv/java-lazy")
                inceptionYear.set("2021")
                licenses {
                    license {
                        name.set("Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0")
                    }
                }
                developers {
                    developer {
                        id.set("santoszv")
                        name.set("Santos Zatarain Vera")
                        email.set("santoszv@inftel.com.mx")
                        url.set("https://www.inftel.com.mx")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/santoszv/java-lazy")
                    developerConnection.set("scm:git:https://github.com/santoszv/java-lazy")
                    url.set("https://github.com/santoszv/java-lazy")
                }
            }

            signing.sign(this)
        }
    }
}

signing {
    useGpgCmd()
}