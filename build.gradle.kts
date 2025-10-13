plugins {
    id("java")
    id("xyz.jpenilla.run-paper") version "2.3.1"
    id("io.github.goooler.shadow") version "8.1.7"
}

group = "blitzwolf007"
version = "1.0-SNAPSHOT"
description = "RTS like sounds for Towny"
java.sourceCompatibility = JavaVersion.VERSION_21
val mainMinecraftVersion = "1.21.8"
val lowestSupportedMinecraftVersion = "1.20"
val townyVersion = "0.101.0.2"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.glaremasters.me/repository/towny/")
    maven("https://repo.aikar.co/content/groups/aikar/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:$mainMinecraftVersion-R0.1-SNAPSHOT")
    compileOnly("com.palmergames.bukkit.towny:towny:$townyVersion")
    implementation("co.aikar:acf-paper:0.5.1-SNAPSHOT")
}

tasks {
    shadowJar {
        val prefix = "${project.group}.lib"
        sequenceOf(
            "co.aikar",
        ).forEach { pkg ->
            relocate(pkg, "$prefix.$pkg")
        }

        archiveFileName.set("${project.name}-${project.version}.jar")
    }
    build {
        dependsOn(shadowJar)
    }
    assemble {
        dependsOn(shadowJar)
    }
    processResources {
        val props = mapOf(
            "name" to project.name,
            "version" to project.version,
            "description" to project.description,
            "apiVersion" to lowestSupportedMinecraftVersion,
            "group" to project.group
        )
        inputs.properties(props)
        filesMatching("paper-plugin.yml") {
            expand(props)
        }
    }

    runServer {
        downloadPlugins {
            github(
                "TownyAdvanced",
                "Towny",
                townyVersion,
                "towny-$townyVersion.jar"
            )
        }
        minecraftVersion(mainMinecraftVersion)
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}
