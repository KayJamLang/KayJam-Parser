plugins {
    kotlin("multiplatform") version "1.5.0"
}

group = "kayjam.parser"
version = "1.0"

repositories {
    mavenCentral()
}


kotlin {
    jvm {
        withJava()
    }

    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation("junit:junit:4.13.1")
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
    }
}
